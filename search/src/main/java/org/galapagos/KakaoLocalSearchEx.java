package org.galapagos;

import java.io.IOException;
import java.util.Scanner;

import org.galapagos.domain.kakao.local.Local;
import org.galapagos.domain.kakao.local.LocalResult;
import org.galapagos.service.KakaoService;

import retrofit2.Call;
import retrofit2.Response;

public class KakaoLocalSearchEx {
	public static void main(String[] args) {
		
		KakaoService api = KakaoService.getService();
		Scanner sc = new Scanner(System.in);
		
		System.out.print("위치검색 >>");
		try {
			String query = sc.nextLine();
			Call<LocalResult> call = api.searchLocal(query, 10, 1);
			Response<LocalResult> res = call.execute();
			if (res.isSuccessful()) {
				LocalResult result = res.body();
				for(Local local : result.getLocals()) {
					System.out.println(local);
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
