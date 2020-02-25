package pets.version1.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import pets.version1.dao.MessageRepository;
import pets.version1.dto.NewPostDto;
import pets.version1.dto.PostDto;
import pets.version1.model.Post;
import pets.version1.exception.*;

@Service
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
	
	
	@Override
	public PostDto removePost(String id) {
		Post post = messageRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		messageRepository.delete(post);
		return convertToPostDto(post);
	}

	@Override
	public PostDto updatePost(NewPostDto postUpdateDto, String id) {
		Post post = messageRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		String text = postUpdateDto.getText();
		if (text != null) {
			post.setText(text);
		}
		
		List<String> images = postUpdateDto.getImages();
		if (images != null) {
			images.forEach(post::removeImage);
			images.forEach(post::addImage);
		}
		messageRepository.save(post);
		return convertToPostDto(post);
	}

	
    public List<PostDto> getAllPosts(Integer pageNo, Integer pageSize, String sortBy)
    {
    	Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("id").descending());
 
    	Page<Post> pagedResult = messageRepository.findAll(paging);

            return pagedResult.getContent().stream()
            		.map(this::convertToPostDto)
    				.collect(Collectors.toList());

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