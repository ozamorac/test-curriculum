package com.demo.testcurriculums.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.testcurriculums.models.entity.Habilidad;

public interface IHabilidadDao extends JpaRepository<Habilidad, Long>{

	@Query("from Estudio")
	public List<Habilidad> findAllHabilidad();
}
