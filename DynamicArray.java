/**
 * This Class is based on Java's built-in Array,
 * adds functionality to it, similar to that of Java ArrayLists
 */
public class DynamicArray<T> implements DynamicArrayADT<T> {
    /* ATTRIBUTES */
    int length;
    T[] storage;
    int low; // lowest index
    int high; // highest index
    String indexErrorMessage = "Invalid index.";

    /* CONSTRUCTORS */
    /**
     * Basic constructor to make Dynamic Array of a specific size/length
     * 
     * @param length The specified length
     */
    public DynamicArray(int length) {
        this.length = length;
        this.storage = allocate(length);
        this.low = 0; // By default, the lowest index is 0
        this.high = -1; // In the beginning, the highest index is -1
    }

    /**
     * Basic constructor to make Dynamic Array of a specific length
     * 
     * @param lowIndex  The lowest index
     * @param highIndex The highest index
     */
    public DynamicArray(int lowIndex, int highIndex) {
        this.storage = allocate(highIndex - lowIndex + 1);
        this.low = lowIndex;
        this.high = highIndex;
    }

    /**
     * Creates a view of an existing `DynamicArray`
     * 
     * @param original The original `DynamicArray`
     * @param newStart The starting index of the copy
     * @param newEnd   The ending index of the copy
     */
    public DynamicArray(DynamicArray<T> original, int newStart, int newEnd) {
        this.storage = original.storage;
        this.length = original.length;
        this.low = newStart;
        this.high = newEnd;
    }

    /**
     * Makes a deep copy of an existing Dynamic Array
     * 
     * @param original The original Dynamic Array
     */
    public DynamicArray(DynamicArray<T> original) {
        this.length = original.length;
        this.low = original.low;
        this.high = original.high;
        this.storage = allocate(original.length);
        // Copy each element over
        for (int i = original.low; i <= original.high; i++) {
            this.set(i, original.get(i));
        }
    }

    /**
     * Private utility to do array allocation
     */
    @SuppressWarnings("unchecked")
    private T[] allocate(int len) {
        return (T[]) new Object[len];
    }

    /**
     * Returns the lowest accessible index of an Dynamic Array
     * 
     * @param dynamicArray The Dynamic Array
     * @return The lowest index
     */
    public int lowIndex(DynamicArray<T> dynamicArray) {
        return low;
    }

    /**
     * Returns the highest accessible index of an Dynamic Array
     * 
     * @param dynamicArray The Dynamic Array
     * @return The lowest index
     */
    public int highIndex(DynamicArray<T> dynamicArray) {
        return high;
    }

    /**
     * Checks to see if an index is accessible within a Dynamic Array
     * 
     * @param index The specified index
     * @return True/False - If the index is within the Dynamic Array
     */
    public boolean indexInRange(int index) {
        if (this.low <= index && index < this.length) {
            return true;
        } else {
            System.out.println("low: " + this.low + ", index: " + index + ", length: " + this.length);
            return false;
        }
    }

    /**
     * Create a String representation of the current `DynamicArray`
     * IM NOT SURE ABOUT CALLING TOSTRING ON A TOSTRING
     */
    public String toString() {
        // Start the full String
        String finalString = "[";
        // Copy every element into full String
        for (int i = this.low; i <= this.high; i++) {
            if (i == this.high) {
                finalString += this.get(i);
            } else {
                finalString += this.get(i) + ", ";
            }
        }
        // Return finalString
        return finalString + "]";
    }

    /* GROUP 1: BASIC OPERATIONS */
    /**
     * Sets the value at the index to a specified value
     * 
     * @param index The index at which to change its value
     * @param item  The new item after setting
     * @return The previous item (or, `null` if index was previously empty)
     * @throws IndexOutOfBoundsException for invalid indices
     */
    public T set(int index, T item) throws IndexOutOfBoundsException {
        // Check index validity
        if (indexInRange(index)) {
            T previousItem = this.get(index); // Stores previous item
            this.storage[index] = item; // Changes value
            return previousItem;
        } else {
            throw new IndexOutOfBoundsException(indexErrorMessage);
        }
    }

    /**
     * Gets the value from a given index of the Dynamic Array
     * 
     * @param index The specified index
     * @return The value at the given index in the Dynamic Array
     * @throws IndexOutOfBoundsException for invalid indices
     */
    public T get(int index) throws IndexOutOfBoundsException {
        // Check index validity
        if (indexInRange(index)) {
            return this.storage[index];
        } else {
            throw new IndexOutOfBoundsException(indexErrorMessage);
        }
    }

    /**
     * Returns the actual number of elements in a Dynamic Array
     * 
     * @return The size of the Dynamic Array
     */
    public int size() {
        return high - low + 1;
    }

