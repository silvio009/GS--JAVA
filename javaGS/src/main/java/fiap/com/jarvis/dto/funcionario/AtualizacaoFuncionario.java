package fiap.com.jarvis.dto.funcionario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AtualizacaoFuncionario(
        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 100, message = "o nome deve ter no máximo 100 caracteres")
        String nome ,
        @NotBlank(message = "CPF é obrigatório")
        @Size(max = 11, min = 11, message = "CPF deve ter no máximo 11 números")
        String cpf,

        @NotBlank(message = "cargo é obrigatório")
        String  cargo,
        @NotBlank(message = "Senha é obrigatório")
        String senha

) {


}
