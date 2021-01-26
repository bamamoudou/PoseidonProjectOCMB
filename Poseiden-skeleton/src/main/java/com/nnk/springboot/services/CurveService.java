package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.CurvePoint;

/**
 * CurveService CRUD manager
 */
public interface CurveService {
	/**
	 * save the curvePoint and return instance save
	 * 
	 * @param curvePoint
	 * @return
	 */
	public CurvePoint saveCurvePoint(CurvePoint curvePoint);

	/**
	 * return curvePoint from id
	 * 
	 * @param id
	 * @return
	 */
	public CurvePoint findCurvePointById(int id);

	/**
	 * return list CurvePoint
	 * 
	 * @return
	 */
	public List<CurvePoint> findCurvePointByList();
	
	/**
	 * Update CurvePoint
	 * @return
	 */
	public CurvePoint updateCurvePoint(CurvePoint curvePoint);

	/**
	 * delete curvePoint from id
	 * 
	 * @param id
	 * @return
	 */
	public void deleteCurvePointById(int id);
}