package controle.estoque.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table (name = "Produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "CODIGO_DO_PRODUTO")
    private UUID codigo;

    @ManyToOne
    @JoinColumn(name = "estoque_id")
    private Estoque estoque;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Preço")
    private double preco;

    @Column(name = "Descrição")
    private String descricao;


    public Produto(){}

    public Produto(UUID codigo, String nome, double preco, String descricao) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
    }

    public UUID getCodigo() {return codigo;}
    public void setCodigo(UUID codigo) {this.codigo = codigo;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public double getPreco() {return preco;}
    public void setPreco(double preco) {this.preco = preco;}

    public String getDescricao() {return descricao;}
    public void setDescricao(String descricao) {this.descricao = descricao;}

    public Estoque getEstoque() {return estoque;}
    public void setEstoque(Estoque estoque) {this.estoque = estoque;}
}
