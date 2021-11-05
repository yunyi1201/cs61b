package synthesizer;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T>  extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        //se TODO: Create new array with capacity elements.
                                                                    //       first, last, and fillCount should all be t to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.

        rb = (T []) new Object[capacity];
        this.capacity = capacity;
        this.fillCount = this.first = this.last = 0;
    }

    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator implements Iterator<T> {

        private int numbers;
        private int ptr;
        public ArrayRingBufferIterator() {
            numbers = 0;
            ptr = first;
        }

        public boolean hasNext() {
            return numbers < fillCount();
        }

        public T next() {
            ptr = (ptr + 1) % capacity;
            numbers += 1;
            return rb[ptr];
        }
    }
    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if (isFull()) {
            new RuntimeException("Ring buffer overflow");
        }

        if (x == null) {
            return;
        }

        last = (last + 1) % capacity;
        rb[last] = x;
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if (isEmpty()) {
            new RuntimeException("Ring buffer underflow");
        }

        first = (first + 1) % capacity;
        fillCount -= 1;
        T returnItem = rb[first];
        rb[first] = null;
        return returnItem;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        if (isEmpty()) {
            new RuntimeException("Ring buffer no items");
        }

        int index = (first + 1) % capacity;
        return rb[index];
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
}
