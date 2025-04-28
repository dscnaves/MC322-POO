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
             if (obst instanceof Lixo && Sensor.dentroDoAlcance(obst.getX1(), obst.getY1(), obst.getZ1(), posicaoX, posicaoY, altitude)) {
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

    public void classificarELimparLixo() {
        for (Obstaculo obst : ambiente.getObstaculos()) {
            
            // Verifica se o obstáculo é um lixo e se está dentro do raio de alcance do sensor ()
            if (obst instanceof Lixo) {
                if (Sensor.dentroDoAlcance(obst.x1, obst.y1, obst.z1, posicaoX, posicaoY, altitude)){
                    Lixo lixo = (Lixo) obst;
                    sensorReciclagem.classificarLixo(lixo.getTipoLixo());
                    ambiente.getObstaculos().remove(obst);
                    ambiente.atualizarMapa(posicaoX, posicaoY, altitude, "_");
                    System.out.println("🗑️ Lixo classificado e removido da posição atual.");
                    return;
                }                
            }
        }

        System.out.println("Nenhum lixo para classificar na posição atual.");
    }
 }
 
 
 