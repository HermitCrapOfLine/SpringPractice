package rambdaex.ex05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;



public class PredicateExample {

	private static List<Student> list = Arrays.asList(
				new Student("홍길동", "남자", 90),
				new Student("황순자", "여자", 80),
				new Student("권이슬", "여자", 59),
				new Student("고윤슬", "여자", 70)
			);
	
	public static double avg(Predicate<Student> predicate) {
		int count = 0, sum = 0;
		for (Student student : list) {
			if(predicate.test(student)) {
				count++;
				sum += student.getScore();
			}
		}
		return (double) sum / count;
	}
	
	
	public static void main(String[] args) {
		double maleAvg = avg(t -> t.getSex().equals("남자"));
		System.out.println("남자 평균 점수 : " + maleAvg);
		
		double femaleAvg = avg(t -> t.getSex().equals("여자"));
		System.out.println("여자 평균 점수 : " + femaleAvg);
		
		
		
		// 60점 미만 사람들의 평균 점수
		double under60 = avg(t -> t.getScore() < 60);
		System.out.println("60점 미만 사람들의 평균점수 : " + under60);
		
		
		// 60점 이상 사람들의 평균 점수
		double over60 = avg(t -> t.getScore() > 60);
		System.out.println("60점 이상 사람들의 평균점수 : " + over60);
	
	
		// list를 성적순으로 정렬하세요
		list.sort( (o1, o2) -> o1.getScore() - o2.getScore() );
		System.out.println(list);
		
		
	
	}
}
