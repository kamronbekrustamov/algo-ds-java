package org.kamronbek.ds.Tree;

import java.util.*;

public class BinarySearchTree {

    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

        /**
         * Inserts a new integer value into the Binary Search Tree.
         * <p>
         * The method traverses the tree to find the appropriate location for the new node.
         * If the tree is empty, the new node becomes the root. For this implementation,
         * if a value being inserted is equal to an existing node's value, it is treated
         * as greater than or equal to and placed in the right subtree.
         *
         * @param value The integer value to insert into the tree.
         */
        public void add(int value) {
            if (this.root == null) {
                this.root = new Node(value);
                return;
            }

            Node current = this.root;
            Node parent = null;

            while (current != null) {
                parent = current;
                if (value < current.value) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            if (value < parent.value) {
                parent.left = new Node(value);
            } else {
                parent.right = new Node(value);
            }
        }

        /**
         * Searches for a specific integer value within the Binary Search Tree.
         * <p>
         * The search leverages the BST property: for any given node, all values in its
         * left subtree are smaller, and all values in its right subtree are larger.
         * This allows the algorithm to efficiently eliminate half of the remaining
         * tree at each step.
         *
         * @param value The integer value to search for.
         * @return {@code true} if the value is found within the tree; {@code false} otherwise.
         */
        public boolean search(int value) {
            Node current = this.root;

            while (current != null) {
                if (current.value == value) {
                    return true;
                }

                if (value < current.value) {
                    current = current.left;
                }
                else {
                    current = current.right;
                }
            }

            return false;
        }

    public boolean remove(int value) {
        if (!this.search(value)) {
            return false;
        }

        this.root = removeRecursive(this.root, value);
        return true;
    }

    /**
     * A private recursive helper method to remove a node and restructure the tree.
     *
     * @param currentNode The root of the current subtree.
     * @param value The value to remove.
     * @return The new root of the subtree after removal.
     */
    private Node removeRecursive(Node currentNode, int value) {
        if (currentNode == null) {
            return null;
        }

        if (value < currentNode.value) {
            currentNode.left = removeRecursive(currentNode.left, value);
        } else if (value > currentNode.value) {
            currentNode.right = removeRecursive(currentNode.right, value);
        } else {
            if (currentNode.left == null) {
                return currentNode.right;
            } else if (currentNode.right == null) {
                return currentNode.left;
            }

            currentNode.value = findInOrderSuccessor(currentNode.right);
            currentNode.right = removeRecursive(currentNode.right, currentNode.value);
        }

        return currentNode;
    }

    /**
     * A private helper to find the in-order successor of a node.
     * The in-order successor is the node with the minimum value in a given subtree.
     *
     * @param node The root of the subtree to search within.
     * @return The integer value of the in-order successor.
     */
    private int findInOrderSuccessor(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.value;
    }



    /**
     * Performs an iterative Preorder traversal of the tree.
     * <p>
     * Preorder traversal visits nodes in the order: Root -> Left -> Right.
     * This iterative implementation uses a stack to simulate the recursive calls.
     * It avoids the risk of a `StackOverflowError` that can occur with the
     * recursive version on very deep, unbalanced trees.
     *
     * @return A list of integers representing the node values in preorder.
     */
    public List<Integer> preorder() {
        if (this.root == null) {
            return Collections.emptyList();
        }

        final List<Integer> result = new LinkedList<>();
        final Deque<Node> stack = new ArrayDeque<>();
        stack.push(this.root);

        while (!stack.isEmpty()) {
            final Node node = stack.pop();
            result.add(node.value);

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
    }

    /**
     * Performs a recursive Preorder traversal of the tree.
     * <p>
     * Preorder traversal visits nodes in the order: Root -> Left -> Right.
     * This recursive approach is often more intuitive and concise but can lead
     * to a `StackOverflowError` if the tree is extremely deep and unbalanced.
     *
     * @return A list of integers representing the node values in preorder.
     */
    public List<Integer> recursivePreorder() {
        final List<Integer> result = new LinkedList<>();
        this.preorderRecursive(this.root, result);
        return result;
    }

    /**
     * A private helper method that implements the recursive preorder logic.
     *
     * @param node The current node being visited.
     * @param list The list to which the traversal results are appended.
     */
    private void preorderRecursive(Node node, List<Integer> list) {
        if (node == null) {
            return;
        }

        list.add(node.value);
        this.preorderRecursive(node.left, list);
        this.preorderRecursive(node.right, list);
    }


    /**
     * Performs an iterative Inorder traversal of the tree.
     * <p>
     * Inorder traversal visits nodes in the order: Left -> Root -> Right.
     * For a Binary Search Tree (BST), this traversal yields the node values
     * in ascending sorted order. This iterative implementation uses a stack
     * to simulate the recursive calls and avoids the risk of a
     * `StackOverflowError` on very deep trees.
     *
     * @return A list of integers representing the node values in inorder.
     */
    public List<Integer> inorder() {
        if (this.root == null) {
            return Collections.emptyList();
        }

        final List<Integer> result = new LinkedList<>();
        final Deque<Node> stack = new ArrayDeque<>();
        Node node = this.root;

        // Continue as long as there are nodes to visit, either on the current path
        // or waiting in the stack.
        while (node != null || !stack.isEmpty()) {
            // --- Phase 1: Traverse as far left as possible ---
            // Push all nodes from the current path onto the stack.
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            // --- Phase 2: Process the node and traverse right ---
            // Pop the last node visited (the leftmost one).
            node = stack.pop();
            // Add its value to the result (this is the "Root" visit).
            result.add(node.value);
            // Move to its right subtree to repeat the entire process.
            node = node.right;
        }
        return result;
    }

    /**
     * Performs a recursive Inorder traversal of the tree.
     * <p>
     * Inorder traversal visits nodes in the order: Left -> Root -> Right.
     * This recursive approach is often more intuitive and concise but can lead
     * to a `StackOverflowError` if the tree is extremely deep and unbalanced.
     *
     * @return A list of integers representing the node values in inorder.
     */
    public List<Integer> recursiveInorder() {
        final List<Integer> result = new LinkedList<>();
        this.inorderRecursive(this.root, result);
        return result;
    }

    /**
     * A private helper method that implements the recursive inorder logic.
     *
     * @param node The current node being visited.
     * @param list The list to which the traversal results are appended.
     */
    private void inorderRecursive(Node node, List<Integer> list) {
        if (node == null) {
            return;
        }

        this.inorderRecursive(node.left, list);
        list.add(node.value);
        this.inorderRecursive(node.right, list);
    }

    /**
     * Performs an iterative Postorder traversal using a "reverse preorder" trick.
     * <p>
     * Postorder traversal visits nodes in the order: Left -> Right -> Root.
     * This clever implementation first performs a modified preorder traversal
     * (Root -> Right -> Left) and adds each visited value to the *front* of the
     * result list. Adding to the front reverses the order, yielding the correct
     * postorder sequence.
     *
     * @return A list of integers representing the node values in postorder.
     */
    public List<Integer> postorder() {
        // If the tree is empty, return an immutable empty list for efficiency.
        if (this.root == null) {
            return Collections.emptyList();
        }

        final List<Integer> result = new LinkedList<>();
        final Deque<Node> stack = new ArrayDeque<>();
        stack.push(this.root);

        while (!stack.isEmpty()) {
            final Node node = stack.pop();

            // Add the node's value to the front of the list.
            // This is the key step that reverses the traversal order.
            result.addFirst(node.value);

            // Push the left child first, then the right child.
            // Because the stack is LIFO, the right child will be processed
            // before the left child, which is what we want for our
            // "Root -> Right -> Left" traversal.
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return result;
    }

    /**
     * Performs an iterative Postorder traversal using a stack and a 'last visited' pointer.
     * <p>
     * This is a more traditional but complex iterative approach that directly
     * simulates the postorder process. It uses a `lastVisitedNode` to keep
     * track of the node that was most recently processed to determine if a
     * node's right subtree has already been visited.
     *
     * @return A list of integers representing the node values in postorder.
     */
    public List<Integer> postorderWithLastVisited() {
        if (this.root == null) {
            return Collections.emptyList();
        }

        final List<Integer> result = new LinkedList<>();
        final Deque<Node> stack = new ArrayDeque<>();
        Node current = this.root;
        Node lastVisitedNode = null;

        while (current != null || !stack.isEmpty()) {
            // --- Phase 1: Traverse as far left as possible ---
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                // --- Phase 2: Inspect the node at the top of the stack ---
                final Node peekNode = stack.peek();

                // If the node has a right child that we haven't visited yet,
                // we must traverse that right subtree before processing the node itself.
                if (peekNode.right != null && peekNode.right != lastVisitedNode) {
                    current = peekNode.right;
                } else {
                    // If there is no right child, or we've already visited it,
                    // we can now process the current node.
                    stack.pop();
                    result.add(peekNode.value);
                    // Mark this node as the last one we've visited.
                    lastVisitedNode = peekNode;
                }
            }
        }
        return result;
    }

    /**
     * Performs a recursive Postorder traversal of the tree.
     * <p>
     * Postorder traversal visits nodes in the order: Left -> Right -> Root.
     * This is useful for operations like deleting nodes from a tree, as you
     * must delete children before deleting the parent.
     *
     * @return A list of integers representing the node values in postorder.
     */
    public List<Integer> recursivePostOrder() {
        final List<Integer> result = new LinkedList<>();
        this.postorderRecursive(this.root, result);
        return result;
    }

    /**
     * A private helper method that implements the recursive postorder logic.
     *
     * @param node The current node being visited.
     * @param list The list to which the traversal results are appended.
     */
    private void postorderRecursive(Node node, final List<Integer> list) {
        if (node == null) {
            return;
        }

        this.postorderRecursive(node.left, list);
        this.postorderRecursive(node.right, list);
        list.add(node.value);
    }

    /**
     * Checks if the binary tree is a valid Binary Search Tree (BST).
     * <p>
     * A BST is defined as a tree where for every node:
     * 1. All values in its left subtree are strictly less than the node's value.
     * 2. All values in its right subtree are greater than or equal to the node's value.
     * 3. Both the left and right subtrees are also BSTs.
     * <p>
     * This implementation uses a recursive approach that validates each node
     * against a permissible range `(min, max)`. This is more robust than
     * simply checking parent-child relationships.
     *
     * @return {@code true} if the tree is a valid BST; {@code false} otherwise.
     */
    public boolean isBST() {
        if (this.root == null) {
            return true;
        }
        return this.isSubtreeBST(this.root, null, null);
    }

    /**
     * A private recursive helper to validate if a subtree is a BST within a given range.
     *
     * @param node The current node to validate.
     * @param min  The minimum allowed value for this node (exclusive). `null` represents negative infinity.
     * @param max  The maximum allowed value for this node (inclusive). `null` represents positive infinity.
     * @return {@code true} if the subtree rooted at 'node' is a valid BST within the range.
     */
    private boolean isSubtreeBST(Node node, Integer min, Integer max) {
        // Base case: An empty subtree is always valid.
        if (node == null) {
            return true;
        }

        // Check if the current node's value violates the permissible range.
        // The check `(min < node.value)` ensures the left side is strictly less.
        // The check `(node.value <= max)` allows for duplicates on the right side.
        if ((min != null && min >= node.value) || (max != null && node.value > max)) {
            return false;
        }

        // Recursively validate the left and right subtrees with updated ranges.
        // For the left subtree, the current node's value becomes the new maximum.
        // For the right subtree, the current node's value becomes the new minimum.
        return isSubtreeBST(node.left, min, node.value) &&
                isSubtreeBST(node.right, node.value, max);
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
}
