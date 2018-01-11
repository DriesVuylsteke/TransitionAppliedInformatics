package sets;

import java.util.Optional;

public class Experiment {
	public static void main(String[] args) {
		UnorderedCollection<Double> numbers = new Bag<>();
		// Some elements added here. 
		numbers.add(4.0); 
		numbers.add(-3.0);
		numbers.add(4.0); 
		numbers.add(1.0);
		// Useful methods that can be applied to streams: 
		// Stream<T>
		
		// filter(Predicate<? super T> predicate) 
		// The functional interface
		// Predicate<T> defines the method 
		// boolean test(T t) 
		
		// <R>Stream<R>
		// map(Function<? super T,? extends R> mapper) 
		// The functional interface
		// Function<T,R> defines the method 
		// R apply(T t) 
		
		// Optional<T>
		// reduce(BinaryOperator<T> accumulator) 
		// The functional interface
		// BinaryOperator<T> defines the method 
		// T apply(T l, T r)
		
		Optional squarePositive = numbers.stream()
				.filter(num -> num > 0)
				.map(num -> num * num)
				.reduce((v,n) -> v + n);
		System.out.println(squarePositive);
	}
}
