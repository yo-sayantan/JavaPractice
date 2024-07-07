// Input: {3,5,6,4,5,4,4,2,2,2,8,8,7}
// Output:{3,6,7,5,5,8,8,2,2,2,4,4,4}	

package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProductSort {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the number of items: ");
		int n = sc.nextInt(); // 13;
		int arr[] = new int[n]; // {3,5,6,4,5,4,4,2,2,2,8,8,7};
		
		System.out.print("Enter the items: ");
		String input = sc.next();
		for(int i=0; i<n; i++)
			arr[i] = Integer.parseInt(input.split(",")[i].trim());
		arr = itemSort(arr, n);
		
		System.out.println("After applying Product Sort:\n" + Arrays.toString(arr));
		sc.close();
	}

	private static int[] itemSort(int[] arr, int n) {
		Set<Integer> arrList = IntStream.of(arr).boxed().collect(Collectors.toSet());
		int freq[] = new int[arrList.size()];
		int j=0, i=0;
		
		Arrays.fill(freq, 0);
		Arrays.sort(arr);
		for(Integer in : arrList) {
			for(i=0; i<n; i++)
				if(arr[i] == in)
					freq[j]++;
			j++;
		}
		j=0;i=0;
		
		List<Integer> sortedList = arrList.stream().collect(Collectors.toList());
		ArrayList<Integer> sortedAList = new ArrayList(sortedList);
		Collections.sort(sortedAList, Comparator.comparing(s -> freq[sortedList.indexOf(s)]));
		Arrays.sort(freq);

		for(Integer in : sortedAList) {
			while(freq[j]-- != 0)
				arr[i++] = in;
			j++;
		}
		return arr;
	}

}
