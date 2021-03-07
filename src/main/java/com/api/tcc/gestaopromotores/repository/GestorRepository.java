package com.api.tcc.gestaopromotores.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.tcc.gestaopromotores.entities.Gestor;

public interface GestorRepository extends JpaRepository<Gestor, Long>{
	
	Gestor findByMatricula(Integer matricula);
	
}
