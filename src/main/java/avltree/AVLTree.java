package avltree;

// 5 points task
/** Write a program implementing an AVL tree. At least create three main functions:
        1) to insert a new element,
        2) to delete an element,
        3) to display all elements. **/

public class AVLTree {

    Node root;

    // Main method
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.root = tree.insertNode(tree.root, 97);
        tree.root = tree.insertNode(tree.root, 105);
        tree.root = tree.insertNode(tree.root, 1);
        tree.root = tree.insertNode(tree.root, 14);
        tree.root = tree.insertNode(tree.root, 22);
        tree.root = tree.insertNode(tree.root, 44);
        tree.printTree(tree.root, "", true);
        tree.root = tree.deleteNode(tree.root, 1);
        System.out.println("\nAVL tree after deletion: ");
        tree.printTree(tree.root, "", true);
    }

    /** to insert node*/
    Node insertNode(Node node, int item) {

        // to find position and insert the node
        if (node == null)
            return (new Node(item));
        if (item < node.item)
            node.left = insertNode(node.left, item);
        else if (item > node.item)
            node.right = insertNode(node.right, item);
        else
            return node;

        // to balance the tree
        node.height = 1 + max(height(node.left), height(node.right));
        int balanceFactor = getBalance(node);
        if (balanceFactor > 1) {
            if (item < node.left.item) {
                return rotateRight(node);
            } else if (item > node.left.item) {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        }
        if (balanceFactor < -1) {
            if (item > node.right.item) {
                return rotateLeft(node);
            } else if (item < node.right.item) {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }
        return node;
    }

    /** Delete a node*/
    Node deleteNode(Node root, int item) {

        // Find the node to be deleted and remove it
        if (root == null)
            return root;
        if (item < root.item)
            root.left = deleteNode(root.left, item);
        else if (item > root.item)
            root.right = deleteNode(root.right, item);
        else {
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;
                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                Node temp = nodeWithMinimumValue(root.right);
                root.item = temp.item;
                root.right = deleteNode(root.right, temp.item);
            }
        }
        if (root == null)
            return root;

        // to update balance
        root.height = max(height(root.left), height(root.right)) + 1;
        int balanceFactor = getBalance(root);
        if (balanceFactor > 1) {
            if (getBalance(root.left) >= 0) {
                return rotateRight(root);
            } else {
                root.left = rotateLeft(root.left);
                return rotateRight(root);
            }
        }
        if (balanceFactor < -1) {
            if (getBalance(root.right) <= 0) {
                return rotateLeft(root);
            } else {
                root.right = rotateRight(root.right);
                return rotateLeft(root);
            }
        }
        return root;
    }

    /** display the AVL tree*/
    private void printTree(Node currPtr, String indent, boolean last) {
        if (currPtr != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("Right----");
                indent += "   ";
            } else {
                System.out.print("Left-----");
                indent += "|  ";
            }
            System.out.println(currPtr.item);
            printTree(currPtr.left, indent, false);
            printTree(currPtr.right, indent, true);
        }
    }

    int height(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    int max(int a, int b) {
        return Math.max(a, b);
    }

    Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
        return x;
    }

    Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        return y;
    }

    // balance
    int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }



    Node nodeWithMinimumValue(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }



    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.item + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    // to create a node
    public class Node {
        int item, height;
        Node left, right;

        Node(int d) {
            item = d;
            height = 1;
        }
    }

}

