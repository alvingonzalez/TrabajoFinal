package com.takmen.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.takmen.models.entity.DetalleServicioPrenda;
import com.takmen.models.entity.Prenda;
import com.takmen.models.entity.Servicio;
import com.takmen.service.IPrendaService;
import com.takmen.service.IServicioService;
import com.takmen.service.ITipoPrendaService;
import com.takmen.util.PageRender;

@Controller
@RequestMapping("/prenda")
@SessionAttributes("prenda")
public class PrendaController {

	@Autowired
	private IPrendaService prendaService;
	
	@Autowired
	private IServicioService servicioService;
	
	@Autowired
	private ITipoPrendaService tipoPrendaService;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@GetMapping(value="/listar")
	public String listaPrenda(@RequestParam(name="page", defaultValue = "0")
	int page, Model model) 
	{
		
		Pageable pageRequest = PageRequest.of(page, 4);
		Page<Prenda> prendas = prendaService.findAll(pageRequest);
		PageRender<Prenda> pageRender = new PageRender<Prenda>("/prenda/listar", prendas);
		model.addAttribute("titulo","Lista de prendas");

		model.addAttribute("prendas",prendas);
		model.addAttribute("page", pageRender);
		return "prenda/listar";
		
	}
	
	
	@PostMapping(value="/guarda")
	public String guardaPrenda(@Valid Prenda prenda,
			BindingResult result, Model model,
			@RequestParam(name = "servicio_id[]", required = false) Long[] servicioId,
			RedirectAttributes flash,
			SessionStatus status)
	{
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Nueva Prenda");
			return "prenda/formulario";
		}
		if (servicioId == null || servicioId.length == 0) {
			model.addAttribute("titulo", "Guardar prenda");
			model.addAttribute("error", "Error: La prenda NO puede no tener servicios!");
			return "prenda/formulario";}
		
		
		for (int i = 0; i < servicioId.length; i++) {
			Servicio servicio = servicioService.findByidServicio(servicioId[i]);

			DetalleServicioPrenda detalle = new DetalleServicioPrenda();
			detalle.setServicio(servicio);
			prenda.addDetalle(detalle);
	
			log.info("ID: " + servicioId[i]);
		}
		
		prendaService.save(prenda);
		status.setComplete();
		flash.addFlashAttribute("Exitoso","Prenda fue creada exitosamente");
		return "redirect:/prenda/listar";
		
	}
	
	
	@GetMapping(value = "edita/{id}")
	public String editaPrenda(@PathVariable(value="id") Long id,
			Model model, RedirectAttributes flash)
	{
		Prenda prenda = null;
		
		if(id > 0)
		{
			prenda = prendaService.findById(id);
			if(prenda==null)
			{
				flash.addFlashAttribute("error", "El ID de al prenda no existe en la base de datos");
				return "redirect:/prenda/listar";
			}
		}else 
		{
			flash.addFlashAttribute("error", "El ID de la prenda no existe en la base de datos");
			return "redirect:/prenda/listar";
		}
		model.addAttribute("prenda", prenda);
		model.addAttribute("tiposPrenda",tipoPrendaService.findAll());
		model.addAttribute("titulo", "Editar prenda");
		
	return 	"prenda/formulario";
	}
	
	
	@GetMapping(value="/cargaServicios/{term}", produces = { "application/json" })
	public @ResponseBody List<Servicio> cargaServicios(@PathVariable String term) {
		return servicioService.findBynombreServicioLikeIgnoreCase(term);
		}
	
	@GetMapping(value = "/nueva")
	public String nuevaPrenda(Model model)
	{
		Prenda prenda= new Prenda();
		model.addAttribute("prenda", prenda);
		model.addAttribute("titulo","Guardar prenda");
		model.addAttribute("tiposPrenda",tipoPrendaService.findAll());
		return "prenda/formulario";
	}
	
	
	
	@GetMapping(value="elimina/{id}")
	public String eliminaPrenda(@PathVariable(value="id") Long id,
		RedirectAttributes flash)
	{
		if (id>0)
		{
			prendaService.deleteById(id);
			flash.addAttribute("exitoso", "Prenda eliminada");	
	}
		return "redirect:/prenda/listar";
	}
	
	@GetMapping(value = "/detalle/{id}")
	public String detallesPrenda(@PathVariable(value="id") Long id,
			Model model, RedirectAttributes flash)
	{
		Prenda prenda = prendaService.findById(id);
		if(prenda == null)
		{
			flash.addAttribute("error", "La prenda no existe");
			return "redirect:/prenda/listar";
		}
		model.addAttribute("prenda", prenda);
		model.addAttribute("titulo","Prenda");
		
		return "prenda/detalle";
		
	}
}
