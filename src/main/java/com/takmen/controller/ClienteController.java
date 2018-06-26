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

import com.takmen.models.entity.Cliente;
import com.takmen.service.IClienteService;
import com.takmen.util.PageRender;

@Controller
@SessionAttributes("cliente")
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	
	@GetMapping(value="/listar")
	public String listarCliente(@RequestParam(name = "pagina", defaultValue="0") int page, Model model)
	{
		Pageable pageRequest = PageRequest.of(page, 8);
		
		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		
		PageRender<Cliente> pageRender = new PageRender<Cliente>("/cliente/listar/", clientes);
		
		model.addAttribute("title", "Lista de clientes");
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		 
		return "cliente/listar";
	}
	
	@GetMapping(value="/nuevo")
	public String nuevoCliente(Model model)
	{
		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo", "Nuevo Cliente");
		
		return "cliente/formulario";
	}
	
	@PostMapping(value="/guardar")
	public String guardarCliente(@Valid Cliente cliente,BindingResult result, Model model, RedirectAttributes flash, SessionStatus status)
	{
		if(result.hasErrors())
		{
			model.addAttribute("titulo", "Guardar Cliente");
			
			return "cliente/formulario";
		}
		
		String mensajeFlash = (cliente.getIdCliente() != null)? "¡Cliente editado exitosamente!": "¡Cliente creado exitosamente!";
		
		clienteService.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("exito", mensajeFlash);
		return "redirect:/cliente/listar";	
		
	}
	
	@GetMapping(value = "/editar/{id}")
	public String editarCliente(@PathVariable(value = "id") Long idCliente, Model model, RedirectAttributes flash)
	{
		Cliente cliente = null;
		
		if(idCliente > 0)
		{
			cliente = clienteService.findById(idCliente);
			if(cliente==null)
			{
				flash.addFlashAttribute("error", "El ID del cliente no existe en la base de datos");
				return "redirect:/cliente/listar";
			}
		}else 
		{
			flash.addFlashAttribute("error", "El ID del cliente no existe en la base de datos");
			return "redirect:/cliente/listar";
		}
		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo", "Editar Cliente");
		
	return 	"cliente/formulario";
	
	}
	
	@GetMapping(value = "/eliminar/{id}")
	public String eliminarCliente(@PathVariable(value="id") Long idCliente, RedirectAttributes flash)
	{
		if(idCliente > 0)
		{
			clienteService.deleteById(idCliente);
			flash.addFlashAttribute("exito", "¡Cliente eliminado exitosamente!");
			
		}
		return "redirect:/cliente/listar";
	}
	
	@GetMapping(value = "/detalle/{id}")
	public String detalleCliente(@PathVariable(value="id")Long idCliente, Model model , RedirectAttributes flash)
	{
		Cliente cliente = clienteService.findById(idCliente);
		if (cliente==null)
		{
			flash.addFlashAttribute("error", "El ID del cliente no existe en la base de datos");
			return "redirect:/cliente/listar";
		}
		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo", "Cliente");
		return "cliente/detalle";
	}
}
