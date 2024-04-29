package teste03.com.br.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import teste03.com.br.Model.Endereco;
import teste03.com.br.Model.Pessoa;
import teste03.com.br.Repository.EnderecoRepository;
import teste03.com.br.Repository.PessoaRepository;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class EnderecoService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Endereco> getAllEnderecosByPessoaId(Long pessoaId) {
        Pessoa pessoa = getPessoaById(pessoaId);
        return pessoa.getEnderecos();
    }

    public Endereco getEnderecoById(Long pessoaId, Long enderecoId) {
        Pessoa pessoa = getPessoaById(pessoaId);
        return pessoa.getEnderecos().stream()
                .filter(endereco -> endereco.getId().equals(enderecoId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "People not found with id: " + id));
    }

    public Endereco createEndereco(Long pessoaId, Endereco endereco) {
        Pessoa pessoa = getPessoaById(pessoaId);
        endereco.setPessoa(pessoa);
        return enderecoRepository.save(endereco);
    }

    public Endereco updateEndereco(Long pessoaId, Long enderecoId, Endereco endereco) {
        Endereco existingEndereco = getEnderecoById(pessoaId, enderecoId);
        existingEndereco.setLogradouro(endereco.getLogradouro());
        existingEndereco.setCep(endereco.getCep());
        existingEndereco.setNumero(endereco.getNumero());
        existingEndereco.setCidade(endereco.getCidade());
        existingEndereco.setEstado(endereco.getEstado());
        return enderecoRepository.save(existingEndereco);
    }

    public Endereco setEnderecoPrincipal(Long pessoaId, Long enderecoId) {
        Pessoa pessoa = getPessoaById(pessoaId);
        Endereco endereco = getEnderecoById(pessoaId, enderecoId);

        for (Endereco end : pessoa.getEnderecos()) {
            if (end.equals(endereco)) {
                end.setPrincipal(true);
            } else {
                end.setPrincipal(false);
            }
        }

        return enderecoRepository.save(endereco);
    }

    private Pessoa getPessoaById(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "People not found with id: " + id));
    }
}