import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Task1 {
	public static void main(String[] args) {
		String input = "";
		assert args.length >= 2;
		try{
			int length = Integer.parseInt(args[0]);	// length of each line
			int num = Integer.parseInt(args[1]);	// number of lines
			String type = "";
			if(args.length >= 2)
				type = args[2];
/*
			try{
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String in;
				while((in = br.readLine()) != null) {
					input = in;
				}
				input = input
					.replaceAll("[^a-zA-Z0-9]","")
					.substring(0, Math.min(input.length(), 1000))
				;
				if(input.isEmpty()) {
*/
					input = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//}
				if (type.equals("e")) {
					Task1.even(length, num, input);
				} else {
					Task1.random(length, num, input);
				}
/*				
			} catch(IOException io) {
				io.printStackTrace();
			}
*/
		} catch (NumberFormatException e) {
			// not an integer!
			System.out.println("We want 2 integers stupid");
		}
	}
	
	// Even amount of each character
	private static void even(int length, int num, String input) {
		int x = 0;
		for(int i = 0; i < num; i++) {
			String output = "";
			for(int j = 0; j < length; j++) {
				output += input.charAt(x);
			}
			if(x < input.length() - 1)
				x++;
			System.out.println(output);
		}
	}
	
	private static void random(int length, int num, String input) {
		for(int i = 0; i < num; i++) {
			String output = "";
			for(int j = 0; j < length; j++) {
				int x = (int) (Math.random() * input.length());
				output += input.charAt(x);
			}
			System.out.println(output);
		}
	}
}