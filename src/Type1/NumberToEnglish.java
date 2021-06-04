package Type1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NumberToEnglish {
	private static String[] ones = new String[]{"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
	private static String[] tensFirst = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
	private static String[] tens = new String[]{"",  "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
	private static String[] power = new String[]{"",  "", "Hundred", "Thousand", "Thousand"};
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a number (must be < 99999): ");
        int num = sc.nextInt();
        if(num > 99999)
        	System.out.println("The entered number must be < 4000. Try Again!!");
        else {
            String result = convertToEnglish(num);
            System.out.println(num + " in words is " + result);
        }
        sc.close();
    }
	
	public static String convertToEnglish(int num) {
		String answer = "";
		List<String> answerList = new ArrayList<>();
		
		while(num != 0) {
			int front, factor;
			int len = String.valueOf(num).length();
			factor = returnFactor(num, len);
			front = num / factor;
			
			answerList.add(String.valueOf(front));
			answerList.add(power[len-1]);
			num -= factor * front;
		}
		
		answer += inEnglish(answerList);
		return answer.trim();
	}

	private static String inEnglish(List<String> answerList) {
		String ans = "";
		for(String s : answerList) {
			if(s.matches(".*\\d.*") && !s.isEmpty())
				ans += convert(s) + " ";
			else
				ans += s + " ";
		}
		return ans;
	}

	private static String convert(String s) {
		String ans = "";
		int len = s.length();
		int num = Integer.parseInt(s.replaceAll(".*\\D.*", ""));
		
		switch(len) {
			case 1:	
				ans = ones[num];
				break;
			case 2:	
				int n = (int)(num / (int)(Math.pow(10, len-1)));
				if(n > 1)
					ans = tens[n] + " " + ones[num%10];
				else
					ans = tensFirst[num%10];
				break;
		}
		
		return ans;
	}

	private static int returnFactor(int num, int len) {
		int factor = 1;
		switch(len) {
			case 5:	factor = 1000; break;
			case 4:	factor = 1000; break;
			case 3:	factor = 100; break;
		}
		return factor;
	}

}
