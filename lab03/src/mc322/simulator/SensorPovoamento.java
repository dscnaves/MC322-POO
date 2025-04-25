package mc322.simulator;

import mc322.simulator.robos.Robo;

public class SensorPovoamento extends Sensor {

    public SensorPovoamento(double raio) {
        super(raio);
    }

    @Override
    public void monitorar(Robo robo) {
        System.out.println("🛰️ " + robo.getNome() + " ativou o Sensor de Povoamento.");

        Ambiente ambiente = robo.getAmbiente();
        int xRobo = robo.getPosicaoX();
        int yRobo = robo.getPosicaoY();
        int zRobo = robo.getAltitude();

        boolean planetaDetectado = false;

        for (Obstaculo obst : ambiente.getObstaculos()) {
            if (obst.getTipo() == TipoObstaculo.PLANETA && obst.contemPonto(xRobo, yRobo, zRobo)) {
                planetaDetectado = true;

                boolean alienEncontrado = false;
                for (Obstaculo o : ambiente.getObstaculos()) {
                    if (o.getTipo() == TipoObstaculo.ALIENIGENA &&
                        o.getX1() >= obst.getX1() && o.getX2() <= obst.getX2() &&
                        o.getY1() >= obst.getY1() && o.getY2() <= obst.getY2()) {
                        alienEncontrado = true;
                        break;
                    }
                }

                if (alienEncontrado) {
                    System.out.println("👽 Planeta povoado detectado!");
                } else {
                    System.out.println("🪐 Planeta vazio (não povoado).");
                }
                break;
            }
        }

        if (!planetaDetectado) {
            System.out.println("Nenhum planeta encontrado na posição atual para analisar.");
        }
    }
}


