public class BinaryTree<E extends Comparable<E>> {
    private Node<E> root;

    private static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;

        Node(E element) {
            this.element = element;
        }
    }

    public void insert(E element) {
        root = insertRec(root, element);
    }

    private Node<E> insertRec(Node<E> root, E element) {
        if (root == null) {
            root = new Node<>(element);
            return root;
        }

        if (element.compareTo(root.element) < 0) {
            root.left = insertRec(root.left, element);
        } else if (element.compareTo(root.element) > 0) {
            root.right = insertRec(root.right, element);
        }

        return root;
    }

    public E search(E toFind) {
        return searchRec(root, toFind);
    }

    private E searchRec(Node<E> root, E toFind) {
        if (root == null || root.element.compareTo(toFind) == 0) {
            return root != null ? root.element : null;
        }

        if (root.element.compareTo(toFind) > 0) {
            return searchRec(root.left, toFind);
        } else {
            return searchRec(root.right, toFind);
        }
    }
}
