package mc322.simulator;

import mc322.simulator.robos.Robo;

public class SensorPortal extends Sensor {

    public SensorPortal(double raio) {
        super(raio);
    }

    @Override
    public void monitorar(Robo robo) {
        System.out.println("🔎 " + robo.getNome() + " ativou o Sensor de Portal.");

        Ambiente ambiente = robo.getAmbiente();
        int xRobo = robo.getPosicaoX();
        int yRobo = robo.getPosicaoY();
        int zRobo = robo.getAltitude();

        int alcance = (int) Math.ceil(raio);

        boolean encontrado = false;
        for (int dx = -alcance; dx <= alcance; dx++) {
            for (int dy = -alcance; dy <= alcance; dy++) {
                for (int dz = -alcance; dz <= alcance; dz++) {
                    int novoX = xRobo + dx;
                    int novoY = yRobo + dy;
                    int novoZ = zRobo + dz;

                    if (ambiente.dentroDosLimites(novoX, novoY, novoZ)) {
                        for (Obstaculo obst : ambiente.getObstaculos()) {
                            if (obst.contemPonto(novoX, novoY, novoZ) && obst.getTipo() == TipoObstaculo.PORTAL) {
                                System.out.println("🌀 Portal detectado nas coordenadas: (" + novoX + ", " + novoY + ", " + novoZ + ")");
                                encontrado = true;
                            }
                        }
                    }
                }
            }
        }
        if (!encontrado) {
            System.out.println("Nenhum portal encontrado dentro do raio.");
        }
    }
}


