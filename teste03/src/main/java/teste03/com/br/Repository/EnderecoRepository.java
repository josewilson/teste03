package teste03.com.br.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teste03.com.br.Model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}

