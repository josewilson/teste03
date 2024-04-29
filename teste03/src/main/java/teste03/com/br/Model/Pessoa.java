package teste03.com.br.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Pessoa {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String nomeCompleto;
    @Setter
    @Getter
    private Date dataNascimento;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos;

    public Pessoa(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

}
