package crm_clientes.model;

import crm_clientes.enums.FormaPagamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.el.lang.LambdaExpressionNestedState;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "venda")
@NoArgsConstructor
@Getter
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idVenda;

    @Column(name = "valor_final", nullable = false, scale = 15, precision = 2)
    private BigDecimal valorFinal = BigDecimal.ZERO;

    @Column(name = "data_fechamento", nullable = false)
    private LocalDate dataFechamento;

    @Setter
    @Column(name = "forma_pagamento", length = 50)
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @OneToOne
    @JoinColumn(name = "id_oportunidade", nullable = false, unique = true)
    private Oportunidade oportunidade;

    @ManyToOne
    @JoinColumn(name = "id_empresa", nullable = false)
    private Empresa empresa;

    @PrePersist
    public void prePersist(){
        if (dataFechamento == null){
            this.dataFechamento = LocalDate.now();
        }

        if (dataCriacao == null){
            this.dataCriacao = LocalDateTime.now();
        }
    }

}
