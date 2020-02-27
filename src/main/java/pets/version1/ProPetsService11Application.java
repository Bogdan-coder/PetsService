package pets.version1;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pets.version1.dao.UserAccountRepository;
import pets.version1.model.UserAccount;


@SpringBootApplication
public class ProPetsService11Application implements CommandLineRunner{
	
	@Autowired
	UserAccountRepository userAccountRepository; 
	public static void main(String[] args) {
		SpringApplication.run(ProPetsService11Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		if(!userAccountRepository.existsById("admin@")) {
			String hashPassword = BCrypt.hashpw("admin@", BCrypt.gensalt());
			UserAccount admin = UserAccount.builder()
								.email("admin@")
								.password(hashPassword)
								.name("Admin")
								.avatar("Admin")
								.phone("Admin")
								.block(false)
								.role("Administrator")
								.role("User")
								.build();
			userAccountRepository.save(admin);
		}

	}

}
