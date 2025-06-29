package mc322.missao;

import mc322.ambiente.Ambiente;
import mc322.comunicacao.LoggerMissao;
import mc322.interfaces.Sensoreavel;
import mc322.robo.Robo;

public class MissaoBuscarPonto implements Missao {
    private int destinoX;
    private int destinoY;
    private int destinoZ;

    public MissaoBuscarPonto(int x, int y, int z) {
        this.destinoX = x;
        this.destinoY = y;
        this.destinoZ = z;
    }

    @Override
    public void executar(Robo robo, Ambiente ambiente) {
        LoggerMissao logger = null;

        try {
            logger = new LoggerMissao("log_" + robo.getId() + ".txt");

            System.out.println("Robô " + robo.getId() + " iniciando missão de buscar ponto...");
            logger.log("Iniciando missão de buscar ponto...");

            // 1. Movimentação (tentada, mas não obrigatória para continuar)
            try {
                int dx = destinoX - robo.getX();
                int dy = destinoY - robo.getY();
                int dz = destinoZ - robo.getZ();

                robo.moverPara(dx, dy, dz);
                System.out.println("Movimentação concluída.");
                logger.log("Movimentação para (" + destinoX + ", " + destinoY + ", " + destinoZ + ") concluída.");
            } catch (Exception e) {
                System.out.println("Erro na movimentação: " + e.getMessage());
                logger.log("Erro na movimentação: " + e.getMessage());
            }

            // 2. Verificação de sensores (executada mesmo que mov. falhe)
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

            // 3. Registro final (sempre executado)
            String status = "Missão finalizada. Estado: " + robo.getEstado();
            System.out.println(status);
            logger.log(status);

        } catch (Exception e) {
            System.out.println("Erro inesperado na missão: " + e.getMessage());
            if (logger != null) logger.log("Erro inesperado: " + e.getMessage());
        } finally {
            if (logger != null) logger.fechar();
        }
    }

}
