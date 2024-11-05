package controle.estoque.service;

import controle.estoque.controllers.CriarProdutoDto;
import controle.estoque.controllers.UpdateProdutoDto;
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

    //BUSCA PELO PRODUTO NO ID
    public Optional<Produto> getProdutoById(String codigo){
        return produtoRepository.findById(UUID.fromString(codigo));
    }

    //LISTA TODOS OS PRODUTOS
    public List<Produto> listProdutos(){
        return produtoRepository.findAll();
    }

    //ATUALIZA PRODUTO PELO ID
    public void updateProdutoById(String codigo, UpdateProdutoDto updateProdutoDto){
        var id = UUID.fromString(codigo);

        var produtoEntity = produtoRepository.findById(id);

        if(produtoEntity.isPresent()){
            var produto = produtoEntity.get();

            if(updateProdutoDto.nome() != null){
                produto.setNome(updateProdutoDto.nome());
            }
            if(updateProdutoDto.descricao() != null){
                produto.setDescricao(updateProdutoDto.descricao());
            }
            if(updateProdutoDto.preco() != 0){
                produto.setPreco(updateProdutoDto.preco());
            }

            produtoRepository.save(produto);
        }

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
