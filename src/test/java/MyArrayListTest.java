import org.example.MyArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyArrayListTest {

    @Test
    public void newMyArrayListShouldHaveSizeZero() {
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
    public void newMyArrayListShouldHaveSizeTwo() {
        MyArrayList<Object> list = new MyArrayList<>();
        list.add(new Object());
        list.add(new Object());

        Assertions.assertEquals(2, list.size());
    }
}
