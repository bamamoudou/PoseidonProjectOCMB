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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.servicesImpl.CurveServiceImpl;

@RestController
public class CurveController {
	/**
	 * Inject Curve Point service
	 */
	@Autowired
	private CurveServiceImpl curveService;

	private static final Logger LOGGER = LoggerFactory.getLogger(CurveController.class);

	/**
	 * Find all Curve Point, add to model
	 * 
	 * @param model
	 * @return curve point list
	 */
	@GetMapping("/curvePoint/list")
	public List<CurvePoint> home() {
		return curveService.findCurvePointByList();
	}

	@PostMapping("/curvePoint/add")
	public ResponseEntity<CurvePoint> addCurvePoint(@RequestBody CurvePoint curvePoint) {
		return new ResponseEntity<CurvePoint>(curveService.saveCurvePoint(curvePoint), HttpStatus.CREATED);
	}

	/**
	 * Get CurvePoint by Id and to model then show to the form
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/curvePoint/{id}")
	public CurvePoint getCurvePointById(@PathVariable("id") Integer id) {
		return curveService.findCurvePointById(id);
	}

	/**
	 * Check data valid and save to db
	 * 
	 * @param curvePoint
	 * @param result
	 * @param model
	 * @return Curve list
	 */
	@PostMapping("/curvePoint/validate")
	public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			curveService.saveCurvePoint(curvePoint);

			return "redirect:/curvePoint/list";
		}
		return "curvePoint/add";
	}

	/**
	 * Check required fields, if valid call service to update Curve
	 * 
	 * @param id
	 * @param curvePoint
	 * @param result
	 * @param model
	 * @return Curve list
	 */
	@PutMapping("/curvePoint/update")
	public CurvePoint updateCurve(@Valid @RequestBody CurvePoint curvePoint) {
		return curveService.updateCurvePoint(curvePoint);
	}

	/**
	 * Find Curve by Id and delete the Curve
	 * 
	 * @param id
	 * @param model
	 * @return Curve list
	 */
	@DeleteMapping("/curvePoint/delete/{id}")
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