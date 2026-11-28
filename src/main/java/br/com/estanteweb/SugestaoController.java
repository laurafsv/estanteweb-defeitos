package br.com.estanteweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SugestaoController {

    @Autowired
    private SugestaoRepository sugestaoRepository;

    @GetMapping("/sugestoes")
    public String listar(@RequestParam(required = false) String nomeCliente,
            @RequestParam(required = false) String livroSugerido, Model model) {
        model.addAttribute("sugestoes", sugestaoRepository.buscar(nomeCliente, livroSugerido));
        model.addAttribute("nomeCliente", nomeCliente);
        model.addAttribute("livroSugerido", livroSugerido);
        return "sugestoes";
    }

    @GetMapping("/sugestoes/nova")
    public String formulario() {
        return "sugestao-nova";
    }

    @PostMapping("/sugestoes")
    public String criar(@RequestParam String nomeCliente,
            @RequestParam String livroSugerido, Model model) {

        if (nomeCliente.isBlank() || livroSugerido.isBlank()) {
            model.addAttribute("erro", "Preencha os dois campos.");
            return "sugestao-nova";
        }

        Sugestao sugestao = new Sugestao();
        sugestao.setNomeCliente(nomeCliente);
        sugestao.setLivroSugerido(livroSugerido);
        sugestaoRepository.salvar(sugestao);

        return "redirect:/sugestoes";
    }
}
