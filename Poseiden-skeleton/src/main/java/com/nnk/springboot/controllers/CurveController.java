package com.nnk.springboot.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.servicesImpl.CurveServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Class used to manage curve")
@RestController
@RequestMapping("/api/curvePoint")
public class CurveController {
	/**
	 * Inject Curve Point service
	 */
	@Autowired
	private CurveServiceImpl curveService;

	private static final Logger LOGGER = LoggerFactory.getLogger(CurveController.class);

	/**
	 * Find all Curve Point
	 * 
	 * @return curve point list
	 */
	@ApiOperation(value = "Retrieve all CurvePoints from database")
	@GetMapping("/list")
	public List<CurvePoint> home() {
		return curveService.findCurvePointByList();
	}

	/**
	 * Check data valid and save to db
	 * 
	 * @param curvePoint
	 * @return Curve added
	 */
	@ApiOperation(value = "Save a new CurvePoint")
	@PostMapping("/add")
	public ResponseEntity<CurvePoint> addCurvePoint(@Valid @RequestBody CurvePoint curvePoint) {
		return new ResponseEntity<CurvePoint>(curveService.saveCurvePoint(curvePoint), HttpStatus.CREATED);
	}

	/**
	 * Get CurvePoint by Id
	 * 
	 * @param id
	 * @return Curve found
	 */
	@ApiOperation(value = "Retrieve CurvePoint from database by Id")
	@GetMapping("/{id}")
	public CurvePoint getCurvePointById(@PathVariable Integer id) {
		return curveService.findCurvePointById(id);
	}

	/**
	 * Check required fields, if valid call service to update Curve
	 * 
	 * @param curvePoint
	 * @return Curve updated
	 */
	@ApiOperation(value = "Update an existing CurvePoint")
	@PutMapping("/update")
	public CurvePoint updateCurve(@Valid @RequestBody CurvePoint curvePoint) {
		return curveService.updateCurvePoint(curvePoint);
	}

	/**
	 * Find Curve by Id and delete the Curve
	 */
	@ApiOperation(value = "Delete a CurvePoint from database by Id")
	@DeleteMapping("/delete/{id}")
	public void deleteCurve(@PathVariable Integer id, HttpServletResponse response) {
		Optional<CurvePoint> bidOptional = Optional.ofNullable(curveService.findCurvePointById(id));
		if (bidOptional.isPresent()) {
			LOGGER.info("delete, SUCCES");
			response.setStatus(200);
			curveService.deleteCurvePointById(id);
		} else {
			LOGGER.error("Delete, ERROR");
		}
	}
}