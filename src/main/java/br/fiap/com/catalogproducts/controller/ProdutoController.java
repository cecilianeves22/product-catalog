package br.fiap.com.catalogproducts.controller;

import br.fiap.com.catalogproducts.model.Produto;
import br.fiap.com.catalogproducts.service.ProdutoService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public String listarProdutos(Model model) {
        model.addAttribute("produto", produtoService.findAll());
        return "listaProdutos";
    }

    @GetMapping("/novo")
    public String mostrarCadastroDeProduto(Model model) {
        model.addAttribute("produto", new Produto());
        return "cadastroProdutos";
    }

    @PostMapping
    public String adicionarProduto(@Valid @ModelAttribute Produto produto, BindingResult result) {
        if (result.hasErrors()) {
            return "cadastroProdutos";
        }
        produtoService.save(produto);
        return "redirect:/produto";
    }

    @GetMapping("/editar/{id}")
    public String mostrarCadastroDeEdicao(@PathVariable Long id, Model model) {
        Produto produto = produtoService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
        model.addAttribute("produto", produto);
        return "cadastroProdutos";
    }

    @PostMapping("/editar/{id}")
    public String atualizarProduto(@PathVariable Long id, @ModelAttribute Produto produto) {
        produtoService.save(produto);
        return "redirect:/produto";
    }

    @GetMapping("/deletar/{id}")
    public String deletarProduto(@PathVariable Long id) {
        produtoService.deleteById(id);
        return "redirect:/produto";
    }
}
