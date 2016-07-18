package maxmatching;

/**
 * Created by jeffrodrigo on 06/07/16.
 *
 * Representa a aresta de um grafo
 */
public class Aresta implements  Comparable<Aresta>{
    public final int peso;
    public Estado estado = Estado.LIVRE;
    public final Vertice origem;
    public final Vertice destino;

    public Aresta(Vertice origem, Vertice destino, int peso) {
        this.peso = peso;
        this.origem = origem;
        this.destino = destino;
    }

    boolean emparelhada() {
        return this.estado == Estado.EMPARELHADO;
    }

    @Override
    public int compareTo(Aresta aresta) {
        return this.peso - aresta.peso;
    }

    public void emparelhar() {
        this.estado = Estado.EMPARELHADO;
        this.origem.emparelhar();
        this.destino.emparelhar();
    }

    public boolean temArestaEmparelhada() {
        if (this.origem.emparelhado()) return true;
        if (this.destino.emparelhado()) return true;
        return false;
    }
}
