package easy;

import java.util.Scanner;

/**
 * Reverse the number and delete the middle digit
 * No of test cases must be >0 and <20
 * The numbers must have 3 digits [100-999]
 * Input = [5
			156
			197
			365
			789
			106]
 * Output = [61
			97
			53
			71
			61]
 * @author sayantan.biswas
 *
 */
public class ReverseAndDelete {
	public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of numbers = ");
        int t = sc.nextInt();
        int i=0,p=t;
        
        int arr[] = new int[t];
        if(t<=0)
            System.out.println(t + " is too small");
        else if(t>20)
            System.out.println(t + " exceeds the limit");
        else {
        	System.out.println("Enter the numbers: ");
            while(t-- > 0) {
                int x = sc.nextInt();
                if(x >= 100 && x <= 999)
                    arr[t] = x;
                else {
                    System.out.println(x + " is not in range");
                    break;
                }
            }
            System.out.println("The output is : ");
            for(i=0; i<p; i++)
                System.out.println(calc(arr[i], 3));
        }
        
        sc.close();
	}
	
	public static String calc(int n, int l) {
        StringBuilder ans = new StringBuilder();
        String num = String.valueOf(n);
        String rev = "" + num.charAt(0) + num.charAt(2);
        
        ans.append(rev);
        ans.reverse();
        return ans.toString();
    }
}
