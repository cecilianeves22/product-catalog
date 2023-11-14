package br.fiap.com.catalogproducts.repository;

import br.fiap.com.catalogproducts.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
