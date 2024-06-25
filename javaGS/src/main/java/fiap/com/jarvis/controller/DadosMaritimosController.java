package fiap.com.jarvis.controller;

import fiap.com.jarvis.dto.dadosMaritimos.AtualizarDados;
import fiap.com.jarvis.dto.dadosMaritimos.DetalhesDadosDto;
import fiap.com.jarvis.dto.dadosMaritimos.ListagemDadosDto;
import fiap.com.jarvis.repository.DadosMaritimoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("Dados-Maritimos")
public class DadosMaritimosController {

    @Autowired
    DadosMaritimoRepository dadosMaritimoRepository;

    @GetMapping("{id}")
    public ResponseEntity<DetalhesDadosDto> get(@PathVariable("id") Long id){
        var dados = dadosMaritimoRepository.getReferenceById(id);
        return ok(new DetalhesDadosDto(dados));
    }

    @GetMapping
    public ResponseEntity<List<ListagemDadosDto>> get(Pageable pageable){
        var dados = dadosMaritimoRepository.findAll().stream().map(ListagemDadosDto::new).toList();
        return  ok(dados);
    }




    @PutMapping("{id}")
    public ResponseEntity<ListagemDadosDto> put(@PathVariable("id") Long id,
                                            @RequestBody @Valid AtualizarDados dto){
        var dados = dadosMaritimoRepository.getReferenceById(id);
        dados.atualizarDados(dto);
        return ResponseEntity.ok(new ListagemDadosDto(dados));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        dadosMaritimoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
