package fiap.com.jarvis.model;

import fiap.com.jarvis.dto.dadosMaritimos.AtualizarDados;
import fiap.com.jarvis.dto.dadosMaritimos.NovosDadosDto;
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
@Table(name = "TB_JV_DADOS_MARITIMO")
public class DadosMaritimo {

    @Id
    @GeneratedValue
    @Column(name = "ID_DADOS_MARITIMOS")
    private Long id;
    @Column(name = "NM_GEOLOCALIZACAO",  length = 200 , nullable = false)
    private String geolocalizacao;
    @Column (name= "NM_TEMPERATURA",  length = 50,  nullable = false)
    private String temperatura;
    @Column(name = "TP_FAUNA",  length = 200 , nullable = false)
    private String fauna;

    @ManyToOne()
    @JoinColumn(name = "ID_MISSAO", nullable = false)
    private Missao missao;

    public DadosMaritimo(NovosDadosDto dto, Missao missao) {
        geolocalizacao = dto.geolocalizacao();
        temperatura = dto.temperatura();
        fauna =  dto.fauna();
        this.missao = missao;
    }

    public void atualizarDados(AtualizarDados dto) {
        if(dto.geolocalizacao() != null)
            geolocalizacao = dto.geolocalizacao();
        if(dto.temperatura() != null)
            temperatura = dto.temperatura();
        if(dto.fauna() != null)
            fauna = dto.fauna();
    }
}
