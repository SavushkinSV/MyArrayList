package org.example;

/**
 * Собственная реализация ArrayList.
 *
 * @author SavushkinSV
 */
public class MyArrayList<T> {

    /**
     * Емкость массива при инициализации.
     */
    private static final int INIT_CAPACITY = 10;

    /**
     * Размер массива.
     */
    private int size;

    /**
     * Емкость внутреннего массива.
     */
    private int capacity;

    /**
     * Массив с данными.
     */
    private Object[] array;

    /**
     * Конструктор без аргументов.
     * Создает массив с количеством элементов по умолчанию.
     */
    public MyArrayList() {
        this.capacity = INIT_CAPACITY;
        this.array = new Object[capacity];
    }

    /**
     * Конструктор с заданной емкостью массива.
     *
     * @param capacity емкость создаваемого массива
     */
    public MyArrayList(int capacity) {
        if (capacity >= 0) {
            this.capacity = capacity;
            this.array = new Object[capacity];
        } else {
            throw new IllegalArgumentException("Некорректное значение массива: " + capacity);
        }
    }

    /**
     * Возвращает размер массива.
     *
     * @return размер массива
     */
    public int size() {
        return size;
    }

    /**
     * Добавляет новый элемент в массив.
     * При достижении максимального значения внутреннего массива удваивает его.
     *
     * @param element добавляемый элемент
     */
    public void add(T element) {
        if (size >= capacity) {
            capacity *= 2;
            resizeArray(capacity);
            System.out.println("resize()");
        }
        array[size++] = element;
    }

    /**
     * Возвращает элемент массива по индексу.
     *
     * @param index индекс возвращаемого элемента
     * @return возвращаемый элемент
     */
    public T get(int index) {
        if (checkIndex(index)) {
            return (T) array[index];
        }
        return null;
    }

    /**
     * Заменяет элемент по индексу.
     *
     * @param index   индекс элемента для замены
     * @param element элемент для замены
     */
    public void set(int index, T element) {
        if (checkIndex(index)) {
            array[index] = element;
        } else {
            throw new IllegalArgumentException("Некорректный индекс массива: " + index);
        }
    }

    /**
     * Удаляет элемент из массива.
     *
     * @param index индекс элемента
     */
    public void remove(int index) {
        if (checkIndex(index)) {
            for (int i = index; i < size - 1; i++) {
                array[i] = array[i + 1];
            }
            array[size - 1] = null;
        }

    }

    /**
     * Увеличивает размер внутреннего массива в два раза.
     */
    private void resizeArray(int newSize) {
        Object[] newArray = new Object[newSize];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    /**
     * Проверяет значение индекса в диапазоне от 0 до size.
     *
     * @param index индекс проверяемого элемента
     * @return
     */
    private boolean checkIndex(int index) {
        boolean result = false;
        if (index >= 0 && index < size) {
            result = true;
        }

        return result;
    }

}
