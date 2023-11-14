package br.fiap.com.catalogproducts;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.fiap.com.catalogproducts.model.Produto;
import br.fiap.com.catalogproducts.repository.ProdutoRepository;
import br.fiap.com.catalogproducts.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProductsApplicationTestsSuccess {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    public void ProdutoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {

        Produto produto1 = new Produto();
        Produto produto2 = new Produto();
        List<Produto> produtos = Arrays.asList(produto1, produto2);
        when(produtoRepository.findAll()).thenReturn(produtos);

        List<Produto> result = produtoService.findAll();
        assertEquals(2, result.size());
        verify(produtoRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {

        Long productId = 1L;
        Produto produto = new Produto();
        produto.setId(productId);
        when(produtoRepository.findById(productId)).thenReturn(Optional.of(produto));

        Optional<Produto> result = produtoService.findById(productId);
        assertTrue(result.isPresent());
        assertEquals(productId, result.get().getId());
        verify(produtoRepository, times(1)).findById(productId);
    }

}