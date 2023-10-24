package rambdaex.ex03;

public class Ex02 {
	private String name = "고양이";
	
	
	public Runnable test() {
		String lname = "고길동";
		Runnable r;
		
		
		// Runnable의 익명 구현객체에서 this 확인 
		r = new Runnable() {
			private String name = "러너블 안의 고양이";
			@Override
			public void run() {
				System.out.println(this.name);
			}
		};
		r.run();
		System.out.println(this);
//		lname = "바뀐 엘네임"; 이렇게 바꾸면 에러남
		
//		r = () -> System.out.println(this.name);
		r = () -> System.out.println(lname);
//		r.run();
		
		return r;
		
	}	
	
	public static void main(String[] args) {
		Ex02 t = new Ex02();
		Runnable r = t.test();
		r.run();
	
	}

}