    /* GROUP 2: ADD/REMOVE ELEMENTS */
    /**
     * Adds an item to the end of the Dynamic Array
     * 
     * @param item The new item to be added
     */
    public void add(T item) {
        // Case 1: There is space to store the new item
        if (this.size() < this.length) {
            this.set(high + 1, item); // Put the item next to the current highest index
            this.high++; // Update highest index
        }
        // Case 2: There is NOT ENOUGH space to store the new item
        else {

            // Create an array with larger size
            T[] tempArr = allocate(this.length + 1);

            // Copy all elements from current `DynamicArray`
            for (int i = this.low; i <= this.high; i++) {
                tempArr[i] = this.get(i);
            }
            // Set final element to item
            tempArr[this.length] = item;

            // Update the current storage to be tempArr
            this.storage = tempArr;
            this.length++; // Update length
            this.high++; // Update highest index
        }
    }

    /**
     * Adds an item to the Dynamic Array at the specified index
     * Shifts every item after the index to the right by 1
     * 
     * @param index The specific index
     * @param item  The item to be added
     * @throws IndexOutOfBoundsException for invalid indices
     */
    public void add(int index, T item) throws IndexOutOfBoundsException {
        // Check index validity
        if (indexInRange(index)) {
            // Case 1: There is enough space
            if (this.size() < this.length) {
                // Shift all subsequent elements to the right by 1
                for (int i = high; i >= index; i--) {
                    this.set(i + 1, this.get(i));
                }
                high++; // Update highest index
                System.out.println("High updated to: " + this.high);
                System.out.println("Size updated to: " + this.size());
                // Set the index's value to the item
                this.set(index, item);
            }
            // Case 2: There is not enough space
            else {
                // Create an array with larger size
                T[] tempArr = allocate(this.length + 1);

                // Copy all previous elements from current `DynamicArray`
                for (int i = 0; i < index; i++) {
                    tempArr[i] = this.get(i);
                }

                // Set item at index
                tempArr[index] = item;

                // Copy all subsequent elements from current `DynamicArray`
                for (int i = index; i <= this.high; i++) {
                    tempArr[i + 1] = this.get(i);
                }

                // Update the current storage to be tempArr
                this.storage = tempArr;
                this.length++; // Update length
                this.high++; // Update highest index
            }
        } else {
            throw new IndexOutOfBoundsException(indexErrorMessage);
        }
    }

    /**
     * Removes an item at a specified index
     * Shifts subsequent elements to the left
     * 
     * @param index The specific index
     * @return The element previously at that index
     * @throws IndexOutOfBoundsException - If the index is less than zero, equal to
     *                                   the size of the list or greater than the
     *                                   size of the list.
     */
    public T remove(int index) throws IndexOutOfBoundsException {
        // Check index validity
        if (indexInRange(index)) {
            // 0. Get removedItem before removal
            T removedItem = this.get(index);
            // 1. Create a tempArr with smaller size
            T[] tempArr = allocate(this.length - 1);
            // 2. Copy all elements before the index
            for (int i = this.low; i < index; i++) {
                tempArr[i] = this.get(i);
            }
            // 3. Copy all elements after the index
            for (int i = index; i < this.high; i++) {
                tempArr[i] = this.get(i + 1);
            }
            // 4. Update the current storage to be tempArr
            this.storage = tempArr;
            this.length--; // Update length
            this.high--; // Update highest index
            // 5. Return removed element
            return removedItem;
        } else {
            throw new IndexOutOfBoundsException(indexErrorMessage);
        }
    }

    /* GROUP 3: WHOLE-ARRAY OPERATIONS */
    /**
     * Concatenates two Dynamic Arrays
     * 
     * @param secondDA The second Dynamic Array
     * @return The resulting Dynamic Array Object
     */
    public DynamicArray<T> append(DynamicArray<T> secondDA) {
        // 1. Create a newDA with larger size to hold the elements of both Dynamic
        // Arrays
        DynamicArray<T> newDA = new DynamicArray<>(this.length + secondDA.length);
        int offset = this.length;
        // 2. Copy over all elements of the current Dynamic Array into newDA
        for (int i = newDA.low; i <= this.high; i++) {
            newDA.set(i, this.get(i));
        }
        // 3. Copy over all elements of secondDA into newDA
        for (int i = secondDA.low; i <= secondDA.high; i++) {
            newDA.set(i + offset, secondDA.get(i));
        }
        // 4. return newDA
        return newDA;
    }

