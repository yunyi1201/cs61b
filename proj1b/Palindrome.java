public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        if (word.length() == 0) {
            return null;
        }
        Deque<Character> res = new LinkedListDeque<>();
        for(int i = 0; i < word.length(); i += 1) {
            res.addLast(word.charAt(i));
        }
        return res;
    }

    private static boolean isPalindromeRecursionHelper(Deque<Character> word) {
        if (word.size() < 2) {
            return true;
        }
        char head = word.removeFirst();
        char tail = word.removeLast();
        return (head == tail) && isPalindromeRecursionHelper(word);
    }

    public boolean isPalindrome(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        Deque<Character> d = wordToDeque(word);
        return isPalindromeRecursionHelper(d);
    }

    private static boolean isPalindromeRecursionHelperByOffOne(Deque<Character> word, CharacterComparator cc) {
        if (word.size() < 2) {
            return true;
        }
        char head = word.removeFirst();
        char tail = word.removeLast();
        return cc.equalChars(head, tail) && isPalindromeRecursionHelper(word);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        Deque<Character> d = wordToDeque(word);
        return isPalindromeRecursionHelperByOffOne(d, cc);
    }
}