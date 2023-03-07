package com.user.service.UserService.Exception;

public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException() {
		super("resource not found : ");
	}
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}

}
