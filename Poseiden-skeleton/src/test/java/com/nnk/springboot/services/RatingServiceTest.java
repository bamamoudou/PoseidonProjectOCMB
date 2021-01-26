package com.nnk.springboot.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.servicesImpl.RatingServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class RatingServiceTest {

	@Mock
	private RatingRepository ratingRepository;

	@InjectMocks
	private RatingServiceImpl ratingService;

	private Rating rating;

	private List<Rating> listResult;

	@Before
	public void setUp() {
		rating = new Rating(1, "Moodys Rating", "Sand PRating", "Fitch Rating", 10);
	}

	@Test
	public void saveRatingTest() {
		// arrange
		when(ratingService.saveRating(rating)).thenReturn(rating);

		// act
		Rating result = ratingService.saveRating(rating);

		// assert
		assertThat(result.getId()).isEqualTo(1);

		verify(ratingRepository, times(1)).save(any(Rating.class));
	}

	@Test
	public void findRatingByIdTest() {
		// arrange
		when(ratingRepository.findById(rating.getId())).thenReturn(Optional.of(rating));

		// Act
		Rating result = ratingService.findRatingById(rating.getId());

		// Assert
		assertThat(result.getId()).isEqualTo(rating.getId());
	}

	@Test
	public void findRatingByListTest() {
		// arrange
		listResult = new ArrayList<>();
		listResult.add(new Rating(1, "Moodys Rating", "Sand PRating", "Fitch Rating", 10));
		when(ratingRepository.findAll()).thenReturn(listResult);

		// act
		List<Rating> result = ratingService.findRatingByList();

		// assert
		assertThat(result.size() > 0).isTrue();
		verify(ratingRepository, times(1)).findAll();
	}

	@Test
	public void updateRatingTest() {
		// arrange
		rating.setMoodysRating("Moodys Rate");
		;

		when(ratingService.updateRating(rating)).thenReturn(rating);

		// act
		Rating result = ratingService.updateRating(rating);

		// assert
		assertThat(result.getMoodysRating()).isEqualTo("Moodys Rate");

		verify(ratingRepository, times(1)).save(any(Rating.class));
	}

	@Test
	public void deleteRatingByIdTest() {
		// arrange
		rating.setId(1);
		when(ratingRepository.findById(rating.getId())).thenReturn(Optional.of(rating));
		Integer id = rating.getId();

		// act
		ratingService.deleteRatingById(id);

		// assert
		verify(ratingRepository, times(1)).delete(any(Rating.class));
	}
}