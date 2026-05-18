package crm_clientes.model;

import crm_clientes.enums.SegmentoEmpresa;
import crm_clientes.enums.StatusEmpresa;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "empresa_cliente")
@Getter
@NoArgsConstructor
public class EmpresaCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idEmpresaCliente;

    @Setter
    @Column(name = "nome_empresa", nullable = false, length = 150)
    private String nomeEmpresa;

    @Setter
    @Column(name = "cnpj_empresa_cliente", nullable = false, length = 18)
    private String cnpjEmpresaCliente;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "segmento_empresa", nullable = false, length = 50)
    private SegmentoEmpresa segmentoEmpresa;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "status_empresa", length = 50)
    private StatusEmpresa statusEmpresa;

    @Setter
    @Column(name = "telefone_empresa", length = 20)
    private String telefone;

    @Column(name = "data_criacao_empresa", nullable = false)
    private LocalDateTime dataCriacaoEmpresa;

    @JoinColumn(name = "empresa")
    @ManyToOne
    private Empresa idEmpresa;

    @OneToMany(mappedBy = "empresaCliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contato> contatos;

    @OneToMany(mappedBy = "empresaCliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Interacao> interacoes;

    @PrePersist
    public void prePersist(){
        if (dataCriacaoEmpresa == null){
            this.dataCriacaoEmpresa = LocalDateTime.now();
        }
    }
}
