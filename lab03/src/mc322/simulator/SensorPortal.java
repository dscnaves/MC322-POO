public class SensorPortal extends Sensor {
    public SensorPortal(double raio) {
        super(raio);
    }

    @Override

    // Método para monitorar o ambiente em busca de portais
    public void monitorar(Robo robo) {
        System.out.println("🔎 " + robo.getNome() + " ativou o Sensor de Portal.");
        Portal portal = checkPortal(robo);
        if (portal != null) {
            System.out.println("🌀 Portal detectado em (" + 
                portal.getX1() + "," + portal.getY1() + "," + portal.getZ1() + ")");
            System.out.println("Destino: (" + 
                portal.getDestinoX() + "," + portal.getDestinoY() + "," + portal.getDestinoZ() + ")");
        } else {
            System.out.println("Nenhum portal encontrado no alcance.");
        }
    }

    public Portal checkPortal(Robo robo) {
        for (Obstaculo obst : robo.getAmbiente().getObstaculos()) {
            if (obst.getTipo() == TipoObstaculo.PORTAL && 
                Sensor.dentroDoAlcance(obst.getX1(), obst.getY1(), obst.getZ1(), 
                                      robo.getPosicaoX(), robo.getPosicaoY(), robo.getAltitude())) {
                return (Portal) obst;
            }
        }
        return null;
    }

    // Método para monitorar o ambiente em busca de portais
    public Portal checkPortal(Robo robo) {

        // Obter o ambiente e a posição do robô
        Ambiente ambiente = robo.getAmbiente();
        int xRobo = robo.getPosicaoX();
        int yRobo = robo.getPosicaoY();
        int zRobo = robo.getAltitude();

        // Verificar cada obstáculo no ambiente
        for (Obstaculo obst : ambiente.getObstaculos()) {

            // Verificar se o obstáculo é um portal e se está dentro do alcance do sensor
            if (obst.getTipo() == TipoObstaculo.PORTAL && Sensor.dentroDoAlcance(obst.x1, obst.y1, obst.z1, xRobo, yRobo, zRobo)) {
                
                Portal portal  = (Portal) obst;

                // Retorna o portal encontrado no raio de alcance
                return portal;
            }
        } 
        return null;
    }
}


