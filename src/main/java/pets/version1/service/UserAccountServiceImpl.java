package pets.version1.service;

import java.util.List;
import java.util.Set;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pets.version1.configuration.AccountConfiguration;
import pets.version1.configuration.UserCredentials;
import pets.version1.dao.UserAccountRepository;
import pets.version1.exception.UserAuthenticationException;
import pets.version1.model.UserAccount;
import pets.version1.dto.NewUserAccountDto;
import pets.version1.dto.UserEditDto;
import pets.version1.dto.UserProfileDto;
import pets.version1.exception.ForbiddenException;
import pets.version1.exception.UserExistsException;
import pets.version1.exception.UserNotFoundException;


@Service
public class UserAccountServiceImpl implements UserAccountService {
	
	@Autowired
	UserAccountRepository accountRepository;
	
	@Autowired
	AccountConfiguration AccountConfiguration;

	@Override
	public UserProfileDto registerUser(NewUserAccountDto newUserAccountDto) {
		if (accountRepository.existsById(newUserAccountDto.getEmail())) {
			throw new UserExistsException();
		}
		
		String hashPassword = BCrypt.hashpw(newUserAccountDto.getPassword(), BCrypt.gensalt()); 
		UserAccount userAccount = UserAccount.builder()
									.email(newUserAccountDto.getEmail())
									.password(hashPassword)
									.name(newUserAccountDto.getName())
									.avatar(newUserAccountDto.getAvatar())
									.block(false)
									.phone(newUserAccountDto.getPhone())
									.role("User")
									.build();
		String avatar = userAccount.getAvatar();
		if (avatar != null) {
			accountRepository.save(userAccount);
			
		}
		userAccount.setAvatar("https://www.gravatar.com/avatar/0?d=mp");
		accountRepository.save(userAccount);

		return userAccountToUserProfileDto(userAccount);
	}

	private UserProfileDto userAccountToUserProfileDto(UserAccount userAccount) {
		return UserProfileDto.builder()
				.name(userAccount.getName())
				.avatar(userAccount.getAvatar())
				.roles(userAccount.getRoles())
				.build();
	}

	@Override
	public NewUserAccountDto loginUser(String token) {
		UserCredentials userCredentials = AccountConfiguration.tokenDecode(token);
		UserAccount userAccount = accountRepository.findById(userCredentials.getEmail()).orElseThrow(UserAuthenticationException::new);
		if(!BCrypt.checkpw(userCredentials.getPassword(), userAccount.getPassword()))
		{
			throw new ForbiddenException();
		}
		return userAccountToNewUserAccountDto(userAccount);
	}
	
	private NewUserAccountDto userAccountToNewUserAccountDto(UserAccount userAccount) {
		return NewUserAccountDto.builder()
				.email(userAccount.getEmail())
				.name(userAccount.getName())
				.avatar(userAccount.getAvatar())
				.phone(userAccount.getPhone())
				.block(userAccount.getBlock())
				.roles(userAccount.getRoles())
				.build();
	}
	
	
	@Override
	public NewUserAccountDto getUser(String token, String email) {
		UserCredentials userCredentials = AccountConfiguration.tokenDecode(token);
		UserAccount userAccount = accountRepository.findById(userCredentials.getEmail()).orElseThrow(UserAuthenticationException::new);
		if(!BCrypt.checkpw(userCredentials.getPassword(), userAccount.getPassword()))
		{
			throw new ForbiddenException();
		}
		
		UserAccount personAccount = accountRepository.findById(email).orElseThrow(() -> new UserNotFoundException(email));
				
		return userAccountToNewUserAccountDto(personAccount);
	}
	
	@Override
	public NewUserAccountDto removeUser(String token) {
		UserCredentials userCredentials = AccountConfiguration.tokenDecode(token);
		UserAccount userAccount = accountRepository.findById(userCredentials.getEmail()).orElseThrow(UserAuthenticationException::new);
		if(!BCrypt.checkpw(userCredentials.getPassword(), userAccount.getPassword()))
		{
			throw new ForbiddenException();
		}
		accountRepository.delete(userAccount);
		
		return userAccountToNewUserAccountDto(userAccount);
	}

	@Override
	public NewUserAccountDto editUser(UserEditDto userEditDto, String token) {
		UserCredentials userCredentials = AccountConfiguration.tokenDecode(token);
		UserAccount userAccount = accountRepository.findById(userCredentials.getEmail()).orElseThrow(UserAuthenticationException::new);
		if(!BCrypt.checkpw(userCredentials.getPassword(), userAccount.getPassword()))
		{
			throw new ForbiddenException();
		}
		
		accountRepository.save(userAccount).setName(userEditDto.getName());
		accountRepository.save(userAccount).setAvatar(userEditDto.getAvatar());
		accountRepository.save(userAccount).setPhone(userEditDto.getPhone());
		accountRepository.save(userAccount);

		return userAccountToNewUserAccountDto(userAccount);
	}

	@Override
	public List<String> addRole(String email, String role, String token) {
		
		UserCredentials userCredentials = AccountConfiguration.tokenDecode(token);
		UserAccount userAccount = accountRepository.findById(userCredentials.getEmail()).orElseThrow(UserAuthenticationException::new);
		
		if(userAccount.getEmail().equals("admin@"))
		{
		
			if(BCrypt.checkpw(userCredentials.getPassword(), userAccount.getPassword()))
			{
				UserAccount personAccount = accountRepository.findById(email).orElseThrow(UserAuthenticationException::new);
				personAccount.addRole(role);
				accountRepository.save(personAccount);
				return personAccount.getRoles();
			}
		
			throw new ForbiddenException();
		}
		
		throw new ForbiddenException();

	}

	@Override
	public List<String> removeRole(String email, String role, String token) {
		
		UserCredentials userCredentials = AccountConfiguration.tokenDecode(token);
		UserAccount userAccount = accountRepository.findById(userCredentials.getEmail()).orElseThrow(UserAuthenticationException::new);
		
		if(userAccount.getEmail().equals("admin@"))
		{
		
			if(BCrypt.checkpw(userCredentials.getPassword(), userAccount.getPassword()))
			{
				UserAccount personAccount = accountRepository.findById(email).orElseThrow(UserAuthenticationException::new);
				personAccount.removeRole(role);
				accountRepository.save(personAccount);
				return personAccount.getRoles();
			}
		
			throw new ForbiddenException();
		}
		
		throw new ForbiddenException();

	}
	
	
	@Override
	public void changePassword(String token, String password) {
		UserAccount userAccount = accountRepository.findById(token).get();
		String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		userAccount.setPassword(hashPassword);
		accountRepository.save(userAccount);

		
	}

}

