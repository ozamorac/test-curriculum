package com.demo.testcurriculums.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.testcurriculums.models.entity.Persona;

public interface IPersonaDao extends JpaRepository<Persona, Long> {

	@Query("from Persona")
	public List<Persona> findAllPersona();
}
