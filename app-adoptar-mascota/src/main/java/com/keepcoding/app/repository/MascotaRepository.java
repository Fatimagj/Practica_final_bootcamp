package com.keepcoding.app.repository;

import java.util.List;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.keepcoding.app.entity.Mascota;

public interface MascotaRepository extends PagingAndSortingRepository<Mascota, Long> {

	@Query("SELECT m FROM Mascota m WHERE LOWER(m.name) = LOWER(:name)")
	List<Mascota> findByNameIgnoreCase(@Param("name") String name);

	List<Mascota> findAll();
	



}
