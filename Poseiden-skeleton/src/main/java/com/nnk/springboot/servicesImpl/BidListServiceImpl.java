package com.nnk.springboot.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;

@Service
public class BidListServiceImpl implements BidListService {
	@Autowired
	BidListRepository bidListRepository;

	@Override
	public BidList saveBidList(BidList bidList) {
		return bidListRepository.save(bidList);
	}

	@Override
	public BidList findBidListById(int id) {
	   Optional<BidList> bidListOptional = bidListRepository.findById(id);
      if (bidListOptional.isPresent()) {
          BidList bidList = bidListOptional.get();
          return bidList;
      }
      return null;
	}

	@Override
	public BidList updateBidList(BidList bidList) {
		if (bidListRepository.findById(bidList.getBidListId()) != null) {
			return bidListRepository.save(bidList);
		}
		return null;
	}

	@Override
	public List<BidList> findBidListByList() {
		return bidListRepository.findAll();
	}

	@Override
	public void deleteBidListById(int id) {
		if (bidListRepository.findById(id) != null) {
			bidListRepository.deleteById(id);
		}
	}
}