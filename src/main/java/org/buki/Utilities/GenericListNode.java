package org.buki.Utilities;

public class GenericListNode<T extends Comparable<T>> {

    private class Node<T> {
        T value;
        Node<T> next;
    }

    private Node<T> head = null;
    private Node<T> next = null;
    int count = 1;

    public T getHead() {
        if (head != null && head.value != null) {
            return head.value;
        } else {
            return null;
        }

    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public void addNode(T e) {
        Node<T> node = new Node<T>();
        node.value = e;
        node.next = null;
        if (head == null) {
            head = node;
            next = head;
        } else {
            next.next = node;

            next = next.next;
        }
        count++;
        sort(head, head.next);
//		for(int i = 1; i <= count; i++) {
//			System.out.println(getNode(i) + " ");
//		}
    }

    // Do it
    public T getNode(int pos) {
        Node<T> next;
        if (pos == 1) {
            return getHead();
        } else {
            next = head.next;
        }
        for (int i = 2; i < pos; i++) {
            next = next.next;

        }
        if (next == null) {
            return null;
        } else {
            return next.value;

        }
    }

    public void sort(Node<T> posNode, Node<T> next) {
        if (next != null) {
            if (posNode.value.compareTo(next.value) <= 0) {
                sort(next, next.next);
                if (posNode.value.compareTo(next.value) > 0) {
                    T temp = posNode.value;
                    posNode.value = next.value;
                    next.value = temp;
                }
            } else {
                T temp = posNode.value;
                posNode.value = next.value;
                next.value = temp;
            }
        }
    }

    public void deleteNode(T e) {
        Node<T> posNode = head;
        Node<T> node = new Node();
        while (posNode != null && !posNode.value.equals(e)) {
            node = posNode;
            posNode = posNode.next;
        }
        if (posNode != null && posNode == head) {
            if (head.next != null) {
                head = head.next;
                next = null;
            } else {
                head = null;
            }
            count--;
        } else if (posNode != null && posNode == next) {
            next = node;
            if (next == head) {
                next = null;
                head.next = null;
            }
            posNode = null;
            count--;
        } else if (posNode != null) {
            node.next = posNode.next;
            posNode = null;
            count--;
        }

    }

    public int size() {
        return count;
    }

}
