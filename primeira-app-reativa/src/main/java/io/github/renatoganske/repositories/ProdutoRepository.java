package io.github.renatoganske.repositories;

import io.github.renatoganske.entities.Produto;
import io.quarkus.runtime.configuration.SystemOnlySourcesConfigBuilder;
import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ApplicationScoped
public class ProdutoRepository {

    private void pausar(int i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Produto gerarNovoProduto(int i) {
        Produto produto = new Produto();
        produto.setId(i);
        produto.setNome("produto " + i);
        return produto;
    }

    private void imprimirNoConsole(int i ){
        System.out.println("iteração: " + i);
    }

    public List<Produto> criarProdutosImperativo() {
        return IntStream
                .range(0, 20)
                .peek(this:: imprimirNoConsole)
                .peek(this::pausar)
                .mapToObj(this::gerarNovoProduto)
                .collect(Collectors.toList());
    }

    public Multi<Produto> criarProdutosReativo() {
        return Multi.createFrom()
                .range(0,20)
                .onItem().invoke(this::imprimirNoConsole)
                .onItem().invoke(this::pausar)
                .onItem().transform(this::gerarNovoProduto)
                .onCancellation().invoke(() -> System.out.println("O usuário cancelou a requisição."))
                .onTermination().invoke(() -> System.out.println("A requisição foi terminada."));
    }
}
