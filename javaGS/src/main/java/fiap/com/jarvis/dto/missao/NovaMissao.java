package fiap.com.jarvis.dto.missao;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record NovaMissao(
        @NotBlank(message = "Nome da missão é obrigatori")
        String nome,
        @NotBlank(message = "Descrição da missão é obrigatori")
        String descricao,

        LocalDate inicio,
        LocalDate fim

) {
}
