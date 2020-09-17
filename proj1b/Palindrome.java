public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new LinkedListDeque<>();
        if (word == null) {
            return res;
        }
        for (int i = 0; i < word.length(); i++) {
            res.addLast(word.charAt(i));
        }
        return res;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        return recursionHelper(wordDeque);
    }

    private boolean recursionHelper(Deque<Character> d) {
        if (d == null || d.size() == 0 || d.size() == 1) {
            return true;
        }
        return (d.removeFirst() == d.removeLast()) && recursionHelper(d);
    }

    /**
     * Override isPalindrome
     * @param word
     * @param cc
     * @return
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        return recursionHelper(wordDeque, cc);
    }

    private boolean recursionHelper(Deque<Character> d, CharacterComparator cc) {
        if (d == null || d.size() == 0 || d.size() == 1) {
            return true;
        }
        return cc.equalChars(d.removeFirst(), d.removeLast()) && recursionHelper(d, cc);
    }
}
