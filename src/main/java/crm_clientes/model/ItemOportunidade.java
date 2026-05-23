package crm_clientes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "item_oportunidade")
@Getter
@NoArgsConstructor
public class ItemOportunidade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idItemOportunidade;

    @Setter
    @Min(1)
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Setter
    @Column(name = "valor_negociado", nullable = false, precision = 15, scale = 2)
    private BigDecimal valorNegociado = BigDecimal.ZERO;

    @Setter
    @Column(name = "desconto", nullable = false, precision = 5, scale = 2)
    @DecimalMin("0.00")
    @DecimalMax("100.00")
    private BigDecimal desconto = BigDecimal.ZERO;

    @Column(name = "subtotal", insertable = false, updatable = false, precision = 15, scale = 2)
    private BigDecimal subtotal;

    @ManyToOne
    @JoinColumn(name = "id_oportunidade", nullable = false)
    private Oportunidade oportunidade;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto idProduto;

}
