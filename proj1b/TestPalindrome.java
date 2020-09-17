import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
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
        assertEquals(true, palindrome.isPalindrome("noon"));
        assertEquals(true, palindrome.isPalindrome("n"));
        assertEquals(true, palindrome.isPalindrome("nolon"));
        assertEquals(true, palindrome.isPalindrome("racecar"));
        assertEquals(true, palindrome.isPalindrome(""));
        assertEquals(true, palindrome.isPalindrome(null));
        assertEquals(false, palindrome.isPalindrome("cat"));
        assertEquals(false, palindrome.isPalindrome("Noon"));
        assertEquals(false, palindrome.isPalindrome("catc"));
        assertEquals(true, palindrome.isPalindrome("NooN"));

        OffByOne onecc = new OffByOne();
        assertEquals(true, palindrome.isPalindrome("flake", onecc));
        assertEquals(true, palindrome.isPalindrome("n", onecc));
        assertEquals(true, palindrome.isPalindrome("", onecc));
        assertEquals(true, palindrome.isPalindrome("flke", onecc));
        assertEquals(true, palindrome.isPalindrome(null, onecc));
        assertEquals(false, palindrome.isPalindrome("noon", onecc));
        assertEquals(false, palindrome.isPalindrome("Flake", onecc));
    }

}
