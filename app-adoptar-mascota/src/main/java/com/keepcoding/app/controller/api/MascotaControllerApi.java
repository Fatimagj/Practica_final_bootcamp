package com.keepcoding.app.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.keepcoding.app.entity.Mascota;
import com.keepcoding.app.service.MascotaService;


@RestController
@RequestMapping("/api/mascotas")
public class MascotaControllerApi {
	
	@Autowired
	private MascotaService mascotaService;

	//Listado de todas las mascotas
    @GetMapping
    public List<Mascota> getAllMascotas() {
       List<Mascota> mascotas = mascotaService.getAllMascotas();
       return mascotas;
    }
    
    //Listado 20 perros más jóvenes
	 @GetMapping("/jovenes") 
	 public ResponseEntity<List<Mascota>> getYoungestMascota() {
	    List<Mascota> youngestMascota = mascotaService.getYoungestMascota(20);
	    return ResponseEntity.ok(youngestMascota);
	    }
	 
    //Obtener perros de forma paginada a 5
 	@GetMapping("/pagina")
 	public ResponseEntity<Page<Mascota>> getMascotaPaginada(@RequestParam(defaultValue = "0") int page) {
 		Page<Mascota> mascota = mascotaService.getMascotaPaginada(page, 5);
 		return ResponseEntity.ok(mascota);
 	} 
 	
	//Busccar por Id
	@GetMapping("/id/{id}")
	public ResponseEntity<Mascota> getMascotaById(@PathVariable Long id) {
		Mascota mascota = mascotaService.getMascotaById(id);
		if (mascota != null) {
			return ResponseEntity.ok(mascota);
		}else {
            return ResponseEntity.notFound().build(); }

	}

	//Busqueda por nombre
	 @GetMapping("/nombre/{name}")
	 public List<Mascota> getMascotaByNombre(@PathVariable String name) {
	     return mascotaService.getMascotaByName(name);
	}
	
	//Registrar nueva mascota
	@PostMapping("/registrar_mascota")
	@ResponseStatus(HttpStatus.CREATED)
	public Mascota saveMascota(@RequestBody @Valid Mascota mascota) {
	    Mascota guardarMascota = mascotaService.saveMascota(mascota);
	    return guardarMascota;
	     
	}

	// Eliminar mascota
    @DeleteMapping("/borrar/{id}")
    public void eliminarMascota(@PathVariable Long id) {
    	mascotaService.deleteMascota(id);
         
    }
    
}


	

	


