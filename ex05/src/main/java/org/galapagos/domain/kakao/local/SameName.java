package org.galapagos.domain.kakao.local;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class SameName {

	@SerializedName("region")
	List<String> region;

	@SerializedName("keyword")
	String keyword;

	@SerializedName("selected_region")
	String selectedRegion;

}