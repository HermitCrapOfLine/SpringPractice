package rambdaex.ex02;

public class MyFunctionInterfaceEx {

	public static void main(String[] args) {
		
		MyFunctionalInterface fi;
		
		fi = (x) -> {
			int result = x * 5;
			System.out.println(result);
		};
		
		fi.method(5);
		
		fi = (x) -> {System.out.println(x * 5); };
		
		fi.method(5);
		
		fi = x -> System.out.println(x * 5);
		
		fi.method(5);
		
		
		Runnable r = () -> System.out.println("method call4");
		
		r.run();
		
	}

}
