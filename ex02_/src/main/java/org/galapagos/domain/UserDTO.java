package org.galapagos.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private String userid;
	private String password;
	private String password2;
	private String email;
	private String[] hobbies;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthDate;
	
}
