package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.Trade;

/**
 * TradeService CRUD manager
 */
public interface TradeService {
	/**
	 * save the trade and return instance save
	 * 
	 * @param trade
	 * @return
	 */
	public Trade saveTrade(Trade trade);

	/**
	 * return trade from id
	 * 
	 * @param id
	 * @return
	 */
	public Trade findTradeById(int id);

	/**
	 * return list trade
	 * 
	 * @return
	 */
	public List<Trade> findTradeBylist();

	/**
	 * Update trade
	 * 
	 * @param trade
	 * @return
	 */
	public Trade updateTrade(Trade trade);

	/**
	 * delete trade from id
	 * 
	 * @param id
	 * @return
	 */
	public void deleteTradeById(int id);
}