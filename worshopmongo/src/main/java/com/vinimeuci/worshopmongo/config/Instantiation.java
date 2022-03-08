package com.vinimeuci.worshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.vinimeuci.worshopmongo.domain.Post;
import com.vinimeuci.worshopmongo.domain.User;
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
		
		Post post1 = new Post(null, sdf.parse("01/03/2022"), "Vamos jogar!", "Estou entrando no Elden Ring", maria);
		Post post2 = new Post(null, sdf.parse("23/04/2020"), "Estou indo tomar a vacina", "Vacinado!", maria);
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		postRepository.saveAll(Arrays.asList(post1, post2));
	}

}
