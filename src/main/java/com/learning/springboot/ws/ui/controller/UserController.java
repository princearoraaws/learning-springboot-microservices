package com.learning.springboot.ws.ui.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.springboot.ws.exceptions.BusinessException;
import com.learning.springboot.ws.ui.model.request.UserDetailRequestModel;
import com.learning.springboot.ws.ui.model.response.User;

@RestController
@RequestMapping("/users")
public class UserController {

	Map usersMap;

	@GetMapping()
	public String getUsers(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "limit", defaultValue = "1", required = false) String limit) {
		return ("getUsers was called , page=" + page + " limit=" + limit);
	}

	@GetMapping(path = "/{userid}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity getUser(@PathVariable String userid) {
//		String test=null;
//		int i=test.length();
		
		//if(true) throw new BusinessException("This is a custom business expcetion" );

		if (null != usersMap && null != usersMap.get(userid))
			return new ResponseEntity(usersMap.get(userid), HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity createtUser(@Valid @RequestBody UserDetailRequestModel userDetails) {
		User newUser = new User();
		newUser.setFirstName(userDetails.getFirstName());
		newUser.setLastName(userDetails.getLastName());
		newUser.setEmail(userDetails.getEmail());

		if (usersMap == null) {
			usersMap = new HashMap();
		}

		String id = UUID.randomUUID().toString();
		newUser.setId(id);
		usersMap.put(id, newUser);

		return new ResponseEntity(newUser, HttpStatus.OK);
	}

	@PutMapping(path = "/{userid}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity updateUser(@PathVariable String userid, @RequestBody UserDetailRequestModel userDetails) {

		User storedUserData = (User) usersMap.get(userid);

		storedUserData.setFirstName(userDetails.getFirstName());
		storedUserData.setLastName(userDetails.getLastName());
		usersMap.put(userid, storedUserData);

		return new ResponseEntity(storedUserData, HttpStatus.OK);

	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity deleteUser(@PathVariable String id) {

		usersMap.remove(id);
		return ResponseEntity.noContent().build();

	}

}
