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

        for (Obstaculo obst : ambiente.getObstaculos()) {
            if (obst.contemPonto(obst.x1, obst.y1, obst.z1) && obst.getTipo() == TipoObstaculo.PORTAL && Sensor.dentroDoAlcance(obst.x1, obst.y1, obst.z1, xRobo, yRobo, zRobo)) {
                System.out.println("🌀 Portal detectado nas coordenadas: (" + obst.x1 + ", " + obst.y1 + ", " + obst.z1 + ")");
                encontrado = true;
            }
        }                    
                
        if (!encontrado) {
            System.out.println("Nenhum portal encontrado dentro do raio.");
            return;
        }
    }
}


