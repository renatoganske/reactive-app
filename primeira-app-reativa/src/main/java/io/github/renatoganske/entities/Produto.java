package io.github.renatoganske.entities;

public class Produto {

    private int id;
    private String nome;

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
