package com.nnk.springboot.datatest;

import java.sql.Timestamp;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.User;

/**
 * This class loads data for unit tests.
 *
 *
 */

public class DataLoader {

	/**
	 * set a new BidList.
	 * 
	 * @return bidList
	 */
	public BidList setBidList() {
		BidList bidList = new BidList();
		bidList.setType("type");
		bidList.setAccount("account");
		bidList.setBidQuantity(22.0);
		bidList.setAskQuantity(22.0);
		bidList.setBid(1.1);
		bidList.setAsk(1.1);
		bidList.setBenchmark("benchmark");
		bidList.setBidListDate(new Timestamp(System.currentTimeMillis()));
		bidList.setCommentary("commentary");
		bidList.setSecurity("security");
		bidList.setStatus("status");
		bidList.setTrader("trade");
		bidList.setBook("book");
		bidList.setCreationName("creation name");
		bidList.setCreationDate(new Timestamp(System.currentTimeMillis()));
		bidList.setRevisionName("revision name");
		bidList.setRevisionDate(new Timestamp(System.currentTimeMillis()));
		bidList.setDealName("deal name");
		bidList.setDealType("deal type");
		bidList.setSourceListId("2");
		bidList.setSide("side");
		return bidList;
	}

	/**
	 * set a new CurvePoint.
	 * 
	 * @return curvePoint
	 */
	public CurvePoint setCurvePoint() {
		CurvePoint curvePoint = new CurvePoint();
		curvePoint.setCurveId(1);
		curvePoint.setAsOfDate(new Timestamp(System.currentTimeMillis()));
		curvePoint.setTerm(100.0);
		curvePoint.setValue(50.0);
		curvePoint.setCreationDate(new Timestamp(System.currentTimeMillis()));
		return curvePoint;
	}

	/**
	 * set a new Rating.
	 * 
	 * @return rating
	 */
	public Rating setRating() {
		Rating rating = new Rating();
		rating.setId(1);
		rating.setMoodysRating("Moody's rating");
		rating.setSandPRating("S&P rating");
		rating.setFitchRating("Fitch rating");
		rating.setOrderNumber(1255);
		return rating;
	}

	/**
	 * set a new RuleName.
	 * 
	 * @return ruleName
	 */
	public RuleName setRuleName() {
		RuleName ruleName = new RuleName();
		ruleName.setId(1);
		ruleName.setName("rule name");
		ruleName.setDescription("rule name description");
		ruleName.setJson("json");
		ruleName.setTemplate("template");
		ruleName.setSqlStr("sql str");
		ruleName.setSqlPart("sql part");
		return ruleName;
	}

	/**
	 * Set a new Trade.
	 * 
	 * @return trade instance
	 */
	public Trade setTrade() {
		Trade trade = new Trade();
		trade.setTradeId(22);
		trade.setAccount("trade account");
		trade.setType("Trade type");
		trade.setBuyQuantity(23.0);
		trade.setSellQuantity(12.0);
		trade.setSellPrice(10.0);
		trade.setBenchmark("Trade benchmark");
		trade.setTradeDate(new Timestamp(System.currentTimeMillis()));
		trade.setSecurity("Trade is secure");
		trade.setStatus("Trade status");
		trade.setTrader("Trader");
		trade.setBook("Trade book");
		trade.setCreationName("Trade creation name");
		trade.setCreationDate(new Timestamp(System.currentTimeMillis()));
		trade.setRevisionName("Trade revision name");
		trade.setRevisionDate(new Timestamp((System.currentTimeMillis())));
		trade.setDealName("Trade deal name");
		trade.setDealType("Trade deal type");
		trade.setSourceListId("Source List ID");
		trade.setSide("Trade Side");
		return trade;
	}

	/**
	 * Set a new User.
	 * 
	 * @return a user instance
	 */
	public User setUser() {
		User user = new User();
		user.setId(9);
		user.setUsername("User Name");
		user.setPassword("UUUser123&");
		user.setFullname("I m user");
		user.setRole("USER");
		return user;
	}
}