import org.example.MyArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MyArrayListTest {

    public static List<String> stringList;

    @BeforeAll
    public static void init() {
        stringList = Arrays.asList("Moscow", "Kazan", "Yaroslavl", "Novosibirsk", "Murmansk");
    }

    @Test
    public void testNewMyArrayListShouldHaveSizeZero() {
        MyArrayList<Object> list = new MyArrayList<>();

        Assertions.assertEquals(0, list.size());
    }

    @Test
    public void testExpectedExceptionWithNegativeArrayValue() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new MyArrayList<>(-10));

        Assertions.assertEquals("Некорректное значение массива: -10", exception.getMessage());
    }

    @Test
    public void testAddMyArrayListShouldHaveSizeTwo() {
        MyArrayList<Object> list = new MyArrayList<>(2);
        list.add(new Object());
        list.add(new Object());

        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void testAddMyArrayListShouldHaveSizeFour() {
        MyArrayList<Object> list = new MyArrayList<>(2);
        list.add(new Object());
        list.add(new Object());
        list.add(1, new Object());
        list.add(0, new Object());

        Assertions.assertEquals(4, list.size());
    }

    @Test
    public void testExpectedExceptionAddWithNegativeArrayValue() {
        MyArrayList<Object> list = new MyArrayList<>(50);
        Exception exception = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.add(-5, new Object()));

        Assertions.assertEquals("Некорректный индекс элемента массива: -5", exception.getMessage());
    }

    @Test
    public void testAddMyArrayListShouldHaveSizeFive() {
        MyArrayList<String> list = new MyArrayList<>(stringList);

        Assertions.assertEquals(5, list.size());
    }

    @Test
    public void testGetShouldReturnString() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Hello");
        list.add("World");
        list.add(1, "Object");
        list.add(0, "new");

        Assertions.assertEquals("Object", list.get(2));
    }

    @Test
    public void testExpectedExceptionIndexOutOfBoundsArray() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Hello");
        list.add("World");
        Exception exception = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));

        Assertions.assertEquals("Некорректный индекс элемента массива: 2", exception.getMessage());
    }

    @Test
    public void testNewMyArrayListShouldHaveCapacityTen() {
        MyArrayList<Integer> list = new MyArrayList<>();
        try {
            Field field = list.getClass().getDeclaredField("capacity");
            field.setAccessible(true);
            int capacity = (int) field.get(list);

            Assertions.assertEquals(10, capacity);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAddManyObjectShouldIncreaseCapacity() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i <= 10; i++) {
            list.add(i);
        }
        try {
            Field field = list.getClass().getDeclaredField("capacity");
            field.setAccessible(true);
            int capacity = (int) field.get(list);

            Assertions.assertEquals(20, capacity);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testRemoveManyObjectShouldReduceCapacity() {
        MyArrayList<Integer> list = new MyArrayList<>();
        int size = 500;
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        for (int i = size - 1; i > 5; i--) {
            list.remove(i);
        }
        try {
            Field field = list.getClass().getDeclaredField("capacity");
            field.setAccessible(true);
            int capacity = (int) field.get(list);

            Assertions.assertEquals(20, capacity);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSetShouldReturnNewObject() {
        String expected = "Minsk";
        MyArrayList<String> list = new MyArrayList<>(Arrays.asList("Moscow", "Kazan", "Yaroslavl", "Novosibirsk", "Murmansk"));
        list.set(3, expected);

        Assertions.assertEquals(expected, list.get(3));
    }

    @Test
    public void testExpectedExceptionSetIndexOutOfBoundsArray() {
        MyArrayList<String> list = new MyArrayList<>();
        Exception exception = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.set(5, "Sochi"));

        Assertions.assertEquals("Некорректный индекс элемента массива: 5", exception.getMessage());
    }

    @Test
    public void testRemoveObjectShouldHaveSizeFour() {
        MyArrayList<Object> list = new MyArrayList<>(stringList);
        list.remove(0);

        Assertions.assertEquals(4, list.size());
    }

    @Test
    public void testExpectedExceptionRemoveIndexOutOfBoundsArray() {
        MyArrayList<Object> list = new MyArrayList<>(stringList);
        Exception exception = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));

        Assertions.assertEquals("Некорректный индекс элемента массива: -1", exception.getMessage());
    }

    @Test
    public void testClearObjectShouldHaveSizeZero() {
        MyArrayList<Object> list = new MyArrayList<>(stringList);
        list.clear();

        Assertions.assertEquals(0, list.size());
    }

    @Test
    public void testToString() {
        MyArrayList<Object> list = new MyArrayList<>(stringList);
        String expected = "[Moscow, Kazan, Yaroslavl, Novosibirsk, Murmansk]";

        Assertions.assertEquals(expected, list.toString());
    }

    @Test
    public void testToStringWithEmptyArray() {
        MyArrayList<Object> list = new MyArrayList<>();
        String expected = "[]";

        Assertions.assertEquals(expected, list.toString());
    }

    @Test
    public void testSort() {
        MyArrayList<Object> list = new MyArrayList<>(stringList);
        String expected = "[Kazan, Moscow, Murmansk, Novosibirsk, Yaroslavl]";
        list.sort();

        Assertions.assertEquals(expected, list.toString());
    }

    @Test
    public void testSortWishComparator() {
        MyArrayList<String> list = new MyArrayList<>(stringList);
        String expected = "[Yaroslavl, Novosibirsk, Murmansk, Moscow, Kazan]";
        list.sort(Comparator.reverseOrder());

        Assertions.assertEquals(expected, list.toString());
    }

    @Test
    public void testSortWishComparatorNull() {
        MyArrayList<String> list = new MyArrayList<>(stringList);
        String expected = "[Kazan, Moscow, Murmansk, Novosibirsk, Yaroslavl]";
        list.sort(null);

        Assertions.assertEquals(expected, list.toString());
    }

    @Test
    public void testSortEmptyArray() {
        MyArrayList<String> list = new MyArrayList<>(0);
        String expected = "[]";
        list.sort();

        Assertions.assertEquals(expected, list.toString());
    }

    @Test
    public void testSortEmptyArrayWithComparator() {
        MyArrayList<String> list = new MyArrayList<>(0);
        String expected = "[]";
        list.sort(Comparator.naturalOrder());

        Assertions.assertEquals(expected, list.toString());
    }

    @Test
    public void testNewMyArrayListConstructorIsNull() {
        MyArrayList<String> list = new MyArrayList<>(null);

        Assertions.assertEquals(0, list.size());
    }

    @Test
    public void testAddManyObjectsWithIndexZero() {
        MyArrayList<Object> list = new MyArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(0, new Object());
        }

        Assertions.assertEquals(10000, list.size());
    }
}