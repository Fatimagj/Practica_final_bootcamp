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

	//Toda la lista de mascotas
	public List<Mascota> getAllMascotas() {
		return mascotaRepository.findAll();
	}
	
	public Iterable<Mascota> getAllMascotaPaged(Pageable pageable) {
		return mascotaRepository.findAll(pageable);
	
	}
	
	//buscar por id
	public Mascota getMascotaById(Long id) {
		return mascotaRepository.findById(id).orElse(null);
	}
	
	//buscar por nombre
	public List<Mascota> getMascotaByName(String name) {
        return mascotaRepository.findByNameIgnoreCase(name);
    }
	
	//buscar los más jóvenes
    public List<Mascota> getYoungestMascota(int count) {
        Pageable pageable = PageRequest.of(0, count, Sort.by(Sort.Direction.ASC, "fechaNac"));
        return mascotaRepository.findAll(pageable).getContent();
    }
    
	
	//Guardar
	public Mascota saveMascota(Mascota mascota) {
		return mascotaRepository.save(mascota);
	}

	//Borrar
	public void deleteMascota(Long id) {
		mascotaRepository.deleteById(id);
		
	}

	

	

	
	//**para modificar una mascota ya guardada
	//public Mascota updateMascota(Mascota mascota) {
		//return mascotaRepository.save(mascota);
	//}


	//public Mascota obtenerMascota(Long id) {
		//return mascotaRepository.findById(id).get();
	//}
	
	
	

}
