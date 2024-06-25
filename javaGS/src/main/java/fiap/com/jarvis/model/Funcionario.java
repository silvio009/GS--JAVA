package fiap.com.jarvis.model;

import fiap.com.jarvis.dto.funcionario.AtualizacaoFuncionario;
import fiap.com.jarvis.dto.funcionario.CadastroFuncionario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "TB_JV_FUNCIONARIO")
@SequenceGenerator(name = "seq_funcionario", sequenceName = "SEQ_TB_JV_FUNCIONARIO", allocationSize = 1)
public class Funcionario {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_funcionario")
    @Column(name = "ID_FUNCIONARIO")
    private Long id;

    @Column(name = "NM_FUNCIONARIO", length = 50)
    private String nome;

    @Column(name = "NR_CPF", length = 11)
    private  String cpf;

    @Column(name= "NM_CARGO", length = 100)
    private String cargo;

    @Column(name= "CD_SENHA", length = 150)
    private String senha;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private List<Email> emails;

    @ManyToMany(mappedBy = "funcionarios")
    private List<Drone>drones;


    public Funcionario(CadastroFuncionario dto) {
        nome = dto.nome();
        cpf = dto.cpf();
        cargo = dto.cargo();
        senha = dto.senha();
    }

    public void atulizarFuncionario(AtualizacaoFuncionario dto) {
        if(dto.nome() != null)
            nome = dto.nome();
        if(dto.cpf() != null)
            cpf = dto.cpf();
        if(dto.cargo() != null)
            cargo = dto.cargo();
        if(dto.senha() != null)
            senha = dto.senha();
    }
}
