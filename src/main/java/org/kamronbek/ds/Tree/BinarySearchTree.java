package org.kamronbek.ds.Tree;

import java.util.Objects;
import java.util.Stack;

public class BinarySearchTree {

    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void addNode(int value) {
        if (this.root == null) {
            this.root = new Node(value);
            return;
        }

        Node temp = this.root;
        while(true) {
            if (temp.value >= value) {
                if (temp.left == null) {
                    temp.left = new Node(value);
                    return;
                } else {
                    temp = temp.left;
                }
            }

            else {
                if (temp.right == null) {
                    temp.right = new Node(value);
                    return;
                } else {
                    temp = temp.right;
                }
            }
        }
    }

    public boolean search(int value) {
        if (this.root == null)
            return false;

        Node temp = this.root;
        while(true) {
            if (temp.value == value)
                return true;

            if (temp.value > value) {
                if (temp.left == null) {
                    return false;
                } else {
                    temp = temp.left;
                }
            }

            else {
                if (temp.right == null) {
                    return false;
                } else {
                    temp = temp.right;
                }
            }
        }
    }

    public boolean delete(int value) {
        if (this.root == null)
            return false;

        Node prev = null;
        Node curr = this.root;

        while (curr.value != value) {

            if (curr.value > value) {
                if (curr.left != null) {
                    prev = curr;
                    curr = curr.left;
                } else {
                    return false;
                }
            } else {
                if (curr.right != null) {
                    prev = curr;
                    curr = curr.right;
                } else {
                    return false;
                }
            }
        }
        deleteNode(prev, curr);
        return true;
    }

    private void deleteNode(Node parent, Node nodeToDelete) {

        // Checking if value is located at the head
        if (parent == null) {
            this.root = null;
            return;
        }

        Node temp;
        // Checking if node has a missing child or not
        if (nodeToDelete.left == null || nodeToDelete.right == null) {
            // checking if a missing child is left child
            if (nodeToDelete.left == null)
                temp = nodeToDelete.right; // if left child is missing then assign right child
            else
                temp = nodeToDelete.left; // if right child is missing then assign left child

            // Identifying if a node is a left child or right child to its parent
            if (parent.left == nodeToDelete)
                parent.left = temp;
            else
                parent.right = temp;
        } else {

            Node inOrderSuccessor = findInOrderSuccessor(nodeToDelete);
            if (parent.left == nodeToDelete) {
                parent.left = inOrderSuccessor;
            } else {
                parent.right = inOrderSuccessor;
            }

            inOrderSuccessor.left = nodeToDelete.left;
            inOrderSuccessor.right = nodeToDelete.right;
        }
    }

    private Node findInOrderSuccessor(Node node) {
        Node parent = node;
        node = node.right;

        while(node.left != null) {
            parent = node;
            node = node.left;
        }

        if (parent.right == node) {
            parent.right = node.right;
        } else {
            parent.left = node.right;
        }

        return node;
    }

