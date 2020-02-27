package pets.version1.dto;


import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewUserAccountDto {
	String email;
	String password;
	String avatar;
	String name;
	String phone;
	Boolean block;
	List<String> roles;
}
