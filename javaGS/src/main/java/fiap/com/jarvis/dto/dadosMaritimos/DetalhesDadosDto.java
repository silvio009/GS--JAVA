package fiap.com.jarvis.dto.dadosMaritimos;

import fiap.com.jarvis.dto.missao.DetalhesMissaoDto;
import fiap.com.jarvis.model.DadosMaritimo;

public record DetalhesDadosDto(Long id, String geolocalizacao, String temperatura, String fauna) {

    public DetalhesDadosDto(DadosMaritimo dadosMaritimo){
        this(dadosMaritimo.getId(), dadosMaritimo.getGeolocalizacao(), dadosMaritimo.getTemperatura(),dadosMaritimo.getFauna());
    }
}
