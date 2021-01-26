package com.nnk.springboot.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;

@Service
public class TradeServiceImpl implements TradeService {
	@Autowired
	TradeRepository tradeRepository;

	@Override
	public Trade saveTrade(Trade trade) {
		return tradeRepository.save(trade);
	}

	@Override
	public Trade findTradeById(int id) {
		Optional<Trade> trade = tradeRepository.findById(id);
		if (trade == null) {
			return null;
		}
		return trade.get();
	}

	@Override
	public Trade updateTrade(Trade trade) {
		return tradeRepository.save(trade);
	}

	@Override
	public List<Trade> findTradeBylist() {
		return tradeRepository.findAll();
	}

	@Override
	public void deleteTradeById(int id) {
		tradeRepository.delete(findTradeById(id));
	}
}