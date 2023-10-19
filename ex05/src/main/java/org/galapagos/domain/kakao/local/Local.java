package org.galapagos.domain.kakao.local;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Local {

	@SerializedName("place_name")
	String placeName;

	@SerializedName("distance")
	String distance;

	@SerializedName("place_url")
	String placeUrl;

	@SerializedName("category_name")
	String categoryName;

	@SerializedName("address_name")
	String addressName;

	@SerializedName("road_address_name")
	String roadAddressName;

	@SerializedName("id")
	String id;

	@SerializedName("phone")
	String phone;

	@SerializedName("category_group_code")
	String categoryGroupCode;

	@SerializedName("category_group_name")
	String categoryGroupName;

	@SerializedName("x")
	String x;

	@SerializedName("y")
	String y;
}
