package ru.otus.utils;

import java.util.*;

public class MyArrayList<E> implements List<E>, RandomAccess {

    protected int modCount = 0;
    private Object[] elementData;
    private int size = 0;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public String toString(){
        /*Arrays.asList(Arrays.stream(elementData)
                .filter(obj->(obj!=null))
                .toArray(Object[]::new)).toString();*/

        return Arrays.asList(elementData).toString();
    }

    @Override
    public boolean add(E e) {
        modCount++;
        add(e, size);
        return true;
    }

    private void add(E e, int s) {
        if (s == elementData.length)
            elementData = Arrays.copyOf(elementData,
                    newCapacity(++size));
        elementData[s] = e;
        size = ++s;
    }

    private int newCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity <= 0) {
            if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
                return Math.max(DEFAULT_CAPACITY, minCapacity);
            return minCapacity;
        }
        return newCapacity;
    }


    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        int cursor;
        int lastRet = -1;
        int expectedModCount = modCount;

        public boolean hasNext() {
            return cursor != size;
        }

        public E next() {
            checkForComodification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = MyArrayList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            lastRet = i;
            return (E) elementData[lastRet];
        }

        public void remove() {}

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    @Override
    public E set(int index, E element) {
        E oldValue = (E) elementData[index];
        elementData[index] = element;
        return oldValue;
    }
    @Override
    public E get(int index) {
        return (E) elementData[index];
    }

    public ListIterator<E> listIterator(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
        return new ListItr(index);
    }

    private class ListItr extends Itr implements ListIterator<E> {
        ListItr(int index) {
            super();
            cursor = index;
        }

        public void set(E e){
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                MyArrayList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        public void add(E e) {}

        public int nextIndex() {
            return 0;
        }

        public boolean hasPrevious() {
            return false;
        }

        public int previousIndex() {
            return 0;
        }

        public E previous() {
            return null;
        }
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListItr(0);
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}
