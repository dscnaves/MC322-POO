package mc322.robo;

import mc322.ambiente.Ambiente;
import mc322.missao.Missao;

public abstract class AgenteInteligente extends RoboTerrestre {
    protected Missao missao;

    public AgenteInteligente(String id, int posicaoX, int posicaoY, String direcao, int velocidadeMaxima, Ambiente ambiente) {
        super(id, posicaoX, posicaoY, direcao, velocidadeMaxima, ambiente);
    }

    public void definirMissao(Missao m) {
        this.missao = m;
    }

    public boolean temMissao() {
        return missao != null;
    }

    public abstract void executarMissao(Ambiente a);
}
