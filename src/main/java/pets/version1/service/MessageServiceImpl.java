package pets.version1.service;

import org.springframework.beans.factory.annotation.Autowired;

import pets.version1.dao.MessageRepository;
import pets.version1.dto.NewPostDto;
import pets.version1.dto.PostDto;
import pets.version1.model.Post;
import pets.version1.exception.*;

public class MessageServiceImpl implements MessageService {

	@Autowired
	MessageRepository messageRepository;

	@Override
	public PostDto addPost(NewPostDto newPost, String ownerId) {
		Post post = new Post(newPost.getText(), ownerId, newPost.getImages());
		post = messageRepository.save(post);
		return convertToPostDto(post);
	}
	
	
	private PostDto convertToPostDto(Post post) {
		return PostDto.builder()
				.id(post.getId())
				.text(post.getText())
				.ownerId(post.getOwnerId())
				.postDate(post.getPostDate())
				.images(post.getImages())
				.build();
	}


	@Override
	public PostDto getPost(String id) {
		Post post = messageRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		return convertToPostDto(post);
	}
	
	

//	@Override
//	public PostDto getPost(String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public PostDto removePost(String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public PostDto updatePost(NewPostDto postUpdateDto, String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public Iterable<PostDto> findPostsByAuthor(String author) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Iterable<PostDto> findPostsByTags(List<String> tags) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}