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
        // TODO: Implement LSD Sort
        int max = Integer.MIN_VALUE;
        for (String s : asciis) {
            if (s.length() > max) {
                max = s.length();
            }
        }

        String[] toSort = asciis.clone();
        for (int  d = 1; d <= max; d++) {
            sortHelperLSD(toSort, d);
        }

        return toSort;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int[] count = new int[256];

        for (String s : asciis) {
            if (s.length() < index) {
                int i = (int) s.charAt(0);
                count[i]++;
            } else {
                int pos = s.length() - index;
                int i = (int) s.charAt(pos);
                count[i]++;
            }
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        String[] tmp = asciis.clone();
        for (int i = asciis.length - 1; i >= 0; i--) {
            String current = tmp[i];
            int pos = current.length() - index;
            if (pos < 0) {
                int dig = (int) current.charAt(0);
                asciis[--count[dig]] = current;
            } else {
                int dig = (int) current.charAt(pos);
                asciis[--count[dig]] = current;
            }

            if (i == 0) {
                tmp = asciis.clone();
            }
        }
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
}
