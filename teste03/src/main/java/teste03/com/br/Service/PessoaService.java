package teste03.com.br.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import teste03.com.br.Model.Pessoa;
import teste03.com.br.Repository.PessoaRepository;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> getAllPessoas() {
        return pessoaRepository.findAll();
    }

    public Pessoa getPessoaById(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "People not found with id: " + id));
    }

    public Pessoa createPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa updatePessoa(Long id, Pessoa pessoa) {
        Pessoa existingPessoa = getPessoaById(id);
        existingPessoa.setNomeCompleto(pessoa.getNomeCompleto());
        existingPessoa.setDataNascimento(pessoa.getDataNascimento());
        return pessoaRepository.save(existingPessoa);
    }

    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }
}
