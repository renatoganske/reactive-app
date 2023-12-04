package io.github.renatoganske.resources;

import io.github.renatoganske.entities.Produto;
import io.github.renatoganske.repositories.ProdutoRepository;
import io.smallrye.mutiny.Multi;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("produto")
public class ProdutoResource {

    @Inject
    ProdutoRepository repository;

    @GET
    @Path("imperativo")
    public List<Produto> getProdutosImperativamente(){
        return repository.criarProdutosImperativo();

    }

    @GET
    @Path("reativo")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Produto> getProdutosReativamente(){
        return repository.criarProdutosReativo();
    }
}
