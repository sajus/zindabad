package com.cyb.tms.dto.base;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class BaseDTO implements Serializable {
	
	@Override
	 public String toString() {
	    return ReflectionToStringBuilder.toString(this);
	 }
}
