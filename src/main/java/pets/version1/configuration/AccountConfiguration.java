package pets.version1.configuration;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

import pets.version1.exception.*;

@Configuration
@ManagedResource
public class AccountConfiguration {
	
	public UserCredentials tokenDecode(String token) {
		try {
			int pos = token.indexOf(" ");
			token = token.substring(pos+1);
			String credential = new String(Base64.getDecoder().decode(token));
			String[] credentials = credential.split(":");
			return new UserCredentials(credentials[0], credentials[1]);
		} catch (Exception e) {
			throw new UserAuthenticationException();
		}
		
	}

}
