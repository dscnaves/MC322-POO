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
 
     public RoboLimpeza(String nome, int posicaoX, int posicaoY, String direcao, int velocidadeMaxima, Ambiente ambiente) {
         super(nome, posicaoX, posicaoY, direcao, velocidadeMaxima, ambiente);
         this.adicionarSensor(new SensorReciclagem(3.0));
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
 }
 
 
 