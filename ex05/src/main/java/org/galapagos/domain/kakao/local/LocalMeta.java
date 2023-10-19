package org.galapagos.domain.kakao.local;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class LocalMeta {

	@SerializedName("same_name")
	SameName sameName;

	@SerializedName("pageable_count")
	int pageableCount;

	@SerializedName("total_count")
	int totalCount;

	@SerializedName("is_end")
	boolean isEnd;

}