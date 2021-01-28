package com.nnk.springboot.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;

@RestController
public class BidListController {
	/**
	 * Inject Bid service
	 */
	@Autowired
	private BidListService bidListService;

	/**
	 * Call service find all bids to show to the view
	 * 
	 * @param model
	 * @return bid List
	 */
	@GetMapping("/bidList/list")
	public List<BidList> home() {
		// model.addAttribute("bidLists", bidListRepository.findAll());
		return bidListService.findBidListByList();
	}

	@GetMapping("/bidList/list{id}")
	public BidList findBidListById(@RequestParam int id) {
		// model.addAttribute("bidLists", bidListRepository.findAll());
		return bidListService.findBidListById(id);
	}

	@PostMapping("/bidList/add")
	public BidList addBidList(@RequestBody BidList bid) {
		return bidListService.saveBidList(bid);
	}

	/**
	 * Check data valid and save to db, after saving return bid list
	 * 
	 * @param bid
	 * @param result
	 * @param model
	 * @return bid list added
	 */
	@PostMapping("/bidList/validate")
	public String validate(@Valid BidList bid, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			bidListService.saveBidList(bid);
			model.addAttribute("bidLists", bidListService.findBidListByList());
			return "redirect:/bidList/list";
		}
		return "bidList/add";
	}

	/**
	 * Get Bid by Id and to model then show to the form
	 * 
	 * @param id
	 * @param model
	 * @return bid list updated
	 */
	@GetMapping("/bidList/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		BidList bidList = bidListService.findBidListById(id);
		if (bidList == null) {
			throw new IllegalArgumentException("Invalid bidList Id:" + id);
		}

		model.addAttribute("bidList", bidList);
		return "bidList/update";
	}

	/**
	 * Check required fields, if valid call service to update Bid
	 * 
	 * @param id
	 * @param bidList
	 * @param result
	 * @param model
	 * @return bid list
	 */
	@PutMapping("/bidList/update")
	public BidList updateBid(@RequestBody BidList bidList) {
		return bidListService.updateBidList(bidList);
	}

	/**
	 * Find Bid by Id and delete the bid
	 * 
	 * @param id
	 * @param model
	 * @return bid list
	 */
	@DeleteMapping("/bidList/delete/{id}")
	public void deleteBid(@RequestParam Integer id) {
		BidList bidList = bidListService.findBidListById(id);
		if (bidList == null) {
			throw new IllegalArgumentException("Invalid bidList Id:" + id);
		}else {
			bidListService.deleteBidListById(id);			
		}
	}
}