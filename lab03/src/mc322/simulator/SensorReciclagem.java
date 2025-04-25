package mc322.simulator;

import mc322.simulator.robos.Robo;
import mc322.simulator.robos.RoboLimpeza;

public class SensorReciclagem extends Sensor {

    public SensorReciclagem(double raio) {
        super(raio);
    }

    @Override
    public void monitorar(Robo robo) {
        System.out.println("🧹 " + robo.getNome() + " ativou o Sensor de Reciclagem.");

        if (!(robo instanceof RoboLimpeza)) {
            System.out.println("SensorReciclagem apenas para RoboLimpeza.");
            return;
        }

        Ambiente ambiente = robo.getAmbiente();
        int xRobo = robo.getPosicaoX();
        int yRobo = robo.getPosicaoY();
        int zRobo = robo.getAltitude();

        // Convertendo a variável raio para o número de quadrados em volta do robo
        int alcance = (int) Math.ceil(raio);
        boolean lixoEncontrado = false;

        // Percorremos os quadradinhos dentro do cubo de (lado = alcance) onde o robo se encontra no centro de forma a encontrarmos ObstáculosLixo em volta do robo
        for (int dx = -alcance; dx <= alcance; dx++) {                  // Percorre em x
            for (int dy = -alcance; dy <= alcance; dy++) {                  // Percorre em y
                for (int dz = -alcance; dz <= alcance; dz++) {                  // Percorre em z
                    int novoX = xRobo + dx;
                    int novoY = yRobo + dy;
                    int novoZ = zRobo + dz;

                    // Verificar se a posição que estamos verificando está dentro dos limites do mapa
                    if (ambiente.dentroDosLimites(novoX, novoY, novoZ)) {

                        // Percorrer a lista de obstáculo dentro do Ambiente
                        for (Obstaculo obst : ambiente.getObstaculos()) {

                            // Verificar se o obstáculo é do tipo lixo e se o obstáculo ocupa a posição que estamos verificando
                            if (obst instanceof Lixo && obst.contemPonto(novoX, novoY, novoZ)) {
                                
                                // Converter obstáculo obst em obstáculo do tipo lixo
                                Lixo lixo = (Lixo) obst;
                                System.out.println("🗑️ Lixo de tipo " + lixo.getTipoLixo() + " detectado nas coordenadas (" + novoX + ", " + novoY + ", " + novoZ + ")!");
                                lixoEncontrado = true;
                            }
                        }
                    }
                }
            }
        }

        if (!lixoEncontrado) {
            System.out.println("Nenhum lixo detectado dentro do raio.");
        }
    }
}


