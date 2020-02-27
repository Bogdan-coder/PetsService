package pets.version1.controller;


import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pets.version1.dto.UserEditDto;
import pets.version1.dto.UserProfileDto;
import pets.version1.dto.NewUserAccountDto;
import pets.version1.service.*;

@RestController
@RequestMapping("/account/v1")
public class UserAccountController {
	
	@Autowired
	UserAccountService userAccountService;
	
	@PostMapping("/user")
	public UserProfileDto registerUser(@RequestBody NewUserAccountDto newUserAccountDto) {
		return userAccountService.registerUser(newUserAccountDto);
	}
	
    @PostMapping("/user/login/{token}")
	public NewUserAccountDto loginUser(@RequestHeader("Authorization") String token)
	{
		return userAccountService.loginUser(token);
	}
    
    @GetMapping("/user/login/{token}/loginAnyUser/{email}")
	public NewUserAccountDto getUser(@RequestHeader("Authorization") String token, @PathVariable String email)
	{
		return userAccountService.getUser(token, email);
	}
    
    
    @DeleteMapping("/user/login/{token}")
	public NewUserAccountDto deleteLogin(@RequestHeader("Authorization") String token)
	{
		return userAccountService.removeUser(token);
	}    

    
    @PutMapping("user/login/{token}")
	public NewUserAccountDto updateLogin(@RequestBody UserEditDto userEditDto,  @RequestHeader("Authorization") String token)
	{
		return userAccountService.editUser(userEditDto, token);
	}

    @PutMapping("/login/{token}/user/{login}/role/{role}")
	public List<String> addRoles(@PathVariable String login, @PathVariable String role,  @RequestHeader("Authorization") String token)
	{
		return userAccountService.addRole(login, role, token);
	}
    
    @DeleteMapping("/login/{token}/user/{login}/role/{role}")
	public List<String> deleteRoles(@PathVariable String login, @PathVariable String role,  @RequestHeader("Authorization") String token)
	{
		return userAccountService.removeRole(login, role, token);
	}
    
    
    @PutMapping("/login/{token}/password/{password}")
	public void updatePassword(@PathVariable String password,  @RequestHeader("Authorization") String token)
	{
		return;
	}
    
}
