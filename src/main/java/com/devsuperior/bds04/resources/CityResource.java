package com.devsuperior.bds04.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.services.CityService;

@RestController
@RequestMapping("/cities")
public class CityResource {
	
	@Autowired
	private CityService service;
	
	@GetMapping
	public ResponseEntity<List<CityDTO>> findAll(){
		List<CityDTO> list = service.findAll();
		return ResponseEntity.ok(list);
	}
	
	@PostMapping
	public ResponseEntity<CityDTO> save(@RequestBody CityDTO dto){
		CityDTO entity = service.save(dto);
		URI uri =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(entity.getId()).toUri();
		return ResponseEntity.created(uri).body(entity);
	}
	

}
