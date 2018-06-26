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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.takmen.models.entity.TipoEmpleado;
import com.takmen.service.ITipoEmpleadoService;
import com.takmen.util.PageRender;

@Controller
@SessionAttributes({"tipoempleado"})
@RequestMapping("/tipoempleados/")
public class TipoEmpleadoController {

	@Autowired
	private ITipoEmpleadoService tipoeservice;
	
	@GetMapping(value="/listTipoEmpleado")
	public String listarTipoEmpleado(@RequestParam(name = "page", defaultValue = "0") int page,Model model) {
		
		Pageable pageRequest = PageRequest.of(page, 8);
		Page<TipoEmpleado> tipoempleados = tipoeservice.findAll(pageRequest);
		PageRender<TipoEmpleado> pageRender = new PageRender<TipoEmpleado>("/tipoempleados/listTipoEmpleado",tipoempleados);
		
		model.addAttribute("title","Lista de Tipo de Empleados");
		model.addAttribute("tipoempleados",tipoempleados);
		model.addAttribute("page", pageRender);
		return "tipoempleado/listTipoEmpleado";//vista, lugar en la  carpeta
	}
	@GetMapping(value="/nuevoTipoEmpleado")//enlace
	public String nuevoTipoEmpleado(Model model) {
		
		TipoEmpleado tipo=new TipoEmpleado();
		model.addAttribute("tipoempleado", tipo);
		model.addAttribute("title", "Nuevo Tipo Empleado");
				
		return "tipoempleado/formTipoEmpleado";
	}
	@PostMapping(value = "/saveTipoEmpleado")
	public String saveTipoEmpleado(@Valid TipoEmpleado tipoempleado, BindingResult result, Model model, RedirectAttributes flash) {//SessionStatus status) {

		if(result.hasErrors()) {
			model.addAttribute("title", "Guardar TipoEmpleado");
			return "tipoempleado/formTipoEmpleado";
		}
		String mensajeFlash=(tipoempleado.getIdtipoempleado()!=null)?"TipoEmpleado editado exitosamente!": "TipoEmpleado creado exitosamente";
		tipoeservice.save(tipoempleado);
		//status.setComplete();
		flash.addFlashAttribute("success",mensajeFlash );
		return "redirect:/tipoempleados/listTipoEmpleado";//enlace no es nombre de la vista
	}
	@GetMapping(value = "/editTipoEmpleado/{idtipoempleado}")
	public String editTipoEmpleado(@PathVariable(value = "idtipoempleado") Long id, Model model, RedirectAttributes flash) {

		TipoEmpleado tipoempleado = null;

		if (id > 0) {
			tipoempleado = tipoeservice.findById(id);
			if (tipoempleado == null) {
				flash.addFlashAttribute("error", "El Id del TipoCliente no existe en la base de datos!");
				return "redirect:/tipoempleados/listTipoEmpleado";
			}
		} else {
			flash.addFlashAttribute("error", "El Id del TipoCliente no puede ser cero!");
			return "redirect:/tipoempleados/";
		}
		model.addAttribute("tipoempleado", tipoempleado);
		model.addAttribute("title", "Editar tipoempleado");
		return "tipoempleado/formTipoEmpleado";
	}
	@GetMapping(value = "/deleteTipoEmpleado/{idtipoempleado}")
	public String deleteTipoEmpleado(@PathVariable(value = "idtipoempleado") Long id, RedirectAttributes flash) {

		if (id > 0) {

			tipoeservice.delete(id);
			flash.addFlashAttribute("success", "TipoEmpleado removed successfully!");
		}
		return "redirect:/tipoempleados/listTipoEmpleado";
	}
}
