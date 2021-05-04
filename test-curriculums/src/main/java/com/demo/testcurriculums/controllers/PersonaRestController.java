package com.demo.testcurriculums.controllers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.testcurriculums.models.service.IPersonaService;
import com.demo.testcurriculums.models.entity.Persona;





@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class PersonaRestController {


	@Autowired
	private IPersonaService personaService;
	
	@GetMapping("/personas")
	public List<Persona> index(){
		
		return personaService.findAll();
	}
	
	@GetMapping("/personas/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Persona persona = null;
		Map<String, Object> response = new HashMap<>();
		try {
			persona = personaService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la Base de datos");
			response.put("Error",  e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (persona == null) {
			response.put("mensaje", "La persona ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Persona>(persona, HttpStatus.OK);
	}
	
	
	
	@PostMapping("/personas")
	public ResponseEntity<?> create(@Valid @RequestBody Persona persona, BindingResult result) {
		Persona personaNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try { 
			personaNew = personaService.save(persona);
		} catch(DataAccessException e) { 
			response.put("mensaje", "Error al realizar el insert en la Base de datos");
			response.put("Error",  e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		
		response.put("mensaje", "El producto ha sido creado con éxito!");	
		response.put("producto", personaNew);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	
	
	@PutMapping("/personas/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Persona persona, BindingResult result, @PathVariable Long id) {
		Persona personaActual = personaService.findById(id);
		
		Persona personaUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (personaActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el producto ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try { 
			personaActual.setNombre(persona.getNombre());
			personaActual.setApellido(persona.getApellido());
			personaActual.setEmail(persona.getEmail());
			personaUpdated = personaService.save(personaActual);
			
		} catch(DataAccessException e) { 
			response.put("mensaje", "Error al actualizar el producto en la Base de datos");
			response.put("Error",  e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		
		response.put("mensaje", "El producto ha sido actualizado con éxito!");	
		response.put("producto", personaUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	
	
	
	@DeleteMapping("/personas/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {

			personaService.delete(id);
			
		} catch(DataAccessException e) { 
			response.put("mensaje", "Error al eliminar el producto de la Base de datos");
			response.put("Error",  e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		response.put("mensaje", "El producto ha sido eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
}
