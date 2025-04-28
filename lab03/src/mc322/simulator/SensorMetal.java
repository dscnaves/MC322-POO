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

        // Verifica cada obstáculo no ambiente
        for (Obstaculo obst : ambiente.getObstaculos()) {

            // Verifica se o obstáculo é um metal e se está dentro do alcance do sensor
            if (Sensor.dentroDoAlcance(obst.x1, obst.y1, obst.z1, xRobo, yRobo, zRobo)) {

                // Verifica se o obstáculo é um tesouro ou lixo
                if (obst.getTipo() == TipoObstaculo.TESOURO || obst.getTipo() == TipoObstaculo.LIXO) {

                    // Atualiza a localização do tesouro e classifica o metal
                    ((RoboRastreador) robo).atualizarLocalizacaoTesouro(obst.x1, obst.y1, obst.z1);
                    ((RoboRastreador) robo).classificarMetal(obst);
                    encontrado = true;
                }
            }
        }
                    
        // Se nenhum metal foi encontrado, informar ao usuário
        if (!encontrado) {
            System.out.println("Nenhum metal detectado dentro do raio.");
        }
    }

    // função check
}


