/**
 * 
 */
package unsw.comp9024.sound;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;

import javax.sound.sampled.*;


/**
 * @author Alexander Whillas (z3446737) <whillas@gmail.com>
 */
public class Reverser {

	private File fileIn;
	private String outputPath;
	private AudioFormat fileFormat;
	private byte[] audioBytes;
	
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
		this.readFile(fileSize);
	}
	
	private void readFile(int fileSize) {
		int totalFramesRead = 0;
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.fileIn);
			this.fileFormat = audioInputStream.getFormat();
			int bytesPerFrame = this.fileFormat.getFrameSize();
			if (bytesPerFrame == AudioSystem.NOT_SPECIFIED) {
				// some audio formats may have unspecified frame size
				// in that case we may read any amount of bytes
				bytesPerFrame = 1;
			}
			// Set an arbitrary buffer size of 1024 frames.
			//int numBytes = 1024 * bytesPerFrame;
			this.audioBytes = new byte[fileSize];
			try {
				int numBytesRead = 0;
				int numFramesRead = 0;
				// Try to read numBytes bytes from the file.
				while ((numBytesRead = audioInputStream.read(this.audioBytes)) != -1) {
					// Calculate the number of frames actually read.
					numFramesRead = numBytesRead / bytesPerFrame;
					totalFramesRead += numFramesRead;
				}
				System.out.println("totalFramesRead: " + totalFramesRead);
				Collections.reverse(Arrays.asList(audioBytes));
				// Once we have it read in, reverse the array... Hope this 
				// doesn't chock the memory...
				
			} catch (Exception ex) {
				// Handle the error...
			}
		} catch (Exception e) {
			// Handle the error...
		}		
	}
	
	public void writeFile() {
		File fileOut = new File(this.outputPath);
		//AudioFileFormat.Type fileType = this.fileFormat.;
		//if (AudioSystem.isFileTypeSupported(fileType, audioInputStream)) {
		//  AudioSystem.write(audioInputStream, fileType, fileOut);
		//}		
	}
	
	/**
	 * 
	 */
	private void play() {
		// TODO Auto-generated method stub
		
	}
}
