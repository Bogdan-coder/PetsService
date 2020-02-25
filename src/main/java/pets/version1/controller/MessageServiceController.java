package pets.version1.controller;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import pets.version1.dto.NewPostDto;
import pets.version1.dto.PostDto;
import pets.version1.model.Post;
import pets.version1.service.MessageService;


@RestController
@RequestMapping("/message/v1")

public class MessageServiceController {

	@Autowired
	MessageService service;

	@PostMapping("/post/owner/{ownerId}")
	public PostDto addPost(@RequestBody NewPostDto newPost, @PathVariable("ownerId") String ownerId) {
		return service.addPost(newPost, ownerId);
	}

	@GetMapping("/post/{id}")
	public PostDto getPost(@PathVariable String id) {
		return service.getPost(id);
	}

	@DeleteMapping("/post/{id}")
	public PostDto removePost(@PathVariable String id) {
		return service.removePost(id);
	}

	@PutMapping("/post/{id}")
	public PostDto updatePost(@PathVariable String id, @RequestBody NewPostDto postUpdateDto) {
		return service.updatePost(postUpdateDto, id);
	}
	

    @GetMapping("/post/allposts")
    public ResponseEntity<List<PostDto>> getAllPosts(
                        @RequestParam(defaultValue = "0") Integer pageNo, 
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(defaultValue = "id") String sortBy) 
    {
        List<PostDto> list = service.getAllPosts(pageNo, pageSize, sortBy);
 
        return new ResponseEntity<List<PostDto>>(list, new HttpHeaders(), HttpStatus.OK); 
    }
    
    
//    @PutMapping("/post/complain/{id}")
//    

//	@PutMapping("/post/{id}/like")
//	public boolean addLike(@PathVariable String id) {
//		return service.addLike(id);
//	}
//
//	@PutMapping("/post/{id}/comment/{author}")
//	public PostDto addComment(@PathVariable String id, @PathVariable String author, @RequestBody NewCommentDto newCommentDto) {
//		return service.addComment(id, author, newCommentDto);
//	}
//
//	@GetMapping("/posts/author/{author}")
//	public Iterable<PostDto> getPostsByAuthor(@PathVariable String author) {
//		return service.findPostsByAuthor(author);
//	}
//	
//	@PostMapping("/posts/tags")
//	public Iterable<PostDto> findPostsByTags(@RequestBody List<String> tags) {
//		return service.findPostsByTags(tags);
//	}
//	
//	@PostMapping("/posts/period")
//	public Iterable<PostDto> findPostsByPeriod(@RequestBody DatePeriodDto datePeriodDto) {
//		return service.findPostsCreatedBetweenDates(datePeriodDto);
//		
//	}
//	
//	@GetMapping("/post/{id}/comments")
//	public Iterable<CommentDto> findAllPostComments(@PathVariable String id){
//		return service.findAllPostComments(id);
//	}
//	
//	@GetMapping("/post/{id}/author/{author}/comments")
//	public Iterable<CommentDto> findAllPostCommentsByAuthor(@PathVariable String id, @PathVariable String author) {
//		return service.findAllPostCommentsByAuthor(id, author);
//	}
	
}
