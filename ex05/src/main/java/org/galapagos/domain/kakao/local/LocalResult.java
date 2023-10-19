package org.galapagos.domain.kakao.local;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class LocalResult {

	@SerializedName("meta")
	LocalMeta meta;

	@SerializedName("documents")
	List<Local> locals;
    
}