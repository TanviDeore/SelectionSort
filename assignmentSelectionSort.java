package dsaAssignment;

import java.util.Scanner;


class Node{
	Node next;
	int value;
	
	Node(int data){
		this.value=data;
		this.next=null;	
	}
}

class LinkedList{
	Node head;
	int size;
}


public class assignmentSelectionSort {
	

	public static LinkedList linkedlistElements(){
		
		LinkedList list = new LinkedList();
		int[] preDefinedList = {4,6,7,9,3,6,0,1,2,5,7,0,75,8,2,66,4,21,-68,-99,100};
		Node previous = null;
		Scanner scan = new Scanner(System.in);
		System.out.println("Do you want to continue with predefined input y/n");
		String choice = scan.next();
		
		if(choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
			for (int data : preDefinedList) {
	            previous = insertElements(data,previous,list);  // Update previous within the loop
	        }
		}
		else {
			System.out.println("Enter atleast 15 elements & enter any character to stop");
			while(true) {
				if(scan.hasNextInt()) {
				int data = scan.nextInt();
				previous = insertElements(data,previous,list);
				}
				else if (list.size<15) {
					System.out.println("Please add more integer elements");
					scan.next();			//skip input
					}
				else
					break;
			}
		
		}
		scan.close();
		return list;
	}
	
	public static Node insertElements(int data, Node previous, LinkedList list) {
		
		Node node = new Node(data);

		if (list.head==null) {
			list.head = node;				//assign head
			}
		else  {
			previous.next = node; 			//add next element
			}
		list.size++;		
		return node;
		
	}

	public static Node selectionSort(Node head, int size, int loop) {
	
		if(loop>=size-1) 
			return head;
		
		Node current  = head;
		Node smallest = head;
		Node previousToSmallest = null;
		Node PreviousToCurrent = null;
		
		
		int relink = 0;
		for(int i=loop;i<size;i++) {

			if(smallest.value > current.value) {
				smallest = current;
				previousToSmallest = PreviousToCurrent;
				relink++;
			}
			PreviousToCurrent = current;
			if(current!=null && current.next!=null) {
			current = current.next;
		}
		}
		
		if(relink>0) {
			 if (previousToSmallest != null) {
		            previousToSmallest.next = smallest.next;  // unlinking the smallest node from its original position
		        }
		        smallest.next = head;  // Move the smallest node to the front
		        head = smallest;  // Update head to point to the new head (smallest node)/ head is updated only once, after 1st call node next to head is updated
		}
		
		traversalFunction(head);
		 head.next =  selectionSort(head.next, size, loop+1);
		 return head;
		        
	}
	
	public static void traversalFunction(Node head) {
		while (head!=null) {
	        System.out.print(head.value + " ");
	        head = head.next;
	    }
	    System.out.println();
	}
	
	
	public static void main(String args[]) {
	LinkedList list = new LinkedList();
	list = linkedlistElements();
	System.out.print("Input given --> ");
	traversalFunction(list.head);
	list.head = selectionSort(list.head, list.size,0);
	System.out.print("Sorted list --> ");
	traversalFunction(list.head);
	
	
	}
}

