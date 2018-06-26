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

import com.takmen.models.entity.TipoPrenda;
import com.takmen.service.ITipoPrendaService;
import com.takmen.util.PageRender;

@Controller 
@SessionAttributes ("tipoprenda")
@RequestMapping("/tipoprenda/")
public class TipoPrendaController {

	@Autowired
	private ITipoPrendaService tipoPrendaService;
	
	@GetMapping(value="/listartipoprenda")
	public String listar (@RequestParam (name="page", defaultValue="0")
	int pagina, Model model) 
	{
		Pageable pageRequest = PageRequest.of(pagina, 15);
		Page<TipoPrenda> tipoprendas = tipoPrendaService.findAll(pageRequest);
		PageRender<TipoPrenda> pageRender = new PageRender<>("/tipoprenda/listartipoprenda/",tipoprendas );
		
		model.addAttribute("title","lista de tipo de prendas");
		
		model.addAttribute("tipoprendas",tipoprendas);
		model.addAttribute("page",pageRender);
		return "tipoprenda/listTipoPrenda";
		}
	
	
	
	@GetMapping(value="/new")
	 public String nuevoTipoPrenda (Model model)
	 {
	TipoPrenda tipoPrenda= new TipoPrenda();
	model.addAttribute("tipoprenda",tipoPrenda);
	model.addAttribute("title","Nuevo Tipo Prenda");
	return "tipoprenda/formTipoPrenda";
	 }
	
@PostMapping(value="/save")
public String saveTipoPrenda (@Valid TipoPrenda tipoPrenda, BindingResult result, 
		Model model, RedirectAttributes flash,SessionStatus status)
{
	if (result.hasErrors()) {
		model.addAttribute("title","Guardar Tipo Prenda");
		return "tipoprenda/formTipoPrenda";
	}
	String mensajeFlash = (tipoPrenda.getIdTipoPrenda() != null) ? "Editado satisfactoriamente tipo prenda!"
			: "Tipo prenda created successfully!";

	tipoPrendaService.save(tipoPrenda);
	status.setComplete();
	flash.addFlashAttribute("success", mensajeFlash);
	return "redirect:/tipoprenda/listartipoprenda";
}
	

@GetMapping(value = "/edit/{idtipoprenda}")
public String editTipoEmpleado(@PathVariable(value = "idtipoprenda") Long id, Model model, RedirectAttributes flash) {

	TipoPrenda tipoprenda = null;

	if (id > 0) {
		tipoprenda = tipoPrendaService.findById(id);
		if (tipoprenda == null) {
			flash.addFlashAttribute("error", "El Id del TipoCliente no existe en la base de datos!");
			return "redirect:/tipoprenda/listartipoprenda";
		}
	} else {
		flash.addFlashAttribute("error", "El Id del TipoCliente no puede ser cero!");
		return "redirect:/tipoprenda/";
	}
	model.addAttribute("tipoprenda", tipoprenda);
	model.addAttribute("title", "Editar tipoempleado");
	return "tipoprenda/formTipoPrenda";
}

@GetMapping(value = "/delete/{idtipoprenda}")
public String eliminarcliente(@PathVariable(value = "idtipoprenda") Long id, RedirectAttributes flash) {

	if (id > 0) {

		tipoPrendaService.delete(id);
		flash.addFlashAttribute("success", "Tipo prenda removed successfully!");
	}
	return "redirect:/tipoprenda/listartipoprenda";
}


}
