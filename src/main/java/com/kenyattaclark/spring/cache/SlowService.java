package com.kenyattaclark.spring.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SlowService {
	
	private SlowRepository slowRepository;
	
	@Cacheable("states")
	public String lookupState(final String code) {
		return slowRepository.lookupState(code);
	}
	
	public void setRepository(final SlowRepository repository) {
		this.slowRepository = repository;
	}
}