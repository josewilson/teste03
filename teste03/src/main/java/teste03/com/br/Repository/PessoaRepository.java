package teste03.com.br.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teste03.com.br.Model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}

