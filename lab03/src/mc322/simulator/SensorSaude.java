package mc322.simulator;

import mc322.simulator.robos.Robo;

public class SensorSaude extends Sensor {

    public SensorSaude(double raio) {
        super(raio);
    }

    @Override
    public void monitorar(Robo robo) {
        System.out.println("🫠 " + robo.getNome() + " ativou o Sensor de Saúde.");

        Ambiente ambiente = robo.getAmbiente();
        int xRobo = robo.getPosicaoX();
        int yRobo = robo.getPosicaoY();
        int zRobo = robo.getAltitude();

        int alcance = (int) Math.ceil(raio);
        boolean doenteEncontrado = false;

        for (int dx = -alcance; dx <= alcance; dx++) {
            for (int dy = -alcance; dy <= alcance; dy++) {
                for (int dz = -alcance; dz <= alcance; dz++) {
                    int novoX = xRobo + dx;
                    int novoY = yRobo + dy;
                    int novoZ = zRobo + dz;

                    if (ambiente.dentroDosLimites(novoX, novoY, novoZ)) {
                        for (Obstaculo obst : ambiente.getObstaculos()) {
                            if (obst instanceof Plantinha && obst.contemPonto(novoX, novoY, novoZ)) {
                                Plantinha plantinha = (Plantinha) obst;
                                if (!plantinha.isSaudavel()) {
                                    System.out.println("🌱 Plantinha " + plantinha.getTipo() + " doente detectada nas coordenadas (" + novoX + ", " + novoY + ", " + novoZ + ")!");
                                    doenteEncontrado = true;
                                }
                            }
                        }
                    }
                }
            }
        }

        if (!doenteEncontrado) {
            System.out.println("Nenhuma plantinha doente detectada dentro do raio.");
        }
    }
}

