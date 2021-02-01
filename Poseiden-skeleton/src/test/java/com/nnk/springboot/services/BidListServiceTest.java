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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.servicesImpl.BidListServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BidListServiceTest {
	@Mock
	private BidListRepository bidListRepository;

	@InjectMocks
	private BidListServiceImpl bidListService;
	private BidList bid;
	private List<BidList> listResult;

	@Before
	public void setUp() {
		bid = new BidList();
		bid.setBidListId(1);
		bid.setAccount("Account Test");
		bid.setType("type Test");
		bid.setBidQuantity(10d);
	}

	@Test
	public void saveBidListTest() {
		// arrange
		when(bidListService.saveBidList(bid)).thenReturn(bid);

		// act
		BidList result = bidListService.saveBidList(bid);

		// assert
		assertThat(result.getBidListId()).isEqualTo(1);

		verify(bidListRepository, times(1)).save(any(BidList.class));
	}

	@Test
	public void findBidListByIdTest() {
		// arrange
		when(bidListRepository.findById(bid.getBidListId())).thenReturn(Optional.of(bid));

		// Act
		BidList result = bidListService.findBidListById(bid.getBidListId());

		// Assert
		assertThat(result.getBidListId()).isEqualTo(bid.getBidListId());
	}

	@Test
	public void findBidListByListTest() {
		// arrange
		listResult = new ArrayList<BidList>();
		listResult.add(new BidList("Account Test", "Type Test", 10d));
		when(bidListRepository.findAll()).thenReturn(listResult);

		// act
		List<BidList> result = bidListService.findBidListByList();

		// assert
		assertThat(result.size() > 0).isTrue();
		verify(bidListRepository, times(1)).findAll();
	}

	@Test
	public void updateBidListTest() {
		// arrange
		bid.setBidQuantity(20d);

		when(bidListService.updateBidList(bid)).thenReturn(bid);

		// act
		BidList result = bidListService.updateBidList(bid);

		// assert
		assertThat(result.getBidQuantity()).isEqualTo(20d);

		verify(bidListRepository, times(1)).save(any(BidList.class));
	}

	@Test
	public void deleteBidListByIdTest() {
		// arrange
		bid.setBidListId(1);
		when(bidListRepository.findById(bid.getBidListId())).thenReturn(Optional.of(bid));
		Integer id = bid.getBidListId();

		// act
		bidListService.deleteBidListById(id);

		// assert
		verify(bidListRepository, times(1)).deleteById(id);
	}
}