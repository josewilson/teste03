package teste03.com.br.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Endereco {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    private String estado;
    private boolean principal;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;


}
