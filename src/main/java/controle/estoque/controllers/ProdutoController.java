package controle.estoque.controllers;

import controle.estoque.entity.Produto;
import controle.estoque.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }
    //METODO POST{CREATE}
    // CRIA UM PRODUTO E UM ID
    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody CriarProdutoDto criarProdutoDto){
        var produtoId = produtoService.criarProduto(criarProdutoDto);
        return ResponseEntity.created(URI.create("/produtos/" + produtoId.toString())).build();
    }

    //METODO GET{ID}
    // BUSCAR PRODUTO PELO ID
    @GetMapping("/{codigo}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable("codigo") String codigo){
        var produto = produtoService.getProdutoById(codigo);
        if(produto.isPresent()){
            return ResponseEntity.ok(produto.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    //METODO GET
    // BUSCA A LISTA DE PRODUTOS
    @GetMapping()
    public ResponseEntity<List<Produto>> listProdutos(){
       var lista =  produtoService.listProdutos();

       return ResponseEntity.ok(lista);
    }

    //METODO DELETE
    //DELETA O PRODUTO PELO ID
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deleteById(@PathVariable("codigo") String codigo){
        produtoService.deleteById(codigo);
        return ResponseEntity.noContent().build();
    }
}
