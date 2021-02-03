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
import org.springframework.web.bind.annotation.RestController;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.servicesImpl.TradeServiceImpl;

@RestController
public class TradeController {
	/**
	 * Inject Trade service
	 */
	@Autowired
	private TradeServiceImpl tradeService;
	private static final Logger LOGGER = LoggerFactory.getLogger(TradeController.class);

	/**
	 * find all Trades
	 * 
	 * @return list of trades
	 */
	@GetMapping("/trade/list")
	public List<Trade> home() {
		return tradeService.findTradeBylist();
	}

	/**
	 * Check data valid and save to db
	 * 
	 * @param trade
	 * @return Trade added
	 */
	@PostMapping("/trade/add")
	public ResponseEntity<Trade> addTrade(@Valid @RequestBody Trade trade) {
		return new ResponseEntity<Trade>(tradeService.saveTrade(trade), HttpStatus.CREATED);
	}

	/**
	 * Get Trade by Id
	 * 
	 * @param id
	 * @return Trade found
	 */
	@GetMapping("/trade/{id}")
	public Trade getTrade(@PathVariable Integer id) {
		return tradeService.findTradeById(id);
	}

	/**
	 * Check required fields, if valid call service to update Trade
	 * 
	 * @param trade
	 * @return Trade updated
	 */
	@PutMapping("/trade/update")
	public Trade updateTrade(@Valid @RequestBody Trade trade) {
		return tradeService.updateTrade(trade);
	}

	/**
	 * Find Trade by Id and delete the Trade
	 * 
	 * @param id
	 */
	@DeleteMapping("/trade/delete/{id}")
	public void deleteTrade(@PathVariable Integer id, HttpServletResponse response) {
		Optional<Trade> bidOptional = Optional.ofNullable(tradeService.findTradeById(id));
		if (bidOptional.isPresent()) {
			LOGGER.info("delete, SUCCES");
			response.setStatus(200);
			tradeService.deleteTradeById(id);
		} else {
			LOGGER.error("Delete, ERROR");
		}
	}
}