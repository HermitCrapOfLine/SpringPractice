package rambdaex.ex07;

import java.util.Arrays;
import java.util.List;

import rambdaex.ex05.Student;

public class LambdaEx {

	private static List<Student> list = Arrays.asList(
			new Student("홍길동", "남자", 90),
			new Student("황순자", "여자", 80),
			new Student("권이슬", "여자", 59),
			new Student("고윤슬", "여자", 70)
		);
	
	public static void main(String[] args) {
		for (Student s : list) {
			System.out.println(s);
		}
		
		System.out.println();
		
		list.forEach(s -> System.out.println(s));
		System.out.println();
		
		list.forEach(System.out::println);
		
		list.forEach(LambdaEx::deco);
		
	}
	
	public static void deco(Student s) {
		System.out.println("========");
		System.out.println(s.getName());
		System.out.println("========");
	}

}
