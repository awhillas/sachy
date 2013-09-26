package unsw.comp9024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Task1 {
	public static void main(String[] args) {
		String input = "";
		assert args.length == 2;
		try{
			int length = Integer.parseInt(args[0]);
			int num = Integer.parseInt(args[1]);
			// is an integer!
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
					input = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
				}
//				System.out.println("Input: "+input);
				for(int i = 0; i < num; i++) {
					String output = "";
					for(int j = 0; j < length; j++) {
						int x = (int) (Math.random() * input.length());
						output += input.charAt(x);
					}
					System.out.println(output);
				}
			} catch(IOException io) {
				io.printStackTrace();
			}
		} catch (NumberFormatException e) {
			// not an integer!
			System.out.println("We want 2 integers stupid");
		}
	}
}