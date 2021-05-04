package com.demo.testcurriculums.models.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.testcurriculums.models.dao.IPersonaDao;
import com.demo.testcurriculums.models.entity.Persona;
import com.demo.testcurriculums.models.service.IPersonaService;



@Service
public class PersonaServiceImpl implements IPersonaService {

	@Autowired
	private IPersonaDao personaDao;
	
	@Transactional(readOnly = true)
	public List<Persona> findAll() {
		return personaDao.findAll();
	}

	@Transactional(readOnly = true)
	public Persona findById(Long id) {
		return personaDao.findById(id).orElse(null);
	}

	@Transactional
	public Persona save(Persona persona) {
		return personaDao.save(persona);
	}
	
	@Transactional
	public void delete(Long id) {
		personaDao.deleteById(id);
	}
}
