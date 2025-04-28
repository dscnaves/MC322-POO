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

        // Para cada obstáculo presente em ambiente
        for (Obstaculo obst : ambiente.getObstaculos()) {

            // Verificar se o obstáculo é do tipo Planeta e se o robo está dentro dos limites do planeta
            if (obst.getTipo() == TipoObstaculo.PLANETA && obst.contemPonto(xRobo, yRobo, zRobo)) {
                planetaDetectado = true;

                // Verificar se há alienígenas no planeta
                boolean alienEncontrado = false;
                for (Obstaculo o : ambiente.getObstaculos()) {

                    // Se o obstáculo dentro de ambiente é um alienígena e se o alienígena está dentro do planeta verificado
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

    public Planeta checkPovo(Robo robo) {
        
        Ambiente ambiente = robo.getAmbiente();
        int xRobo = robo.getPosicaoX();
        int yRobo = robo.getPosicaoY();
        int zRobo = robo.getAltitude();

        boolean planetaDetectado = false;

        // Para cada obstáculo presente em ambiente
        for (Obstaculo obst : ambiente.getObstaculos()) {

            // Verificar se o obstáculo é do tipo Planeta e se o robo está dentro dos limites do planeta
            if (obst.getTipo() == TipoObstaculo.PLANETA && obst.contemPonto(xRobo, yRobo, zRobo)) {
                planetaDetectado = true;

                Planeta planeta  = (Planeta) obst;

                // Verificar se há alienígenas no planeta
                boolean alienEncontrado = false;
                for (Obstaculo o : ambiente.getObstaculos()) {

                    // Se o obstáculo dentro de ambiente é um alienígena e se o alienígena está dentro do planeta verificado
                    if (o.getTipo() == TipoObstaculo.ALIENIGENA &&
                        o.getX1() >= obst.getX1() && o.getX2() <= obst.getX2() &&
                        o.getY1() >= obst.getY1() && o.getY2() <= obst.getY2()) {
                        alienEncontrado = true;
                        break;
                    }
                }

                if (alienEncontrado) { // planeta já povoado
                    return null;
                } else { // planeta não povoado
                    return planeta;
                }
            }
        }
        return null; // nenhum planeta
    }
}


