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

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.servicesImpl.CurveServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CurveServiceTest {

	@Mock
	private CurvePointRepository curvePointRepository;

	@InjectMocks
	private CurveServiceImpl curveService;

	private CurvePoint curvePoint;

	private List<CurvePoint> listResult;

	@Before
	public void setUp() {
		curvePoint = new CurvePoint(1, 10d, 30d);
	}

	@Test
	public void saveCurvePointTest() {
		// arrange
		when(curveService.saveCurvePoint(curvePoint)).thenReturn(curvePoint);

		// act
		CurvePoint result = curveService.saveCurvePoint(curvePoint);

		// assert
		assertThat(result.getCurveId()).isEqualTo(1);

		verify(curvePointRepository, times(1)).save(any(CurvePoint.class));
	}

	@Test
	public void findCurvePointByIdTest() {
		// arrange
		when(curvePointRepository.findById(curvePoint.getCurveId())).thenReturn(Optional.of(curvePoint));

		// Act
		CurvePoint result = curveService.findCurvePointById(curvePoint.getCurveId());

		// Assert
		assertThat(result.getCurveId()).isEqualTo(curvePoint.getCurveId());
	}

	@Test
	public void findCurvePointByListTest() {
		// arrange
		listResult = new ArrayList<>();
		listResult.add(new CurvePoint(1, 10d, 30d));
		when(curvePointRepository.findAll()).thenReturn(listResult);

		// act
		List<CurvePoint> result = curveService.findCurvePointByList();

		// assert
		assertThat(result.size() > 0).isTrue();
		verify(curvePointRepository, times(1)).findAll();
	}

	@Test
	public void updateCurvePointTest() {
		// arrange
		curvePoint.setTerm(20d);

		when(curveService.updateCurvePoint(curvePoint)).thenReturn(curvePoint);

		// act
		CurvePoint result = curveService.updateCurvePoint(curvePoint);

		// assert
		assertThat(result.getTerm()).isEqualTo(20d);

		verify(curvePointRepository, times(1)).save(any(CurvePoint.class));
	}

	@Test
	public void deleteCurvePointByIdTest() {
		// arrange
		curvePoint.setCurveId(1);
		when(curvePointRepository.findById(curvePoint.getCurveId())).thenReturn(Optional.of(curvePoint));
		Integer id = curvePoint.getCurveId();

		// act
		curveService.deleteCurvePointById(id);

		// assert
		verify(curvePointRepository, times(1)).delete(any(CurvePoint.class));
	}
}