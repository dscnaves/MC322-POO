package mc322.missao;

import mc322.ambiente.Ambiente;
import mc322.comunicacao.LoggerMissao;
import mc322.interfaces.Sensoreavel;
import mc322.robo.Robo;

public class MissaoCircular implements Missao {

    @Override
    public void executar(Robo robo, Ambiente ambiente) {
        LoggerMissao logger = null;

        try {
            logger = new LoggerMissao("log_" + robo.getId() + ".txt");

            System.out.println("Robô " + robo.getId() + " iniciando missão circular...");
            logger.log("Iniciando missão circular.");

            // 1. Movimentação
            try {
                robo.moverPara(1, 0, 0);
                robo.moverPara(0, 1, 0);
                robo.moverPara(-1, 0, 0);
                robo.moverPara(0, -1, 0);
                System.out.println("Movimentação em padrão quadrado concluída.");
                logger.log("Movimentação em padrão quadrado concluída.");
            } catch (Exception e) {
                System.out.println("Erro durante movimentação circular: " + e.getMessage());
                logger.log("Erro na movimentação: " + e.getMessage());
            }

            // 2. Verificação de sensores
            if (robo instanceof Sensoreavel) {
                try {
                    ((Sensoreavel) robo).acionarSensores();
                    System.out.println("Sensores ativados com sucesso.");
                    logger.log("Sensores ativados.");
                } catch (Exception e) {
                    System.out.println("Erro ao acionar sensores: " + e.getMessage());
                    logger.log("Erro ao acionar sensores: " + e.getMessage());
                }
            }

            // 3. Registro final
            String status = "Missão finalizada. Estado: " + robo.getEstado();
            System.out.println(status);
            logger.log(status);

        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
            if (logger != null) logger.log("Erro inesperado: " + e.getMessage());
        } finally {
            if (logger != null) logger.fechar();
        }
    }
}
