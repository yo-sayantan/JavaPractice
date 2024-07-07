package easy;

import java.util.Scanner;

public class FiringGun {

	 public static void main(String args[]) throws InterruptedException {
	        Scanner sc = new Scanner(System.in);
	        int stock, magazine = 20, speed = 100, reloadTime = 3000;
	        System.out.print("Enter magazine stock: ");
	        stock = sc.nextInt();
	        
	        while(stock-- > 1) {
	            fire(magazine, speed);
	            reload(reloadTime);
	        }
	        fire(magazine, speed);
	        System.out.println("Complete..");
	        sc.close();
	    }
	    
	    public static void fire(int magazine, int speed) throws InterruptedException {
	        int count = 0;
	        while(magazine-- > 0) {
	            System.out.println("Firing bullet " + ++count);
	            Thread.sleep(speed);
	        }
	    }
	    
	    public static void reload(int reloadTime) throws InterruptedException {
	        while(reloadTime > 0) {
	            System.out.println("Reloading...");
	            Thread.sleep(reloadTime);
	            reloadTime -= 500;
	        }
	    }
}
