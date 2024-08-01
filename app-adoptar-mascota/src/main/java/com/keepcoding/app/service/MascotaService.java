package com.keepcoding.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.keepcoding.app.entity.Mascota;
import com.keepcoding.app.repository.MascotaRepository;


@Service
public class MascotaService {
	
	@Autowired
	private MascotaRepository mascotaRepository;

	//Listado de todas las mascotas
	public List<Mascota> getAllMascotas() {
		return mascotaRepository.findAll();
	}
	
	//Listado 20 mascotas más jóvenes
    public List<Mascota> getYoungestMascota(int count) {
        Pageable pageable = PageRequest.of(0, count, Sort.by(Sort.Direction.ASC, "fechaNac"));
        return mascotaRepository.findAll(pageable).getContent();
    }
    
    //Obtener mascotas de forma paginada a 5
	public Page<Mascota> getMascotaPaginada(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return mascotaRepository.findAll(pageable);
	}
	
	//Buscar por id
	public Mascota getMascotaById(Long id) {
		return mascotaRepository.findById(id).orElse(null);
	}
	
	//Buscar por nombre
	public List<Mascota> getMascotaByName(String name) {
        return mascotaRepository.findByNameIgnoreCase(name);
    }
	
	//Registrar nueva mascota
	public Mascota saveMascota(Mascota mascota) {
		return mascotaRepository.save(mascota);
	}

	//Eliminar mascota
	public void deleteMascota(Long id) {
		mascotaRepository.deleteById(id);
	}

}
