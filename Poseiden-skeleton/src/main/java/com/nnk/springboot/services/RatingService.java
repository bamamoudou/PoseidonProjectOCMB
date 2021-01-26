package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.Rating;

/**
 * RatingService CRUD manager
 */
public interface RatingService {
	/**
	 * save the rating and return instance save
	 * 
	 * @param rating
	 * @return
	 */
	public Rating saveRating(Rating rating);

	/**
	 * return rating from id
	 * 
	 * @param id
	 * @return
	 */
	public Rating findRatingById(int id);

	/**
	 * return list rating
	 * 
	 * @return
	 */
	public List<Rating> findRatingByList();

	/**
	 * Update Rating
	 * 
	 * @param rating
	 * @return
	 */
	public Rating updateRating(Rating rating);

	/**
	 * delete rating from id
	 * 
	 * @param id
	 * @return
	 */
	public void deleteRatingById(int id);
}