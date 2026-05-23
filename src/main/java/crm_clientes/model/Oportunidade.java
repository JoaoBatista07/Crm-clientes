package crm_clientes.model;

import crm_clientes.enums.StatusOportunidade;
import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "oportunidade")
@Getter
@NoArgsConstructor
public class Oportunidade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idOportunidade;

    @Setter
    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @Setter
    @Column(name = "status_oportunidade", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private StatusOportunidade statusOportunidade;

    @Setter
    @Column(name = "valor_estimado", nullable = false, precision = 15, scale = 2)
    private BigDecimal valorEstimado = BigDecimal.ZERO;

    @Setter
    @Column(name = "previsao_faturamento")
    private Date previsaoFaturamento;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    @JoinColumn(name = "id_usuario", nullable = false)
    @ManyToOne
    private Usuario idUsuario;

    @ManyToOne
    @JoinColumn(name = "id_empresa_cliente", nullable = false)
    private EmpresaCliente empresaCliente;

    @OneToMany(mappedBy = "oportunidade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemOportunidade> itensOportunidades;

    @OneToOne(mappedBy = "oportunidade")
    private Venda vendas;

    @PrePersist
    public void prePersist(){
        if (dataCriacao == null){
            this.dataCriacao = LocalDateTime.now();
        }

        if (dataAtualizacao == null){
            this.dataAtualizacao = LocalDateTime.now();
        }
    }
}
