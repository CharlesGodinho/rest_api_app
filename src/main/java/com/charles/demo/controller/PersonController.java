package com.charles.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.charles.demo.data.vo.v1.PersonVO;
import com.charles.demo.services.PersonService;
import com.charles.demo.util.MediaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for managing people")
public class PersonController {

	@Autowired
	private PersonService service;

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML })
	@Operation(summary = "busca por Id de pessoas", description = "busca por Id de pessoas", tags = {"People"},responses = {
			@ApiResponse(description = "Success", responseCode="200", 
					content = @Content(schema = @Schema(implementation = PersonVO.class))),
			@ApiResponse(description = "Bad Request", responseCode="400", content = @Content),
			@ApiResponse(description = "Not Content", responseCode="204", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode="401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode="404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode="500", content = @Content),
	})
	public PersonVO findById(@PathVariable(value = "id") Long id) {
		return service.findByid(id);
	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_YML,
			MediaType.APPLICATION_YML }, consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_YML,
					MediaType.APPLICATION_YML })
	@Operation(summary = "Create Person", description = "Create Person", tags = {"People"},responses = {
			@ApiResponse(description = "Success", responseCode="200", 
					content = @Content(schema = @Schema(implementation = PersonVO.class))),
			@ApiResponse(description = "Bad Request", responseCode="400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode="401", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode="500", content = @Content),
	})
	public PersonVO create(@RequestBody PersonVO PersonVO) {
		return service.create(PersonVO);
	}

	@PutMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_YML,
			MediaType.APPLICATION_YML }, consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_YML,
					MediaType.APPLICATION_YML })
	@Operation(summary = "Update Person", description = "Update Person", tags = {"People"},responses = {
			@ApiResponse(description = "Success", responseCode="200", 
					content = @Content(schema = @Schema(implementation = PersonVO.class))),
			@ApiResponse(description = "Bad Request", responseCode="400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode="401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode="404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode="500", content = @Content),
	})
	public PersonVO update(@RequestBody PersonVO PersonVO) {
		return service.update(PersonVO);
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Delete Person", description = "Delete Person", tags = {"People"},responses = {
			@ApiResponse(description = "Success", responseCode="200", 
					content = @Content(schema = @Schema(implementation = PersonVO.class))),
			@ApiResponse(description = "Bad Request", responseCode="400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode="401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode="404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode="500", content = @Content),
	})
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_YML, MediaType.APPLICATION_YML })
	@Operation(summary = "busca a listagem de pessoas", description = "Listagem de pessoas", tags = {"People"},responses = {
			@ApiResponse(description = "Success", responseCode="200", 
					content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PersonVO.class)))
					),
			@ApiResponse(description = "Bad Request", responseCode="400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode="401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode="404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode="500", content = @Content),
	})
	public List<PersonVO> findByAll() {
		return service.findAll();
	}
}