/**
 * 
 */
package utility;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class LinkedListNode {
		
	LinkedListNode head;
	Integer data;
	LinkedListNode next;
	
	public LinkedListNode() {
		this.data = null;
		this.next = null;
	}
	
	LinkedListNode(int data) {
		this.data = data;
		this.next = null;
	}

	public void addFirst(int x) {
		LinkedListNode newNode = new LinkedListNode(x);
		if(head == null)
			head = new LinkedListNode();
		newNode.next = head;
		head = newNode;
	}

	public List<?> linkedListToList() {
		List<String> list = new ArrayList<>();
		
		try {
			if(head == null) {
				throw new Exception("Link Empty");
			} else {
				LinkedListNode temp = head;
				while(temp.next != null) {
					list.add(String.valueOf(temp.data));
					temp = temp.next;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void printLinkedList() {
		System.out.println(linkedListToList());
	}
	
	@Override
	public String toString() {
		String str = linkedListToList()
						.toString()
						.substring(1, 3 * getSize() - 1)
						.replaceAll(", ", "");
		
		return str;
	}

	public int getSize() {
		int size = 0;
		LinkedListNode temp = head;
		
		if(temp == null)
			return 0;
		if(temp.next == null)
			return 1;
		
		while(temp.next != null) {
			size++;
			temp = temp.next;
		}
		
		return size;
	}

	public int getValue(int position) throws Exception {
		LinkedListNode temp = head;
		int p = position;
		if(temp == null)
			throw new Exception("Empty LinkedList");
		
		while(position-- > 1) {
			if(temp == null && position != 0)
				throw new Exception("LinkedList is short. Value not found for position "+ (p-position));
			else
				temp = temp.next;
		}
		return temp.data;
	}

	public void updateValue(int position, int newValue) throws Exception {
		LinkedListNode temp = head;
		int p = position;
		if(head == null)
			throw new Exception("Empty LinkedList");
		
		while(position-- > 1) {
			temp = temp.next;
			if(temp == null && position != 0)
				throw new Exception("LinkedList is short. Value not found fro position "+ (p-position));
		}
		
		temp.data = newValue;
	}
}
