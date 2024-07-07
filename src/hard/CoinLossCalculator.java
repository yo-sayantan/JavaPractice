package hard;

import java.util.*;

public class CoinLossCalculator {
    private static double buy, sell, avgBuy, principal, target, invest=0;
    private static int option;
    private static Scanner sc = null;
    
    private static void enterValues() {
        System.out.print("\nCurrent buy price: ");
        buy = Double.parseDouble(sc.next());
        System.out.print("Current sell price: ");
        sell = Double.parseDouble(sc.next());
        System.out.print("Average buy price: ");
        avgBuy = Double.parseDouble(sc.next());
        System.out.print("Invested principal amount: ");
        principal = Double.parseDouble(sc.next());
    }
    
    private static int chooseOptions() {
        int x;
        System.out.println("\nChoose option:");
        System.out.println("1. Calculate extra investment for min 2% loss.");
        System.out.println("2. Calculate extra investment to reach target.");
        System.out.println("3. Calculate profit/loss after investment.");
        System.out.println("4. Exit.");
        System.out.print("Your choice: ");
        x = Integer.parseInt(sc.next());
        
        return x;
    }
    
    private static void calc2Percent() {
        double gap, diff, ratio, loss;
        loss = 0.02;
        // gap = Math.abs(avgBuy - buy);
        // ratio = invest / principal;
        // diff = Math.abs(avgBuy - sell);
        // invest = diff * principal / gap;
        invest = (principal * sell) / ((buy * (1 + loss) * (avgBuy + principal)) + sell);
        
        System.out.println("\nFor 0 loss,");
        System.out.printf("Extra investment required = %.2f %n", invest);
        calcAfter();
    }
    
    private static void calcReachTarget() {
        double gap, target;
        System.out.print("Target sell price: ");
        target = Double.parseDouble(sc.next());
        if(target > avgBuy || target <= buy) {
            System.out.println("\nTarget Reach is Impossible !!");
            return;
        }
        else {
            gap = target - buy;
            invest = principal * (avgBuy - target) / gap;
            
            System.out.printf("\nTo reach target of %.2f", target);
            System.out.printf("\nExtra investment required = %.2f %n", invest);
            calcAfter();
        }
    }
    
    private static void calcAfter() {
        double lastBuy, totalCapital;
        if(option == 3) {
            System.out.print("Available investment amount: ");
            invest = Double.parseDouble(sc.next());
        }
        
        totalCapital = principal + invest;
        lastBuy = ((avgBuy * principal) + (buy * invest)) / totalCapital;
        calcProfitLoss(avgBuy, sell, principal);
        System.out.println("\nAfter new Investment,");
        System.out.printf("New Average Buy Price = %.2f", lastBuy);
        calcProfitLoss(lastBuy, sell, totalCapital);
    }
    
    private static void calcProfitLoss(double buy, double sell, double p) {
        double diff, currDiff, percent;
        diff = sell - buy;
        percent = diff / buy;
        currDiff = percent * p;
        percent = percent * 100;
        
        if(diff == 0) {
            System.out.printf("\nNo profit/loss, Diff = %.2f %n", currDiff);
            return;
        }
        if(diff > 0)
            System.out.printf("\nProfit = %.2f %nProfit", currDiff);
        else if(diff < 0)
            System.out.printf("\nLoss = %.2f %nLoss", Math.abs(currDiff));
        System.out.printf(" Percentage = %.1f", percent);
        System.out.println("%");
    }
    
    public static void main(String args[]) {
        sc = new Scanner(System.in);
        System.out.println("STOCKS LOSS CALCULATOR");
        
        option = chooseOptions();
        enterValues();
        
        while(option != 4) {
            switch(option) {
                case 1: calc2Percent();
                        break;
                case 2: calcReachTarget();
                        break;
                case 3: calcAfter();
                        break;
                default:System.out.println("Try Again !!");
            }
            
            System.out.print("\nUpdate prices? (y/n): ");
            String op = sc.next();
            if(op.charAt(0) == 'y' || op.charAt(0) == 'Y')
                enterValues();
            option = chooseOptions();
        }
        sc.close();
        System.out.println("THANK YOU !!");
    }
}