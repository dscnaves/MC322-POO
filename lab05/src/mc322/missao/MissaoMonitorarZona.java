package mc322.missao;

import mc322.ambiente.Ambiente;
import mc322.comunicacao.LoggerMissao;
import mc322.interfaces.Sensoreavel;
import mc322.robo.Robo;

public class MissaoMonitorarZona implements Missao {

    @Override
    public void executar(Robo robo, Ambiente ambiente) {
        LoggerMissao logger = null;

        try {
            logger = new LoggerMissao("log_" + robo.getId() + ".txt");

            System.out.println("Robô " + robo.getId() + " iniciando missão de monitoramento da zona...");
            logger.log("Iniciando missão de monitoramento.");

            // 1. Movimentação
            try {
                robo.moverPara(1, 0, 0);
                System.out.println("Movimentação: robô andou uma posição no eixo X.");
                logger.log("Movimentação: +1 no eixo X.");
            } catch (Exception e) {
                System.out.println("Erro ao tentar mover o robô: " + e.getMessage());
                logger.log("Erro na movimentação: " + e.getMessage());
            }

            // 2. Verificação de sensores
            if (robo instanceof Sensoreavel) {
                try {
                    ((Sensoreavel) robo).acionarSensores();
                    System.out.println("Sensores ativados com sucesso.");
                    logger.log("Sensores ativados com sucesso.");
                } catch (Exception e) {
                    System.out.println("Erro ao acionar sensores: " + e.getMessage());
                    logger.log("Erro ao acionar sensores: " + e.getMessage());
                }
            } else {
                System.out.println("Robô não possui sensores para monitoramento.");
                logger.log("Robô sem sensores.");
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
