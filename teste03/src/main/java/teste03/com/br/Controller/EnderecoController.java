package teste03.com.br.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teste03.com.br.Model.Endereco;
import teste03.com.br.Service.EnderecoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pessoas/{pessoaId}/enderecos")
class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public List<Endereco> getEnderecos(@PathVariable Long pessoaId) {
        return enderecoService.getAllEnderecosByPessoaId(pessoaId);
    }

    @GetMapping("/{enderecoId}")
    public ResponseEntity<Endereco> getEnderecoById(@PathVariable Long pessoaId, @PathVariable Long enderecoId) {
        Endereco endereco = enderecoService.getEnderecoById(pessoaId, enderecoId);
        return ResponseEntity.ok().body(endereco);
    }

    @PostMapping
    public ResponseEntity<Endereco> createEndereco(@PathVariable Long pessoaId, @RequestBody Endereco endereco) {
        Endereco createdEndereco = enderecoService.createEndereco(pessoaId, endereco);
        return ResponseEntity.created(URI.create("/api/pessoas/" + pessoaId + "/enderecos/" + createdEndereco.getId())).body(createdEndereco);
    }

    @PutMapping("/{enderecoId}")
    public ResponseEntity<Endereco> updateEndereco(@PathVariable Long pessoaId, @PathVariable Long enderecoId, @RequestBody Endereco endereco) {
        Endereco updatedEndereco = enderecoService.updateEndereco(pessoaId, enderecoId, endereco);
        return ResponseEntity.ok().body(updatedEndereco);
    }

    @PutMapping("/{enderecoId}/principal")
    public ResponseEntity<Endereco> setEnderecoPrincipal(@PathVariable Long pessoaId, @PathVariable Long enderecoId) {
        Endereco endereco = enderecoService.setEnderecoPrincipal(pessoaId, enderecoId);
        return ResponseEntity.ok().body(endereco);
    }
}

