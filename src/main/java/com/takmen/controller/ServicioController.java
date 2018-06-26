	package com.takmen.controller;

	import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.takmen.models.entity.Servicio;
import com.takmen.service.IServicioService;
import com.takmen.util.PageRender;

	@Controller 
	@SessionAttributes ("servicio")
	@RequestMapping("/servicio/")
	public class ServicioController {

		@Autowired
		private IServicioService servicioService;
		
		@GetMapping(value="/listarservicio")
		public String listar(@RequestParam (name="page",defaultValue="0") 
		int pagina, Model model )
		{
		 Pageable pageRequest = PageRequest.of(pagina, 15);	
		 Page<Servicio> servicios= servicioService.findAll(pageRequest);
		 PageRender< Servicio> pageRender = new PageRender<Servicio>("/servicio/listarservicio/", servicios);
		 
		 model.addAttribute("title", "List of customers");

			model.addAttribute("servicios", servicios);
			model.addAttribute("page", pageRender);

			return "servicio/listarServicio";
		}
		
		
		

	@GetMapping(value="/new")
		 public String nuevoServico (Model model)
		 {
		Servicio servicio= new Servicio();
		model.addAttribute("servicio",servicio);
		model.addAttribute("title","nuevo servicio");
		return "servicio/formServicio";
		 }
		
	@PostMapping(value="/save")
	public String guardarServicio (@Valid Servicio servicio, BindingResult result, 
			Model model, RedirectAttributes flash,SessionStatus status)
	{
		if (result.hasErrors()) {
			model.addAttribute("title","Guardar Servicio");
			return "servicio/formServicio";
		}
		String mensajeFlash = (servicio.getIdServicio()!= null) ? "Successfully edited Servicio!"
				: "Servicio creado satisfactoriamente!";

		servicioService.save(servicio);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/servicio/listarservicio";
	}
		
	@GetMapping(value = "/edit/{idservicio}")
	public String editarServicio(@PathVariable(value = "idservicio") Long id, Model model, RedirectAttributes flash) {

		Servicio servicio= null;

		if (id > 0) {
			servicio = servicioService.findByidServicio(id);
			if (servicio == null) {
				flash.addFlashAttribute("error", "The customer ID does not exist in the database!");
				return "redirect:/servicio/listarservicio";
			}
		} else {
			flash.addFlashAttribute("error", "The service ID can not be zero!");
			return "redirect:/servicio/";
		}
		model.addAttribute("servicio", servicio);
		model.addAttribute("title", "Editar Servicio");
		return "servicio/formServicio";
	}


	@GetMapping(value = "/delete/{idservicio}")
	public String eliminarServicio(@PathVariable(value = "idservicio") Long id, RedirectAttributes flash) {

		if (id > 0) {

			servicioService.delete(id);
			flash.addFlashAttribute("success", "Servicee removed successfully!");
		}
		return "redirect:/servicio/listarservicio";
	}


	}

