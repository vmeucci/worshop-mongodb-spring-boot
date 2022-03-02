package com.vinimeuci.worshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vinimeuci.worshopmongo.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<User>> findAll() {
		User vinicius = new User("1", "Vinicius Meuci", "viniciusmeuci@icloud.com");
		User antonio = new User("2", "Antônio Lisoe", "lisoeant@icloud.com");
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(vinicius, antonio));
		return ResponseEntity.ok().body(list);
		
	}
}