    public void iterativePreOrder() {
        /*
         * Printing the pre_order iteratively. The idea is implemented using a stack
         * 1) Create an empty stack and push the root to the stack
         * 2) While stack is not empty pop the item from stack
         * 3) Print the value of popped item
         * 4) Push the right child of popped item to the stack
         * 5) Push the left child of popped item to the stack
         */

        if (this.root == null)
            return;

        Stack<Node> stack = new Stack<>();
        stack.push(this.root);
        Node node;
        while (!stack.isEmpty()) {
            node = stack.pop();
            System.out.print(node.value + "|");
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
        System.out.println();
    }

    public void iterativeInOrder() {
        /*
         * Printing in_order iteratively. The idea is implemented using a stack
         * 1) Create an empty stack
         * 2) If root is not None
         * 3) Push the root to stack
         * 4) Assign the left child of root to root
         * 5) If root is None and stack is not empty
         * 6) Pop the value from stack and print its value
         * 7) Assign the right child to root
         * 8) If root is None and stack is empty break the loop
         */

        if (this.root == null)
            return;

        Stack<Node> stack = new Stack<>();
        Node node = this.root;
        while (!(stack.isEmpty() && node == null)) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            }
            else {
                node = stack.pop();
                System.out.print(node.value + "|");
                node = node.right;
            }
        }

    }

    public void iterativePostOrder1() {
        /*
         * Printing post_order iteratively. The idea is using two stacks.
         * 1) Push the root to the stack 1
         * 2) while stack 1 is not empty
         * 3) Pop the value from stack 1 and push it to stack 2
         * 4) If not None push left child and right child of popped item to stack 1
         * 5) After stack 1 is empty pop the values from stack 2 and print them
         */

        if (this.root == null)
            return;

        Stack<Node> stack1 = new Stack<>();
        stack1.push(this.root);
        Stack<Node> stack2 = new Stack<>();
        Node node;
        while (!stack1.isEmpty()) {
            node = stack1.pop();
            stack2.push(node);
            if (node.left != null)
                stack1.push(node.left);
            if (node.right != null)
                stack1.push(node.right);
        }
        for (int i = stack2.size() - 1; i >= 0; i--) {
            System.out.print(stack2.get(i).value + "|");
        }
        System.out.println();

    }

    public void iterativePostOrder2() {
        /*
         * 1.1 Create an empty stack
         * 2.1 Do following while root is not NULL
         *   a) Push root's right child and then root to stack.
         *   b) Set root as root's left child.
         * 2.2 Pop an item from stack and set it as root.
         *   a) If the popped item has a right child and the right child
         *   is at top of stack, then remove the right child from stack,
         *   push the root back and set root as root's right child.
         *   b) Else print root's data and set root as NULL.
         * 2.3 Repeat steps 2.1 and 2.2 while stack is not empty.
         */

        if (this.root == null)
            return;

        Stack<Node> stack = new Stack<>();
        Node node = this.root;

        do {
            if (node != null) {
                if (node.right != null)
                    stack.push(node.right);
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                if ((!stack.isEmpty()) && (node.right != null) && (node.right == (stack.peek()))) {
                    stack.pop();
                    stack.push(node);
                    node = node.right;
                } else {
                    System.out.print(node.value + "|");
                    node = null;
                }
            }
        } while (!stack.isEmpty());
        System.out.println();
    }

    public void iterativePostOrder3() {
        /*
         * Inserting as pairs Pair(node, count)
         * count = 0 means no left or right child is processed
         * count = 1 means left child is processed
         * count = 2 means both left and right child are processed
         */
        if (this.root == null)
            return;
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(this.root, 0));
        Pair temp;
        while (!stack.isEmpty()) {
            temp = stack.pop();
            if (temp.count == 0) {
                temp.count = 1;
                stack.push(temp);
                if (temp.node.left != null)
                    stack.push(new Pair(temp.node.left, 0));
            }
            else if (temp.count == 1) {
                temp.count = 2;
                stack.push(temp);
                if (temp.node.right != null)
                    stack.push(new Pair(temp.node.right, 0));
            } else {
                System.out.print(temp.node.value + "|");
            }
        }
        System.out.println();
    }

    public void recursivePreOrder() {
        this.preOrderRecursive(this.root);
        System.out.println();
    }

    private void preOrderRecursive(Node node) {
        if (node == null)
            return;
        System.out.print(node.value + "|");
        this.preOrderRecursive(node.left);
        this.preOrderRecursive(node.right);
    }

    public void recursiveInOrder() {
        this.inOrderRecursive(this.root);
        System.out.println();
    }

    private void inOrderRecursive(Node node) {
        if (node == null)
            return;
        this.inOrderRecursive(node.left);
        System.out.print(node.value + "|");
        this.inOrderRecursive(node.right);
    }

    public void recursivePostOrder() {
        this.postOrderRecursive(this.root);
        System.out.println();
    }

    private void postOrderRecursive(Node node) {
        if (node == null)
            return;
        this.postOrderRecursive(node.left);
        this.postOrderRecursive(node.right);
        System.out.print(node.value + "|");
    }

    public boolean isBST() {
        if (this.root == null)
            return true;
        return this.isSubtreeBST(this.root.left, Integer.MIN_VALUE, this.root.value) &&
                this.isSubtreeBST(this.root.right, this.root.value, Integer.MAX_VALUE);
    }

    private boolean isSubtreeBST(Node node, int min, int max) {
        if (node == null)
            return true;
        if ((min < node.value) && (node.value <= max))
            return isSubtreeBST(node.left, min, node.value) &&
                    isSubtreeBST(node.right, node.value, max);
        return false;
    }

    private static class Node {
        public int value;
        public Node right;
        public Node left;

        public Node(int value) {
            this.value = value;
            this.right = null;
            this.left = null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return value == node.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    private static class Pair {
        public Node node;
        public int count;

        public Pair(Node node, int count) {
            this.node = node;
            this.count = count;
        }
    }
}
