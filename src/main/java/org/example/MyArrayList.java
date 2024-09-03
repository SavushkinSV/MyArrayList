package org.example;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Собственная реализация ArrayList.
 *
 * @param <T> тип элементов в массиве
 * @author SavushkinSV
 * @version 1.0
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
     * Конструктор создает массив с начальной емкостью 10 элементов.
     */
    public MyArrayList() {
        this.capacity = INIT_CAPACITY;
        this.array = new Object[capacity];
    }

    /**
     * Конструктор создает массив с заданной емкостью.
     *
     * @param capacity емкость создаваемого массива
     * @throws IllegalArgumentException если передано отрицательное значение
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
     * Конструктор создает массив из элементов указанной коллекции.
     *
     * @param c коллекция, элементы которой помещаются в массив
     */
    public MyArrayList(Collection<? extends T> c) {
        if (c != null) {
            Object[] array = c.toArray();
            size = array.length;
            this.array = array;
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
        }
        array[size++] = element;
    }

    /**
     * Добавляет новый элемент по индексу в массив.
     *
     * @param index   индекс добавляемого элемента
     * @param element добавляемый элемент
     */
    public void add(int index, T element) {
        if (checkIndex(index)) {
            if (size + 1 >= capacity) {
                capacity *= 2;
                resizeArray(capacity);
            }
            for (int i = size - 1; i >= index; i--) {
                array[i + 1] = array[i];
            }
            array[index] = element;
            size++;
        }
    }

    /**
     * Возвращает элемент массива по индексу.
     *
     * @param index индекс возвращаемого элемента
     * @return возвращаемый элемент
     * @throws IndexOutOfBoundsException если индекс выходит за пределы диапазона
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);

        return (T) array[index];
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
            size--;
        }

    }

    /**
     * Очищает весь массив.
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    /**
     * Сортирует массив.
     *
     * @throws ClassCastException если массив содержит элементы, которые не сопоставимы друг с другом с помощью встроенного компаратора
     */
    public void sort() {
        quickSort(array, 0, size - 1);
    }

    /**
     * Сортирует массив по заданному компаратору.
     *
     * @param comparator передаваемый компаратор
     * @throws ClassCastException если массив содержит элементы, которые не сопоставимы друг с другом с помощью заданного компаратора
     */
    public void sort(Comparator<? super T> comparator) {
        if (comparator == null) {
            quickSort(array, 0, size - 1);
        } else {
            quickSort(array, 0, size - 1, comparator);
        }

    }

    /**
     * Увеличивает размер внутреннего массива.
     *
     * @param newSize новый размер массива
     */
    private void resizeArray(int newSize) {
        Object[] newArray = new Object[newSize];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    /**
     * Проверяет значение индекса в диапазоне [0, size).
     *
     * @throws IndexOutOfBoundsException если индекс выходит за пределы диапазона (index < 0 || index >= size()
     */
    private boolean checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Некорректный индекс элемента массива: " + index);

        return true;
    }

    private Iterator<T> iterator() {
        return new MyArrayList.Itr();
    }

    private class Itr implements Iterator<T> {
        int cursor;       // индекс следующего элемента для возврата
        int lastRet = -1; // индекс последнего возвращенного элемента; -1, если такого нет

        Itr() {
        }

        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public T next() {
            int i = cursor;
            Object[] elementData = MyArrayList.this.array;
            cursor = i + 1;

            return (T) elementData[lastRet = i];
        }
    }

    /**
     * Возвращает строковое представление объекта.
     *
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        Iterator<T> it = iterator();
        if (!it.hasNext())
            return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (; ; ) {
            T t = it.next();
            sb.append(t == this ? "(this Collection)" : t);
            if (!it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }

    /**
     * Сортирует массив методом быстрой сортировки.
     *
     * @param arr  массив данных
     * @param low  нижняя граница массива
     * @param high верхняя граница массива
     * @throws ClassCastException если массив содержит элементы, которые не сопоставимы друг с другом с помощью указанного компаратора
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void quickSort(Object[] arr, int low, int high) {
        //завершить, если массив пуст или уже нечего делить
        if (arr.length == 0 || low >= high) return;

        //выбираем опорный элемент
        int middle = low + (high - low) / 2;
        Object border = arr[middle];

        //разделяем на подмассивы и меняем местами
        int i = low, j = high;
        while (i <= j) {
            while (((Comparable) arr[i]).compareTo(border) < 0) i++;
            while (((Comparable) arr[j]).compareTo(border) > 0) j--;
            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }

        //рекурсия для сортировки левой и правой части
        if (low < j) quickSort(arr, low, j);
        if (high > i) quickSort(arr, i, high);
    }

    /**
     * Сортирует массив методом быстрой сортировки с заданным компаратором.
     *
     * @param arr  массив данных
     * @param low  нижняя граница массива
     * @param high верхняя граница массива
     * @throws ClassCastException если массив содержит элементы, которые не сопоставимы друг с другом с помощью указанного компаратора
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void quickSort(Object[] arr, int low, int high, Comparator c) {
        //завершить, если массив пуст или уже нечего делить
        if (arr.length == 0 || low >= high) return;

        //выбираем опорный элемент
        int middle = low + (high - low) / 2;
        Object border = arr[middle];

        //разделяем на подмассивы и меняем местами
        int i = low, j = high;
        while (i <= j) {
            while (c.compare(arr[i], border) < 0) i++;
            while (c.compare(arr[j], border) > 0) j--;
            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }

        //рекурсия для сортировки левой и правой части
        if (low < j) quickSort(arr, low, j, c);
        if (high > i) quickSort(arr, i, high, c);
    }

    /**
     * Меняет местами объекты массива.
     *
     * @param arr массив данных
     * @param a   индекс первого элемента для замены
     * @param b   индекс второго элемента для замены
     */
    private static void swap(Object[] arr, int a, int b) {
        Object tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
