package rambdaex.ex04;

import java.util.function.Consumer;

public class ConsumerExample {

	
	public ConsumerExample() {
		Consumer<String> consumer = t -> System.out.println(t + "8");
		consumer.accept("java");
		
	
	}
	
}
