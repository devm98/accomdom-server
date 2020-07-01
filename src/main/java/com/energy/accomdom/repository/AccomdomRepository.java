package com.energy.accomdom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.energy.accomdom.entity.Accomdom;

public interface AccomdomRepository extends JpaRepository<Accomdom, Long>, PagingAndSortingRepository<Accomdom, Long> {
	
	@Query(value = "SELECT u FROM Accomdom u WHERE u.id = ?1")
	Accomdom findByAccomdomId(Long id);
}
