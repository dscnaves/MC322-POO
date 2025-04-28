public class SensorPortal extends Sensor {
    public SensorPortal(double raio) {
        super(raio);
    }

    @Override

    // Método para monitorar o ambiente em busca de portais
    public void monitorar(Robo robo) {
        System.out.println("🔎 " + robo.getNome() + " ativou o Sensor de Portal.");

        // Obter o ambiente e a posição do robô
        Ambiente ambiente = robo.getAmbiente();
        int xRobo = robo.getPosicaoX();
        int yRobo = robo.getPosicaoY();
        int zRobo = robo.getAltitude();

        // Calcular o alcance do sensor
        int alcance = (int) Math.ceil(raio);

        boolean encontrado = false;     

        // Verificar cada obstáculo no ambiente
        for (Obstaculo obst : ambiente.getObstaculos()) {
            
            // Verificar se o obstáculo é um portal e se está dentro do alcance do sensor
            if (obst.getTipo() == TipoObstaculo.PORTAL && Sensor.dentroDoAlcance(obst.x1, obst.y1, obst.z1, xRobo, yRobo, zRobo)) {
                System.out.println("🌀 Portal detectado nas coordenadas: (" + obst.x1 + ", " + obst.y1 + ", " + obst.z1 + ")");
                encontrado = true;
            }
        } 
        
        // Se nenhum portal foi encontrado, informar ao usuário
        if (!encontrado) {
            System.out.println("Nenhum portal encontrado dentro do raio.");
            return;
        }
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


