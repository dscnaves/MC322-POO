package mc322.simulator;

import mc322.simulator.robos.Robo;
import mc322.simulator.robos.RoboLimpeza;

public class SensorReciclagem extends Sensor {

    private int lixoX;
    private int lixoY;
    private int lixoZ;


    public SensorReciclagem(double raio) {
        super(raio);
        lixoX = -1;
        lixoY = -1;
        lixoZ = -1;
    }

    @Override
    public void monitorar(Robo robo) {

        if (!(robo instanceof RoboLimpeza)) {
            System.out.println("SensorReciclagem apenas para RoboLimpeza.");
            return;
        }

        System.out.println("🧹 " + robo.getNome() + " ativou o Sensor de Reciclagem.");

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
                                lixoX = novoX;
                                lixoY = novoY;
                                lixoZ = novoZ;
                                lixoEncontrado = true;
                                break;
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

    public void classificarLixo(String tipo) {
        switch (tipo.toLowerCase()) {
            case "papel":
                System.out.println("- Papel: Direcionando para compactação.");
                break;
            case "plastico":
                System.out.println("- Plastico: Direcionando para reciclagem de plastico.");
                break;
            case "metal":
                System.out.println("- Metal: Direcionando para fundição.");
                break;
            case "organico":
                System.out.println("- Orgânico: Direcionando para compostagem.");
                break;
            case "vidro":
                System.out.println("- Vidro: Direcionando para reciclagem de vidro.");
                break;
            default:
                System.out.println("- Tipo de lixo desconhecido. Armazenar para análise manual.");
        }
    }


    // Funções Getters
    public int getLixoX() {
        return lixoX;
    }

    public int getLixoY() {
        return lixoY;
    }

    public int getLixoZ() {
        return lixoZ;
    }

}


