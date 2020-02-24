package pets.version1.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;


@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = { "id" })
@ToString
@Document(collection = "posts")
public class Post {
	
	String id;
	@Setter
	String text;
	String ownerId;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	LocalDateTime postDate;
	@Setter
	@Singular
	List<String> images;

	public Post(String text, String ownerId, List<String> images) {
	    this.text = text;
		this.ownerId = ownerId;
		this.images = images;
		postDate = LocalDateTime.now();
		
	}

	public boolean addImage(String image) {
		return images.add(image);
	}

	public boolean removeImage(String image) {
		return images.remove(image);
	}
}
