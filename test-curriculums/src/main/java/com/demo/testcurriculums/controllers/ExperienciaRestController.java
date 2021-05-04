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

import com.demo.testcurriculums.models.entity.Experiencia;
import com.demo.testcurriculums.models.service.IExperienciaService;


@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ExperienciaRestController {

	@Autowired
	private IExperienciaService experienciaService;
	@GetMapping("/experiencias")
	public List<Experiencia> index(){
		
		return experienciaService.findAll();
	}
	
	@GetMapping("/experiencias/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Experiencia experiencia = null;
		Map<String, Object> response = new HashMap<>();
		try {
			experiencia = experienciaService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la Base de datos");
			response.put("Error",  e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (experiencia == null) {
			response.put("mensaje", "La persona ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Experiencia>(experiencia, HttpStatus.OK);
	}


	
	@PostMapping("/experiencias")
	public ResponseEntity<?> create(@Valid @RequestBody Experiencia experiencia, BindingResult result) {
		Experiencia experienciaNew = null;
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
			experienciaNew = experienciaService.save(experiencia);
		} catch(DataAccessException e) { 
			response.put("mensaje", "Error al realizar el insert en la Base de datos");
			response.put("Error",  e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		
		response.put("mensaje", "El producto ha sido creado con éxito!");	
		response.put("producto", experienciaNew);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	
	
	@PutMapping("/experiencias/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Experiencia experiencia, BindingResult result, @PathVariable Long id) {
		Experiencia experienciaActual = experienciaService.findById(id);
		
		Experiencia experienciaUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (experienciaActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el producto ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try { 
			experienciaActual.setId_persona(experiencia.getId_persona());
			experienciaActual.setEmpresa(experiencia.getEmpresa());
			experienciaActual.setCargo(experiencia.getCargo());
			experienciaActual.setDescripcion(experiencia.getDescripcion());
			experienciaActual.setFechas(experiencia.getFechas());
			experienciaUpdated = experienciaService.save(experienciaActual);
			
		} catch(DataAccessException e) { 
			response.put("mensaje", "Error al actualizar la experiencia en la Base de datos");
			response.put("Error",  e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		
		response.put("mensaje", "El experiencia ha sido actualizado con éxito!");	
		response.put("experiencia", experienciaUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	
	
	
	@DeleteMapping("/experiencias/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {

			experienciaService.delete(id);
			
		} catch(DataAccessException e) { 
			response.put("mensaje", "Error al eliminar la experiencia de la Base de datos");
			response.put("Error",  e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		response.put("mensaje", "El experiencia  ha sido eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	
	
	
}
