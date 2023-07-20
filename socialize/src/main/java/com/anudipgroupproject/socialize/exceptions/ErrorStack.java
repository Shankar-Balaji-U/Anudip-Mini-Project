package com.anudipgroupproject.socialize.exceptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ErrorStack implements List<Object> {
    private List<Object> internalList;

    public ErrorStack() {
        internalList = new ArrayList<>();
    }

    @Override
    public int size() {
        return internalList.size();
    }

    @Override
    public boolean isEmpty() {
        return internalList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return internalList.contains(o);
    }

    @Override
    public Object[] toArray() {
        return internalList.toArray();
    }

    @Override
    public <Z> Z[] toArray(Z[] a) {
        return internalList.toArray(a);
    }

    @Override
    public boolean add(Object e) {
        if (e == null) {
            throw new IllegalArgumentException("Error: Null elements are not allowed.");
        }
        return internalList.add(e);
    }
    
    @Override
    public void add(int index, Object e) {
    	if (e == null) {
            throw new IllegalArgumentException("Error: Null elements are not allowed.");
        }
        internalList.add(index, e);
    }

    @Override
    public boolean remove(Object o) {
        return internalList.remove(o);
    }

    // Other List interface methods can be implemented similarly
    // ...

    // Additional custom methods for error handling
    public Object getSafe(int index) {
        if (index < 0 || index >= internalList.size()) {
            throw new IndexOutOfBoundsException("Error: Index is out of bounds.");
        }
        return internalList.get(index);
    }

    public void clearAll() {
        internalList.clear();
    }

    @Override
    public Iterator<Object> iterator() {
        return internalList.iterator();
    }

	@Override
	public boolean containsAll(Collection<?> c) {
		return internalList.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends Object> c) {
		if (c.contains(null)) {
	        throw new IllegalArgumentException("Error: Null elements are not allowed.");
	    }
	    return internalList.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends Object> c) {
		if (c.contains(null)) {
	        throw new IllegalArgumentException("Error: Null elements are not allowed.");
	    }
	    return internalList.addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return internalList.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return internalList.retainAll(c);
	}

	@Override
	public void clear() {
		internalList.clear();
	}

	@Override
	public Object get(int index) {
		return internalList.get(index);
	}

	@Override
	public Object set(int index, Object element) {
	    if (element == null) {
	        throw new IllegalArgumentException("Error: Null elements are not allowed.");
	    }
	    return internalList.set(index, element);
	}

	@Override
	public Object remove(int index) {
		return internalList.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		return internalList.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return internalList.lastIndexOf(o);
	}

	@Override
	public ListIterator<Object> listIterator() {
		return internalList.listIterator();
	}

	@Override
	public ListIterator<Object> listIterator(int index) {
		return internalList.listIterator(index);
	}

	@Override
	public List<Object> subList(int fromIndex, int toIndex) {
		return internalList.subList(fromIndex, toIndex);
	}
}
