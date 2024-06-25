package fiap.com.jarvis.repository;

import fiap.com.jarvis.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
