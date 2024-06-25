package fiap.com.jarvis.model;

import fiap.com.jarvis.dto.drone.AtualizacaoDrone;
import fiap.com.jarvis.dto.drone.CadastroDroneDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_JV_DRONE")
public class Drone {

    @Id
    @GeneratedValue
    @Column(name = "ID_DRONE", length = 8)
    private Long id;

    @Column(name = "NM_MODELO", length = 150)
    private String modelo;

    @Column(name = "NM_FUNCAO", length = 150)
    private String funcao;

    @Column(name = "NM_FABRICANTE", length = 50)
    private String fabricante;

    @Column(name = "NM_DISTANCIA", length = 100)
    private String  distancia;

    @Column(name = "TP_BATERIA", length = 50)
    private String bateria;

    @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL)
    private List<Missao> missoes;


    @ManyToMany
    @JoinTable(name = "TB_JV_USUARIO_DRONE",
               joinColumns = @JoinColumn(name = "ID_DRONE"),
                inverseJoinColumns = @JoinColumn(name = "ID_FUNCIONARIO"))
    private List<Funcionario> funcionarios;


    public Drone(CadastroDroneDto dto) {
       modelo = dto.modelo();
       funcao = dto.funcao();
       fabricante = dto.fabricante();
       distancia = dto.distancia();
       bateria = dto.bateria();
    }

    public void atualizarDrone(AtualizacaoDrone dto) {
        if(dto.modelo() != null)
            modelo = dto.modelo();
        if(dto.funcao() != null)
            funcao = dto.funcao();
        if(dto.fabricante() != null)
            fabricante = dto.fabricante();
        if(dto.distancia() != null)
            distancia = dto.distancia();
        if(dto.bateria() != null)
            bateria = dto.bateria();
    }
}
