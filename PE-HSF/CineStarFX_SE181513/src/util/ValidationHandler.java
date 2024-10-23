package util;

public abstract class ValidationHandler<T> {

	public abstract T valid(T data) throws Exception ;
	
}
