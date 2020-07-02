package com.energy.accomdom.service;

import java.util.List;

import com.energy.accomdom.entity.Type;

public interface TypeService {
	
	List<Type> getAllTypes();
	
    Type findByTypeName(String typeName);

    Type save(Type type);

    Type findOne(Long id);
}
