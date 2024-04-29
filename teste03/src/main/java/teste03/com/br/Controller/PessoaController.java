package teste03.com.br.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teste03.com.br.Model.Pessoa;
import teste03.com.br.Service.PessoaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> getPessoas() {
        return pessoaService.getAllPessoas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.getPessoaById(id);
        return ResponseEntity.ok().body(pessoa);
    }

    @PostMapping
    public ResponseEntity<Pessoa> createPessoa(@RequestBody Pessoa pessoa) {
        Pessoa createdPessoa = pessoaService.createPessoa(pessoa);
        return ResponseEntity.created(URI.create("/api/pessoas/" + createdPessoa.getId())).body(createdPessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        Pessoa updatedPessoa = pessoaService.updatePessoa(id, pessoa);
        return ResponseEntity.ok().body(updatedPessoa);
    }
}

