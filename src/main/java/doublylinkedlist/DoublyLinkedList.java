package doublylinkedlist;

// 5 points task
/**
 * Write a program implementing a doubly-linked list. At least create three main functions:
 * 1) to insert a new element,
 * 2) to delete an element,
 * 3) to display all elements.
 */

public class DoublyLinkedList {

    private Node head;
    private Node tail;
    private int size = 0;

    // main method
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        list.insertFirst(1);
        System.out.println("Inserted element in the beginning of the list: ");
        list.display();
        list.insertFirst(2);
        System.out.println("Inserted element in the beginning of the list: ");
        list.display();
        list.insertAtIndex(3, 2);
        System.out.println("Inserted element at specific index: ");
        list.display();
        list.insertLast(4);
        System.out.println("Inserted element in the end of the list: ");
        list.display();
        list.insertLast(5);
        System.out.println("Inserted element in the end of the list: ");
        list.display();
        Node node = list.deleteAtIndex(2);
        System.out.println("Element with value " + node.i + " was deleted");
        list.display();
    }

    // insert element as first
    public void insertFirst(int element) {
        Node newNode = new Node(element);
        if (isEmpty()) {
            tail = newNode;
        } else {
            head.previous = newNode;
        }
        newNode.next = head;
        head = newNode;
        size++;
    }

    // insert element as last
    public void insertLast(int element) {
        Node newNode = new Node(element);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
        }
        tail = newNode;
        size++;
    }

    // insert element at a specific index
    public void insertAtIndex(int element, int index) {
        Node newNode = new Node(element);
        Node current = head;
        if (index == 0) {
            insertFirst(element);
        } else if (index == size) {
            insertLast(element);
        } else {
            for (int j = 0; j < index && current.next != null; j++) {
                current = current.next;
            }
            newNode.next = current;
            current.previous.next = newNode;
            newNode.previous = current.previous;
            current.previous = newNode;
            size++;
        }
    }

    // delete first element
    public Node deleteFirst() {
        Node first = head;
        if (head.next == null) {
            tail = null;
        } else {
            head.next.previous = null;
        }
        head = head.next;
        size--;
        return first;
    }

    // delete last element
    public Node deleteLast() {

        Node last = tail;
        if (head.next == null) {
            head = null;
        } else {
            tail.previous.next = null;
        }
        tail = tail.previous;
        size--;
        return last;
    }


    // delete element at a specific index
    public Node deleteAtIndex(int index) {
        Node current = head;
        if (index == 0) {
            return deleteFirst();
        }
        else if (index == size - 1) {
            return deleteLast();
        } else {
            for (int j = 0; j < index && current.next != null; j++) {
                current = current.next;
            }
            current.previous.next = current.next;
            assert current.next != null;
            current.next.previous = current.previous;
            size--;
        }
        return current;
    }

    // Display all elements
    public void display() {
        Node current = head;
        while (current != null) {
            current.displayElements();
            current = current.next;
        }
        System.out.println("");
    }

    static class Node {
        int i;
        Node next;
        Node previous;

        Node(int i) {
            this.i = i;
        }

        public void displayElements() {
            System.out.print(" " + i);
        }
    }

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

}
