package com.api.tcc.gestaopromotores.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.tcc.gestaopromotores.entities.Promotor;

public interface PromotorRepository extends JpaRepository<Promotor, Long> {
	
	Promotor findByMatricula(Integer id);

}
