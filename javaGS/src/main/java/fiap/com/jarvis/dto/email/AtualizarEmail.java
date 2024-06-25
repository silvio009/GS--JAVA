package fiap.com.jarvis.dto.email;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AtualizarEmail(
        @NotBlank(message = "Email é obrigatório")
        @Size(max = 150, message = "o nome deve ter no máximo 100 caracteres")
        String email ,

        @NotBlank(message = "Email é obrigatório")
        @Size(max = 30, message = "o dados do email deve ter no máximo 30 caracteres")
        String dadosEmail,

        @NotBlank(message = "Status é obrigatório")
        @Size(max = 20, message = "o status do email deve ter no máximo 20 caracteres")
        String statusEmail
) {
}
