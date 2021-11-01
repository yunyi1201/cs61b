public class LinkedListDeque<T> {

    private class Node {
        private T item;
        private Node prev;
        private Node succ;
    }

    private int size;
    private Node dummy;

    public LinkedListDeque() {
        this.size = 0;
        this.dummy = new Node();
        dummy.prev = dummy.succ = dummy;
    }

    public void addFirst(T x) {
        Node temp = new Node();
        temp.item = x;
        Node prev = this.dummy;
        Node succ = this.dummy.succ; 

        prev.succ = temp;
        temp.prev = prev;

        succ.prev = temp;
        temp.succ = succ;
        this.size += 1;
    }

    public void addLast(T x) {
        Node temp = new Node();
        temp.item = x;
        Node prev = this.dummy.prev;
        Node succ = this.dummy;
        
        prev.succ = temp;
        temp.prev = prev;

        succ.prev = temp;
        temp.succ = succ;
        this.size += 1;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        for(Node p = this.dummy.succ; p != this.dummy; p = p.succ) {
            System.out.println(p.item);
        }
    }

    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        } 
        this.size -= 1;
        Node deleteNode = this.dummy.succ;
        T returnValue = deleteNode.item;
        Node prev = deleteNode.prev;
        Node succ = deleteNode.succ;
        
        prev.succ = succ;
        succ.prev = prev;
        return returnValue;
    }

    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        this.size -= 1;
        Node deleteNode = this.dummy.prev;
        T returnValue = deleteNode.item;
        Node prev = deleteNode.succ;
        Node succ = deleteNode.prev;
        
        prev.prev = succ;
        succ.succ = prev;
        return returnValue;
    }
    
    public T get(int index) {
        if (index >= this.size()) {
            return null;
        }
        T returnValue;
        if (index < this.size() / 2) {
            Node start = this.dummy.succ;
            while(index > 0) {
                start = start.succ;
                index -= 1;
            }
            returnValue = start.item;
        } else {
            index = size - index - 1;
            Node start = this.dummy.prev;
            while(index > 0) {
                start = start.prev;
                index -= 1;
            }
            returnValue = start.item;
        }
        return returnValue;
    }

    private T getRecursiveHelper(Node n, int index) {
        if (index == 0) {
            return n.item;
        } else {
            return getRecursiveHelper(n.succ, index-1);
        }
    }

    public T getRecursive(int index) {
        if (index == 0) {
            return this.dummy.succ.item;
        }
        return getRecursiveHelper(this.dummy.succ, index);
    }
}
