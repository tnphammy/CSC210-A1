import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DynamicArrayTests {

    private DynamicArray<Character> a1;
    private DynamicArray<Character> a2;
    private DynamicArray<Character> empty;
    private DynamicArray<Character> s;

    /**
     * Initializes DynamicArray<Character> instances to be used for testing.
     * Re-initializes before each test.
     * This ensures that tests do not interfere with one another.
     */
    @Before
    public void setUp() {
        a1 = stringToArray("abcdef");
        a2 = stringToArray("wxyz");
        empty = stringToArray("");
        s = stringToArray("s");
    }

    /**
     * Puts the characters of a string into an array structure
     */
    public DynamicArray<Character> stringToArray(String s) {
        DynamicArray<Character> result = new DynamicArray<Character>(s.length());
        for (int i = 0; i < s.length(); i++) {
            result.add(i, s.charAt(i));
        }
        return result;
    }

    /**
     * Compares the sizes of a DynamicArray<Character> and a string
     */
    public void compareSize(DynamicArray<Character> arr, String s) {
        assertEquals("[" + s + "] Array lengths are equal", arr.size(), s.length());
    }

    /**
     * Compares each element in a DynamicArray<Character>
     * against those in a string.
     */
    public void compareToString(DynamicArray<Character> arr, String s) {
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i).charValue() + " " + s.charAt(i));
            assertEquals("[" + s + "] Elements are equal at index " + i, arr.get(i).charValue(), s.charAt(i));
        }
    }

    // ~*~*~*~*~ Get Tests Below ~*~*~*~*~
    /**
     * Tests that getting one element from an array retrieves the correct element
     */
    @Test
    public void testGetStandard() {
        Character actualChar = (Character) a1.get(0);
        assertEquals("a", String.valueOf(actualChar));
    }

    /**
     * Tests that getting an inaccessible element throws an Exception
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetBounds() {
        empty.get(0);
    }

    // ~*~*~*~*~ Add Tests Below ~*~*~*~*~
    /**
     * Tests that adding one element adds it to the end of the array
     */
    @Test
    public void testStandardSingleAdd() {
        a1.add('g');
        compareToString(a1, "abcdefg");
    }

    /**
     * Tests that adding an item with a specified index adds it into the array at
     * the correct spot
     */
    @Test
    public void testIndexedAdd() {
        a1.add(0, 'z');
        compareToString(a1, "zabcdef");
    }

    /**
     * Tests that the adding an item to an index that
     * isn't accessible throws the corresponding error
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddBounds() {
        a1.add(6, 'z');
    }

    // ~*~*~*~*~ Insert Tests Below ~*~*~*~*~
    /**
     * Tests that inserting an empty DynamicArray into another results in
     * a new DynamicArray that is the same as the non-empty one
     */
    @Test
    public void testInsertEmpty() {
        compareToString(a1.insert(0, empty), "abcdef");
    }

    /**
     * Tests that inserting a non-empty array into itself
     * will result in a new array twice as long, with one part being the full
     * original array
     * and on either side, the elements corresponding to where the index was
     * specified
     */
    @Test
    public void testInsertSelf() {
        compareToString(a1.insert(0, a1), "abcdefabcdef");
    }
    
    /**
     * Tests that inserting an array at internal index (not lowIndex or highIndex)
     * results in a new array which contains the original array with
     * the inserted array at the correct placement
     */
    @Test
    public void testInsertMiddle() {
        compareToString(a1.insert(3, s), "abcsdef");
    }

    /**
     * Tests that inserting an array to the end of an array
     * would create an array with the original array,
     * with the new array attached right after it
     */
    @Test
    public void testInsertEnd() {
        compareToString(a1.insert(6, a1), "abcdefabcdef");
    }

    // ~*~*~*~*~ Delete Tests Below ~*~*~*~*~
    /**
     * Tests that deleting from a higher to a lower index throws the right exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDeleteBounds() {
        // fromIndex is larger than toIndex
        a1.delete(4, 2);
        // toIndex is negative
        a1.delete(0, -2);
    }

    /**
     * Tests that deleting from the lowest index to the length
     * results in an empty array
     */
    @Test
    public void testDeleteEntire() {
        compareToString(a1.delete(0, 6), "");
    }

    /**
     * Tests that deleting with the final index and length
     * will result in the original array missing the final element
     */
    @Test
    public void testDeleteEnd() {
        compareToString(a1.delete(5, 6), "abcde");
    }

    /**
     * Tests that deleting to and from the same index deletes nothing
     * and returns a new array identical to the original array
     */
    @Test
    public void testDeleteZero() {
        compareToString(a1.delete(6, 6), "abcdef");
    }

    // ~*~*~*~*~ Split Tests Below ~*~*~*~*~
    /**
     * Tests that spliting up until the end of an array results in the entire array
     */
    @Test
    public void testSplitPrefixAtEnd() {
        compareToString(a1.splitPrefix(6), "abcdef");
    }

    /**
     * Tests that splitting suffix from the end returns an empty array
     */
    @Test
    public void testSplitSuffixAtEnd() {
        compareSize(a1.splitSuffix(6), "");
    }

    // ~*~*~*~*~ Append Tests Below ~*~*~*~*~
    
    /**
     * Tests that appending two non-empty arrays results in
     * a new array containing the elements of both, in order.
     */
    @Test
    public void testAppendStandard() {
        compareToString(a1.append(a2), "abcdefwxyz");
        compareToString(a2.append(a1), "wxyzabcdef");
    }

    /**
     * Tests that appending a non-empty array to itself results in
     * a new array containing the elements repeated twice.
     */
    @Test
    public void testAppendSelf() {
        compareToString(a1.append(a1), "abcdefabcdef");
        compareToString(a2.append(a2), "wxyzwxyz");
    }

    /**
     * Tests that appending a non-empty array and an array of
     * length one results in a new array containing the elements
     * of both, in order.
     */
    @Test
    public void testAppendSingle() {
        compareToString(a1.append(s), "abcdefs");
        compareToString(s.append(a1), "sabcdef");
        compareToString(s.append(s), "ss");
    }

    /**
     * Tests that appending an empty array
     * results in a new array that matches the other array
     */
    @Test
    public void testAppendEmpty() {
        compareToString(a1.append(empty), "abcdef");
        compareToString(empty.append(a1), "abcdef");
        compareToString(empty.append(empty), "");
    }

    // ~*~*~*~*~ Add Extract Tests Below ~*~*~*~*~

    /**
     * Tests that the result of extracting indices within an array is the correct
     * window
     */
    @Test
    public void testExtractStandard() {
        DynamicArray<Character> extractedA1 = a1.extract(2, 4);
        compareToString(extractedA1, "cd");
    }

    //
    /**
     * Tests that the result of extracting an entire array matches the original
     * array
     */
    @Test
    public void testExtractEntire() {
        DynamicArray<Character> extractedA1 = a1.extract(0, 6);
        compareToString(extractedA1, "abcdef");

    }

    /**
     * Tests that extracting from and to the same index gets an empty array
     */
    @Test
    public void testExtractZero() {
        DynamicArray<Character> extractedA1 = a1.extract(6, 6);
        compareToString(extractedA1, "");
    }

    /**
     * Tests that ..
     */
    @Test
    public void testExtractEmpty() {
        DynamicArray<Character> emptyArray = empty.extract(0, 0);
        compareToString(emptyArray, "");

    }

    /**
     * Tests that extract throws the proper exception
     * when called on a toIndex that is higher than the fromIndex
     */
    @Test (expected = IllegalArgumentException.class)
    public void testExtractInvalid() {
        a1.extract(8, 0);
    }

    /**
     * Tests that extract throws the proper exception
     * when called on invalid indices
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testExtractBounds() {
        // low index is negative => throws ArrayIndexOutOfBoundsException
        a1.extract(-1, 5);

        // high index is greater than array length => throws
        // ArrayIndexOutOfBoundsException
        a1.extract(0, 7);

        // low index is greater than array length => throws
        // ArrayIndexOutOfBoundsException
        a1.extract(7, 1);

        // high index is negative => throws ArrayIndexOutOfBoundsException
        a1.extract(0, -1);

        // high index is less than low
        a1.extract(3, 1);
    }

}
