package plugmod.util.java;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class EnumList<T extends Enum<T>> implements List<T> {
	
	private final Class<T> type;
	private boolean[] data;
	
	public EnumList(Class<T> type) {
		this.type = type;
		this.data = new boolean[type.getEnumConstants().length];
	}
	
	@Override
	public boolean add(T e) {
		return data[e.ordinal()] = true;
	}
	
	public boolean addObj(Object e) {
		if (type.isInstance(e)) {
			return data[type.cast(e).ordinal()] = true;
		}
		return false;
	}

	@Override
	public void add(int index, T element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends T> es) {
		Wrapper<Boolean> altered = new Wrapper<Boolean>(false);
		es.forEach(e -> {
			add(e);
			altered.set(true);
		});
		return altered.get();
	}
	
	public boolean addAllObj(Collection<?> es) {
		Wrapper<Boolean> altered = new Wrapper<Boolean>(false);
		es.forEach(e -> {
			addObj(e);
			altered.set(true);
		});
		return altered.get();
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		for (int i = 0; i < data.length; i++) {
			data[i] = false;
		}
	}

	@Override
	public boolean contains(Object e) {
		if (type.isInstance(e)) {
			return data[type.cast(e).ordinal()];
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> es) {
		return es.stream().allMatch(e -> contains(e));
	}

	@Override
	public T get(int index) {
		return type.getEnumConstants()[index];
	}

	@Override
	public int indexOf(Object e) {
		if (type.isInstance(e)) {
			return type.cast(e).ordinal();
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		for (int i = 0; i < data.length; i++) {
			if (data[i]) {
				return false;
			}
		}
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object e) {
		if (type.isInstance(e)) {
			data[type.cast(e).ordinal()] = false;
			return true;
		}
		return false;
	}
	
	public boolean remove(T e) {
		return data[e.ordinal()] = false;
	}

	@Override
	public T remove(int index) {
		if (data[index]) {
			data[index] = false;
			return type.getEnumConstants()[index];
		}
		return null;
	}

	@Override
	public boolean removeAll(Collection<?> es) {
		Wrapper<Boolean> altered = new Wrapper<Boolean>(false);
		es.forEach(e -> {
			remove(e);
			altered.set(true);
		});
		return altered.get();
	}

	@Override
	public boolean retainAll(Collection<?> es) {
		for (int i = 0; i < data.length; i++) {
			data[i] = es.contains(type.getEnumConstants()[i]);
		}
		return true;
	}

	@Override
	public T set(int index, T element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		return data.length;
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

}
