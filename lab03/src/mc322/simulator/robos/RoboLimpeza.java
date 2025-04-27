/*
 * RoboLimpeza.java
 * 
 * Última modificação: 30/03/2025
 * 
 * Classe componente do Lab02 da disciplina MC322 - Programação Orientada a Objetos
 * 
 * Autores: Anita Almeida e Daniela Naves
 */

/*
 * Esta classe contém a estrutura de implementação de RoboLimpeza de um 
 * simulador de robôs, que representa uma classe para robôs especializados
 * em coleta e compactação de lixo.
 */

 package mc322.simulator.robos;

 import mc322.simulator.Ambiente;
 import mc322.simulator.Obstaculo;
 import mc322.simulator.Lixo;
 import mc322.simulator.SensorReciclagem;
 import mc322.simulator.TipoObstaculo;
 
 public class RoboLimpeza extends RoboTerrestre {

    private SensorReciclagem sensorReciclagem;
 
     public RoboLimpeza(String nome, int posicaoX, int posicaoY, String direcao, int velocidadeMaxima, Ambiente ambiente) {
         super(nome, posicaoX, posicaoY, direcao, velocidadeMaxima, ambiente);
         this.sensorReciclagem = new SensorReciclagem(3.0);
         this.adicionarSensor(sensorReciclagem);
     }
 
     public void limparLixo() {
         boolean lixoRemovido = false;
         for (int i = 0; i < ambiente.getObstaculos().size(); i++) {
             Obstaculo obst = ambiente.getObstaculos().get(i);
             if (obst instanceof Lixo && obst.contemPonto(posicaoX, posicaoY, altitude)) {
                 ambiente.getObstaculos().remove(i);
                 ambiente.atualizarMapa(posicaoX, posicaoY, altitude, "_");
                 System.out.println("🗑️ Lixo removido com sucesso na posição (" + posicaoX + ", " + posicaoY + ", " + altitude + ")!");
                 lixoRemovido = true;
                 break;
             }
         }
 
         if (!lixoRemovido) {
             System.out.println("Nenhum lixo encontrado para limpar na posição atual.");
         }
     }

     public void irAteLixoProximo() {
        int lixoX = sensorReciclagem.getLixoX();
        int lixoY = sensorReciclagem.getLixoY();
        int lixoZ = sensorReciclagem.getLixoZ();

        if (lixoX == -1 || lixoY == -1 || lixoZ == -1) {
            System.out.println("Nenhum lixo detectado para se mover.");
            return;
        }

        while (posicaoX != lixoX || posicaoY != lixoY || altitude != lixoZ) {
            if (posicaoX < lixoX) mover(1, 0, 0);
            else if (posicaoX > lixoX) mover(-1, 0, 0);
            else if (posicaoY < lixoY) mover(0, 1, 0);
            else if (posicaoY > lixoY) mover(0, -1, 0);
            else if (altitude < lixoZ) mover(0, 0, 1);
            else if (altitude > lixoZ) mover(0, 0, -1);
            else break;
        }

        System.out.println("🗑️ RoboLimpeza chegou ao lixo!");
    }

    public void classificarELimparLixo() {
        for (Obstaculo obst : ambiente.getObstaculos()) {
            if (obst instanceof Lixo && obst.contemPonto(posicaoX, posicaoY, altitude)) {
                Lixo lixo = (Lixo) obst;
                sensorReciclagem.classificarLixo(lixo.getTipoLixo());
                ambiente.getObstaculos().remove(obst);
                ambiente.atualizarMapa(posicaoX, posicaoY, altitude, "_");
                System.out.println("🗑️ Lixo classificado e removido da posição atual.");
                return;
            }
        }

        System.out.println("Nenhum lixo para classificar na posição atual.");
    }
 }
 
 
 