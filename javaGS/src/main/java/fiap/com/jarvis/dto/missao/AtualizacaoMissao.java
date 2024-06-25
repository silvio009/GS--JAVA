package fiap.com.jarvis.dto.missao;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record AtualizacaoMissao(

        @NotBlank(message = "Nome da missão é obrigatorio")
        String nome,
        @NotBlank(message = "Descrição da missão é obrigatorio")
        String descricao,
        LocalDate inicio,
        LocalDate fim
) {
}
