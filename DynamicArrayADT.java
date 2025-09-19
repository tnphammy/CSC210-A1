/**
 * Interface describes the operations supported by a `DynamicArray`.
 * 
 * Arguments for building an Object:
 * public int length;
 */
public interface DynamicArrayADT<T> {
    /* GROUP 1: BASIC OPERATIONS */
    /**
     * Sets the value at the index to a specified value
     * @param index The index at which to change its value
     * @param item The new item after setting
     * @return The previous item (or, `null` if index was previously empty)
     * @throws IndexOutOfBoundsException for invalid indices
     */
    public T set(int index, T item) throws IndexOutOfBoundsException;

    /**
     * Gets the value from a given index of the Dynamic Array
     * @param index The specified index
     * @return The value at the given index in the Dynamic Array
     * @throws IndexOutOfBoundsException for invalid indices
     */
    public T get(int index) throws IndexOutOfBoundsException;

    /**
     * Returns the actual number of elements in a Dynamic Array
     * @return The size of the Dynamic Array
     */
    public int size();

    /* GROUP 2: ADD/REMOVE ELEMENTS*/
    /**
     * Adds an item to the end of the Dynamic Array
     * @param item The new item to be added
     */
    public void add(T item);

    /**
     * Adds an item to the Dynamic Array at the specified index
     * Shifts every item after the index to the right by 1
     * @param index The specific index
     * @param item The item to be added
     * @throws IndexOutOfBoundsException for invalid indices
     */
    public void add(int index, T item) throws IndexOutOfBoundsException;

    /**
     * Removes an item at a specified index
     * Shifts subsequent elements to the left
     * @param index The specific index
     * @return The element previously at that index
     * @throws IndexOutOfBoundsException - If the index is less than zero, equal to the size of the list or greater than the size of the list.
     */
    public T remove(int index) throws IndexOutOfBoundsException;

    /* GROUP 3: WHOLE-ARRAY OPERATIONS*/
    /**
     * Concatenates two Dynamic Arrays
     * @param secondDA The second Dynamic Array
     * @return The resulting Dynamic Array Object
     */ 
    public DynamicArray<T> append(DynamicArray<T> secondDA) ;
    
    /**
     * Inserts new Dynamic Array into current `DynamicArray` at a specified index 
     * @param index The specific index to for insertion
     * @param newDA The Dynamic Array to be inserted
     * @return The resulting Dynamic Array Object
     * @throws IndexOutOfBoundsException for invalid indices
     */
    public DynamicArray<T> insert(int index, DynamicArray<T> newDA) throws IndexOutOfBoundsException;

    /**
     * Returns a view of the current `DynamicArray` in a specified range
     * @param fromIndex The starting index (Inclusive)
     * @param toIndex The ending index (Exclusive)
     * @return A copy containing the items of the `DynamicArray` within the range
     * @throws IndexOutOfBoundsException for invalid indices
     * @throws IllegalArgumentException  if starting index is larger than ending
     *                                   index
     */
    public DynamicArray<T> sublist(int fromIndex, int toIndex) throws IndexOutOfBoundsException, IllegalArgumentException;

    /**
     * Returns the elements before a specified index as a new `DynamicArray`.
     * @param toIndex The ending index (Exclusive)
     * @return A copy containing the items of the `DynamicArray` within the range
     * @throws IndexOutOfBoundsException for invalid indices
     */
    public DynamicArray<T> splitPrefix(int toIndex) throws IndexOutOfBoundsException;

    /**
     * Returns the elements from a specified index and after as a new `DynamicArray`.
     * @param fromIndex The starting index (Inclusive)
     * @return A copy containing the items of the `DynamicArray` within the range
     * @throws IndexOutOfBoundsException for invalid indices
     */
    public DynamicArray<T> splitSuffix(int fromIndex) throws IndexOutOfBoundsException;

    /**
     * Deletes items at a specified range in current `DynamicArray`
     * @param fromIndex The starting index (Inclusive)
     * @param toIndex The ending index (Exclusive)
     * @return A copy of the resulting Dynamic Array 
     * @throws IndexOutOfBoundsException for invalid indices
     * @throws IllegalArgumentException if the starting index is larger than the ending index
     */
    public DynamicArray<T> delete(int fromIndex, int toIndex) throws IndexOutOfBoundsException, IllegalArgumentException;

    /**
     * Creates a new Dynamic Array from the ç in a specified range
     * @param fromIndex The starting index (Inclusive)
     * @param toIndex The ending index (Exclusive)
     * @return A new Dynamic Array containing the items from the specified range
     * @throws IndexOutOfBoundsException for invalid indices
     * @throws IllegalArgumentException if the starting index is larger than the ending index
     */
    public DynamicArray<T> extract(int fromIndex, int toIndex) throws IndexOutOfBoundsException, IllegalArgumentException;
}
