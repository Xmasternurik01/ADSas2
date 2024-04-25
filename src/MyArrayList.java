import java.util.Iterator;

public class MyArrayList<T extends Comparable<T>> implements MyList<T> {
    // The internal array that stores the items in the list
    private T[] arr;
    // The number of items in the list
    private int size;

    // Constructs a new, empty MyArrayList object
    public MyArrayList(){
        arr = (T[]) new Comparable[5];
        size = 0;
    }

    // Increases the capacity of the internal array by doubling its size
    private void increaseBuffer(){
        T[] newArr = (T[]) new Comparable[arr.length * 2];
        for (int i = 0; i < arr.length; i++){
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    // Throws an IndexOutOfBoundsException if the given index is out of bounds
    private void checkIndex(int index) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index not correct");
    }

    // Adds an item to the end of the list
    @Override
    public void add(T item) {
        add(size, item);
    }

    // Sets the item at the given index to the specified value
    @Override
    public void set(int index, T item) {
        checkIndex(index);
        arr[index] = item;
    }

    // Inserts an item at the specified index
    @Override
    public void add(int index, T item) {
        if(size != index)
            checkIndex(index);
        if (size == arr.length){
            increaseBuffer();
        }
        for (int i = size; i > index; i--){
            arr[i] = arr[i - 1];
        }
        arr[index] = item;
        size++;
    }

    // Adds an item to the beginning of the list
    @Override
    public void addFirst(T item) {
        add(0, item);
    }

    // Adds an item to the end of the list
    @Override
    public void addLast(T item) {
        add(item);
    }

    // Returns the item at the specified index
    @Override
    public T get(int index) {
        checkIndex(index);
        return arr[index];
    }

    // Returns the first item in the list
    @Override
    public T getFirst() {
        return arr[0];
    }

    // Returns the last item in the list
    @Override
    public T getLast() {
        return arr[size - 1];
    }

    // Removes the item at the specified index
    @Override
    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        size--;
    }

    // Removes the first item in the list
    @Override
    public void removeFirst() {
        remove(0);
    }

    // Removes the last item in the list
    @Override
    public void removeLast() {
        remove(size - 1);
    }

    // Sorts the list using a bubble sort algorithm
    @Override
    public void sort() {
        for (int i = 0; i < size - 1; i++){
            for (int j = 0; j < size - i - 1; j++){
                if (arr[j].compareTo(arr[j + 1]) > 0){
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Returns the index of the first occurrence of the specified object
    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    // Returns the index of the last occurrence of the specified object
    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; --i) {
            if (arr[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    // Returns true if the specified object is in the list, and false otherwise
    @Override
    public boolean exists(Object object) {
        return (indexOf(object) != -1);
    }

    // Returns an array containing all of the items in the list
    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    // Clears the list, removing all items
    @Override
    public void clear() {
        arr = (T[]) new Comparable[5];
        size = 0;
    }

    // Returns the number of items in the list
    @Override
    public int size() {
        return size;
    }

    // Returns an iterator for the list
    @Override
    public Iterator<T> iterator() {
        return new MyArrayListIterator();
    }

    // A private inner class that implements the Iterator interface
    private class MyArrayListIterator implements Iterator<T> {
        // The current position of the iterator
        private int cursor;

        // Constructs a new MyArrayListIterator object
        MyArrayListIterator(){
            cursor = 0;
        }

        // Returns true if there are more items to iterate over, and false otherwise
        @Override
        public boolean hasNext() {
            return (cursor < size);
        }

        // Returns the next item in the iteration
        @Override
        public T next() {
            if (!hasNext())
                return null;
            return arr[cursor++];
        }
    }
}