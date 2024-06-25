package fiap.com.jarvis.dto.funcionario;

import fiap.com.jarvis.model.Funcionario;

public record DetalhesFuncionarioDto(Long id , String nome ,String cpf, String cargo, String senha) {
    public DetalhesFuncionarioDto(Funcionario funcionario){
        this(funcionario.getId(), funcionario.getNome(), funcionario.getCpf(), funcionario.getCargo(), funcionario.getSenha());
    }
}
