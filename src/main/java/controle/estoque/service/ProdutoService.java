package controle.estoque.service;

import controle.estoque.controllers.CriarProdutoDto;
import controle.estoque.entity.Produto;
import controle.estoque.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {this.produtoRepository = produtoRepository;}

    //Criação do produto
    public UUID criarProduto(CriarProdutoDto criarProdutoDto){

        var entity = new Produto(
                UUID.randomUUID(),
                criarProdutoDto.nome(),
                criarProdutoDto.preco(),
                criarProdutoDto.descricao());
        var produtoSalvo = produtoRepository.save(entity);

        return produtoSalvo.getCodigo();
    }

    //Busca de produtor pelo ID
    public Optional<Produto> getProdutoById(String codigo){
        return produtoRepository.findById(UUID.fromString(codigo));
    }

    public List<Produto> listProdutos(){
        return produtoRepository.findAll();
    }

    //Deleta o produto pelo ID
    public void deleteById(String codigo){
        var id = UUID.fromString(codigo);
        var produtoExists = produtoRepository.existsById(id);

        if(produtoExists){
            produtoRepository.deleteById(id);
        }
    }
}
