package fiap.com.jarvis.model;

import fiap.com.jarvis.dto.email.AtualizarEmail;
import fiap.com.jarvis.dto.email.NovoEmail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_JV_EMAIL")
public class Email {
    @Id
    @GeneratedValue
    @Column(name = "ID_EMAIL", length = 8 )
    private Long id;

    @Column(name = "NM_EMAIL", length = 22 ,nullable = false , unique = true)
    private String email;

    @Column(name = "DS_EMAIL", length = 30 ,nullable = false)
    private  String dadosEmail;

    @Column(name = "ST_EMAIL", length = 30 ,nullable = false)
    private  String statusEmail;

    @ManyToOne
    @JoinColumn(name= "ID_FUNCIONARIO", nullable = false)
    private  Funcionario funcionario;

    public Email(NovoEmail dto, Funcionario funcionario) {
        email = dto.email();
        dadosEmail = dto.dadosEmail();
        statusEmail = dto.statusEmail();
        this.funcionario = funcionario;
    }

    public void atualizacaoEmail(AtualizarEmail dto) {
        if(dto.email() != null)
            email = dto.email();
        if(dto.dadosEmail() != null)
            dadosEmail = dto.dadosEmail();
        if (dto.statusEmail() != null)
            statusEmail = dto.statusEmail();
    }

}
