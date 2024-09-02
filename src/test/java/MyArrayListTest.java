import org.example.MyArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyArrayListTest {

    @Test
    public void testNewMyArrayListShouldHaveSizeZero() {
        MyArrayList<Object> list = new MyArrayList<>();

        Assertions.assertEquals(0, list.size());
    }

    @Test
    public void testExpectedExceptionWithNegativeArrayValue() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new MyArrayList<>(-10);});

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


}
