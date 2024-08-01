package com.keepcoding.app.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    // para obtener perros de forma paginada
 	@GetMapping("/pagina/{pagina}")
 	public Iterable<Mascota> getMascotaPaged(@PathVariable("pagina")@RequestParam int pagina) {
 		PageRequest pageRequest  = PageRequest.of(pagina, 5);
 		return mascotaService.getAllMascotaPaged(pageRequest);
 	} 
 	
	//obtener id para su busqueda 
	@GetMapping("/id/{id}")
	public ResponseEntity<Mascota> getMascotaById(@PathVariable Long id) {
		Mascota mascota = mascotaService.getMascotaById(id);
		if (mascota != null) {
			return ResponseEntity.ok(mascota);
		}else {
            return ResponseEntity.notFound().build(); }

		}

	// Obtener nombre para la búsqueda
	 @GetMapping("/nombre/{name}")
	 public List<Mascota> getMascotaByNombre(@PathVariable String name) {
	     return mascotaService.getMascotaByName(name);
	    }
	  

	 //Para obtener los 20 perros más jóvenes
	 @GetMapping("/jovenes") 
	 public ResponseEntity<List<Mascota>> getYoungestMascota() {
	    List<Mascota> youngestMascota = mascotaService.getYoungestMascota(20);
	    return ResponseEntity.ok(youngestMascota);
	    }
	
	
	//registrar nueva mascota
	@PostMapping("/registrar_mascota")
	@ResponseStatus(HttpStatus.CREATED)
	public Mascota saveMascota(@ModelAttribute("mascota") @Valid Mascota mascota) {
	    Mascota guardarMascota = mascotaService.saveMascota(mascota);
	    return guardarMascota;
	     
	}

	 // Eliminar mascota
    @DeleteMapping("/borrar/{id}")
    public void eliminarMascota(@PathVariable Long id) {
    	mascotaService.deleteMascota(id);
         
    }
    
 
    
    //MIRAR ESTO QUE ME ESTA DANDO ERROR PARA EDITARLA
    //Para editar una mascota ya guardada
    /*
	@GetMapping("/mascota/editar/{id}")
	public String updateMascotaForm(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("mascota_update", mascotaService.saveMascota(id));
		return "editar_mascota";
	}
	
	@PostMapping("/mascota/{id}")
	public String updateMascota(@PathVariable Long id, @ModelAttribute("mascota_update") Mascota mascota) {
		Mascota mascotaExistente = mascotaService.saveMascota(id);
		mascotaExistente.setId(id);
		mascotaExistente.setName(mascota.getName());
		mascotaExistente.setFechaNac(mascota.getFechaNac());
		mascotaExistente.setRaza(mascota.getRaza());
		mascotaExistente.setPeso(mascota.getPeso());
		mascotaExistente.setTiene_chip(mascota.isTiene_chip());
		mascotaExistente.setUrl_foto(mascota.getUrl_foto());
	
		mascotaService.updateMascota(mascotaExistente);
		return "redirect:/lista_mascotas";
	} 
	*/
}


	

	


