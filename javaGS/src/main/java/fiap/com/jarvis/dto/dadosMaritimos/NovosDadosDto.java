package fiap.com.jarvis.dto.dadosMaritimos;

import jakarta.validation.constraints.NotBlank;

public record NovosDadosDto(
        @NotBlank(message = "Geolocalização não pode estar vazio")
        String geolocalizacao,

        @NotBlank(message = "Temperatura não pode estar vazio")
        String temperatura,

        @NotBlank(message = "Fauna não pode estar vazio")
        String fauna
) {
}
