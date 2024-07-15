package br.com.fiap.autenticador.v1.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AutenticadorController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Bem-vindo à página inicial!");
        return "home";
    }

    @GetMapping("/secured")
    public String secured(Model model) {
        model.addAttribute("message", "Bem-vindo à página segura!");
        return "secured";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}