package com.LFS.EnzoKevin.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Services.Services;

@RestController
@RequestMapping("/hello-world")
public class HelloWorldController {

	private Services Service;
	
	public void ServicesController(Services Service) {
		this.Service = Service;
	}
	
	
	@GetMapping
	public String helloWorld() {
		return Service.HelloService("Enzo");
	}
}
