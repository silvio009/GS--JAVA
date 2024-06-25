package fiap.com.jarvis.controller;


import fiap.com.jarvis.dto.email.DetalhesEmailDto;
import fiap.com.jarvis.dto.email.NovoEmail;
import fiap.com.jarvis.dto.funcionario.AtualizacaoFuncionario;
import fiap.com.jarvis.dto.funcionario.CadastroFuncionario;
import fiap.com.jarvis.dto.funcionario.DetalhesFuncionarioDto;
import fiap.com.jarvis.dto.funcionario.ListagemFuncionario;
import fiap.com.jarvis.model.Email;
import fiap.com.jarvis.model.Funcionario;
import fiap.com.jarvis.repository.DroneRepository;
import fiap.com.jarvis.repository.EmailRepository;
import fiap.com.jarvis.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("funcionario")
public class FuncionarioController {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    private EmailRepository emailRepositoy;

    @Autowired
    private DroneRepository droneRepository;


    @GetMapping
    public ResponseEntity<List<ListagemFuncionario>> get(Pageable pageable){
        var ListagemFuncinario = funcionarioRepository.findAll().stream().map(ListagemFuncionario::new).toList();
        return ok(ListagemFuncinario);
    }

    @GetMapping("{id}")
    public  ResponseEntity<DetalhesFuncionarioDto> get(@PathVariable("id") Long id){
        var funcionarios = funcionarioRepository.getReferenceById(id);
        return ok(new DetalhesFuncionarioDto(funcionarios));
    }

    @GetMapping("por-nome")
    public ResponseEntity<Page<DetalhesFuncionarioDto>> getNome(@RequestParam("nome") String nome, Pageable pageable){
        var page = funcionarioRepository.buscarPorNome(nome, pageable).map(DetalhesFuncionarioDto::new);
        return ResponseEntity.ok(page);
    }


    @GetMapping("por-cargo")
    public ResponseEntity<Page<DetalhesFuncionarioDto>> getCargo(@RequestParam("cargo")String cargo , Pageable pageable){
        var page = funcionarioRepository.buscarPorCargo(cargo, pageable).map(DetalhesFuncionarioDto::new);
        return  ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesFuncionarioDto> create(@RequestBody @Valid CadastroFuncionario dto , UriComponentsBuilder builder){
        var funcionario = new Funcionario(dto);
        funcionarioRepository.save(funcionario);
        var url = builder.path("funcionario/{id}").buildAndExpand(funcionario.getId()).toUri();
        return  ResponseEntity.created(url).body(new DetalhesFuncionarioDto(funcionario));
    }

    // Email
    @PostMapping("{id}/email")
    @Transactional
    public ResponseEntity<DetalhesEmailDto> post (@PathVariable("id") Long id,
                                                        @RequestBody @Valid NovoEmail dto,
                                                        UriComponentsBuilder uriBuilder){
        var funcionario  = funcionarioRepository.getReferenceById(id);
        var email = new Email(dto, funcionario);
        emailRepositoy.save(email);
        var uri = uriBuilder.path("email/{id}").buildAndExpand(email.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesEmailDto(email));
    }


    // TABELA TB_JV_USUARIO_DRONE

    @PutMapping("{idFuncionario}/drone/{idDrone}")
    @Transactional
    public ResponseEntity<DetalhesFuncionarioDto> put(@PathVariable("idFuncionario") Long idFuncionario, @PathVariable("idDrone") Long idDrone){
        var funcionario = funcionarioRepository.getReferenceById(idFuncionario);
        var drone =  droneRepository.getReferenceById(idDrone);
        drone.getFuncionarios().add(funcionario);
        return ResponseEntity.ok().body(new DetalhesFuncionarioDto(funcionario));
    }



    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<ListagemFuncionario> put(@PathVariable("id") Long id,
                                                   @RequestBody AtualizacaoFuncionario dto){
        var funcionario = funcionarioRepository.getReferenceById(id);
        funcionario.atulizarFuncionario(dto);
        return ResponseEntity.ok(new ListagemFuncionario(funcionario));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        funcionarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
