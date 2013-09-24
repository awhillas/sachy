/**
 * 
 */
package unsw.comp9024.sound;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import javax.sound.sampled.*;


/**
 * @author Alexander Whillas (z3446737) <whillas@gmail.com>
 */
public class Reverser implements LineListener {

	private File fileIn;
	private String outputPath;
	private AudioFormat streamFormat;
	private ByteArrayOutputStream b_out;
	private Clip clip;
	private AudioFileFormat fileFormat;
	
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
		this.fileIn = new File(inputPath);
		int fileSize = (int) fileIn.length();
		if(fileSize == 0L) {
			System.out.println("Input file "+inputPath+" does not exist?");
		}
		//System.out.println("File size is: "+fileSize);
		this.readFile(fileSize);
		if (outputPath != "") {
			this.writeFile();
		}
	}
	
	private void readFile(int fileSize) {
		int totalFramesRead = 0;
		try {
			AudioInputStream audioInputStream = 
					AudioSystem.getAudioInputStream(this.fileIn);

			this.streamFormat = audioInputStream.getFormat();
			this.fileFormat = AudioSystem.getAudioFileFormat(this.fileIn);
			int bytesPerFrame = this.streamFormat.getFrameSize();
			if (bytesPerFrame == AudioSystem.NOT_SPECIFIED) {
				// some audio formats may have unspecified frame size
				// in that case we may read any amount of bytes
				bytesPerFrame = 1;
			}
			
			// Set an arbitrary buffer size of 1024 frames.
			//int numBytes = 1024 * bytesPerFrame;
			byte[] audioBytes = new byte[fileSize];
			try {
				int numBytesRead = 0;
				int numFramesRead = 0;
				this.b_out = new ByteArrayOutputStream();
				// Try to read numBytes bytes from the file.
				while ((numBytesRead = audioInputStream.read(audioBytes)) != -1) {
					// Calculate the number of frames actually read.
					numFramesRead = numBytesRead / bytesPerFrame;
					totalFramesRead += numFramesRead;
				}
				byte[] reversed = new byte[fileSize];
				for(int i = audioBytes.length - bytesPerFrame - 1; i > 0; i -= bytesPerFrame) {
					for(int j = i; j <= i + bytesPerFrame ; j++) {
						reversed[audioBytes.length - j - 1] = audioBytes[j];
					}
				}
				this.b_out.write(reversed, 0, reversed.length);
				System.out.println("totalFramesRead: " + totalFramesRead);
			} catch (Exception ex) {
				// Handle the error...
				System.out.println("102 - "+ex);
				ex.printStackTrace();
			}
		} catch (Exception e) {
			// Handle the error...
			System.out.println("101 "+e);
			e.printStackTrace();
		}		
	}
	
	public void writeFile() {
		//AudioInputStream ais = new AudioInputStream(this.audioBytes, this.fileFormat, 
		//		(long) this.audioBytes.length);
	    //AudioSystem.write(ais, AudioFileFormat.Type.WAVE, new File(this.outputPath));
		// Hook output stream to output file
    	ByteArrayInputStream b_in = new ByteArrayInputStream(this.b_out.toByteArray());
    	AudioInputStream ais = new AudioInputStream(b_in, this.streamFormat, (int) fileIn.length());
    	File output = new File(this.outputPath);
    	try {
			AudioSystem.write(ais, this.fileFormat.getType(), output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	private void play() {
		Line.Info linfo = new Line.Info(Clip.class);
		Line line = null;
		try {
			line = AudioSystem.getLine(linfo);
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		}
		clip = (Clip) line;
		clip.addLineListener(this);
		//AudioInputStream ais = AudioSystem.getAudioInputStream(this.audioBytes);
		//clip.open(ais);
//		try {
//			clip.open(this.fileFormat, this.audioBytes, 0, this.audioBytes.length);
//		} catch (LineUnavailableException e) {
//			e.printStackTrace();
//		}
//		clip.start();		
	}

	/* (non-Javadoc)
	 * @see javax.sound.sampled.LineListener#update(javax.sound.sampled.LineEvent)
	 */
	@Override
	public void update(LineEvent le) {
		LineEvent.Type type = le.getType();
		if (type == LineEvent.Type.OPEN) {
			System.out.println("OPEN");
		} else if (type == LineEvent.Type.CLOSE) {
			System.out.println("CLOSE");
			System.exit(0);
		} else if (type == LineEvent.Type.START) {
			System.out.println("START");
			//playingDialog.setVisible(true);
		} else if (type == LineEvent.Type.STOP) {
			System.out.println("STOP");
			//playingDialog.setVisible(false);
			clip.close();
		}
	}
}
