package com.demo.testcurriculums.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.testcurriculums.models.dao.IHabilidadDao;
import com.demo.testcurriculums.models.entity.Habilidad;
import com.demo.testcurriculums.models.service.IHabilidadService;

@Service
public class HabilidadServiceImpl implements IHabilidadService {

	@Autowired
	private IHabilidadDao habilidadDao;
	
	@Transactional(readOnly = true)
	public List<Habilidad> findAll() {
		// TODO Auto-generated method stub
		return habilidadDao.findAll();
	}

	@Transactional(readOnly = true)
	public Habilidad findById(Long id) {
		// TODO Auto-generated method stub
		return habilidadDao.findById(id).orElse(null);
	}

	@Transactional
	public Habilidad save(Habilidad habilidad) {
		// TODO Auto-generated method stub
		return habilidadDao.save(habilidad);
	}

	@Transactional
	public void delete(Long id) {
		habilidadDao.deleteById(id);
		
	}

}
