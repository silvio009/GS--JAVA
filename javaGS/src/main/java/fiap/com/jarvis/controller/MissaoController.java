package fiap.com.jarvis.controller;



import fiap.com.jarvis.dto.dadosMaritimos.DetalhesDadosDto;
import fiap.com.jarvis.dto.dadosMaritimos.NovosDadosDto;
import fiap.com.jarvis.dto.missao.AtualizacaoMissao;
import fiap.com.jarvis.dto.missao.DetalhesMissaoDto;
import fiap.com.jarvis.dto.missao.ListagemMissaoDto;
import fiap.com.jarvis.model.DadosMaritimo;
import fiap.com.jarvis.repository.DadosMaritimoRepository;
import fiap.com.jarvis.repository.MissaoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("missao")
public class MissaoController {

    @Autowired
    MissaoRepository missaoRepository;

    @Autowired
    DadosMaritimoRepository dadosMaritimoRepository;


    @GetMapping("{id}")
    public ResponseEntity<DetalhesMissaoDto> get(@PathVariable("id") Long id) {
        var missao = missaoRepository.getReferenceById(id);
        return ok(new DetalhesMissaoDto (missao));
    }

    @GetMapping
    public ResponseEntity<List<ListagemMissaoDto>> get(Pageable pageable){
        var ListagemMissao = missaoRepository.findAll().stream().map(ListagemMissaoDto::new).toList();
        return ok(ListagemMissao);
    }


    // DADOS
    @PostMapping("{id}/dados")
    @Transactional
    public ResponseEntity<DetalhesDadosDto> post(@PathVariable("id")Long id,
                                                 @RequestBody @Valid NovosDadosDto dto,
                                                 UriComponentsBuilder uribuilder){
        var missao = missaoRepository.getReferenceById(id);
        var dados = new DadosMaritimo(dto, missao);
        dadosMaritimoRepository.save(dados);
        var url = uribuilder.path("dados/{id}").buildAndExpand(dados.getId()).toUri();
        return  ResponseEntity.created(url).body(new DetalhesDadosDto(dados));
    }




    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<ListagemMissaoDto> put(@PathVariable("id") Long id,
                                                 @RequestBody @Valid AtualizacaoMissao dto){
        var missao = missaoRepository.getReferenceById(id);
        missao.atualizarMissao(dto);
        return ResponseEntity.ok(new ListagemMissaoDto(missao));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        missaoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
