package fiap.com.jarvis.dto.drone;

import jakarta.validation.constraints.NotBlank;

public record AtualizacaoDrone(

        @NotBlank(message = "Modelo do drone é necessario")
        String modelo,
        @NotBlank(message = "a função do drone é necessario")
        String funcao,
        @NotBlank(message = "Nome da fabricante  do drone é necessario")
        String fabricante,
        @NotBlank(message = "A distancia que drone percorre  é necessario")
        String distancia,
        @NotBlank(message = "A quantidade de bateria do drone é necessario")
        String bateria
) {
}
