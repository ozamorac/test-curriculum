package com.demo.testcurriculums.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.testcurriculums.models.entity.Experiencia;

public interface IExperienciaDao extends JpaRepository<Experiencia, Long>{
	
	
	@Query("from Experiencia")
	public List<Experiencia> findAllExperiencia();
}
