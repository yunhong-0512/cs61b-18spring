import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        assertEquals(true, offByOne.equalChars('a', 'b'));
        assertEquals(true, offByOne.equalChars('b', 'a'));
        assertEquals(true, offByOne.equalChars('r', 'q'));
        assertEquals(false, offByOne.equalChars('a', 'a'));
        assertEquals(false, offByOne.equalChars('a', 'z'));
        assertEquals(false, offByOne.equalChars('a', 'A'));
        assertEquals(true, offByOne.equalChars('B', 'A'));
        assertEquals(true, offByOne.equalChars('A', 'B'));
        assertEquals(false, offByOne.equalChars('A', 'A'));
        assertEquals(false, offByOne.equalChars('Z', 'A'));
        assertEquals(false, offByOne.equalChars('%', 'a'));
        assertEquals(true, offByOne.equalChars('&', '%'));
    }
}
