package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.BidList;

/**
 * BidService CRUD manager
 */
public interface BidListService {

	/**
	 * save the BidList and return instance save
	 * 
	 * @param bidList
	 * @return
	 */
	public BidList saveBidList(BidList bidList);

	/**
	 * return BidList from id
	 * 
	 * @param id
	 * @return
	 */
	public BidList findBidListById(int id);

	/**
	 * return list of BidList
	 * 
	 * @return
	 */
	public List<BidList> findBidListByList();

	/**
	 * Update bid list
	 * 
	 * @return
	 */
	public BidList updateBidList(BidList bidList);

	/**
	 * delete BidList from id
	 * 
	 * @param id
	 * @return
	 */
	public void deleteBidListById(int id);
}