    /**
     * Inserts new Dynamic Array into current `DynamicArray` at a specified index
     * 
     * @param index The specific index to for insertion
     * @param newDA The Dynamic Array to be inserted
     * @return The resulting Dynamic Array Object
     * @throws IndexOutOfBoundsException for invalid indices
     */
    public DynamicArray<T> insert(int index, DynamicArray<T> newDA) throws IndexOutOfBoundsException {
        // Check index validity
        if (indexInRange(index)) {
            // 1. Create a fullDA with larger size to hold elements of both Dynamic Arrays
            DynamicArray<T> fullDA = new DynamicArray<>(this.length + newDA.length);
            // 2. Copy all elements (from current `DynamicArray`) before the index into
            // fullDA
            for (int i = 0; i < index; i++) {
                fullDA.set(i, this.get(i));
            }
            // 3. Copy all elements of newDA into fullDA
            int offset = index;
            for (int i = newDA.low; i <= newDA.high; i++) {
                fullDA.set(i + offset, newDA.get(i));
            }
            // 4. Copy all remaining elements (from current `DynamicArray`) into fullDA
            for (int i = index; i <= this.high; i++) {
                fullDA.set(i + offset, newDA.get(i));
            }
            // 4. Return fullDA
            return fullDA;
        } else {
            throw new IndexOutOfBoundsException(indexErrorMessage);
        }
    }

    /**
     * Returns a view of the current `DynamicArray` in a specified range
     * 
     * @param fromIndex The starting index (Inclusive)
     * @param toIndex   The ending index (Exclusive)
     * @return A copy containing the items of the `DynamicArray` within the range
     * @throws IndexOutOfBoundsException for invalid indices
     * @throws IllegalArgumentException  if starting index is larger than ending
     *                                   index
     */
    public DynamicArray<T> sublist(int fromIndex, int toIndex)
            throws IndexOutOfBoundsException, IllegalArgumentException {
        // Check index validity
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("Invalid start and end indeces.");
        }
        if (indexInRange(fromIndex) && indexInRange(toIndex - 1)) {
            // Call viewing constructor
            DynamicArray<T> subArray = new DynamicArray<>(this, fromIndex, toIndex - 1);
            return subArray;
        } else {
            throw new IndexOutOfBoundsException(indexErrorMessage);
        }
    }

    /**
     * Deletes items at a specified range in current `DynamicArray`
     * 
     * @param fromIndex The starting index (Inclusive)
     * @param toIndex   The ending index (Exclusive)
     * @return A copy of the resulting Dynamic Array
     * @throws IndexOutOfBoundsException for invalid indices
     * @throws IllegalAccessException    if the starting index is larger than the
     *                                   ending index
     */
    public DynamicArray<T> delete(int fromIndex, int toIndex) throws IndexOutOfBoundsException, IllegalAccessException {
        // Check index validity
        if (fromIndex > toIndex) {
            throw new IllegalAccessException("Invalid start and end indeces.");
        }
        if (indexInRange(fromIndex) && toIndex <= this.length) {
            int offset = fromIndex - toIndex;
            // 1. Create a newDA with smaller size
            DynamicArray<T> newDA = new DynamicArray<>(this.length - (offset));
            // 2. Copy all elements before fromIndex
            for (int i = this.low; i < fromIndex; i++) {
                newDA.set(i, this.get(i));
            }
            // 3. Copy all elements after toIndex
            for (int i = fromIndex; i <= newDA.high; i++) {
                newDA.set(i, this.get(i + offset));
            }
            // 4. Return newDA
            return newDA;
        } else {
            throw new IndexOutOfBoundsException(indexErrorMessage);
        }
    }

    /**
     * Creates a new Dynamic Array from a specified range of the current
     * `DynamicArray`
     * 
     * @param fromIndex The starting index (Inclusive)
     * @param toIndex   The ending index (Exclusive)
     * @return A new Dynamic Array containing the items from the specified range
     * @throws IndexOutOfBoundsException for invalid indices
     * @throws IllegalArgumentException  if the starting index is larger than the
     *                                   ending index
     */
    public DynamicArray<T> extract(int fromIndex, int toIndex)
            throws IndexOutOfBoundsException, IllegalArgumentException {
        // Check index validity
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("Invalid start and end indeces.");
        }
        if (indexInRange(fromIndex) && toIndex <= this.length) {
            // 1. Make deep copy called newDA of original DA
            DynamicArray<T> newDA = new DynamicArray<>(this);
            // 2. Make sublist of newDA
            DynamicArray<T> subArray = new DynamicArray<>(this, fromIndex, toIndex - 1);
            // 3. Assign newDA to sublist
            newDA = subArray;
            System.out.println(subArray);
            return newDA;
        } else {
            throw new IndexOutOfBoundsException(indexErrorMessage);
        }
    }
}