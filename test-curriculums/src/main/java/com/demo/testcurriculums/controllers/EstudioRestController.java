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

import com.demo.testcurriculums.models.entity.Estudio;
import com.demo.testcurriculums.models.service.IEstudioService;


@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class EstudioRestController {

	
	
	@Autowired
	private IEstudioService estudioService;
	
	@GetMapping("/estudios")
	public List<Estudio> index(){
		
		return estudioService.findAll();
	}
	
	@GetMapping("/estudios/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Estudio estudio = null;
		Map<String, Object> response = new HashMap<>();
		try {
			estudio = estudioService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la Base de datos");
			response.put("Error",  e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (estudio == null) {
			response.put("mensaje", "La Estudio ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Estudio>(estudio, HttpStatus.OK);
	}
	
	
	
	@PostMapping("/estudios")
	public ResponseEntity<?> create(@Valid @RequestBody Estudio estudio, BindingResult result) {
		Estudio estudioNew = null;
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
			estudioNew = estudioService.save(estudio);
		} catch(DataAccessException e) { 
			response.put("mensaje", "Error al realizar el insert en la Base de datos");
			response.put("Error",  e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		
		response.put("mensaje", "El producto ha sido creado con éxito!");	
		response.put("producto", estudioNew);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	
	
	@PutMapping("/estudios/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Estudio estudio, BindingResult result, @PathVariable Long id) {
		Estudio estudioActual = estudioService.findById(id);
		
		Estudio estudioUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (estudioActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el producto ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try { 
			estudioActual.setId_persona(estudio.getId_persona());
			estudioActual.setEstudio(estudio.getEstudio());
			estudioActual.setDescripcion(estudio.getDescripcion());
			estudioUpdated = estudioService.save(estudioActual);
			
		} catch(DataAccessException e) { 
			response.put("mensaje", "Error al actualizar el producto en la Base de datos");
			response.put("Error",  e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		
		response.put("mensaje", "El producto ha sido actualizado con éxito!");	
		response.put("producto", estudioUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	
	
	
	@DeleteMapping("/estudios/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {

			estudioService.delete(id);
			
		} catch(DataAccessException e) { 
			response.put("mensaje", "Error al eliminar el producto de la Base de datos");
			response.put("Error",  e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		response.put("mensaje", "El producto ha sido eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
}
