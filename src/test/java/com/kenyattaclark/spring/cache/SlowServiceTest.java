package com.kenyattaclark.spring.cache;

import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("classpath:application-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SlowServiceTest {
	
	private static final String WV = "WV";
	private static final String TX = "TX";
	private static final String OK = "OK";

	@Autowired 
	private SlowService service;
	
	@Mock
	private SlowRepository repository;
	
	@Before
	public void init() {
		initMocks(this);
		// Manually inject our mocked repository
		service.setRepository(repository);
	}
	
	@Test
	public void lookUpStateCache() {
		// First lookup - should hit slow repository
		service.lookupState(OK);
		
		// First lookup - should hit slow repository
		service.lookupState(TX);
		
		// Second lookup - should hit cache
		service.lookupState(OK);
		
		// Third lookup - should hit cache
		service.lookupState(OK);
		
		// First lookup - should hit repository
		service.lookupState(WV);
		
		// Second lookup - should hit cache
		service.lookupState(TX);
		
		verify(repository, atMost(1)).lookupState(OK);
		verify(repository, atMost(1)).lookupState(TX);
		verify(repository, atMost(1)).lookupState(WV);
	}
}