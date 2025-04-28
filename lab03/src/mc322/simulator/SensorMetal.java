public class SensorMetal extends Sensor {

    public SensorMetal(double raio) {
        super(raio);
    }

    @Override
    public void monitorar(Robo robo) {
        System.out.println("🔍 " + robo.getNome() + " ativou o Sensor de Metal.");

        if (!(robo instanceof RoboRastreador)) {
            System.out.println("SensorMetal somente aplicável a RoboRastreador.");
            return;
        }

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
                            if (obst.contemPonto(novoX, novoY, novoZ)) {
                                if (obst.getTipo() == TipoObstaculo.TESOURO || obst.getTipo() == TipoObstaculo.LIXO) {
                                    ((RoboRastreador) robo).atualizarLocalizacaoTesouro(novoX, novoY, novoZ);
                                    ((RoboRastreador) robo).classificarMetal(obst);
                                    encontrado = true;
                                }
                            }
                        }
                    }
                }
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum metal detectado dentro do raio.");
        }
    }
}


