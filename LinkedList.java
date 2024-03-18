import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Linked List Project");

        // Create a new linked list
        LinkedList<Integer> list = new LinkedList<>();
        list.printList(); // Print the empty list

        // Create a linked list with a single element
        list = new LinkedList<>(5);
        list.printList(); // Print the list with a single element

        // Add elements to the list
        list.add(56); // Add element to the beginning of the list
        list.add(59); // Add element to the beginning of the list
        list.add(1, 89); // Add an element at index 1
        list.add(800); // Add element to the beginning of the list
        list.printList(); // Print the updated list

        // Remove an element at index 1
        list.remove(1);
        list.printList(); // Print the list after removal
        Integer[] arr = {1, 2, 3, 4, 5}; // Sample array
        ArrayList<Integer> alist = new ArrayList<>(Arrays.asList(arr));
        LinkedList<Integer> list3 = new LinkedList<>(alist);
        System.out.println(list3);
    }
}

class LinkedList<K> {
    private class Node {
        Node next;
        K val;

        // Node constructor
        Node() {
            this.val = null;
            this.next = null;
        }

        // Node constructor with value
        Node(K val) {
            this.val = val;
            this.next = null;
        }
    }

    private Node head; // Head of the linked list

    // Constructor to initialize an empty linked list
    LinkedList() {
        head = null;
    }

    // Constructor to initialize a linked list with a single element
    LinkedList(K val) {
        if (val == null) {
            head = null;
        } else {
            head = new Node(val);
        }
    }

    // Constructor to initialize a linked list with another list
    LinkedList(LinkedList<K> list) {
        if (list.head == null) return;
        this.head = list.head;
    }

    // Constructor to initialize a linked list with an ArrayList element
    LinkedList(ArrayList<K> list) {
        if (list.size() == 0) return;
        head = new Node(list.get(0));
        Node temp = head;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) == null)
                throw new NullPointerException("null pointer found");
            temp.next = new Node(list.get(i));
            temp = temp.next;
        }
    }

    // Method to print the elements of the linked list
    public void printList() {
        if (head == null) {
            System.out.println("[NULL]");
            return;
        }
        Node temp = head;
        System.out.print("[");
        while (temp != null) {
            System.out.print(temp.val);

            // Print arrow between elements
            System.out.print(" -> ");

            temp = temp.next;
        }
        System.out.println("NULL]");
    }

    // Method to add an element to the beginning of the linked list
    public K add(K val) {
        if (val == null)
            throw new NullPointerException("Value cannot be null");
        if (head == null)
            head = new Node(val); // Create head node if list is empty
        else {
            Node newNode = new Node(val);
            Node temp = head;
            head = newNode;
            newNode.next = temp;
        }
        return val;
    }

    // Method to add an element at a specific index in the linked list
    public K add(int index, K val) {
        if (index < 0)
            throw new IndexOutOfBoundsException("Index out of bounds");
        Node temp = head;
        Node newNode = new Node(val);
        if (index == 0) {
            head = newNode; // Update head if adding at index 0
            newNode.next = temp;
            return val;
        }
        while (--index > 0) {
            if (temp == null)
                throw new IndexOutOfBoundsException("Index out of bounds");
            temp = temp.next;
        }
        if (temp == null)
            throw new IndexOutOfBoundsException("Index out of bounds");
        Node next = temp.next;
        temp.next = newNode; // Insert new node at index
        newNode.next = next;
        return val;
    }

    // Method to remove the head of the linked list
    public K remove() {
        if (head == null)
            throw new NullPointerException("List is empty");
        K val = head.val;
        head = head.next; // Remove head node
        return val;
    }

    // Method to remove an element at a specific index in the linked list
    public K remove(int index) {
        if (index < 0)
            throw new IndexOutOfBoundsException("Index out of bounds");
        Node temp = head;
        if (index == 0) {
            if (temp == null)
                throw new NullPointerException("List is empty");
            K val = head.val;
            head = head.next; // Remove head node
            return val;
        }
        while (--index > 0) {
            if (temp == null)
                throw new IndexOutOfBoundsException("Index out of bounds");
            temp = temp.next;
        }
        if (temp == null || temp.next == null)
            throw new IndexOutOfBoundsException("Index out of bounds");
        Node next = temp.next;
        K val = next.val;
        temp.next = temp.next.next; // Remove node at index
        next.next = null;
        return val;
    }

    // Method to get the value of the head node
    public K get() {
        return head.val;
    }

    // Method to get the value at a specific index in the linked list
    public K get(int index) {
        Node temp = head;
        while (--index > 0) {
            if (temp == null)
                throw new IndexOutOfBoundsException("Index out of bounds");
            temp = temp.next;
        }
        if (temp == null)
            throw new IndexOutOfBoundsException("Index out of bounds");
        return temp.val;
    }

    // Method to get the head node
    public Node getNode() {
        return head;
    }

    // Method to get the node at a specific index in the linked list
    public Node getNodeAt(int index) {
        Node temp = head;
        while (--index > 0) {
            if (temp == null)
                throw new IndexOutOfBoundsException("Index out of bounds");
            temp = temp.next;
        }
        if (temp == null)
            throw new IndexOutOfBoundsException("Index out of bounds");
        return temp;
    }

    // Method to convert the linked list to a string
    @Override
    public String toString() {
        if (head == null) {
            return "NULL";
        }
        Node temp = head;
        StringBuilder res = new StringBuilder("[");
        while (temp != null) {
            res.append(temp.val);
            if(temp.next != null)
            res.append(",");

            temp = temp.next;
        }
        res.append("]");
        return res.toString();
    }
}
