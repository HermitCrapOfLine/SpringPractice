package org.galapagos.service;

import org.galapagos.domain.naver.LocalResult;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface NaverLocalSearch {

	String BASE_URL = "https://openapi.naver.com/";
	
	@GET("v1/search/local")
	@Headers({
		"X-Naver-Client-Id:1yYw4JtNOtPlf1vwPdBI",
		"X-Naver-Client-Secret:oeiass9rI1"
	})
	Call<LocalResult> searchLocal(@Query("query") String query, @Query("display") int display,
			@Query("start") int start);
	
	public static NaverLocalSearch getService() {
		Retrofit retrofit = new Retrofit.Builder()
						.baseUrl(BASE_URL)
						.addConverterFactory(GsonConverterFactory.create())
						.build();
		
		return retrofit.create(NaverLocalSearch.class);
	}
}
