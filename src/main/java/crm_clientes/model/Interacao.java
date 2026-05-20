package crm_clientes.model;

import crm_clientes.enums.ResultadoInteracao;
import crm_clientes.enums.TipoInteracao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.CodePointLength;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "interacao")
@Getter
@NoArgsConstructor
public class Interacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idInteracao;

    @Setter
    @Column(name = "tipo_interacao", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private TipoInteracao tipoInteracao;

    @Setter
    @Column(name = "descricao_interacao")
    private String descricaoInteracao;

    @Setter
    @Column(name = "resultado_interacao", length = 50)
    @Enumerated(EnumType.STRING)
    private ResultadoInteracao resultadoInteracao;

    @Column(name = "data_criacao_interacao", nullable = false)
    private LocalDateTime dataCriacaoInteracao;

    @JoinColumn(name = "usuario", nullable = false)
    @ManyToOne
    private Usuario idUsuario;

    @JoinColumn(name = "empresa_cliente", nullable = false)
    @ManyToOne
    private EmpresaCliente empresaCliente;

    @JoinColumn(name = "contato")
    @ManyToOne
    private Contato contatoInteracao;

    @PrePersist
    public void prePersist(){
        if (dataCriacaoInteracao == null){
            this.dataCriacaoInteracao = LocalDateTime.now();
        }
    }
}
