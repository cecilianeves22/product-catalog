package br.fiap.com.catalogproducts;

import br.fiap.com.catalogproducts.model.Produto;
import br.fiap.com.catalogproducts.repository.ProdutoRepository;
import br.fiap.com.catalogproducts.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class CatalogProductsApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    void testFindAll() {
        when(produtoRepository.findAll()).thenReturn(Arrays.asList(new Produto()));

        List<Produto> result = produtoService.findAll();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(produtoRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(new Produto()));
        Optional<Produto> result = produtoService.findById(1L);
        assertNotNull(result);
        verify(produtoRepository, times(1)).findById(1L);
    }

    @Test
    void testListarProdutos() throws Exception {
        // Mockando o comportamento do serviço
        when(produtoService.findAll()).thenReturn(Arrays.asList(new Produto()));

        // Executando a requisição
        mockMvc.perform(get("/produto"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("produto"))
                .andExpect(view().name("listaProdutos"));
    }

    @Test
    void testMostrarCadastroDeProduto() throws Exception {
        // Executando a requisição
        mockMvc.perform(get("/produto/novo"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("produto"))
                .andExpect(view().name("cadastroProdutos"));
    }
}
