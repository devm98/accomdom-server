package com.energy.accomdom.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.energy.accomdom.entity.Accomdom;
import com.energy.accomdom.model.request.AccomdomModelRequest;
import com.energy.accomdom.service.AccomdomService;

@RestController
@RequestMapping(value = "accomdoms")
@CrossOrigin(origins = "http://localhost:3000")
public class AccomdomController {
	private final AccomdomService accomdomService;

	public AccomdomController(AccomdomService accomdomService) {
		this.accomdomService = accomdomService;
	}

	@GetMapping(params = {})
	public ResponseEntity<List<Accomdom>> getAccomdoms(@RequestParam(required = false) Long id) {
		List<Accomdom> accomdoms;

		if (id != null) {
			accomdoms = accomdomService.getAccomdomsByUserId(id);
		} else {
			accomdoms = accomdomService.getAccomdoms();
		}

		return new ResponseEntity<>(accomdoms, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Accomdom> getAccomdomPostById(@PathVariable("id") long id) {
		Accomdom accomdom = accomdomService.findOne(id);

		return new ResponseEntity<>(accomdom, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<Void> createAccomdomPost(@RequestBody AccomdomModelRequest req) {
		Accomdom results = accomdomService.createAccomdomPost(req);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(results.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Accomdom> updateAccomdomPost(@PathVariable("id") long id,
			@RequestBody AccomdomModelRequest req) {
		Accomdom results = accomdomService.updateAccomdomPost(id, req);
		return new ResponseEntity<>(results, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
		accomdomService.deleteAccomdomPost(id);
		return ResponseEntity.noContent().build();
	}
}
