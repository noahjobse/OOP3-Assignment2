package implementations;

import utilities.ListADT;
import utilities.Iterator;

import java.util.ArrayList;

public class MyDLL<E> implements ListADT<E> {
    private ArrayList<E> list = new ArrayList<>();

    @Override
    public boolean add(E toAdd) {
        if (toAdd == null) throw new NullPointerException();
        return list.add(toAdd);
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }  

    @Override public int size() { return list.size(); }
    @Override public void clear() {}
    @Override public boolean add(int index, E toAdd) { return false; }
    @Override public boolean addAll(ListADT<? extends E> toAdd) { return false; }
    @Override public E remove(int index) { return null; }
    @Override public E remove(E toRemove) { return null; }
    @Override public E set(int index, E toChange) { return null; }
    @Override public boolean isEmpty() { return list.isEmpty(); }
    @Override public boolean contains(E toFind) { return false; }
    @Override public E[] toArray(E[] toHold) { return null; }
    @Override public Object[] toArray() { return null; }
    @Override public Iterator<E> iterator() { return null; }
}