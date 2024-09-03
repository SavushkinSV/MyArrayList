import org.example.MyArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

public class MyArrayListTest {

    public static MyArrayList<String> stringList;

    @BeforeAll
    public static void init() {
        stringList = new MyArrayList<>(Arrays.asList("Moscow", "Kazan", "Yaroslavl", "Novosibirsk", "Murmansk"));
    }

    @Test
    public void testNewMyArrayListShouldHaveSizeZero() {
        MyArrayList<Object> list = new MyArrayList<>();

        Assertions.assertEquals(0, list.size());
    }

    @Test
    public void testExpectedExceptionWithNegativeArrayValue() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new MyArrayList<>(-10);
        });

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
    public void testAddMyArrayListShouldHaveSizeFive() {

        Assertions.assertEquals(5, stringList.size());
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
        Exception exception = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(2);
        });

        Assertions.assertEquals("Некорректный индекс элемента массива: 2", exception.getMessage());
    }

    @Test
    public void testNewMyArrayListShouldHaveCapacityTen() {
        MyArrayList<Integer> list = new MyArrayList<>();
        try {
            Field field = field = list.getClass().getDeclaredField("capacity");
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
            Field field = field = list.getClass().getDeclaredField("capacity");
            field.setAccessible(true);
            int capacity = (int) field.get(list);

            Assertions.assertEquals(20, capacity);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


}