package com.nnk.springboot.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurveService;

@Service
public class CurveServiceImpl implements CurveService {
	@Autowired
	CurvePointRepository curvePointRepository;

	@Override
	public CurvePoint saveCurvePoint(CurvePoint curvePoint) {
		return curvePointRepository.save(curvePoint);
	}

	@Override
	public CurvePoint findCurvePointById(int id) {
		Optional<CurvePoint> curvePoint = curvePointRepository.findById(id);
		if (curvePoint == null) {
			return null;
		} else
			return curvePoint.get();
	}

	@Override
	public List<CurvePoint> findCurvePointByList() {
		return curvePointRepository.findAll();
	}

	@Override
	public CurvePoint updateCurvePoint(CurvePoint curvePoint) {
		return curvePointRepository.save(curvePoint);
	}

	@Override
	public void deleteCurvePointById(int id) {
		curvePointRepository.delete(findCurvePointById(id));
	}
}