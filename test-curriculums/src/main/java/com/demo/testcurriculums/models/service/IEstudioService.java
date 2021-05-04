package com.demo.testcurriculums.models.service;

import java.util.List;

import com.demo.testcurriculums.models.entity.Estudio;

public interface IEstudioService {

	public List<Estudio> findAll();
	
	public Estudio findById(Long id);
	
	public Estudio save(Estudio estudio);
	
	public void delete(Long id);
}
