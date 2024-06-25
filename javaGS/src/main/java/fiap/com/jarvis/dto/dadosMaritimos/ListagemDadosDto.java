package fiap.com.jarvis.dto.dadosMaritimos;

import fiap.com.jarvis.dto.missao.ListagemMissaoDto;
import fiap.com.jarvis.model.DadosMaritimo;

public record ListagemDadosDto(Long id, String geolocalizacao, String temperatura, String fauna) {
    public ListagemDadosDto(DadosMaritimo dadosMaritimo){
        this(dadosMaritimo.getId(), dadosMaritimo.getGeolocalizacao(), dadosMaritimo.getTemperatura(),dadosMaritimo.getFauna());
    }
}
