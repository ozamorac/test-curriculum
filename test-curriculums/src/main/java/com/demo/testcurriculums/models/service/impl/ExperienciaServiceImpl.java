package com.demo.testcurriculums.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.demo.testcurriculums.models.dao.IExperienciaDao;
import com.demo.testcurriculums.models.entity.Experiencia;
import com.demo.testcurriculums.models.service.IExperienciaService;

@Service
public class ExperienciaServiceImpl implements IExperienciaService {

	@Autowired
	private IExperienciaDao experienciaDao;
	
	@Transactional(readOnly = true)
	public List<Experiencia> findAll() {
		// TODO Auto-generated method stub
		return experienciaDao.findAll();
	}

	@Transactional(readOnly = true)
	public Experiencia findById(Long id) {
		// TODO Auto-generated method stub
		return experienciaDao.findById(id).orElse(null);
	}

	@Transactional
	public Experiencia save(Experiencia experiencia) {
		// TODO Auto-generated method stub
		return experienciaDao.save(experiencia);
	}

	@Transactional
	public void delete(Long id) {
		experienciaDao.deleteById(id);
		
	}

}
