package rambdaex.ex01;

public class Practice {

	public static void main(String[] args) {

		Runnable run = new Runnable() {
			public void run() {
				System.out.println("실행1");
			}
		};
		
		run.run();
		
		run = () -> {System.out.println("실행2");};
		
		run.run();
		
		run = () -> System.out.println("실행3");
		run.run();
	}
}
