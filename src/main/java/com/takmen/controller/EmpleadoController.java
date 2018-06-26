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

import com.takmen.models.entity.Empleado;
import com.takmen.service.IEmpleadoService;
import com.takmen.util.PageRender;

@Controller
@SessionAttributes("empleado")
@RequestMapping("/empleado")
public class EmpleadoController {

	@Autowired
	private IEmpleadoService empleadoService;
	
	@GetMapping(value="/listar")
	public String listarEmpleado(@RequestParam(name = "pagina", defaultValue="0") 
	int page, Model model)
	{
		Pageable pageRequest = PageRequest.of(page, 8);		
		Page<Empleado> empleados = empleadoService.findAll(pageRequest);		
		PageRender<Empleado> pageRender = new PageRender<Empleado>("/empleado/listar/", empleados);
		
		model.addAttribute("title", "Lista de empleados");
		model.addAttribute("empleados", empleados);
		model.addAttribute("page", pageRender);
		 
		return "empleado/listar";
	}
	
	@GetMapping(value="/nuevo")
	public String nuevoEmpleado(Model model)
	{
		Empleado empleado = new Empleado();
		model.addAttribute("empleado", empleado);
		model.addAttribute("tipoempleados", empleadoService.tiposempleados());
		model.addAttribute("titulo", "Nuevo Empleado");
		
		return "empleado/formulario";
	}
	
	@PostMapping(value="/guardar")
	public String guardarEmpleado(@Valid Empleado empleado,BindingResult result, Model model, RedirectAttributes flash, SessionStatus status)
	{
		if(result.hasErrors())
		{
			model.addAttribute("titulo", "Guardar Empleado");
			model.addAttribute("tipoempleados", empleadoService.tiposempleados());
			return "empleado/formulario";
		}
		
		String mensajeFlash = (empleado.getIdEmpleado() != null) ? "¡Emplado editado exitosamente!": "¡Empleado creado exitosamente!";
		empleadoService.save(empleado);
		status.setComplete();
		flash.addFlashAttribute("exito", mensajeFlash);
		return "redirect:/empleado/listar";	
		
	}
	
	@GetMapping(value = "/editar/{id}")
	public String editarEmpleado(@PathVariable(value = "id") Long idEmpleado, Model model, RedirectAttributes flash)
	{
		Empleado empleado = null;
		
		if(idEmpleado > 0)
		{
			empleado = empleadoService.findById(idEmpleado);
			if(empleado==null)
			{
				flash.addFlashAttribute("error", "El ID del empleado no existe en la base de datos");
				return "redirect:/empleado/listar";
			}
		}else 
		{
			flash.addFlashAttribute("error", "El ID del empleado no existe en la base de datos");
			return "redirect:/empleado/listar";
		}
		model.addAttribute("empleado", empleado);
		model.addAttribute("tipoempleados", empleadoService.tiposempleados());
		model.addAttribute("titulo", "Editar empleado");
		
	return 	"empleado/formulario";
	
	}
	
	@GetMapping(value = "/eliminar/{id}")
	public String eliminarEmpleado(@PathVariable(value="id") Long idEmpleado, RedirectAttributes flash)
	{
		if(idEmpleado > 0)
		{
			empleadoService.deleteById(idEmpleado);
			flash.addFlashAttribute("exito", "¡Empleado eliminado exitosamente!");
			
		}
		return "redirect:/empleado/listar";
	}
	
	@GetMapping(value = "/detalle/{id}")
	public String detalleEmpleado(@PathVariable(value="id")Long idEmpleado, Model model , RedirectAttributes flash)
	{
		Empleado empleado = empleadoService.findById(idEmpleado);
		if (empleado==null)
		{
			flash.addFlashAttribute("error", "El ID del empleado no existe en la base de datos");
			return "redirect:/empleado/listar";
		}
		model.addAttribute("empleado", empleado);
		model.addAttribute("titulo", "Empleado");
		return "empleado/detalle";
	}
}
