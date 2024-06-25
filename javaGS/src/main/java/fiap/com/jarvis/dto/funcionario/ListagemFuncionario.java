package fiap.com.jarvis.dto.funcionario;

import fiap.com.jarvis.model.Funcionario;

public record ListagemFuncionario(Long id, String nome , String cpf , String cargo, String senha) {
    public ListagemFuncionario(Funcionario funcionario){
        this(funcionario.getId(), funcionario.getNome(), funcionario.getCpf(), funcionario.getCargo(), funcionario.getSenha());
    }
}
