package com.energy.accomdom.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.energy.accomdom.entity.Type;
import com.energy.accomdom.repository.TypeRepository;
import com.energy.accomdom.service.TypeService;

@Service
public class TypeServiceImpl implements TypeService{
	private final TypeRepository typeRepository;
	
	public TypeServiceImpl(TypeRepository typeRepository) {
		this.typeRepository = typeRepository;
	}

	@Override
	public List<Type> getAllTypes() {
		return (List<Type>) typeRepository.findAll();
	}

	@Override
	public Type findByTypeName(String typeName) {
		return typeRepository.findByTypeName(typeName);
	}

	@Override
	public Type save(Type type) {
		return typeRepository.save(type);
	}

	@Override
	public Type findOne(Long id) {
		return typeRepository.findById(id).get();
	}

}
