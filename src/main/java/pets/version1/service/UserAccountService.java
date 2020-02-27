package pets.version1.service;

import java.util.List;
import java.util.Set;

import pets.version1.dto.NewUserAccountDto;
import pets.version1.dto.UserEditDto;
import pets.version1.dto.UserProfileDto;

public interface UserAccountService {
	
	UserProfileDto registerUser(NewUserAccountDto newUserAccountDto); //add user
	
	NewUserAccountDto loginUser(String token);
	
	NewUserAccountDto getUser(String token, String email);
	
	NewUserAccountDto removeUser(String token);
	
	NewUserAccountDto editUser(UserEditDto userEditDto, String token); //update user
	
    List<String> addRole(String email,String role, String token);
    
    List<String> removeRole(String email,String role, String token);
    
    void changePassword(String token, String password);
}
