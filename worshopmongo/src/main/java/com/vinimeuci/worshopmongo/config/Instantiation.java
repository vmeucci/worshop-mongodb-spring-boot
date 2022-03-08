package com.vinimeuci.worshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.vinimeuci.worshopmongo.domain.Post;
import com.vinimeuci.worshopmongo.domain.User;
import com.vinimeuci.worshopmongo.dto.AuthorDTO;
import com.vinimeuci.worshopmongo.dto.CommentDTO;
import com.vinimeuci.worshopmongo.repository.PostRepository;
import com.vinimeuci.worshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("01/03/2022"), "Vamos jogar!", "Estou entrando no Elden Ring", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/04/2020"), "Estou indo tomar a vacina", "Vacinado!",  new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Excelente jogo", sdf.parse("04/03/2022"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Estou viciado", sdf.parse("02/03/2022"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Finalmente estamos vacinados", sdf.parse("10/05/2020"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().add(c3);
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
