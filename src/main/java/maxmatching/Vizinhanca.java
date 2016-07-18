package maxmatching;

/**
 * Created by jeffrodrigo on 13/07/16.
 */

public class Vizinhanca {
    private final Grafo grafo;

    Vizinhanca(Grafo grafo){
        this.grafo = grafo;
    }

    public Aresta[] melhorVizinhoDuplo(Aresta[] solucao) {
        Aresta[] permutacao, melhor = solucao;
        int custoPermutacao, menorCusto = Grasp.custoSolucao(solucao);

        // Permutacoes dois a dois
        for (int i = 0; i < solucao.length - 1; i++) {
            for (int j = i + 1; j < solucao.length; j++) {
                permutacao = permutacoesDuplas(solucao, i, j);

                custoPermutacao = Grasp.custoSolucao(permutacao);
                if (custoPermutacao < menorCusto) {
                    melhor = permutacao;
                    menorCusto = custoPermutacao;
                }
            }
        }

        return melhor;
    }

    public Aresta[] melhorVizinhoTriplo(Aresta[] solucao) {
        Aresta[] permutacao, melhor = solucao;
        int custoPermutacao, menorCusto = Grasp.custoSolucao(solucao);

        // Permutacoes dois a dois
        for (int i = 0; i < solucao.length - 1; i++) {
            for (int j = i + 1; j < solucao.length; j++) {
                permutacao = permutacoesDuplas(solucao, i, j);

                custoPermutacao = Grasp.custoSolucao(permutacao);
                if (custoPermutacao < menorCusto) {
                    melhor = permutacao;
                    menorCusto = custoPermutacao;
                }
            }
        }

        //permutacoes tres a tres
        for (int p = 0; p < solucao.length - 2; p++) {
            for (int q = p + 1; q < solucao.length - 1; q++) {
                for (int r = q + 1; r < solucao.length; r++) {
                    permutacao = permutacoesTriplas(solucao, p, q, r);

                    custoPermutacao = Grasp.custoSolucao(permutacao);
                    if (custoPermutacao < menorCusto) {
                        melhor = permutacao;
                        menorCusto = custoPermutacao;
                    }
                }
            }
        }

        return melhor;
    }

    private Aresta[] permutacoesDuplas(Aresta[] solucao, int p, int q) {
        Aresta[] melhor = solucao;
        int menorCusto = Grasp.custoSolucao(solucao);

        Aresta[] permutacao;
        int custoPermutacao;

        permutacao = permutacaoDuplaSimples(solucao, p, q);
        custoPermutacao = Grasp.custoSolucao(permutacao);
        if (custoPermutacao < menorCusto) {
            melhor = permutacao;
            menorCusto = custoPermutacao;
        }

        permutacao = permutacaoDuplaCruzada(solucao, p, q);
        custoPermutacao = Grasp.custoSolucao(permutacao);
        if (custoPermutacao < menorCusto)
            melhor = permutacao;

        return melhor;
    }

    private Aresta[] permutacaoDuplaSimples(Aresta[] solucao, int i, int j) {
        Aresta arestaI = Grafo.retornaAresta(grafo, solucao[i].origem, solucao[j].origem);
        Aresta arestaJ = Grafo.retornaAresta(grafo, solucao[i].destino, solucao[j].destino);

        Aresta[] permutacao = new Aresta[solucao.length];
        for (int k = 0; k < solucao.length; k++)
            permutacao[k] = solucao[k];
        permutacao[i] = arestaI;
        permutacao[j] = arestaJ;

        return permutacao;
    }

    private Aresta[] permutacaoDuplaCruzada(Aresta[] solucao, int i, int j) {
        Aresta arestaI = Grafo.retornaAresta(grafo, solucao[i].origem, solucao[j].destino);
        Aresta arestaJ = Grafo.retornaAresta(grafo, solucao[i].destino, solucao[j].origem);

        Aresta[] permutacao = new Aresta[solucao.length];
        for (int k = 0; k < solucao.length; k++)
            permutacao[k] = solucao[k];
        permutacao[i] = arestaI;
        permutacao[j] = arestaJ;

        return permutacao;
    }

