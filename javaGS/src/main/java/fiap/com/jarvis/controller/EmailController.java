package fiap.com.jarvis.controller;


import fiap.com.jarvis.dto.email.AtualizarEmail;
import fiap.com.jarvis.dto.email.ListagemEmailDto;
import fiap.com.jarvis.repository.EmailRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("email")
public class EmailController {

    @Autowired
    private EmailRepository emailRepository;

    @GetMapping
    public ResponseEntity<List<ListagemEmailDto>> get(Pageable pageable){
        var ListagemEmail = emailRepository.findAll(pageable).stream().map(ListagemEmailDto::new).toList();
        return ok(ListagemEmail);

    }

    @GetMapping("{id}")
    public  ResponseEntity<ListagemEmailDto> get(@PathVariable("id") Long id){
        var email = emailRepository.getReferenceById(id);
        return ok(new ListagemEmailDto(email));
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<ListagemEmailDto> put(@PathVariable("id") Long id,
                                                @RequestBody @Valid AtualizarEmail dto){
        var email = emailRepository.getReferenceById(id);
        email.atualizacaoEmail(dto);
        return ResponseEntity.ok(new ListagemEmailDto(email));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        emailRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
