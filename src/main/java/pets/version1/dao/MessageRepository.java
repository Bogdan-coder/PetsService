package pets.version1.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;

import pets.version1.model.Post;

public interface MessageRepository extends MongoRepository<Post, String> {

//    Stream<Post> findByAuthor(String author);
//	
//	Stream<Post> findByTagsIn(List<String> tags);
//	
//	Stream<Post> findByDateCreatedBetween(LocalDate from, LocalDate to);
}
