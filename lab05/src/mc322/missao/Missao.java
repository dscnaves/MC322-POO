package mc322.missao;

import mc322.ambiente.Ambiente;
import mc322.robo.Robo;

public interface Missao {
    void executar(Robo robo, Ambiente ambiente);
}
