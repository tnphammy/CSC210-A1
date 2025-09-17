public class DynamicArrayTest {
    public static void main(String[] args) {
        DynamicArray<Character> a1 = new DynamicArray<Character>(6);
        a1.add(0, 'a');
        a1.add(1, 'b');
        a1.add(2, 'c');
        a1.add(3, 'd');
        a1.add(4, 'e');
        a1.add(5, 'f');
        a1.add('g');
        System.out.println(a1);
        a1.remove(6);
        System.out.println(a1);
        // DynamicArray<Character> empty = new DynamicArray<Character>(0);
        // empty.get(0);

    }
}