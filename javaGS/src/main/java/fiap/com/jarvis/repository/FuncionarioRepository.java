package fiap.com.jarvis.repository;

import fiap.com.jarvis.model.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FuncionarioRepository  extends JpaRepository<Funcionario , Long> {


    @Query("from Funcionario f where lower(f.nome) like lower(concat('%', :nome, '%'))")
    Page<Funcionario> buscarPorNome(String nome, Pageable pageable);

    @Query("from Funcionario f where lower(f.cargo) like lower(concat('%', :cargo, '%'))")
    Page<Funcionario> buscarPorCargo(String cargo, Pageable pageable);


}
