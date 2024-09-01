package org.example;

import java.util.*;

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
        size--;
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

    public void sort(Comparator<? super T> comparator) {


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
     * Проверяет значение индекса в диапазоне от 0 до size.
     *
     * @param index индекс проверяемого элемента
     * @return
     */
    private boolean checkIndex(int index) {

        return index >= 0 && index < size;
    }

    public Iterator<T> iterator() {
        return new MyArrayList.Itr();
    }

    private class Itr implements Iterator<T> {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such

        Itr() {
        }

        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public T next() {
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = MyArrayList.this.array;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (T) elementData[lastRet = i];
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();

            try {
                MyArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

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


}
