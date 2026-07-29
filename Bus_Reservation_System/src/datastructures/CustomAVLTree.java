package datastructures;

import models.Bus;

public class CustomAVLTree<T extends Comparable<T>> {

    public static class AVLNode<T> {
        public T data;
        public int height;
        public AVLNode<T> left;
        public AVLNode<T> right;

        public AVLNode(T data) {
            this.data = data;
            this.height = 1;
        }
    }

    private AVLNode<T> root;

    private int height(AVLNode<T> node) {
        return (node == null) ? 0 : node.height;
    }

    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    private int getBalance(AVLNode<T> node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private AVLNode<T> rightRotate(AVLNode<T> y) {
        AVLNode<T> x = y.left;
        AVLNode<T> T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private AVLNode<T> leftRotate(AVLNode<T> x) {
        AVLNode<T> y = x.right;
        AVLNode<T> T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // --- INSERTION ---
    public void insert(T data) {
        root = insertRec(root, data);
    }

    private AVLNode<T> insertRec(AVLNode<T> node, T data) {
        if (node == null) {
            return new AVLNode<>(data);
        }

        int cmp = data.compareTo(node.data);
        if (cmp < 0) {
            node.left = insertRec(node.left, data);
        } else if (cmp > 0) {
            node.right = insertRec(node.right, data);
        } else {
            return node; // Duplicate entries ignored
        }

        node.height = 1 + max(height(node.left), height(node.right));
        int balance = getBalance(node);

        if (balance > 1 && data.compareTo(node.left.data) < 0)
            return rightRotate(node);

        if (balance < -1 && data.compareTo(node.right.data) > 0)
            return leftRotate(node);

        if (balance > 1 && data.compareTo(node.left.data) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && data.compareTo(node.right.data) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // --- DISPLAY (In-Order Traversal) ---
    public void display() {
        inOrderRec(root);
    }

    private void inOrderRec(AVLNode<T> node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.println(node.data);
            inOrderRec(node.right);
        }
    }
}