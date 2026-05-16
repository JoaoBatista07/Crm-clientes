package crm_clientes.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idUsuario;

    @Column(name = "nome_usuario", nullable = false, length = 150)
    private String nomeUsuario;

    @Column(name = "email_usuario", nullable = false, length = 150, unique = true)
    private String emailUsuario;

    @Column(name = "senha_hash", nullable = false, length = 255)
    private String senhaHash;

    @Column(name = "cargo_usuario", nullable = false)
    private String cargoUsuario;

    @Column(name = "ativo_usuario", nullable = false )
    private boolean ativoUsuario;

    @PrePersist
    public void prePersist(){
        if ((this.ativoUsuario == false)){
            this.ativoUsuario = true;
        }
    }

    @ManyToOne
    @JoinColumn(name = "empresa")
    private Empresa empresaId;

}
