import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("noon"));
        assertFalse(palindrome.isPalindrome("ndns"));
    }

    @Test
    public void testIsPalindromeByOne() {
        CharacterComparator offByOne = new OffByOne();
        assertTrue(palindrome.isPalindrome("ab", offByOne));
        assertFalse(palindrome.isPalindrome("aA", offByOne));
        assertTrue(palindrome.isPalindrome("&%", offByOne));
        assertTrue(palindrome.isPalindrome("aab", offByOne));
    }

    @Test
    public void testIspalindromeByN() {
        CharacterComparator offByN = new OffByN(3);
        assertTrue(palindrome.isPalindrome("ad", offByN));
        assertTrue(palindrome.isPalindrome("a", offByN));
        assertTrue(palindrome.isPalindrome("add", offByN));
        assertFalse(palindrome.isPalindrome("aa", offByN));
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests("all", TestPalindrome.class);
    }
}
