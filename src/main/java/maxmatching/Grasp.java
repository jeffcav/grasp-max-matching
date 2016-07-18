package maxmatching;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeffrodrigo on 06/07/16.
 *
 * Classe que implementa a meta-heuristica GRASP para o problema 1-matching.
 */

class Grasp {
	private final Grafo grafo;

	Grasp(Grafo grafo) {
		this.grafo = grafo;
	}

	/**
	 * Implementa a meta-heuristica GRASP para o problema 1-matching
	 * @return A melhor solucao como um vetor de arestas
	 */
	public Aresta[] computarMaxMatching(int maxIteracoes) {
		EmparelhamentoMaximo emparelhamentos = new EmparelhamentoPorOrdenacao();
		Aresta[] melhorSolucao = null;
		Aresta[] solucao;

		for (int i = 0; i < maxIteracoes; i++) {
			solucao = emparelhamentos.construir(grafo);

			if (i == 0)
				melhorSolucao = solucao;

			solucao = buscaLocal(solucao);
			melhorSolucao = atualizarSolucao(solucao, melhorSolucao, i);
			grafo.desemparelhar();
			System.out.print((i+1) + " ");
		}

		return melhorSolucao;
	}

	private Aresta[] buscaLocal(Aresta[] solucao) {
		Vizinhanca vizinhanca = new Vizinhanca(grafo);

		Aresta[] melhor = vizinhanca.melhorVizinhoTriplo(solucao);

		return melhor;
	}

	/**
	 * Retorna a melhor de duas solucoes.
	 */
	private Aresta[] atualizarSolucao(Aresta[] solucao1, Aresta[] solucao2, int iteracao) {
		int custo1 = custoSolucao(solucao1);
		int custo2 = custoSolucao(solucao2);

		if (custo1 < custo2) {
			imprimirNovaSolucao(solucao1, iteracao);
			return solucao1;
		}
		return solucao2;
	}

	private void imprimirNovaSolucao(Aresta[] solucao, int iteracao) {
		System.out.println();
		System.out.print((iteracao + 1) + " ");
		System.out.print(Grasp.custoSolucao(solucao) + " [ ");

		java.util.Date date = new java.util.Date();

		for (Aresta aresta : solucao) {
			int origem = aresta.origem.nome + 1;
			int destino = aresta.destino.nome + 1;
			int peso = aresta.peso;
			System.out.print(origem + "->" + destino + " ");
		}
		System.out.println(" ] " + date + "\n");
	}

	/**
	 * Calcula o custo das arestas de uma solucao
	 */
	public static int custoSolucao(Aresta[] solucao) {
		int custo = 0;

		if (isNull(solucao))
			return 999999;

		for (Aresta aresta : solucao) {
			custo += aresta.peso;
		}
		return custo;
	}

	static boolean isNull(Object o) {
		return o == null;
	}
}
