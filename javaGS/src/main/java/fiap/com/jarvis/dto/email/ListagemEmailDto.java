package fiap.com.jarvis.dto.email;

import fiap.com.jarvis.dto.funcionario.DetalhesFuncionarioDto;
import fiap.com.jarvis.model.Email;

public record ListagemEmailDto(Long id, String email , String dadosEmail, String statusEmail, DetalhesFuncionarioDto detalhesFuncionarioDto) {

    public ListagemEmailDto(Email email){
        this(email.getId(), email.getEmail(), email.getDadosEmail(), email.getStatusEmail(), new  DetalhesFuncionarioDto(email.getFuncionario()) );
    }
}
