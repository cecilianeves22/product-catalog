package br.fiap.com.catalogproducts;

import br.fiap.com.catalogproducts.model.Produto;
import br.fiap.com.catalogproducts.repository.ProdutoRepository;
import br.fiap.com.catalogproducts.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ProductsApplicationTestesException {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        produtoService = new ProdutoService(produtoRepository);
    }

    @Test
    public void testSaveWithInvalidData() {

        Produto produto = new Produto();
        produto.setPreco(-10.0);
        when(produtoRepository.save(produto)).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> {
            produtoService.save(produto);
        });

        verify(produtoRepository, times(1)).save(produto);
    }

}