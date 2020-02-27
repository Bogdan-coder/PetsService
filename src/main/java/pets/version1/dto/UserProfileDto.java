package pets.version1.dto;

import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class UserProfileDto {
	String name;
	String avatar;
	@Singular
	List<String> roles;
}
