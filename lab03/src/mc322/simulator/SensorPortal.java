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
}


