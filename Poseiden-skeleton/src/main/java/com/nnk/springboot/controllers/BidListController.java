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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.servicesImpl.BidListServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Class used to manage bidList")
@RestController
@RequestMapping("/api/bidList")
public class BidListController {
	/**
	 * Inject Bid service
	 */
	@Autowired
	private BidListServiceImpl bidListService;

	private static final Logger LOGGER = LoggerFactory.getLogger(BidListController.class);

	/**
	 * Call service find all bids to show to the view
	 * 
	 * @return bid List found
	 */
	@ApiOperation(value = "Retrieve all BidLists from database")
	@GetMapping("/list")
	public List<BidList> home() {
		return bidListService.findBidListByList();
	}

	/**
	 * Get Bid by Id
	 * 
	 * @param id
	 * @return Bid found
	 */
	@ApiOperation(value = "Retrieve a BidList by its id from database")
	@GetMapping("/list/{id}")
	public BidList findBidListById(@PathVariable int id) {
		return bidListService.findBidListById(id);
	}

	/**
	 * Check data valid and save to db
	 * 
	 * @param bid
	 * @return bid list added
	 */
	@ApiOperation(value = "Save a new BidList")
	@PostMapping("/add")
	public ResponseEntity<BidList> addBidList(@Valid @RequestBody BidList bid) {
		return new ResponseEntity<BidList>(bidListService.saveBidList(bid), HttpStatus.CREATED);
	}

	/**
	 * Check required fields, if valid call service to update Bid
	 * 
	 * @param bidList
	 * @return bid list updated
	 */
	@ApiOperation(value = "Update an existing BidList")
	@PutMapping("/update")
	public BidList updateBid(@Valid @RequestBody BidList bidList) {
		return bidListService.updateBidList(bidList);
	}

	/**
	 * Find Bid by Id and delete the bid
	 */
	@ApiOperation(value = "Delete an existing BidList from database")
	@DeleteMapping("/delete/{id}")
	public void deleteBid(@PathVariable Integer id, HttpServletResponse response) {
		Optional<BidList> bidOptional = Optional.ofNullable(bidListService.findBidListById(id));
		if (bidOptional.isPresent()) {
			LOGGER.info("delete, SUCCES");
			response.setStatus(200);
			bidListService.deleteBidListById(id);
		} else {
			LOGGER.error("Delete, ERROR");
		}
	}
}