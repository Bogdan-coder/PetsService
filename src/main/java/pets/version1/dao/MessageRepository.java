package pets.version1.dao;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import pets.version1.model.Post;

public interface MessageRepository extends MongoRepository<Post, String>, PagingAndSortingRepository<Post, String> {

//    Stream<Post> findByAuthor(String author);
//	
//	Stream<Post> findByTagsIn(List<String> tags);
//	
//	Stream<Post> findByDateCreatedBetween(LocalDate from, LocalDate to);
}
