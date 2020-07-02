package com.energy.accomdom.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.energy.accomdom.entity.Accomdom;
import com.energy.accomdom.entity.Type;
import com.energy.accomdom.entity.User;
import com.energy.accomdom.model.request.AccomdomModelRequest;
import com.energy.accomdom.repository.AccomdomRepository;
import com.energy.accomdom.repository.TypeRepository;
import com.energy.accomdom.repository.UserRepository;
import com.energy.accomdom.service.AccomdomService;

@Service
public class AccomdomServiceImpl implements AccomdomService {
	private final AccomdomRepository accomdomRepository;
	private final TypeRepository typeRepository;
	private final UserRepository userRepository;

	public AccomdomServiceImpl(AccomdomRepository accomdomRepository, TypeRepository typeRepository,
			UserRepository userRepository) {
		this.accomdomRepository = accomdomRepository;
		this.typeRepository = typeRepository;
		this.userRepository = userRepository;
	}
	

	@Override
	public List<Accomdom> getAccomdoms() {
		List<Accomdom> users = accomdomRepository.findAll();
		return users;
	}

	@Override
	public List<Accomdom> getAccomdoms(int page, int limit) {
		Pageable paging = PageRequest.of(page, limit);
		Page<Accomdom> results = accomdomRepository.findAll(paging);
		return results.toList();
	}

	@Override
	public Accomdom createAccomdomPost(AccomdomModelRequest req) {
		Accomdom localAccomdom = accomdomRepository.findByAccomdomId(req.getId());
		Accomdom a = new Accomdom();

		a.setTitle(req.getTitle());
		a.setContent(req.getContent());
		a.setAddress(req.getAddress());
		a.setArea(req.getArea());
		a.setRice(req.getRice());
		a.setImage(req.getImage());

		Optional<Type> type = typeRepository.findById(req.getType_id());
		a.setType(type.get());

		Optional<User> user = userRepository.findById(req.getUser_id());
		a.setUser(user.get());

		localAccomdom = accomdomRepository.save(a);

		return localAccomdom;
	}

	@Override
	public Accomdom updateAccomdomPost(long id, AccomdomModelRequest req) {
		Accomdom localAccomdom = accomdomRepository.findByAccomdomId(req.getId());
		Accomdom a = new Accomdom();

		a.setId(id);
		a.setTitle(req.getTitle());
		a.setContent(req.getContent());
		a.setAddress(req.getAddress());
		a.setArea(req.getArea());
		a.setRice(req.getRice());
		a.setImage(req.getImage());

		Optional<Type> type = typeRepository.findById(req.getType_id());
		a.setType(type.get());

		Optional<User> user = userRepository.findById(req.getUser_id());
		a.setUser(user.get());

		localAccomdom = accomdomRepository.save(a);

		return localAccomdom;
	}

	@Override
	public Accomdom findOne(Long id) {
		return accomdomRepository.findById(id).get();
	}

	@Override
	public void deleteAccomdomPost(Long id) {
		accomdomRepository.deleteById(id);
	}

}
