package ua.edu.ucu.queue;

public class ImmutableLinkedList implements ImmutableList {
    private Node first;
    private int length;

    private static class Node {
        private Object data;
        private Node next;

        public Node() {
            this.data = null;
            this.next = null;
        }

        public Node(Object data) {
            this.data = data;
            this.next = null;
        }

        public void setNextNode(Node nxtNode) {
            this.next = nxtNode;
        }
    }

    public ImmutableLinkedList() {
        this.first = new Node();
        this.length = 0;
    }

    public ImmutableLinkedList(Object element) {
        this.first = new Node(element);
        this.length = 1;
    }

    public ImmutableLinkedList(Object[] elements) {
        this.first = new Node(elements[0]);
        this.length = 1;
        Node curNode = this.first;
        for (int i = 1; i < elements.length; i++) {
            Node newNode = new Node(elements[i]);
            curNode.setNextNode(newNode);
            this.length += 1;
            curNode = newNode;
        }
    }

    @Override
    public ImmutableLinkedList add(Object e) {
        Object[] element = {e};
        return addAll(this.length, element);
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        Object[] element = {e};
        return addAll(index, element);
    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        return addAll(this.length, c);
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        if (index > this.length) {
            throw new IndexOutOfBoundsException();
        }
        Object[] finArray = new Object[this.length + c.length];
        Node curNode = this.first;
        for (int i = 0; i < index; i++) {
            finArray[i] = curNode.data;
            curNode = curNode.next;
        }
        int counter = 0;
        for (int i = index; i < index + c.length; i++) {
            finArray[i] = c[counter];
            counter += 1;
        }
        for (int i = index + c.length; i < this.length + c.length; i++) {
            finArray[i] = curNode.data;
            curNode = curNode.next;
        }
        ImmutableLinkedList expandedLinList = new ImmutableLinkedList(finArray);
        return expandedLinList;
    }


    @Override
    public Object get(int index) {
        if (index > this.length) {
            throw new IndexOutOfBoundsException();
        }
        return toArray()[index];
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        if (index > this.length) {
            throw new IndexOutOfBoundsException();
        }
        Object[] finArray = new Object[this.length - 1];
        System.arraycopy(toArray(), 0, finArray, 0, index);
        System.arraycopy(toArray(), index + 1,
                finArray, index, this.length - 1 - index);
        return new ImmutableLinkedList(finArray);
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        if (index > this.length) {
            throw new IndexOutOfBoundsException();
        }
        Object[] finArray = new Object[this.length];
        System.arraycopy(toArray(), 0, finArray, 0, index);
        finArray[index] = e;
        System.arraycopy(toArray(), index + 1,
                finArray, index + 1, this.length - 1 - index);
        return new ImmutableLinkedList(finArray);
    }

    @Override
    public int indexOf(Object e) {
        Node curNode = this.first;
        for (int i = 0; i < this.length; i++) {
            if (curNode.data == e) {
                return i;
            }
            curNode = curNode.next;
        }
        return -1;
    }

    @Override
    public int size() {
        return this.length;
    }

    @Override
    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return this.length == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[length];
        Node curNode = this.first;
        for (int i = 0; i < this.length; i++) {
            array[i] = curNode.data;
            curNode = curNode.next;
        }
        return array;
    }

    public ImmutableLinkedList addFirst(Object e) {
        return add(0, e);
    }

    public ImmutableLinkedList addLast(Object e) {
        return add(this.length, e);
    }

    public Object getFirst() {
        return toArray()[0];
    }

    public Object getLast() {
        return toArray()[this.length - 1];
    }

    public ImmutableLinkedList removeFirst() {
        return remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return remove(this.length - 1);
    }

    public String toString() {
        StringBuffer arrayString = new StringBuffer();
        for (Object element : toArray()) {
            if (element == toArray()[this.length - 1]) {
                arrayString.append(element);
            } else {
                arrayString.append(element + ", ");
            }
        }
        return arrayString.toString();
    }
}
