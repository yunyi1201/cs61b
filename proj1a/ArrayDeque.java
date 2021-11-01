public class ArrayDeque<T> {

    private T[] item;
    private static final int defaultCapacity = 8;
    private int head;
    private int tail;    
    private int size;
    private int capacity;

    public ArrayDeque() {
        item = (T []) new Object[defaultCapacity];
        capacity = defaultCapacity;
        size = head = tail = 0;
    }

    private void doublesize(int resize) {
        T[] temp = (T []) new Object[resize];
        int p = (tail + 1) % capacity;
        int n = capacity;
        int r = n - p;
        capacity = resize;
        System.arraycopy(item, p, temp, 0, r);     
        System.arraycopy(item, 0, temp, r, p);
        item = temp;
        tail = capacity - 1;
        head = size;       
    }

    private void downsize(int resize) {
        T[] temp = (T []) new Object[resize];
        int oldCapacity = capacity;
        capacity = resize;
        if (tail < head) {
            System.arraycopy(item, tail + 1, temp, 0, size);     
        } else {
            int rightSize = oldCapacity - tail - 1;
            System.arraycopy(item, tail + 1, temp, 0, rightSize);
            System.arraycopy(item, 0, temp, rightSize, size - rightSize);
        }
        item = temp;
        tail = capacity - 1;
        head = size;
    }

    /* actual capacity = capacity - 1 */
    public void addFirst(T x) {
        if (size == capacity - 1) {
            doublesize(capacity * 2);
        } 
        if (head == tail) {
            tail = (tail - 1 + capacity) % capacity;
        }
        item[head] = x;
        head = (head + 1) % capacity;
        size += 1;
    }

    public void addLast(T x) {
        if (size == capacity - 1) {
            doublesize(capacity * 2);
        }
        if (head == tail) {
            head = (head + 1) % capacity;
        }
        item[tail] = x;
        tail = (tail - 1 + capacity) % capacity;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int start = head;
        for(int i = 0; i < size; i++) {
            start = (start - 1 + capacity) % capacity;
            System.out.println(item[start]);
        }
    }
    
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        head = (head - 1 + capacity) % capacity;
        T res = item[head];
        item[head] = null; 
        size -= 1;
        
        /* resize */
        if (capacity >= 16 && size < (capacity / 4)) {
            downsize(capacity / 2);
        }

        return res;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        tail = (tail + 1) % capacity;
        T res = item[tail];
        item[tail] = null;
        size -= 1;

        /* resize */
        if (capacity >= 16 && size < (capacity / 4)) {
            downsize(capacity / 2);
        }

        return res;
    }

    public T get(int index) {
        if (index >= size) {
            return null;    
        }
        index = ((head - (index + 1)) + capacity) % capacity;
        return item[index];
    }

}
