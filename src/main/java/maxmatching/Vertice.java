package maxmatching;

/**
 * Created by jeffrodrigo on 06/07/16.
 *
 * Representa o Vertice de um grafo
 */
public class Vertice {
    public final int nome;
    public final Aresta[] arestas;
    public Estado estado = Estado.LIVRE;

    Vertice(int nome, int nArestas) {
        this.nome = nome;
        arestas = new Aresta[nArestas];
    }

    int retornaIndice() {
        return nome;
    }
    
    public void trocaStatus(){
    	if(estado == Estado.LIVRE) estado = Estado.EMPARELHADO;
    	else if(estado == Estado.EMPARELHADO) estado = Estado.LIVRE;
    }
    
    public Aresta getArestaSaturada(){
    	for (int i = 0; i < arestas.length; i++) {
			if(arestas[i].estado == Estado.EMPARELHADO){
				return arestas[i];
			}
		}
    	//eh um vertice livre
    	return null;
    }
    
    public Estado getEstado(){
    	return estado;
    }

    public boolean emparelhado() {
        return this.estado == Estado.EMPARELHADO;
    }

    public void emparelhar() {
        this.estado = Estado.EMPARELHADO;
    }
}
