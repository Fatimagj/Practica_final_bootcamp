package com.keepcoding.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.keepcoding.app.entity.Mascota;
import com.keepcoding.app.service.MascotaService;



@Controller
//@RequestMapping("/api/mascotas")
public class MascotaController {
	
	@Autowired
	private MascotaService mascotaService;

	//Listado de todas las mascotas
    @GetMapping("/lista_mascotas")
    public String mostrarMascotas(Model model) {
        List<Mascota> mascotas = mascotaService.getAllMascotas();
        model.addAttribute("mascotas", mascotas);
        return "lista_mascotas";
    }

 // para obtener perros de forma paginada a 5
  	@GetMapping("/lista_mascotas/pagina")
  	public String getAllMascotaPaginada(@RequestParam(defaultValue = "0") int page, Model model) {
  		Page<Mascota> mascotaPage = mascotaService.getMascotaPaginada(page, 5);
  		model.addAttribute("mascotas", mascotaPage);
  		model.addAttribute("currentPage", page);
  		return "lista_mascotas";
  	} 
    
    // busqeuda por id
    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam(name = "id", required = false) Long id, Model model) {
        if (id != null) {
            Mascota mascota = mascotaService.getMascotaById(id);
            if (mascota != null) {
                model.addAttribute("mascota", mascota);
            } else {
                model.addAttribute("error", "Mascota no encontrada.");
            }
        }
        return "busqueda_id"; // nombre de la vista Thymeleaf
    }
    //busqueda por nombre
    @GetMapping("/buscar/nombre")
    public String getMascotaByName(@RequestParam String name, Model model) {
        List<Mascota> mascotas = mascotaService.getMascotaByName(name);
        if (mascotas.isEmpty()) {
            model.addAttribute("error", "No se encontraron mascotas con ese nombre.");
        } else {
            model.addAttribute("mascotas", mascotas);
        }
        return "busqueda_name";
    }
    
	 
	 // 20 mascotas más jóvenes
	 @GetMapping("/mascotas_jovenes") 
	 public String verYoungestMascotas(Model model) {
		 List<Mascota> mascotas = mascotaService.getYoungestMascota(20);
		 model.addAttribute("mascotas", mascotas);
		 return "mascotas_jovenes";
	    }
	
	
	//registrar nueva mascota

	@GetMapping ("/registrar_mascota")
	public String newMascotaForm(Model modelo) {
		Mascota mascota = new Mascota();
		modelo.addAttribute("mascota", mascota);
		return "registrar_mascota";
	}
	
	@PostMapping("/registrar_mascota")
	public String saveMascota(@ModelAttribute("mascota") @Valid Mascota mascota, BindingResult result) {
	    if (result.hasErrors()) {
	        return "registrar_mascota";
	    }
	    mascotaService.saveMascota(mascota);
	    return "redirect:/lista_mascotas";
	}

	 // Eliminar mascota
    @PostMapping("/delete/{id}")
    public String eliminarMascota(@PathVariable Long id) {
        mascotaService.deleteMascota(id);
        return "redirect:/lista_mascotas";
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


	

	


