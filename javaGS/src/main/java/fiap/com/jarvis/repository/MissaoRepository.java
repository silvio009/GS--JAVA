package fiap.com.jarvis.repository;

import fiap.com.jarvis.model.Missao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissaoRepository extends JpaRepository<Missao, Long> {
}
