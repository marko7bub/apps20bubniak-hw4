package ua.edu.ucu.queue;

import ua.edu.ucu.queue.ImmutableLinkedList;


public class Queue {
    private ImmutableLinkedList queue;

    public Queue() {
        queue = new ImmutableLinkedList();
    }

    public Queue(Object e) {
        queue = new ImmutableLinkedList(e);
    }

    public Queue(Object[] elements) {
        queue = new ImmutableLinkedList(elements);
    }

    public Object peek() {
        return queue.getLast();
    }

    public void enqueue(Object e) {
        queue = queue.addFirst(e);
    }

    public Object dequeue() {
        Object element = queue.getLast();
        queue = queue.removeLast();
        return element;
    }

    public Object[] toArray() {
        return queue.toArray();
    }

}
