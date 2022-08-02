package com.devsuperior.bds04.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.bds04.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
}
