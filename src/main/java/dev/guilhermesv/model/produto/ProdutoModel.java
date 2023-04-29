package dev.guilhermesv.model.produto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import dev.guilhermesv.model.DefaultEntity;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class ProdutoModel extends DefaultEntity {
 
    private String descricao;

   // private String characters;

    private int estoque;

    private Double preco;

    @Enumerated(EnumType.STRING)
    private ProdutoStatus status;
}
