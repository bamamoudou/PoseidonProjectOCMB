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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.servicesImpl.TradeServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Class used to manage trade")
@RestController
@RequestMapping("/api/trade")
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
	@ApiOperation(value = "Retrieve all Trades from database")
	@GetMapping("/list")
	public List<Trade> home() {
		LOGGER.debug("GET request sent from the TradeController" + " to retrieve all Trades");
		return tradeService.findTradeBylist();
	}

	/**
	 * Check data valid and save to db
	 * 
	 * @param trade
	 * @return Trade added
	 */
	@ApiOperation(value = "Save a new Trade to database")
	@PostMapping("/add")
	public ResponseEntity<Trade> addTrade(@Valid @RequestBody Trade trade) {
		LOGGER.debug("POST request sent from the TradeController" + " to save a new Trades {}", trade.getTradeId());
		return new ResponseEntity<Trade>(tradeService.saveTrade(trade), HttpStatus.CREATED);
	}

	/**
	 * Get Trade by Id
	 * 
	 * @param id
	 * @return Trade found
	 */
	@ApiOperation(value = "Retrieve Trade by its id from database")
	@GetMapping("/{id}")
	public Trade getTrade(@PathVariable Integer id) {
		LOGGER.debug("GET request sent from the TradeController" + " to retrieve Trade {}", id);
		return tradeService.findTradeById(id);
	}

	/**
	 * Check required fields, if valid call service to update Trade
	 * 
	 * @param trade
	 * @return Trade updated
	 */
	@ApiOperation(value = "Update an existing Trade")
	@PutMapping("/update")
	public Trade updateTrade(@Valid @RequestBody Trade trade) {
		LOGGER.debug("PUT request sent from the TradeApiController" + " to update Trade {}", trade);
		return tradeService.updateTrade(trade);
	}

	/**
	 * Find Trade by Id and delete the Trade
	 * 
	 * @param id
	 */
	@ApiOperation(value = "Delete an existing Trade from database")
	@DeleteMapping("/delete/{id}")
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