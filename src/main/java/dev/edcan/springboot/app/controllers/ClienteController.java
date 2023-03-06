package dev.edcan.springboot.app.controllers;

import dev.edcan.springboot.app.models.dao.IClienteDao;
import dev.edcan.springboot.app.models.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class ClienteController {

    @Autowired
    private IClienteDao clienteDao;

    @RequestMapping(value="/listar",method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("titulo","Listado de Clientes");
        model.addAttribute("clientes",clienteDao.findAll());
        return "listar";
    }

    @RequestMapping(value="/form") // pasar metodos por map
    public String crear(Map<String, Object> model) {
        Cliente cliente = new Cliente();

        model.put("titulo","Formulario de cliente");
        model.put("cliente",cliente);
        return "form";
    }

    @RequestMapping(value="/form",method = RequestMethod.POST) // pasar metodos por map
    public String guardar(Cliente cliente) {

        clienteDao.save(cliente);

        return "redirect:listar";
    }
}
