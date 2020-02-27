package pets.version1.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = {"email"})
@Document(collection = "users")
public class UserAccount {
	@Id
	String email;
	String password;
	String avatar;
	String name;
	String phone;
	Boolean block;
	@Singular
	List<String> roles;


	
	public boolean addRole(String role) {
		return roles.add(role);
	}
	
	public boolean removeRole(String role) {
		return roles.remove(role);
	}
	
	public boolean addBlockUser() {
		return block.equals(true);
	}
	
	public boolean removeBlockUser() {
		return block.equals(false);
	}

}

