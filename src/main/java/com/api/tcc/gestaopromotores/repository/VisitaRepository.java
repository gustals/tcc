package com.api.tcc.gestaopromotores.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.api.tcc.gestaopromotores.entities.Visita;


public interface VisitaRepository extends JpaRepository<Visita, Long>{
	
	List<Visita> findByPromotorIdAndDataVisitaBetween(Long id,  LocalDate inicioMes, LocalDate fimMes);
	
	List<Visita> findByGestorResponsavelIdAndDataVisitaBetween(Long id,  LocalDate inicioMes, LocalDate fimMes);
	
}
