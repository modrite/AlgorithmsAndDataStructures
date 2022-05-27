package queueusingsinglylinkedlist;

// 5 points task
/**
 * Write a program implementing a stack, a queue or a deque (choose only one of the three data
 * structures provided) using a singly linked list. At least create three main functions:
 * 1) to insert a new element,
 * 2) to delete an element,
 * 3) to display all elements.
 */

public class QueueUsingSinglyLinkedList {

    private Node front;
    private Node rear;
    private int currentSize;

    // main method
    public static void main(String[] args) {

        QueueUsingSinglyLinkedList queue = new QueueUsingSinglyLinkedList();
        queue.enqueue(1);
        queue.dequeue();
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.dequeue();
        queue.enqueue(6);
        queue.enqueue(7);
        queue.dequeue();
        queue.enqueue(8);
    }

    /**
     * insert new element in the end of the queue
     */
    public void enqueue(int data) {
        Node oldRear = rear;
        rear = new Node();
        rear.data = data;
        rear.next = null;
        if (isEmpty()) {
            front = rear;
        } else {
            oldRear.next = rear;
        }
        currentSize++;
        System.out.println(data + " added to the end of the queue");
    }

    /**
     * to delete an element from the beginning of the queue
     */
    public int dequeue() {
        int data = front.data;
        front = front.next;
        if (isEmpty()) {
            rear = null;
        }
        currentSize--;
        System.out.println(data + " deleted from the beginning of the queue");
        return data;
    }

    private static class Node {
        int data;
        Node next;
    }

    public QueueUsingSinglyLinkedList() {
        front = null;
        rear = null;
        currentSize = 0;
    }

    public boolean isEmpty() {
        return (currentSize == 0);
    }

}

