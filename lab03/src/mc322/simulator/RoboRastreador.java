public class RoboRastreador extends RoboTerrestre {

    // Atributos
    private int tesouroX;
    private int tesouroY;
    private int tesouroZ;
    private int qtdeTesouro;
    private SensorMetal sensorMetal = new SensorMetal(7.0);

    // Construtor
    public RoboRastreador(String nome, int posicaoX, int posicaoY, String direcao, int velocidadeMaxima, Ambiente ambiente) {
        super(nome, posicaoX, posicaoY, direcao, velocidadeMaxima, ambiente);
        this.tesouroX = -1;
        this.tesouroY = -1;
        this.tesouroZ = -1;
        this.qtdeTesouro = 0;

        // Adiciona o Sensor de Metal
        this.adicionarSensor(sensorMetal);
    }

    // Métodos para atualizar a localização do tesouro
    public void atualizarLocalizacaoTesouro(int x, int y, int z) {
        this.tesouroX = x;
        this.tesouroY = y;
        this.tesouroZ = z;
        System.out.println("📌 Tesouro detectado nas coordenadas: (" + x + ", " + y + ", " + z + ")");
    }

    // Método para classificar o metal entre puro ou impiruro
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

    // Método para extrair tesouro do ambiente
    public void extrairTesouro(){
        Obstaculo obstaculo = sensorMetal.detectorTesouros(this);
        if (obstaculo != null){
            classificarMetal(obstaculo);
            ambiente.atualizarMapa(obstaculo.getX1(), obstaculo.getY1(), obstaculo.getZ1(), '_');
            ambiente.removerObstaculo(obstaculo);            
        } else {
            System.out.println("❌ Não há tesouro para extrair.");
        }
    }

    // Método para verificar a quantidade de tesouros encontrados
    public void checkCriptomoeda() {
        System.out.println("💎 Tesouros encontrados até agora: " + qtdeTesouro);
    }

    // Getters e Setters
    public int getQtdeTesouro() {
        return qtdeTesouro;
    }
    public void setQtdeTesouro(int qtdeTesouro);
}


