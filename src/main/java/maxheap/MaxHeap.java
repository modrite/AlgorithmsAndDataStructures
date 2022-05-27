package maxheap;

// 3 points
/** Write a program implementing a heap. At least create three main functions:
        1) to insert a new element,
        2) to delete an element,
        3) to display all elements. */

public class MaxHeap {

    private final int[] heap;
    private int size;

    // main method
    public static void main(String[] arg) {

        System.out.println("Max Heap: ");

        MaxHeap maxHeap = new MaxHeap(20);

        maxHeap.insert(1);
        maxHeap.insert(2);
        maxHeap.insert(5);
        maxHeap.insert(100);
        maxHeap.insert(99);
        maxHeap.insert(102);
        maxHeap.insert(6);
        maxHeap.insert(14);
        maxHeap.insert(78);
        maxHeap.deleteMaxElement();

        maxHeap.display();

    }

    /** to insert new element to max heap */
    public void insert(int element) {
        heap[size] = element;
        int current = size;
        while (heap[current] > heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
        size++;
    }


    /** to delete the biggest element from the heap */
    public void deleteMaxElement() {
        int popped = heap[0];
        heap[0] = heap[size--];
        maxHeapify(0);
    }

    /** to display heap */
    public void display() {

        for (int i = 0; i < size / 2; i++) {

            System.out.print("Parent node : " + heap[i]);

            if (leftChild(i) < size) //if the child is out of the bound of the array
                System.out.print("\n Left child node: " + heap[leftChild(i)]);

            if (rightChild(i) < size) //if the right child index must not be out of the index of the array
                System.out.print("\n Right child node: " + heap[rightChild(i)]);

            System.out.println();
        }

    }


    public MaxHeap(int maxsize) {
        this.size = 0;
        heap = new int[maxsize];
    }

    // position of the parent
    private int parent(int position) {
        return (position - 1) / 2;
    }

    // left child
    private int leftChild(int position) {
        return (2 * position) + 1;
    }

    // right child
    private int rightChild(int position) {
        return (2 * position) + 2;
    }

    private boolean isLeaf(int position) {
        if (position > (size / 2) && position <= size) {
            return true;
        }
        return false;
    }

    // swapping nodes
    private void swap(int fpos, int spos) {
        int tmp;
        tmp = heap[fpos];
        heap[fpos] = heap[spos];
        heap[spos] = tmp;
    }

    private void maxHeapify(int pos) {
        if (isLeaf(pos))
            return;

        if (heap[pos] < heap[leftChild(pos)]
                || heap[pos] < heap[rightChild(pos)]) {

            if (heap[leftChild(pos)]
                    > heap[rightChild(pos)]) {
                swap(pos, leftChild(pos));
                maxHeapify(leftChild(pos));
            } else {
                swap(pos, rightChild(pos));
                maxHeapify(rightChild(pos));
            }
        }
    }


}
