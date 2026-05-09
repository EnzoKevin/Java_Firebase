package com.LFS.Main.Controllers;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	private String name;
	private String email;
	private Double age;
	private String corporation;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Double getAge() {
		return age;
	}
	
	public void setAge(Double age) {
		this.age = age;
	}
	
	public String getCorporation() {
		return corporation;
	}
	
	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}
	
	
}
