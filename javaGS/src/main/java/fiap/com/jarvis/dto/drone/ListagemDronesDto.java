package fiap.com.jarvis.dto.drone;

import fiap.com.jarvis.model.Drone;

public record ListagemDronesDto(Long id, String modelo, String funcao, String fabricante, String distancia, String bateria) {

        public ListagemDronesDto(Drone drone){
            this(drone.getId(),drone.getModelo(), drone.getFuncao(), drone.getFabricante(), drone.getDistancia(), drone.getBateria());
        }

}
