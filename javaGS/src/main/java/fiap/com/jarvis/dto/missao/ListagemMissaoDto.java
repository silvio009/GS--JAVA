package fiap.com.jarvis.dto.missao;

import fiap.com.jarvis.dto.drone.DetalhesDroneDto;
import fiap.com.jarvis.model.Missao;

import java.time.LocalDate;

public record ListagemMissaoDto(Long id , String nome, String descricao, LocalDate inicio, LocalDate fim,  DetalhesDroneDto detalhesDroneDto) {
    public  ListagemMissaoDto(Missao missao){
        this(missao.getId(), missao.getNome(), missao.getDescricao(),missao.getInicio(),missao.getFim(), new DetalhesDroneDto(missao.getDrone()));
    }
}
