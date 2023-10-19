package org.galapagos;

import java.io.IOException;
import java.util.Scanner;

import org.galapagos.domain.kakao.book.Book;
import org.galapagos.domain.kakao.book.BookResult;
import org.galapagos.service.KakaoService;

import retrofit2.Call;
import retrofit2.Response;

public class BookSearchEx1 {
	
	public static void main(String[] args) {
		KakaoService api = KakaoService.getService();
		Scanner sc = new Scanner(System.in);
		
		try {
			System.out.print("도서검색 >>");
			String bookName = sc.next();
			Call<BookResult> call = api.searchBook(bookName, 10, 1); // 요청을 할 수 있는 Call 객체 리턴
//			System.out.println(call.request());			// 요청 URL 확인
//			System.out.println(call.request().headers()); // 헤더 확인 - 인증 키
			
			Response<BookResult> res = call.execute(); // 서버에 요청 전송 동기식 : execute()
														// 비동기식 : enqueue(callback)
			if (res.isSuccessful()) {
				BookResult result = res.body();
				
				System.out.println(result.getMeta());
				for(Book book : result.getBooks()) {
					System.out.println(book.getTitle());
				}
			}else {
				System.out.println("호출 실패");
				System.out.println(res);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
