package com.demo.testcurriculums.models.service;

import java.util.List;

import com.demo.testcurriculums.models.entity.Experiencia;

public interface IExperienciaService {

	public List<Experiencia> findAll();
	
	public Experiencia findById(Long id);
	
	public Experiencia save(Experiencia experiencia);
	
	public void delete(Long id);
	
}
