package maxmatching;

import java.util.Arrays;

/**
 * Created by jeffrodrigo on 06/07/16.
 *
 * Representa um grafo
 */
class Grafo {
    public final int N;
    public final Vertice[] vertices;

    private final Aresta[] ordenadas;

    Grafo(int ordem){
        this.N = ordem;
        this.vertices = new Vertice[this.N];

        for (int i = 0; i < this.N; i++) {
            this.vertices[i] = new Vertice(i, this.N);
        }

        ordenadas = new Aresta[(N * (N-1)) / 2];
    }

    /**
     * Retorna uma aresta, dados seus vertices de origem e destino
     *
     * @param grafo
     * @param origem
     * @param destino
     * @return Aresta com a origem e destino passados por parametro
     */
    public static Aresta retornaAresta(Grafo grafo, int origem, int destino) {
        if (origem <= destino)
            return grafo.vertices[origem].arestas[destino];
        else
            return grafo.vertices[destino].arestas[origem];
    }

    public static Aresta retornaAresta(Grafo grafo, Vertice origem, Vertice destino) {
        return Grafo.retornaAresta(grafo, origem.nome, destino.nome);
    }

    public int retornaPosicaoOrigemAresta(Grafo grafo, Vertice origem) {
        
    	for (int i = 0; i < grafo.vertices.length; i++) {
            if(grafo.vertices[i] == origem){
            	return i;
            }
		}
    	return -1;
    }
    
    public int retornaPosicaoDestinoAresta(Grafo grafo, Vertice destino) {
        
    	for (int i = 0; i < grafo.vertices.length; i++) {
            if(grafo.vertices[i] == destino){
            	return i;
            }
		}
    	return -1;
    }

    public void ordenarArestas() {
        int totalArestas = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < vertices[i].arestas.length; j++, totalArestas++) {
                ordenadas[totalArestas] = vertices[i].arestas[j];
            }
        }
        Arrays.sort(ordenadas, 0, totalArestas);
    }

    public Aresta[] retornaArestasOdenadas(){
        return ordenadas;
    }

    public void desemparelhar() {
        for (int i = 0; i < N; i++) {
            vertices[i].estado = Estado.LIVRE;
            for (int j = i; j < N; j++) {
                vertices[i].arestas[j].estado = Estado.LIVRE;
            }
        }
    }
}
