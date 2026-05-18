package crm_clientes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "contato")
@Getter
@NoArgsConstructor
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idContato;

    @Setter
    @Column(name = "nome_contato", nullable = false, length = 150)
    private String nomeContato;

    @Setter
    @Column(name = "email_contato", nullable = false, length = 150)
    private String emailContato;

    @Setter
    @Column(name = "telefone_contato", length = 20)
    private String telefoneContato;

    @Setter
    @Column(name = "cargo_contato", nullable = false, length = 100)
    private String cargoContato;

    @Column(name = "data_criacao_contato", nullable = false)
    private LocalDateTime dataCriacaoContato;

    @JoinColumn(name = "empresa_cliente", nullable = false)
    @ManyToOne
    private EmpresaCliente empresaCliente;

    @OneToMany(mappedBy = "contatoInteracao")
    private List<Interacao> interacoes;

    @PrePersist
    public void prePersist(){
        if (dataCriacaoContato == null){
            this.dataCriacaoContato = LocalDateTime.now();
        }
    }
}
