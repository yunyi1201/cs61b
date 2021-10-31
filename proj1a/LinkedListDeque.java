public class LinkedListDeque<DataType> {

    public class Node {
        public DataType item;
        public Node prev;
        public Node succ;
    }

    private int size;
    private Node dummy;

    public LinkedListDeque() {
        this.size = 0;
        this.dummy = new Node();
        dummy.prev = dummy.succ = dummy;
    }

    public LinkedListDeque(DataType x) {
        this.size = 0;
        this.dummy = new Node();
        dummy.prev = dummy.succ = dummy;
        addFirst(x);
    }

    public void addFirst(DataType x) {
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

    public void addLast(DataType x) {
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

    public DataType removeFirst() {
        if (this.isEmpty()) {
            return null;
        } 
        this.size -= 1;
        Node deleteNode = this.dummy.succ;
        DataType returnValue = deleteNode.item;
        Node prev = deleteNode.prev;
        Node succ = deleteNode.succ;
        
        prev.succ = succ;
        succ.prev = prev;
        return returnValue;
    }

    public DataType removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        this.size -= 1;
        Node deleteNode = this.dummy.prev;
        DataType returnValue = deleteNode.item;
        Node prev = deleteNode.succ;
        Node succ = deleteNode.prev;
        
        prev.prev = succ;
        succ.succ = prev;
        return returnValue;
    }
    /*
    public DataType get(int index) {
        if (index >= this.size()) {
            return null;
        }


        
    }
    */
}
