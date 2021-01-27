package com.nnk.springboot.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@Controller
public class BidListController {
	/**
	 * Inject Bid service
	 */
	@Autowired
	private BidListRepository bidListRepository;

	/**
	 * Call service find all bids to show to the view
	 * 
	 * @param model
	 * @return bid List
	 */
	@RequestMapping("/bidList/list")
	public String home(Model model) {
		model.addAttribute("bidLists", bidListRepository.findAll());
		return "bidList/list";
	}

	@GetMapping("/bidList/add")
	public String addBidForm(BidList bid) {
		return "bidList/add";
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
			bidListRepository.save(bid);
			model.addAttribute("bidLists", bidListRepository.findAll());
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
		BidList bidList = bidListRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
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
	@PostMapping("/bidList/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			bidList.setBidListId(id);
			bidListRepository.save(bidList);
			model.addAttribute("bidLists", bidListRepository.findAll());
			return "redirect:/bidList/list";
		}
		return "redirect:/bidList/list";
	}

	/**
	 * Find Bid by Id and delete the bid
	 * 
	 * @param id
	 * @param model
	 * @return bid list
	 */
	@GetMapping("/bidList/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		BidList bidList = bidListRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
		bidListRepository.delete(bidList);
		model.addAttribute("bidList", bidListRepository.findAll());
		return "redirect:/bidList/list";
	}
}