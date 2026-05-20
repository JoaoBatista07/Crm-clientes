package crm_clientes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuario")
@Getter
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idUsuario;

    @Setter
    @Column(name = "nome_usuario", nullable = false, length = 150)
    private String nomeUsuario;

    @Setter
    @Column(name = "email_usuario", nullable = false, length = 150, unique = true)
    private String emailUsuario;

    @Column(name = "senha_hash", nullable = false, length = 255)
    private String senhaHash;

    @Setter
    @Column(name = "cargo_usuario", nullable = false)
    private String cargoUsuario;

    @Setter
    @Column(name = "ativo_usuario", nullable = false )
    private boolean ativoUsuario;

    @Setter
    @ManyToOne
    @JoinColumn(name = "empresa")
    private Empresa empresaId;


    @OneToMany(mappedBy = "idUsuario")
    private List<Interacao> interacoes;

    @OneToMany(mappedBy = "idUsuario")
    private List<Oportunidade> oportunidades;

    @PrePersist
    public void prePersist(){
        if ((!this.ativoUsuario)){
            this.ativoUsuario = true;
        }
    }
}
