package com.demo.testcurriculums.models.service;

import java.util.List;

import com.demo.testcurriculums.models.entity.Habilidad;

public interface IHabilidadService {
	
	public List<Habilidad> findAll();
	
	public Habilidad findById(Long id);
	
	public Habilidad save(Habilidad habilidad);
	
	public void delete(Long id);

}
