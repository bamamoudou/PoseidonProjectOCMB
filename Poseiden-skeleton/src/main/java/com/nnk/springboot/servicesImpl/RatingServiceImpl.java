package com.nnk.springboot.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService {
	@Autowired
	RatingRepository ratingRepository;

	@Override
	public Rating saveRating(Rating rating) {
		return ratingRepository.save(rating);
	}

	@Override
	public Rating findRatingById(int id) {
		Optional<Rating> rating = ratingRepository.findById(id);
		if (rating == null) {
			return null;
		} else
			return rating.get();
	}

	@Override
	public List<Rating> findRatingByList() {
		return ratingRepository.findAll();
	}

	@Override
	public Rating updateRating(Rating rating) {
		return ratingRepository.save(rating);
	}

	@Override
	public void deleteRatingById(int id) {
		ratingRepository.delete(findRatingById(id));
	}
}