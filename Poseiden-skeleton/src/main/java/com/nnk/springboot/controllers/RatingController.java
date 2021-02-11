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

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.servicesImpl.RatingServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Class used to manage rating")
@RestController
@RequestMapping("/api/rating")
public class RatingController {
	/**
	 * Inject Rating service
	 */
	@Autowired
	private RatingServiceImpl ratingService;

	private static final Logger LOGGER = LoggerFactory.getLogger(RatingController.class);

	/**
	 * find all Rating, add to model
	 * 
	 * @return Rating list
	 */
	@ApiOperation(value = "Retrieve all Ratings from database")
	@RequestMapping("/list")
	public List<Rating> home() {
		return ratingService.findRatingByList();
	}

	/**
	 * Check data valid and save to db
	 * 
	 * @param rating
	 * @return rating added
	 */
	@ApiOperation(value = "Save a new Rating to database")
	@PostMapping("/add")
	public ResponseEntity<Rating> addRating(@Valid @RequestBody Rating rating) {
		return new ResponseEntity<Rating>(ratingService.saveRating(rating), HttpStatus.CREATED);
	}

	/**
	 * Get Rating by Id
	 * 
	 * @param id
	 * @return Rating found
	 */
	@ApiOperation(value = "Retrieve Rating by its id from database")
	@GetMapping("/{id}")
	public Rating getRatingById(@PathVariable Integer id) {
		return ratingService.findRatingById(id);
	}

	/**
	 * Check required fields, if valid call service to update Rating
	 * 
	 * @param rating
	 * @return Rating update
	 */
	@ApiOperation(value = "Update an existing Rating")
	@PutMapping("/update")
	public Rating updateRating(@Valid @RequestBody Rating rating) {
		return ratingService.updateRating(rating);
	}

	/**
	 * Find Rating by Id and delete the Rating.
	 */
	@ApiOperation(value = "Delete an existing Rating from database")
	@DeleteMapping("/delete/{id}")
	public void deleteRating(@PathVariable Integer id, HttpServletResponse response) {
		Optional<Rating> bidOptional = Optional.ofNullable(ratingService.findRatingById(id));
		if (bidOptional.isPresent()) {
			LOGGER.info("delete, SUCCES");
			response.setStatus(200);
			ratingService.deleteRatingById(id);
		} else {
			LOGGER.error("Delete, ERROR");
		}
	}
}