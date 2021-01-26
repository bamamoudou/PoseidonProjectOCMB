package com.nnk.springboot;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Test;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.User;

public class ValidatorTest {

	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	public void userInvalidPasswordTest() {
		User user = new User("Mamoudou", "1234556", "Mamoudou Ba", "ROLE_USER");
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void userValidPasswordTest() {
		User user = new User("Mamoudou", "Mamoudou@2021", "Mamoudou Ba", "ROLE_USER");
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		assertEquals(0, constraintViolations.size());
	}

	@Test
	public void bidListInvalidTest() {
		BidList bid = new BidList("Account Test", "Type Test", 0.0);
		Set<ConstraintViolation<BidList>> constraintViolations = validator.validate(bid);
		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void bidListValidTest() {
		BidList bid = new BidList("Account Test", "Type Test", 0.01);
		Set<ConstraintViolation<BidList>> constraintViolations = validator.validate(bid);
		assertEquals(0, constraintViolations.size());
	}

	@Test
	public void curvePointInvalidTest() {
		CurvePoint curvePoint = new CurvePoint(10, 10d, 0.0);
		Set<ConstraintViolation<CurvePoint>> constraintViolations = validator.validate(curvePoint);
		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void curvePointValidTest() {
		CurvePoint curvePoint = new CurvePoint(10, 10d, 0.01);
		Set<ConstraintViolation<CurvePoint>> constraintViolations = validator.validate(curvePoint);
		assertEquals(0, constraintViolations.size());
	}
}