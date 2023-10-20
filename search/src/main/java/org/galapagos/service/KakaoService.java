package org.galapagos.service;

import org.galapagos.domain.kakao.book.BookResult;
import org.galapagos.domain.kakao.local.LocalResult;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface KakaoService {
	
	// 인터페이스의 변수는 상수만 가능하다. 앞에 static final 생략
	String BASE_URL = "https://dapi.kakao.com/";
	
	@GET("v3/search/book")
	@Headers({ "Authorization: KakaoAK d1d68ccbc76688be2f03c65d1ea07a31" })
	Call<BookResult> searchBook(@Query("query") String query, @Query("size") int size, 
			@Query("page") int page);
	
	@GET("v2/local/search/keyword")
	@Headers({ "Authorization: KakaoAK d1d68ccbc76688be2f03c65d1ea07a31" })
	Call<LocalResult> searchLocal(@Query("query") String query, @Query("size") int size, 
			@Query("page") int page);
	
	
	public static KakaoService getService() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		
		return retrofit.create(KakaoService.class);
	}
}
