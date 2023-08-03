package org.example;

import org.example.Exception.ElementNotFoundException;
import org.example.Exception.InvalidIndexException;
import org.example.Exception.NullItemException;
import org.example.Exception.StorageIsFullException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList{
    private final Integer[] storage;

    private int size;

    public IntegerListImpl() {
        storage = new Integer[10];
    }

    private final Integer[] arr = new Integer[100000];  // для тестов скорости сортировки

    public void fillArray(){                           // для заполнения массива рандомными числами
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ((int)(Math.random() * 100000));
        }
    }

    @Override
    public Integer add(Integer item) {
        validateSize();
        validateItem(item);
        storage[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        validateSize();
        validateItem(item);
        validateIndex(index);

        if (index == size) {
            storage[size++] = item;
            return item;
        }
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        storage[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFoundException();
        }
        if (index == size) {
            storage[size--] = null;
            return item;
        }
        System.arraycopy(storage, index + 1, storage, index, size - index);
        size--;
        return item;
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);
        Integer item = storage[index];
        if (index == size) {
            storage[size--] = null;
            return item;
        }
        System.arraycopy(storage, index + 1, storage, index, size - index);
        size--;
        return item;
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            Integer s = storage[i];
            if (s.equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            Integer s = storage[i];
            if (s.equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return storage[index];
    }

    @Override
    public boolean equals(Integer[] otherList) {
        return Arrays.equals(this.toArray(), otherList);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(storage, size);
    }

    private void validateItem(Integer item) {
        if (item == null) {
            throw new NullItemException();
        }

    }

    private void validateSize() {
        if (size == storage.length) {
            throw new StorageIsFullException();
        }

    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new InvalidIndexException();
        }

    }

    public long checkBubble(){
        fillArray();
        long start = System.currentTimeMillis();
        sortBubble(arr);
        return System.currentTimeMillis() - start;
    }

    public long checkSelection(){
        fillArray();
        long start = System.currentTimeMillis();
        sortSelection(arr);
        return System.currentTimeMillis() - start;
    }

    public long checkInsertion(){
        fillArray();
        long start = System.currentTimeMillis();
        sortInsertion(arr);
        return System.currentTimeMillis() - start;
    }

    private void sortBubble(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    private void sortSelection(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    private void sortInsertion(Integer[] arr) {   //самый быстрый
        for (int i = 1; i < arr.length; i++) {
            Integer temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }
    @Override
    public Integer[] goSortInsertionForTest(Integer[] arr){
        sortInsertion(arr);
        return arr;
    }

    private boolean contains(Integer[] arr, Integer element) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (element.equals(arr[mid])) {
                return true;
            }

            if (element < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }
    @Override
    public boolean contains(Integer item) {
        sortInsertion(storage);
        return contains(storage,item);
    }

}




