package com.demo.testcurriculums.models.service;

import java.util.List;

import com.demo.testcurriculums.models.entity.Persona;

public interface IPersonaService {

	public List<Persona> findAll();
	
	public Persona findById(Long id);
	
	public Persona save(Persona persona);
	
	public void delete(Long id);
}