    private Aresta[] permutacoesTriplas(Aresta[] solucao, int p, int q, int r) {
        Aresta[] permutacao;
        Aresta[] melhor = solucao;
        int custoPermutacao, menorCusto = Grasp.custoSolucao(solucao);

        Aresta A = solucao[p];
        Aresta B = solucao[q];
        Aresta C = solucao[r];

        Vertice a, b, c, d, e, f;
        a = A.origem;
        b = A.destino;

        c = B.origem;
        d = B.destino;

        e = C.origem;
        f = C.destino;

        permutacao = montarPermutacaoTripla(solucao, p, q, r,
                a, c, b, e, d, f);
        custoPermutacao = Grasp.custoSolucao(permutacao);
        if (custoPermutacao < menorCusto) {
            melhor = permutacao;
            menorCusto = custoPermutacao;
        }

        permutacao = montarPermutacaoTripla(solucao, p, q, r,
                a, c, b, f, d, e);
        custoPermutacao = Grasp.custoSolucao(permutacao);
        if (custoPermutacao < menorCusto) {
            melhor = permutacao;
            menorCusto = custoPermutacao;
        }

        for (int i = 0; i < permutacao.length - 1; i++) {
            for (int j = i + 1; j < permutacao.length; j++) {
                permutacao = permutacoesDuplas(permutacao, i, j);

                custoPermutacao = Grasp.custoSolucao(permutacao);
                if (custoPermutacao < menorCusto) {
                    melhor = permutacao;
                    menorCusto = custoPermutacao;
                }
            }
        }



        permutacao = montarPermutacaoTripla(solucao, p, q, r,
                a, d, b, e, c, f);
        custoPermutacao = Grasp.custoSolucao(permutacao);
        if (custoPermutacao < menorCusto) {
            melhor = permutacao;
            menorCusto = custoPermutacao;
        }

        for (int i = 0; i < permutacao.length - 1; i++) {
            for (int j = i + 1; j < permutacao.length; j++) {
                permutacao = permutacoesDuplas(permutacao, i, j);

                custoPermutacao = Grasp.custoSolucao(permutacao);
                if (custoPermutacao < menorCusto) {
                    melhor = permutacao;
                    menorCusto = custoPermutacao;
                }
            }
        }



        permutacao = montarPermutacaoTripla(solucao, p, q, r,
                a, d, b, f, c, e);
        custoPermutacao = Grasp.custoSolucao(permutacao);
        if (custoPermutacao < menorCusto) {
            melhor = permutacao;
            menorCusto = custoPermutacao;
        }

        for (int i = 0; i < permutacao.length - 1; i++) {
            for (int j = i + 1; j < permutacao.length; j++) {
                permutacao = permutacoesDuplas(permutacao, i, j);

                custoPermutacao = Grasp.custoSolucao(permutacao);
                if (custoPermutacao < menorCusto) {
                    melhor = permutacao;
                    menorCusto = custoPermutacao;
                }
            }
        }



        permutacao = montarPermutacaoTripla(solucao, p, q, r,
                a, e, b, c, d, f);
        custoPermutacao = Grasp.custoSolucao(permutacao);
        if (custoPermutacao < menorCusto) {
            melhor = permutacao;
            menorCusto = custoPermutacao;
        }

        for (int i = 0; i < permutacao.length - 1; i++) {
            for (int j = i + 1; j < permutacao.length; j++) {
                permutacao = permutacoesDuplas(permutacao, i, j);

                custoPermutacao = Grasp.custoSolucao(permutacao);
                if (custoPermutacao < menorCusto) {
                    melhor = permutacao;
                    menorCusto = custoPermutacao;
                }
            }
        }



        permutacao = montarPermutacaoTripla(solucao, p, q, r,
                a, e, b, d, c, f);
        custoPermutacao = Grasp.custoSolucao(permutacao);
        if (custoPermutacao < menorCusto) {
            melhor = permutacao;
            menorCusto = custoPermutacao;
        }

        for (int i = 0; i < permutacao.length - 1; i++) {
            for (int j = i + 1; j < permutacao.length; j++) {
                permutacao = permutacoesDuplas(permutacao, i, j);

                custoPermutacao = Grasp.custoSolucao(permutacao);
                if (custoPermutacao < menorCusto) {
                    melhor = permutacao;
                    menorCusto = custoPermutacao;
                }
            }
        }



        permutacao = montarPermutacaoTripla(solucao, p, q, r,
                a, f, b, c, d, e);
        custoPermutacao = Grasp.custoSolucao(permutacao);
        if (custoPermutacao < menorCusto) {
            melhor = permutacao;
            menorCusto = custoPermutacao;
        }

        for (int i = 0; i < permutacao.length - 1; i++) {
            for (int j = i + 1; j < permutacao.length; j++) {
                permutacao = permutacoesDuplas(permutacao, i, j);

                custoPermutacao = Grasp.custoSolucao(permutacao);
                if (custoPermutacao < menorCusto) {
                    melhor = permutacao;
                    menorCusto = custoPermutacao;
                }
            }
        }



        permutacao = montarPermutacaoTripla(solucao, p, q, r,
                a, f, b, d, c, e);
        custoPermutacao = Grasp.custoSolucao(permutacao);
        if (custoPermutacao < menorCusto) {
            melhor = permutacao;
            menorCusto = custoPermutacao;
        }

        for (int i = 0; i < permutacao.length - 1; i++) {
            for (int j = i + 1; j < permutacao.length; j++) {
                permutacao = permutacoesDuplas(permutacao, i, j);

                custoPermutacao = Grasp.custoSolucao(permutacao);
                if (custoPermutacao < menorCusto) {
                    melhor = permutacao;
                    menorCusto = custoPermutacao;
                }
            }
        }


        return melhor;
    }

    private Aresta[] montarPermutacaoTripla(
            Aresta[] solucao,
            int p, int q, int r,
            Vertice origemA, Vertice destinoA,
            Vertice origemB, Vertice destinoB,
            Vertice origemC, Vertice destinoC)
    {
        // copiar solucao
        Aresta[] permutacao = new Aresta[solucao.length];
        for (int i = 0; i < solucao.length; i++)
            permutacao[i] = solucao[i];

        Aresta arestaA = Grafo.retornaAresta(grafo, origemA, destinoA);
        Aresta arestaB = Grafo.retornaAresta(grafo, origemB, destinoB);
        Aresta arestaC = Grafo.retornaAresta(grafo, origemC, destinoC);

        solucao[p] = arestaA;
        solucao[q] = arestaB;
        solucao[r] = arestaC;

        return permutacao;
    }

}
