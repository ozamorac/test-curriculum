package com.demo.testcurriculums.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.testcurriculums.models.entity.Estudio;

public interface IEstudioDao extends JpaRepository<Estudio, Long> {
	
	@Query("from Estudio")
	public List<Estudio> findAllEstudios();
}
