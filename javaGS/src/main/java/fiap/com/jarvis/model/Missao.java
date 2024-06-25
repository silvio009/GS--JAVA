package fiap.com.jarvis.model;

import fiap.com.jarvis.dto.missao.AtualizacaoMissao;
import fiap.com.jarvis.dto.missao.NovaMissao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "TB_JV_MISSAO")
public class Missao {

    @Id
    @GeneratedValue
    @Column(name = "ID_MISSAO", length = 8)
    private Long id;

    @Column(name = "NM_MISSAO", length = 100)
    private String nome;

    @Column(name = "DS_MISSAO", length = 150)
    private String descricao;

    @Column(name= "DT_INICIO")
    private LocalDate inicio;

    @Column(name= "DT_FIM")
    private LocalDate fim;

    @OneToMany(mappedBy = "missao", cascade = CascadeType.ALL)
    private List<DadosMaritimo>dadosMaritimos;

    @ManyToOne()
    @JoinColumn(name = "ID_DRONE", nullable = false)
    private Drone drone;

    public Missao(NovaMissao dto, Drone drone) {
        nome = dto.nome();
        descricao = dto.descricao();
        inicio = dto.inicio();
        fim = dto.fim();
        this.drone = drone;
    }


    public void atualizarMissao(AtualizacaoMissao dto) {
        if(dto.nome() != null)
            nome = dto.nome();
        if(dto.descricao() != null)
            descricao = dto.descricao();
        if(dto.inicio() != null)
            inicio = dto.inicio();
        if(dto.fim() != null)
            fim = dto.fim();
    }
}
