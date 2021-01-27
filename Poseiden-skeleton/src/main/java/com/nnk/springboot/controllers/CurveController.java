package com.nnk.springboot.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@RestController
public class CurveController {
	/**
	 * Inject Curve Point service
	 */
	@Autowired
	private CurvePointRepository curvePointRepository;

	/**
	 * Find all Curve Point, add to model
	 * 
	 * @param model
	 * @return curve point list
	 */
	@RequestMapping("/curvePoint/list")
	public String home(Model model) {
		model.addAttribute("curvePoints", curvePointRepository.findAll());
		return "curvePoint/list";
	}

	@GetMapping("/curvePoint/add")
	public String addBidForm(CurvePoint bid) {
		return "curvePoint/add";
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
			curvePointRepository.save(curvePoint);
			model.addAttribute("curvePoints", curvePointRepository.findAll());
			return "redirect:/curvePoint/list";
		}
		return "curvePoint/add";
	}

	/**
	 * Get CurvePoint by Id and to model then show to the form
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/curvePoint/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		CurvePoint curvePoint = curvePointRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
		model.addAttribute("curvePoint", curvePoint);
		return "curvePoint/update";
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
	@PostMapping("/curvePoint/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result,
			Model model) {
		if (!result.hasErrors()) {
			curvePoint.setId(id);
			curvePointRepository.save(curvePoint);
			model.addAttribute("curvePoints", curvePointRepository.findAll());
			return "redirect:/curvePoint/list";
		}
		return "redirect:/curvePoint/list";
	}

	/**
	 * Find Curve by Id and delete the Curve
	 * 
	 * @param id
	 * @param model
	 * @return Curve list
	 */
	@GetMapping("/curvePoint/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		CurvePoint curvePoint = curvePointRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
		curvePointRepository.delete(curvePoint);
		model.addAttribute("curvePoints", curvePointRepository.findAll());
		return "redirect:/curvePoint/list";
	}
}