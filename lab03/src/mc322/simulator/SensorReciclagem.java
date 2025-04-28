public class SensorReciclagem extends Sensor {

    private int lixoX;
    private int lixoY;
    private int lixoZ;

    public SensorReciclagem(double raio) {
        super(raio);
        lixoX = -1;
        lixoY = -1;
        lixoZ = -1;
    }

    @Override
    public void monitorar(Robo robo) {

        // Verifica se o robo é do tipo RoboLimpeza
        if (!(robo instanceof RoboLimpeza)) {
            System.out.println("SensorReciclagem apenas para RoboLimpeza.");
            return;
        }

        System.out.println("🧹 " + robo.getNome() + " ativou o Sensor de Reciclagem.");

        // Obtendo o ambiente e a posição do robo
        Ambiente ambiente = robo.getAmbiente();
        int xRobo = robo.getPosicaoX();
        int yRobo = robo.getPosicaoY();
        int zRobo = robo.getAltitude();

        // Convertendo a variável raio para o número de quadrados em volta do robo
        int alcance = (int) Math.ceil(raio);
        boolean lixoEncontrado = false;        

        // Percorrer a lista de obstáculo dentro do Ambiente
        for (Obstaculo obst : ambiente.getObstaculos()) {

            // Verificar se o obstáculo é do tipo lixo e se o obstáculo ocupa a posição que estamos verificando
            if (obst instanceof Lixo && Sensor.dentroDoAlcance(obst.x1, obst.y1, obst.z1, xRobo, yRobo, zRobo)) {
                
                // Converter obstáculo obst em obstáculo do tipo lixo
                Lixo lixo = (Lixo) obst;
                System.out.println("🗑️ Lixo de tipo " + lixo.getTipoLixo() + " detectado nas coordenadas (" + obst.x1 + ", " + obst.y1 + ", " +obst.z1 + ")!");
                lixoX = obst.x1;
                lixoY = obst.y1;
                lixoZ =obst.z1;
                lixoEncontrado = true;
                break;
            }
        }
    
        // Se nenhum lixo foi encontrado, informar ao usuário
        if (!lixoEncontrado) {
            System.out.println("Nenhum lixo detectado dentro do raio.");
        }
    }

    public void classificarLixo(String tipo) {
        switch (tipo.toLowerCase()) {
            case "papel":
                System.out.println("- Papel: Direcionando para compactação.");
                break;
            case "plastico":
                System.out.println("- Plastico: Direcionando para reciclagem de plastico.");
                break;
            case "metal":
                System.out.println("- Metal: Direcionando para fundição.");
                break;
            case "organico":
                System.out.println("- Orgânico: Direcionando para compostagem.");
                break;
            case "vidro":
                System.out.println("- Vidro: Direcionando para reciclagem de vidro.");
                break;
            default:
                System.out.println("- Tipo de lixo desconhecido. Armazenar para análise manual.");
        }
    }

    // não precisa da função checkup pq ele já guarda a posição do lixo

    // Funções Getters
    public int getLixoX() {
        return lixoX;
    }

    public int getLixoY() {
        return lixoY;
    }

    public int getLixoZ() {
        return lixoZ;
    }

}


