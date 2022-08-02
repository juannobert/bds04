package com.devsuperior.bds04.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.services.EventService;

@RestController
@RequestMapping("/events")
public class EventResource {
	
	@Autowired
	private EventService service;
	
	@GetMapping
	public ResponseEntity<Page<EventDTO>> findAll(Pageable pageable){
		Page<EventDTO> list = service.findAll(pageable);
		return ResponseEntity.ok(list);
	}
	
	@PostMapping
	public ResponseEntity<EventDTO> save(@RequestBody EventDTO dto){
		EventDTO entity = service.save(dto);
		URI uri =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(entity.getId()).toUri();
		return ResponseEntity.created(uri).body(entity);
	}
	

}
