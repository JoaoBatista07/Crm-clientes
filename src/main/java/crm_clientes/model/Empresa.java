package crm_clientes.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "empresa")
public class Empresa {

    public Empresa(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idEmpresa;

    @Column(name = "nome_empresa", nullable = false, length = 150)
    private String nomeEmpresa;

    @Column(name = "cnpj_empresa", nullable = false, length = 18)
    private String cnpjEmpresa;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @OneToMany(mappedBy = "empresa")
    private List<Usuario> listUsuarios;

    @PrePersist
    public void prePersist() {
        if (dataCriacao == null) {
            this.dataCriacao = LocalDateTime.now();
        }
    }

    public UUID getIdEmpresa() {
        return idEmpresa;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setIdEmpresa(UUID idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
