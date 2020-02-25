package pets.version1.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import pets.version1.model.*;

public interface UserAccountRepository extends MongoRepository<UserAccount, String> {

}
