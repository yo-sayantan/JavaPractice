package Type1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordWrap {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        String input;
        int wrapSize;
        
        System.out.print("Input String: ");
        input = sc.nextLine();
        System.out.print("Enter word wrap length: ");
        wrapSize = sc.nextInt();
        wordWrap(input, wrapSize);
        sc.close();
	}

	private static void wordWrap(String input, int wrapSize) {
		List<String> subStr = new ArrayList<>();
		int len = input.length();
		int count = len/wrapSize;
		int begin = 0, end = wrapSize;
		
		System.out.println("Output Strings: ");
		while(count > 0) {
			subStr.add(input.substring(begin, end));
			begin += wrapSize;
			end += wrapSize;
			count--;
		}
		if(len % wrapSize > 0)
			subStr.add(input.substring(begin, len));
		
		System.out.print("|");
		for(String s : subStr)
			System.out.print(s + "|");
	}
}
