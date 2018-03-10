package plugmod.util.java;

public class Wrapper<T> {
	
	private T wrapped;
	
	public Wrapper(T wrapped) {
		this.set(wrapped);
	}
	
	public T get() {
		return wrapped;
	}
	
	public void set(T wrapped) {
		this.wrapped = wrapped;
	}
	
}
