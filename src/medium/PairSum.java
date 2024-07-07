/**
 * Problem statement
You are given an integer array 'ARR' of size 'N' and an integer 'S'. Your task is to return the list of all pairs of elements such that each sum of elements of each pair equals 'S'.
Note: Each pair should be sorted i.e the first value should be less than or equals to the second value. 
Return the list of pairs sorted in non-decreasing order of their first value. In case if two pairs have the same first value, the pair with a smaller second value should come first.
 * 
 **/
package medium;

import java.util.*;

public class PairSum {
    public static List<int[]> pairSum(int[] arr, int s) {
        HashSet<Integer> set = new HashSet<>();
        Arrays.sort(arr);
        int l = arr.length;
        List<int[]> list = new ArrayList<int[]>();
        for (int i = 0; i < l; i++)
            set.add(arr[i]);

        for (int x : set) {
            if (set.contains(s - x) && x <= s-x) {
            	int[] sum = new int[2];
                sum[0] = x;
                sum[1] = s - x;
                list.add(sum);
            }
        }
        return list;
    }

    public static void main(String[] arg) {
        System.out.println();
        int[] arr = new int[] {1,43,5,2,2,4,56,7,8,45,3,2};
        List<int[]> list = pairSum(arr, arr.length);
        System.out.println("Original Array = " + Arrays.toString(arr));
        System.out.print("Final Pairs = ");
        for(int[] a : list)
        	System.out.print(Arrays.toString(a)+",");
    }
}