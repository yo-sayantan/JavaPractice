/**
 * 
 */
package hard;

import utility.LinkedListNode;

/**
 * Q: Add any number to a linked list containing only 1 digit each, which will form a number as a whole.
 * Return the sum of the two numbers.
 */
public class AddToLinkedList {	
	public static void main(String args[]) {
        int m = 1215783;
        int n = 781227849;
        LinkedListNode node = new LinkedListNode();
        getSumViaLinkedList(node, m, n);
        
		System.out.println("Sum of "+m+" and " +n+ " = " + node.toString());
    }

	private static void getSumViaLinkedList(LinkedListNode node, int m, int n) {		
		int num1 = (m>n) ? m : n;
		int num2 = (m<n) ? m : n;
		
		getLinkedListFromNum(node, num1);
		addition(node, num2);
		node.printLinkedList();
	}

	private static void addition(LinkedListNode node, int num) {
		int length = node.getSize();
		int carry = 0;
		
		try {
			while(length>0) {
				int value = node.getValue(length);
				int newValue = num%10;
				int addedValue = (carry == 0) ? value+newValue : value+newValue+carry;
				carry = addedValue/10;
				addedValue %= 10;
				
				node.updateValue(length, addedValue);
				num /= 10;
				length--;
			}
			if(carry != 0)
				node.addFirst(carry);
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void getLinkedListFromNum(LinkedListNode node, int n) {
		while(n != 0) {
			int x = n%10;
			n /= 10;
			node.addFirst(x);
		}
		
		node.printLinkedList();
	}
	
	
	

}
