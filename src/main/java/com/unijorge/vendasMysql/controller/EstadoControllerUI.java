package com.unijorge.vendasMysql.controller;

import com.unijorge.vendasMysql.model.Estado;
import com.unijorge.vendasMysql.repository.EstadoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/estados/ui")
public class EstadoControllerUI {
    private final EstadoRepository estadoRepository;

    public EstadoControllerUI(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @GetMapping("/listar")
    public String home(Model model){
        List<Estado> estados = estadoRepository.findAll();
        model.addAttribute("estados", estados);
        return "index";
    }

    @GetMapping("/criar")
    public String mostrarFormularioAdicionar(Model model) {
        model.addAttribute("estados", new Estado());
        return "cadastra";
    }

    @PostMapping("/salvar")
    public String salvarEstado(@ModelAttribute("estados") Estado estado, BindingResult result, Model model) {
            Estado estadoSalvo = estadoRepository.save(estado);

            if (estadoSalvo == null) {
                model.addAttribute("mensagem", "Ocorreu um erro!");
                return "cadastra";
            }

            model.addAttribute("mensagem", "Estado cadastrado com sucesso!");
            return "redirect:/estados/ui/listar";


    }

}
