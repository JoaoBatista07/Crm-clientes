package crm_clientes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "empresa")
@Getter
@NoArgsConstructor
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idEmpresa;

    @Setter
    @Column(name = "nome_empresa", nullable = false, length = 150)
    private String nomeEmpresa;

    @Setter
    @Column(name = "cnpj_empresa", nullable = false, length = 18)
    private String cnpjEmpresa;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Setter
    @OneToMany(mappedBy = "empresaId")
    private List<Usuario> listUsuarios;

    @OneToMany(mappedBy = "idEmpresa")
    private List<Produto> produtos;

    @OneToMany(mappedBy = "idEmpresa")
    private List<EmpresaCliente> empresasClientes;

    @OneToMany(mappedBy = "empresa")
    private List<Venda> vendas;

    @PrePersist
    public void prePersist() {
        if (dataCriacao == null) {
            this.dataCriacao = LocalDateTime.now();
        }
    }
}
