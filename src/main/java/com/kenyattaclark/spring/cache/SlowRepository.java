package com.kenyattaclark.spring.cache;

public interface SlowRepository {
	
	String lookupState(String code);
}