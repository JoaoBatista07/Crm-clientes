package crm_clientes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "produto")
@Getter
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idProduto;

    @Setter
    @Column(name = "nome_produto", nullable = false, length = 150)
    private String nomeProduto;

    @Setter
    @Column(name = "descricao_produto")
    private String descricaoProduto;

    @Column(name = "preco_base_produto", nullable = false, precision = 15, scale = 2)
    private BigDecimal precoBaseProduto = BigDecimal.ZERO;

    @Setter
    @Column(name = "ativo_produto", nullable = false)
    private boolean ativoProduto;

    @Column(name = "data_criacao_produto", nullable = false)
    private LocalDateTime dataCriacaoProduto;

    @ManyToOne
    @JoinColumn(name = "empresa")
    private Empresa idEmpresa;

    @PrePersist
    public void prePersist(){
        if ((!this.ativoProduto)){
            this.ativoProduto = true;
        }

        if (dataCriacaoProduto == null){
            this.dataCriacaoProduto = LocalDateTime.now();
        }
    }
}
