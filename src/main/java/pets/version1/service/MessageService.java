package pets.version1.service;

import java.util.List;

import pets.version1.dto.NewPostDto;
import pets.version1.dto.PostDto;
import pets.version1.model.Post;


public interface MessageService {

	PostDto addPost(NewPostDto newPost, String ownerId);

	PostDto getPost(String id);

//	PostDto removePost(String id);
//
//	PostDto updatePost(NewPostDto postUpdateDto, String id);
//
//	Iterable<PostDto> findPostsByAuthor(String author);
//	
//	Iterable<PostDto> findPostsByTags(List<String> tags);

	
	
}
