public class MyDynamicArrayTest {
    public static void main(String[] args) throws IndexOutOfBoundsException, IllegalAccessException {
        DynamicArray<Character> a1 = new DynamicArray<Character>(6);
        a1.add(0, 'a');
        a1.add(1, 'b');
        a1.add(2, 'c');
        a1.add(3, 'd');
        a1.add(4, 'e');
        a1.add(5, 'f');
        System.out.println(a1);
        DynamicArray<Character> one = new DynamicArray<Character>(1);
        one.add('s');
        System.out.println("result: " + (a1.insert(3,one)));
        // DynamicArray<Character> empty = new DynamicArray<Character>(0);
        // a1.insert(2, empty);
        System.out.println(a1);

    }
}