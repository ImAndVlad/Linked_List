package app;

import java.util.*;

public class Linked_List extends Product implements List<Product>, Deque<Product> {

    static class Node<Product> {
        Product product;
        Node<Product> next;
        Node<Product> prev;

        public Node(Product product, Node<Product> next, Node<Product> prev) {
            this.product = product;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<Product> first;
    private Node<Product> last;
    private int size;

    private void linkFirst(Product product) {
        Node<Product> f = first;
        Node<Product> newNode = new Node<>(product, f, null);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;
    }

    void linkLast(Product product) {
        Node<Product> l = last;
        Node<Product> newNode = new Node<>(product, null, l);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void addFirst(Product product) {
        linkFirst(product);
    }

    @Override
    public void addLast(Product product) {
        linkLast(product);
    }

    @Override
    public boolean offerFirst(Product product) {
        addFirst(product);
        return true;
    }

    @Override
    public boolean offerLast(Product product) {
        addLast(product);
        return true;
    }

    private Product unlinkFirst(Node<Product> f) {
        Product element = f.product;
        Node<Product> next = f.next;
        f.product = null;
        f.next = null; // help GC
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        return element;
    }

    @Override
    public Product removeFirst() {
        Node<Product> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return unlinkFirst(f);
    }

    private Product unlinkLast(Node<Product> l) {
        Product element = l.product;
        Node<Product> prev = l.prev;
        l.product = null;
        l.prev = null; // help GC
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
        return element;
    }

    @Override
    public Product removeLast() {
        Node<Product> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return unlinkLast(l);
    }

    @Override
    public Product pollFirst() {
        Node<Product> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }

    @Override
    public Product pollLast() {
        Node<Product> l = last;
        return (l == null) ? null : unlinkLast(l);
    }

    @Override
    public Product getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return get(0);
    }

    @Override
    public Product getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return get(size - 1);
    }

    @Override
    public Product peekFirst() {
        Node<Product> f = first;
        return (f == null) ? null : f.product;
    }

    @Override
    public Product peekLast() {
        Node<Product> l = last;
        return (l == null) ? null : l.product;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean offer(Product product) {
        return add(product);
    }

    @Override
    public Product remove() {
        return removeFirst();
    }

    @Override
    public Product poll() {
        return null;
    }

    @Override
    public Product element() {
        return getFirst();
    }

    @Override
    public Product peek() {
        return null;
    }

    @Override
    public void push(Product product) {

    }

    @Override
    public Product pop() {
        return null;
    }

    @Override
    public Iterator<Product> descendingIterator() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public boolean contains(Object o) {
        Node<Product> current = first;
        for (int i = 0; i < size; i++) {
            if (current.product.equals(o)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public Iterator<Product> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Product product) {
        linkLast(product);
        return true;
    }

    Product unlink(Node<Product> x) {
        // assert x != null;
        Product element = x.product;
        Node<Product> next = x.next;
        Node<Product> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.product = null;
        size--;
        return element;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<Product> x = first; x != null; x = x.next) {
                if (x.product == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<Product> x = first; x != null; x = x.next) {
                if (o.equals(x.product)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Product> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Product> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        first = last = null;
        size = 0;
    }

    @Override
    public Product get(int index) {
        Objects.checkIndex(index, size);
        return getNodeByIndex(index).product;
    }

    @Override
    public Product set(int index, Product element) {
        Objects.checkIndex(index, size);
        Node<Product> node = getNodeByIndex(index);
        node.product = element;

        return element;
    }

    private Node<Product> getNodeByIndex(int index) {
        Node<Product> current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    @Override
    public void add(int index, Product element) {
        Objects.checkIndex(index, size + 1);
        Node<Product> newNode = new Node<>(element, null, null);
        if (first == null) {
            first = last = newNode;
        } else if (index == 0) {
            newNode.next = first;
            first = newNode;
        } else if (index == size) {
            last.next = newNode;
            last = newNode;
        } else {
            Node<Product> prev = getNodeByIndex(index - 1);
            newNode.next = prev.next;
            prev.next = newNode;
        }
        size++;
    }

    @Override
    public Product remove(int index) {
        Objects.checkIndex(index, size);
        Product removeProduct;
        if (index == 0) {
            removeProduct = first.product;
            first = first.next;
            if (first == null) {
                last = null;
            }
        } else {
            Node<Product> prev = getNodeByIndex(index - 1);
            removeProduct = prev.next.product;
            prev.next = prev.next.next;
            if (index == size - 1) {
                last = prev;
            }
        }
        size--;
        return removeProduct;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<Product> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Product> listIterator(int index) {
        return null;
    }

    @Override
    public List<Product> subList(int fromIndex, int toIndex) {
        return null;
    }
}
