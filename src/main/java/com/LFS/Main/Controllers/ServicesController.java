package com.LFS.Main.Controllers;

import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.LFS.Main.Services.*;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;


@RestController
public class ServicesController {

	private final Services Service;
	
	public ServicesController(Services Service) {
		this.Service = Service;
	}
	
	
	@GetMapping("/get")
	public String helloWorld() {
		return Service.HelloService("Enzo");
	}
	
	@PostMapping("/create")
	public String Create(@RequestBody User user) throws InterruptedException, ExecutionException {	
		
		return Service.PostService(user);
	}
	
	@DeleteMapping("/delete") 
	public String Delete(@RequestParam String userId) throws InterruptedException, ExecutionException {
		return Service.DeleteService(userId);
	}
	
	@PutMapping("/Update")
	public String Update(@RequestBody User user) throws InterruptedException, ExecutionException {
		return Service.UpdateService(user);
	}
	
	@GetMapping("/{id}")
	public User GetById(@RequestParam String userId) throws InterruptedException, ExecutionException {
		return Service.GetByIdService(userId);
	}
	
}
