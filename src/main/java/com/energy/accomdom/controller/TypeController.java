package com.energy.accomdom.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.energy.accomdom.entity.Type;
import com.energy.accomdom.service.TypeService;

@RestController
@RequestMapping("accomdom-types")
@CrossOrigin(origins = "http://localhost:3000")
public class TypeController {
	private final TypeService typeService;

	public TypeController(TypeService typeService) {
		this.typeService = typeService;
	}
	
	@GetMapping()
	public ResponseEntity<List<Type>> getAllTypes(){
		List<Type> types = typeService.getAllTypes();
		
		return new ResponseEntity<>(types, HttpStatus.OK);
	}
}
