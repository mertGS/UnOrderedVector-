package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author mert
 */
public class UnorderedVector<E> implements UnorderedListADT<E> {

    private E[] arr;
    private int size;

    public UnorderedVector() {
        arr = (E[]) new Object[100];
        size = 0;
    }

    private void resize() {
        E[] tmp = (E[]) new Object[arr.length * 2];
        for (int i = 0; i < arr.length; i++) {
            tmp[i] = arr[i];
        }
        arr = null;
        arr = tmp;
    }

    //  Adds the Object obj to the list in first position.
    @Override
    public void addFirst(E obj) {
        if (size >= arr.length) {
            resize();
        }
        for (int i = size; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = obj;
        size++;
    }

    //  Adds the Object obj to the end of the list.
    @Override
    public void addLast(E obj) {
        if (size >= arr.length) {
            resize();
        }
        arr[size] = obj;
        size++;
    }

    //  Adds the Object obj to the list in the position indicated.  The list is one based, and
//  the first element is at position #1 (not zero).  If the position is currently occupied
//  existing elements must be shifted over to make room for the insertion.
    @Override
    public void add(E obj, int position) {
        if (position < 0) {
            addFirst(obj);
        } else if (position > size) {
            addLast(obj);
        } else {
            if (size >= arr.length) {
                resize();
            }
            for (int i = size; i > position; i--) {
                arr[i] = arr[i - 1];
            }
            arr[position] = obj;
            size++;
        }
    }

//  Removes and returns the object located at the parameter position.
//  Throws a RuntimeException if the position does not map to a valid position within the list.    
    @Override
    public E remove(int position) {
        if (position < 0 || position >= size) {
            throw new NoSuchElementException("Invalid position");
        }
        E value = arr[position];
        for (int i = position; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        size--;
        return value;
    }

    //  Removes and returns the parameter object obj from the list if the list contains it, 
//  null otherwise.  If more than one element matches, the element is lowest position is removed
//  and returned.
    @Override
    public E remove(E obj) {
        for (int i = 0; i < size; i++) {
            if (obj.equals(arr[i])) {
                return remove(i);
            }
        }
        return null;
    }

//  Removes and returns the first element in the list and null if the it is empty.
    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        return remove(0);
    }

    //  Removes and returns the last element in the list and null if the it is empty.
    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        return remove(size - 1);
    }

    //  Returns the object located at the parameter position.
//  Throws a RuntimeException if the position does not map to a valid position within 
//  the list.
    @Override
    public E get(int position) {
        if (isEmpty()) {
            return null;
        }
        if (position < 0 || position >= size) {
            throw new NoSuchElementException("Invalid position");
        }
        return arr[position];
    }

    //  Returns the list object that matches the parameter, and null if the list is empty
//  or if the element is not in the list.  If obj matches more than one element, 
//  the element with the lowest position is returned.
    @Override
    public E get(E obj) {
        if (isEmpty()) {
            return null;
        }
        for (int i = 0; i < size; i++) {
            if (obj.equals(arr[i])) {
                return arr[i];
            }
        }
        return null;
    }

    //  Returns the position of the first element that matches the parameter obj
//  and -1 if the item is not in the list.
    @Override
    public int find(E obj) {
        for (int i = 0; i < size; i++) {
            if (obj.equals(arr[i])) {
                return i;
            }
        }
        return -1;
    }

    //  Returns true if the parameter object obj is in the list, false otherwise.
    @Override
    public boolean contains(E obj) {
        for (int i = 0; i < size; i++) {
            if (obj.equals(arr[i])) {
                return true;
            }
        }
        return false;
    }

    //  The list is returned to an empty state.
    @Override
    public void clear() {
        size = 0;
    }

    //  Returns true if the list is empty, otherwise false
    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    //  Returns true if the list is full, otherwise false.  
    @Override
    public boolean isFull() {
        return size >= arr.length;
    }

    //  Returns the number of Objects currently in the list.
    @Override
    public int size() {
        return size;
    }

    //  Returns an Iterator of the values in the list, presented in
//  the same order as the list.
    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            // current index
            private int index = 0;
            
            // return true when the next object is in the list
            @Override
            public boolean hasNext() {
                return index < size && arr[index] != null;
            }
            
            // get the next object in the list
            @Override
            public E next() {
                return arr[index++];
            }
        };

        return it;
    }

}
