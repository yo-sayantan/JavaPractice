package easy;

import java.util.Scanner;

public class ArabicToRoman {

	public static String[] roman = {"M","D","C","L","X","V","I"};
    public static int[] arabic = {1000,500,100,50,10,5,1};

	
	public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        
        int l = arabic.length;
        System.out.print("Enter number of Test Cases: ");
        int t = sc.nextInt();
        System.out.print("Enter the numbers (must be <4000): ");
        
        while(t-- > 0) {
            int n = sc.nextInt();
            if(n>3999) {
            	System.out.println("The entered number must be < 4000. Try Again!!");
            } else {
	            String result = convertToRoman(n, l);
	            System.out.println("Roman of " + n + " = " + result);
            }
        }
        sc.close();
    }


	private static String convertToRoman(int n, int l) {
		String result = "";
		int ct = 1;
		
		while(n > 0) {
            for(int i=0; i<l; i++) {
                if(n >= arabic[i]) {
                    n -= arabic[i];
                    if(result.contains("i"))
                        result = result.replace("i", roman[i]);
                    else
                        result += roman[i];
                    
                    int size = result.length();
                    if(size>1) {
                        if(result.charAt(size-1) == result.charAt(size-2))
                            ct++;
                        else
                            ct=1;
                    }
                    if(ct>3) {
                        result = result.substring(0, size-ct) + "i" + roman[i-1];
                        int ind = result.indexOf("i");
                        
                        if(i-2>=0 && ind>0 && (result.charAt(ind-1) == result.charAt(ind+1))) {
                            char c = result.charAt(ind-1);
                            result = result.replace(c+"i"+c, "i"+roman[i-2]);
                        }
                        n += arabic[i];
                        ct=0;
                    }
                    i=-1;
                }
            }
        }
		return result;
	}

}
