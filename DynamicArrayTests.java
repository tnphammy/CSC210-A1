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

    // ~*~*~*~*~ Delete Tests Below ~*~*~*~*~
    @Test(expected = IllegalArgumentException.class)
    public void testDeleteBounds() {
        a1.delete(4,2);
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
     * Tests that ..
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
     * when called on invalid indices
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testExtractBounds() {
        // low index is negative => throws ArrayIndexOutOfBoundsException
        a1.extract(-1, 5);
        
        // high index is greater than array length => throws ArrayIndexOutOfBoundsException
        a1.extract(0, 7);

        // low index is greater than array length => throws ArrayIndexOutOfBoundsException
        a1.extract(7, 1);

        // high index is negative => throws ArrayIndexOutOfBoundsException
        a1.extract(0, -1);

        // high index is less than low
        a1.extract(3, 1);
    }

}
