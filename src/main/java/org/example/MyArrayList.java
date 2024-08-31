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
     * @return - размер массива
     */
    public int size() {
        return size;
    }

    /**
     * Добавляет новый элемент в массив.
     * При достижении максимального значения внутреннего массива удваивает его.
     *
     * @param item
     */
    public void add(T item) {
        if (size >= capacity) {
            resizeArray();
        }
        array[size++] = item;
    }

    /**
     * Увеличивает размер внутреннего массива в два раза.
     */
    private void resizeArray() {
        capacity *= 2;
        Object[] newArray = new Object[capacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

}
