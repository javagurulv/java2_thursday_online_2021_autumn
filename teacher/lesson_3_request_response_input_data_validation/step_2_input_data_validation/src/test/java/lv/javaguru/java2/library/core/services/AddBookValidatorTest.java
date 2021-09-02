package lv.javaguru.java2.library.core.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import lv.javaguru.java2.library.core.requests.AddBookRequest;
import lv.javaguru.java2.library.core.responses.CoreError;
import lv.javaguru.java2.library.core.services.validators.AddBookValidator;

public class AddBookValidatorTest {

	private AddBookValidator validator = new AddBookValidator();

	@Test
	public void shouldReturnEmptyList() {
		AddBookRequest request = new AddBookRequest("title", "author");
		List<CoreError> errorList = validator.validate(request);
		assertTrue(errorList.isEmpty());
	}

	@Test
	public void shouldReturnTitleError() {
		AddBookRequest request = new AddBookRequest("", "author");
		List<CoreError> errorList = validator.validate(request);
		assertEquals(errorList.size(), 1);
		assertEquals(errorList.get(0).getField(), "title");
		assertEquals(errorList.get(0).getMessage(), "Must not be empty!");
	}

	@Test
	public void shouldReturnAuthorError() {
		AddBookRequest request = new AddBookRequest("title", "");
		List<CoreError> errorList = validator.validate(request);
		assertEquals(errorList.size(), 1);
		assertEquals(errorList.get(0).getField(), "author");
		assertEquals(errorList.get(0).getMessage(), "Must not be empty!");
	}

	@Test
	public void shouldReturnTitleAndAuthorErrors() {
		AddBookRequest request = new AddBookRequest("", "");
		List<CoreError> errorList = validator.validate(request);
		assertEquals(errorList.size(), 2);
		assertEquals(errorList.get(0).getField(), "title");
		assertEquals(errorList.get(0).getMessage(), "Must not be empty!");
		assertEquals(errorList.get(1).getField(), "author");
		assertEquals(errorList.get(1).getMessage(), "Must not be empty!");
	}

}