package synthesizer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void TestArrayRingBuffer() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        assertEquals(10, arb.capacity());
        assertEquals(0, arb.fillCount());
        arb.enqueue(10);
        arb.enqueue(11);
        int firstItem = arb.peek();
        assertEquals(10, firstItem);
        assertEquals(2, arb.fillCount());
        int deleteItem = arb.dequeue();
        assertEquals(10, deleteItem);
        assertEquals(1, arb.fillCount());
    }

    @Test
    public void TestArrayRingBufferIterator() {
        AbstractBoundeQueue<Integer> arb = new ArrayRingBuffer<>(10);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);

        Iterator iter = arb.iterator();

        assertEquals(1,(int)iter.next());
        assertEquals(2, (int)iter.next());
        assertEquals(3, (int)iter.next());

    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
