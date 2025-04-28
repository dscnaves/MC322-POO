public class RoboRastreador extends RoboTerrestre {

    private int tesouroX;
    private int tesouroY;
    private int tesouroZ;
    private int qtdeTesouro;
    private SensorMetal sensorMetal = new SensorMetal(3.0);

    public RoboRastreador(String nome, int posicaoX, int posicaoY, String direcao, int velocidadeMaxima, Ambiente ambiente) {
        super(nome, posicaoX, posicaoY, direcao, velocidadeMaxima, ambiente);
        this.tesouroX = -1;
        this.tesouroY = -1;
        this.tesouroZ = -1;
        this.qtdeTesouro = 0;

        // Adiciona o Sensor de Metal
        this.adicionarSensor(sensorMetal);
    }

    public void atualizarLocalizacaoTesouro(int x, int y, int z) {
        this.tesouroX = x;
        this.tesouroY = y;
        this.tesouroZ = z;
        System.out.println("📌 Tesouro detectado nas coordenadas: (" + x + ", " + y + ", " + z + ")");
    }

    public void classificarMetal(Obstaculo obstaculo) {
        if (obstaculo.getTipo() == TipoObstaculo.TESOURO) {
            System.out.println("💰 Tesouro identificado e capturado!");
            qtdeTesouro++;
        } else if (obstaculo.getTipo() == TipoObstaculo.LIXO) {
            System.out.println("🗑️ Metal lixo encontrado.");
        } else {
            System.out.println("❌ Objeto encontrado não é metal conhecido.");
        }
    }

    public void checkCriptomoeda() {
        System.out.println("💎 Tesouros encontrados até agora: " + qtdeTesouro);
    }

    public int getQtdeTesouro() {
        return qtdeTesouro;
    }
}


