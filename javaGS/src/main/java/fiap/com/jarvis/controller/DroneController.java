package fiap.com.jarvis.controller;

import fiap.com.jarvis.dto.drone.AtualizacaoDrone;
import fiap.com.jarvis.dto.drone.CadastroDroneDto;
import fiap.com.jarvis.dto.drone.DetalhesDroneDto;
import fiap.com.jarvis.dto.drone.ListagemDronesDto;
import fiap.com.jarvis.dto.missao.DetalhesMissaoDto;
import fiap.com.jarvis.dto.missao.NovaMissao;
import fiap.com.jarvis.model.Drone;
import fiap.com.jarvis.model.Missao;
import fiap.com.jarvis.repository.DroneRepository;
import fiap.com.jarvis.repository.MissaoRepository;
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
@RequestMapping("drone")
public class DroneController {
    @Autowired
    DroneRepository droneRepository;

    @Autowired
    MissaoRepository missaoRepository;

    @GetMapping("{id}")
    public ResponseEntity<DetalhesDroneDto> get(@PathVariable("id") Long id) {
        var drones = droneRepository.getReferenceById(id);
        return ok(new DetalhesDroneDto(drones));
    }

    @GetMapping
    public ResponseEntity<List<ListagemDronesDto>> get(Pageable pageable) {
        var ListagemDrone = droneRepository.findAll().stream().map(ListagemDronesDto::new).toList();
        return ok(ListagemDrone);
    }

    @GetMapping("por-fabricante")
    public ResponseEntity<Page<DetalhesDroneDto>> getFabricante(@RequestParam("fabricante") String fabricante, Pageable pageable){
        var page = droneRepository.buscarPorFabricante(fabricante, pageable).map(DetalhesDroneDto::new);
        return  ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesDroneDto> create(@RequestBody @Valid CadastroDroneDto dto, UriComponentsBuilder builder){
        var drone = new Drone(dto);
        droneRepository.save(drone);
        var url = builder.path("cliente/{id}").buildAndExpand(drone.getId()).toUri();
        return ResponseEntity.created(url).body(new DetalhesDroneDto(drone));
    }


    // Missao

    @PostMapping("{id}/missao")
    @Transactional
    public ResponseEntity<DetalhesMissaoDto> post(@PathVariable("id") Long id,
                                                  @RequestBody @Valid NovaMissao dto,
                                                  UriComponentsBuilder uriBuilder){
        var drone = droneRepository.getReferenceById(id);
        var missao = new Missao(dto, drone);
        missaoRepository.save(missao);
        var uri = uriBuilder.path("missao/{id}").buildAndExpand(missao.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesMissaoDto(missao));
    }



    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<ListagemDronesDto> put(@PathVariable("id") Long id,
                                                  @RequestBody @Valid AtualizacaoDrone dto){
        var drone = droneRepository.getReferenceById(id);
        drone.atualizarDrone(dto);
        return ResponseEntity.ok(new ListagemDronesDto(drone));
    }




    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        droneRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }





    }


