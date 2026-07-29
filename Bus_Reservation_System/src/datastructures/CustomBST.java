package datastructures;

public class CustomBST<T extends Comparable<T>> {

    private BSTNode<T> root;

    public CustomBST() {
        root = null;
    }

    // Display method delegating to your inorder traversal
    public void display() {
        if (root == null) {
            System.out.println("  [Empty BST]");
            return;
        }
        inorder();
    }

    // Insert
    public void insert(T data) {
        root = insertRecursive(root, data);
    }

    private BSTNode<T> insertRecursive(BSTNode<T> node, T data) {
        if(node == null) {
            return new BSTNode<>(data);
        }

        if(data.compareTo(node.getData()) < 0) {
            node.setLeft(insertRecursive(node.getLeft(), data));
        } else if(data.compareTo(node.getData()) > 0) {
            node.setRight(insertRecursive(node.getRight(), data));
        }

        return node;
    }

    // Search
    public T search(T data) {
        BSTNode<T> result = searchRecursive(root, data);
        if(result == null) {
            return null;
        }
        return result.getData();
    }

    private BSTNode<T> searchRecursive(BSTNode<T> node, T data) {
        if(node == null) {
            return null;
        }

        int comparison = data.compareTo(node.getData());

        if(comparison == 0) {
            return node;
        } else if(comparison < 0) {
            return searchRecursive(node.getLeft(), data);
        } else {
            return searchRecursive(node.getRight(), data);
        }
    }

    // Inorder traversal
    public void inorder() {
        inorderRecursive(root);
    }

    private void inorderRecursive(BSTNode<T> node) {
        if(node != null) {
            inorderRecursive(node.getLeft());
            System.out.println(node.getData());
            inorderRecursive(node.getRight());
        }
    }
}