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

import com.demo.testcurriculums.models.entity.Habilidad;
import com.demo.testcurriculums.models.service.IHabilidadService;


@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class HabilidadRestController {

	


	@Autowired
	private IHabilidadService habilidadService;
	
	@GetMapping("/habilidades")
	public List<Habilidad> index(){
		
		return habilidadService.findAll();
	}
	
	@GetMapping("/habilidades/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Habilidad habilidad = null;
		Map<String, Object> response = new HashMap<>();
		try {
			habilidad = habilidadService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la Base de datos");
			response.put("Error",  e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (habilidad == null) {
			response.put("mensaje", "La habilidad ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Habilidad>(habilidad, HttpStatus.OK);
	}
	
	
	
	@PostMapping("/habilidades")
	public ResponseEntity<?> create(@Valid @RequestBody Habilidad habilidad, BindingResult result) {
		Habilidad habilidadNew = null;
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
			habilidadNew = habilidadService.save(habilidad);
		} catch(DataAccessException e) { 
			response.put("mensaje", "Error al realizar el insert en la Base de datos");
			response.put("Error",  e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		
		response.put("mensaje", "El producto ha sido creado con éxito!");	
		response.put("producto", habilidadNew);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	
	
	@PutMapping("/habilidades/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Habilidad habilidad, BindingResult result, @PathVariable Long id) {
		Habilidad habilidadActual = habilidadService.findById(id);
		
		Habilidad habilidadUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (habilidadActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el producto ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try { 
			habilidadActual.setId_persona(habilidad.getId_persona());
			habilidadActual.setNombre(habilidad.getNombre());
			habilidadActual.setNivel(habilidad.getNivel());
			habilidadUpdated = habilidadService.save(habilidadActual);
			
		} catch(DataAccessException e) { 
			response.put("mensaje", "Error al actualizar el producto en la Base de datos");
			response.put("Error",  e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		
		response.put("mensaje", "El producto ha sido actualizado con éxito!");	
		response.put("producto", habilidadUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	
	
	
	@DeleteMapping("/habilidades/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {

			habilidadService.delete(id);
			
		} catch(DataAccessException e) { 
			response.put("mensaje", "Error al eliminar el producto de la Base de datos");
			response.put("Error",  e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		response.put("mensaje", "El producto ha sido eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
}
