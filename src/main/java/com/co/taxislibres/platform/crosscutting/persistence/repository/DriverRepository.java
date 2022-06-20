package com.co.taxislibres.platform.crosscutting.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.co.taxislibres.platform.crosscutting.persistence.entity.ConductorEntity;


@Repository
public interface DriverRepository extends PagingAndSortingRepository<ConductorEntity, String> {
	
	public Optional<ConductorEntity> findByEmail(String email);

}
