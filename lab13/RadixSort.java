import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        String[] sorted = new String[asciis.length];
        int maxLen = 0;
        for (int i = 0; i < asciis.length; i++) {
            maxLen = maxLen > asciis[i].length() ? maxLen : asciis[i].length();
            sorted[i] = asciis[i];
        }

        for (int i = maxLen - 1; i >= 0; i--) {
            sortHelperLSD(sorted, i);
        }
        return sorted;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        Queue<String>[] buckets = new Queue[256];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<String>();
        }
        for (String str : asciis) {
            if (str.length() > index) {
                buckets[str.charAt(index)].offer(str);
            } else {
                buckets[0].offer(str);
            }

        }

        int j = 0;
        for (int i = 0; i < buckets.length; i++) {
            while (buckets[i].size() != 0) {
                asciis[j] = buckets[i].poll();
                j++;
            }
        }
        return;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    /*
    public static void main(String[] args) {
        String[] test = {"destructive", "method", "that", "changes", "the", "passed", "in", "array"};
        String[] sorted = RadixSort.sort(test);
        for (String str : sorted) {
            System.out.print(str + " ");
        }
    }
     */
}
