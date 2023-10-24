package rambdaex.ex06;

import java.util.function.ToIntBiFunction;

public class ArgumentMethodReferencesExample {

	public static void main(String[] args) {
		
		ToIntBiFunction<String, String> function;
		
		function = (a, b) -> a.compareToIgnoreCase(b);
		
		function = String :: compareToIgnoreCase;
		System.out.print(function.applyAsInt("Java8", "JAVA8"));
		
	}

}
