import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    static CharacterComparator offBy3 = new OffByN(3);

    @Test
    public void testEqualChars() {
        assertTrue(offBy3.equalChars('a','d'));
        assertFalse(offBy3.equalChars('a','a'));
        assertFalse(offBy3.equalChars('a','A'));
    }

    public static void main(String[] args) {
       jh61b.junit.TestRunner.runTests("all", TestOffByN.class);
    }
}
