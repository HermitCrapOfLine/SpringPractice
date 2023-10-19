package org.galapagos.domain.naver;

import java.io.IOException;
import java.util.Scanner;

import org.galapagos.service.NaverLocalSearch;

import retrofit2.Call;
import retrofit2.Response;

public class LocalSearchEx1 {
	public static void main(String[] args) {
		NaverLocalSearch api = NaverLocalSearch.getService();
		Scanner sc = new Scanner(System.in);
		System.out.print("위치검색 >>");
		try {
			String query = sc.nextLine();
			Call<LocalResult> call = api.searchLocal(query, 5, 1);
			System.out.println(call.request());
			System.out.println(call.request().headers());
			Response<LocalResult> res = call.execute();
			if (res.isSuccessful()) {
				LocalResult result = res.body();
				for(Local x : result.getLocals()) {
					System.out.println(x);
				}
			} else {
				System.out.println("호출 실패");
				System.out.println(res);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
