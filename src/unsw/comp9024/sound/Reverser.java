/**
 * @see http://stackoverflow.com/questions/1267488/play-wav-file-backward
 */
package unsw.comp9024.sound;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;

import javax.sound.sampled.*;


/**
 * @author Alexander Whillas (z3446737) <whillas@gmail.com>
 */
public class Reverser {

	private String outputPath;
	private byte[][] frames;
	private AudioInputStream stream;
	private AudioFileFormat audioFileFormat;
	private SourceDataLine auline;
	
	/**
	 * Arguments
	 * Expects two file names: first is the audio file to process, second is the output file.
	 * If no 2nd file name then plays the reversed file. If 2nd filename and --play option
	 * then outputs and plays at the same time.
	 * 
	 * p.s. would be nice to use: http://commons.apache.org/proper/commons-cli/
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String inputPath = "", outputPath = "";
		boolean play = true;
		// Parse command line args *yAWN!*
		if(args.length < 1) {
			System.out.println("Need at least one argument i.e. the file name to process.");
			return;
		}
		if (args.length > 0) {
			inputPath = args[0];
			play = false;
		}  
		if (args.length > 1) {
			outputPath = args[1];
		}
		if (args.length > 2 && args[2].trim().compareToIgnoreCase("--play") == 0) {
			play = true;			
		}
		Reverser r = new Reverser(inputPath, outputPath);
		if(play) {
			System.out.println("Playing...");
			r.play();
		}
	}

	public Reverser(String inputPath, String outputPath) {
		this.outputPath = outputPath;
		File fileIn = new File(inputPath);
		int fileSize = (int) fileIn.length();
		if(fileSize == 0L) {
			System.out.println("Input file "+inputPath+" does not exist?");
		}
		this.audioFileFormat = this.getFileFormat(fileIn);
		this.stream = this.getFileInputStream(fileIn);
		if (stream != null) {
			SourceDataLine auline = this.getDataLine(stream);
			if (auline != null) {
				System.out.println(auline);
				this.readFile(fileSize);
				if (outputPath != "") {
					this.writeFile();
				}
			}			
		}
	}
	
	private AudioFileFormat getFileFormat(File fileIn) {
		try {
			 return AudioSystem.getAudioFileFormat(fileIn);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void readFile(int fileSize) {
		try {
			readFrames();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		// Reverse array in place
		for (int i = 0; i < this.frames.length / 2; i++) {
			byte[] temp = this.frames[i];
			this.frames[i] = this.frames[frames.length - 1 - i];
			this.frames[frames.length - i - 1] = temp;
		}		
	}
	
	private AudioInputStream getFileInputStream(File soundFile) {
		AudioInputStream audioInputStream = null;
        try { 
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (UnsupportedAudioFileException e1) { 
            e1.printStackTrace();
        } catch (IOException e1) { 
            e1.printStackTrace();
        }
        return audioInputStream;
	}
	
	private SourceDataLine getDataLine(AudioInputStream stream) {
		AudioFormat format = stream.getFormat();
        SourceDataLine auline = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
 
        try { 
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
        } catch (LineUnavailableException e) { 
            e.printStackTrace();
        } catch (Exception e) { 
            e.printStackTrace();
        }
        return auline;
	}
	
	private void readFrames() throws IOException {
		System.out.println(stream);
        int frameSize = stream.getFormat().getFrameSize();
        this.frames = new byte[stream.available() / frameSize][frameSize];
        for (int i = 0; i < frames.length; i++)
        {
            byte[] frame = new byte[frameSize];
            int numBytes = stream.read(frame, 0, frameSize);
            if (numBytes == -1)
            {
                break;
            }
            this.frames[i] = frame;
        }
        System.out.println("FrameSize = " + frameSize);
        System.out.println("Number frames = " + frames.length);
    }
	
	public void writeFile() {
		// Hook output stream to output file
		int size = this.frames.length * this.frames[0].length;
		System.out.println(size);
		ByteArrayInputStream byteStream = new ByteArrayInputStream(flattern(this.frames));
		AudioInputStream steamOut = new AudioInputStream(byteStream, stream.getFormat(), size);
    	File output = new File(this.outputPath);
    	try {
			AudioSystem.write(steamOut, audioFileFormat.getType(), output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private byte[] flattern(byte[][] in) {
		int size = in.length * in[0].length;
		byte[] out = new byte[size];
		int s = 0;
		for(int i = 0; i < in.length; i++) {
			for(int j = 0; j < in[0].length; j++) {
				out[s] = in[i][j];
				s++;
			}			
		}
		return out;
	}
	
	/**
	 * 
	 */
	private void play() {
		auline = this.getDataLine(stream);
		auline.start();
		for(int i = 0; i < frames.length - 1; i++) {
			auline.write(frames[i], 0, stream.getFormat().getFrameSize());
		}
        auline.drain();
        auline.close();
	}
}
