package com.takmen.controller;

import java.util.List;

import javax.validation.Valid;

import org.assertj.core.api.DoublePredicateAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.takmen.models.entity.Cliente;
import com.takmen.models.entity.DetalleOP;
import com.takmen.models.entity.Empleado;
import com.takmen.models.entity.Orden;
import com.takmen.models.entity.Prenda;
import com.takmen.service.IClienteService;
import com.takmen.service.IEmpleadoService;
import com.takmen.service.IOrdenService;
import com.takmen.service.IPrendaService;
import com.takmen.service.IServicioService;
import com.takmen.service.ITipoPrendaService;

@Controller
@RequestMapping("/orden")
@SessionAttributes("orden")
public class OrdenController {

	@Autowired
	private IOrdenService ordenService;

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IEmpleadoService empleadoService;

	@Autowired
	private IServicioService servicioService;

	@Autowired
	private ITipoPrendaService tipoPrendaService;

	@Autowired
	private IPrendaService prendaService;

	private final Logger log = LoggerFactory.getLogger(getClass());

	@GetMapping("/nuevaOrden/{idCliente}")
	public String nuevaOrden(@PathVariable(value = "idCliente") Long idCliente, Model model, RedirectAttributes flash) {
		Cliente cliente = clienteService.findById(idCliente);

		if (cliente == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/listar";
		}

		Orden orden = new Orden();
		orden.setCliente(cliente);
		orden.setEstadoOrden(0);

		if (orden.getEmpleado() == null) {
			Long i = new Long(4);

			Empleado empleado = empleadoService.findById(i);
			orden.setEmpleado(empleado);
		}

		model.addAttribute("orden", orden);
		model.addAttribute("titulo", "Crear Orden");

		return "orden/formulario";
	}

	@GetMapping("/detalle/{id}")
	public String detalleOrden(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {

		Orden orden = ordenService.detalleFactura(id);

		if (orden == null) {
			flash.addFlashAttribute("error", "La orden no existe en la base de datos!");
			return "redirect:/cliente/listar";
		}

		model.addAttribute("orden", orden);
		model.addAttribute("titulo", "Detalle orden");
		return "orden/detalle";
	}

	@GetMapping(value = "/cargarPrenda/{term}", produces = { "application/json" })
	public @ResponseBody List<Prenda> cargarPrenda(@PathVariable String term) {
		return prendaService.findBynombrePrendaLikeIgnoreCase(term);
	}

	@PostMapping("/guardar")
	public String saveInvoice(@Valid Orden orden, BindingResult result, Model model,
			@RequestParam(name = "item_id[]", required = false) Long[] itemId,
			@RequestParam(name = "cantidad[]", required = false) Integer[] cantidad, RedirectAttributes flash,
			SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Nueva Orden");
			return "orden/formulario";
		}

		if (itemId == null || itemId.length == 0) {
			model.addAttribute("titulo", "Nueva Orden");
			model.addAttribute("error", "Error: La orden no tiene prendas asignadas!");
			return "orden/formulario";
		}

		for (int i = 0; i < itemId.length; i++) {
			Prenda prenda = prendaService.findById(itemId[i]);

			DetalleOP detalle = new DetalleOP();
			detalle.setCantidad(cantidad[i]);
			detalle.setPrenda(prenda);
			orden.addDetalleOP(detalle);

			log.info("ID: " + itemId[i].toString() + ", Cantidad: " + cantidad[i].toString());
		}

		ordenService.save(orden);
		status.setComplete();

		flash.addFlashAttribute("exito", "Orden creada con exito!");

		return "redirect:/cliente/detalle/" + orden.getCliente().getIdCliente();
	}

}
