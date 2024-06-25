package fiap.com.jarvis.dto.drone;


import fiap.com.jarvis.model.Drone;

public record DetalhesDroneDto(Long id, String modelo, String funcao, String fabricante, String distancia, String bateria) {

    public DetalhesDroneDto(Drone drone){
        this(drone.getId(),drone.getModelo(), drone.getFuncao(), drone.getFabricante(), drone.getDistancia(), drone.getBateria());
    }
}
