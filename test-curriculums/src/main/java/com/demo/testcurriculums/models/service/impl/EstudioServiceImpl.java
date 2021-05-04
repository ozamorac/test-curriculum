package com.demo.testcurriculums.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.testcurriculums.models.dao.IEstudioDao;
import com.demo.testcurriculums.models.entity.Estudio;
import com.demo.testcurriculums.models.service.IEstudioService;

@Service
public class EstudioServiceImpl implements IEstudioService{

	@Autowired
	private IEstudioDao estudioDao;
	
	
	@Transactional(readOnly = true)
	public List<Estudio> findAll() {
		// TODO Auto-generated method stub
		return estudioDao.findAll();
	}

	@Transactional(readOnly = true)
	public Estudio findById(Long id) {
		// TODO Auto-generated method stub
		return estudioDao.findById(id).orElse(null);
	}

	@Transactional
	public Estudio save(Estudio estudio) {
		// TODO Auto-generated method stub
		return estudioDao.save(estudio);
	}

	@Transactional
	public void delete(Long id) {
		estudioDao.deleteById(id);
		
	}

}
