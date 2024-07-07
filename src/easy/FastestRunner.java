package easy;

import java.util.Scanner;

/**
 * All runners should not have same running time
 * Runners running time should not be <8 or >12
 * Not more than 10 Test cases
 * Prints the name having least running time
 * Input = [6
			Robert,9.50
			Peter,9.58
			Richard,9.48
			Ronald,9.59
			Simon,9.54
			Williams,9.52]
 * Output = Richard
 * @author sayantan.biswas
 *
 */
public class FastestRunner {
	public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of runners = ");
        int t = sc.nextInt();
        String[] arr = new String[t];
        
        if(t>10)
            System.out.println(t + " is an invalid number of runners");
        else {
        	System.out.println("Enter data: ");
            for(int i=0; i<t; i++)
                arr[i] = sc.next();
        
            if(check(arr))
                return;
            else
                System.out.println(runner(arr));
        }
        
        sc.close();
    }
	
	public static String runner(String[] arr) {
        String name = arr[0].split(",")[0];
        float min = Float.parseFloat(arr[0].split(",")[1]);
        
        for(String s : arr) {
            String[] x = s.split(",");
            float time = Float.parseFloat(x[1]);

            if(time < min) {
                min = time;
                name = x[0];
            }
        }
        System.out.print("Fastest runner is : ");
        return name;
    }
    
    public static boolean check(String[] arr) {
        float ch = Float.parseFloat(arr[0].split(",")[1]);
        int ct = 0;
        
        for(String s : arr) {
            float x = Float.parseFloat(s.split(",")[1]);
            if(x > 12 && x < 8) {
                System.out.println(x + " is an invalid input");
                return true;
            }
            
            if(x == ch)
                ct++;
            else 
                return false;
        }
        System.out.println(arr.length + " runners have same timing");
        return true;
    }

}
