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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.servicesImpl.TradeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TradeServiceTest {

	@Mock
	private TradeRepository tradeRepository;

	@InjectMocks
	private TradeServiceImpl tradeService;

	private Trade trade;

	private List<Trade> listResult;

	@Before
	public void setUp() {
		trade = new Trade(1, "Trade Account", "Type");
	}

	@Test
	public void saveTradeTest() {
		// arrange
		when(tradeService.saveTrade(trade)).thenReturn(trade);

		// act
		Trade result = tradeService.saveTrade(trade);

		// assert
		assertThat(result.getTradeId()).isEqualTo(1);

		verify(tradeRepository, times(1)).save(any(Trade.class));
	}

	@Test
	public void findTradeByIdTest() {
		// arrange
		when(tradeRepository.findById(trade.getTradeId())).thenReturn(Optional.of(trade));

		// Act
		Trade result = tradeService.findTradeById(trade.getTradeId());

		// Assert
		assertThat(result.getTradeId()).isEqualTo(trade.getTradeId());
	}

	@Test
	public void findTradeByListTest() {
		// arrange
		listResult = new ArrayList<>();
		listResult.add(new Trade(1, "Trade Account", "Type"));
		when(tradeRepository.findAll()).thenReturn(listResult);

		// act
		List<Trade> result = tradeService.findTradeBylist();

		// assert
		assertThat(result.size() > 0).isTrue();
		verify(tradeRepository, times(1)).findAll();
	}

	@Test
	public void updateTradeTest() {
		// arrange
		trade.setAccount("Trade Account Update");

		when(tradeService.updateTrade(trade)).thenReturn(trade);

		// act
		Trade result = tradeService.updateTrade(trade);

		// assert
		assertThat(result.getAccount()).isEqualTo("Trade Account Update");

		verify(tradeRepository, times(1)).save(any(Trade.class));
	}

	@Test
	public void deleteRatingByIdTest() {
		// arrange
		trade.setTradeId(1);
		when(tradeRepository.findById(trade.getTradeId())).thenReturn(Optional.of(trade));
		Integer id = trade.getTradeId();

		// act
		tradeService.deleteTradeById(id);

		// assert
		verify(tradeRepository, times(1)).delete(any(Trade.class));
	}
}