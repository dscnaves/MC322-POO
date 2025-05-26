/*
* RoboRastreador.java
* 
* Última modificação: 28/04/2025
* 
* Classe componente do Lab03 da disciplina MC322 - Programação Orientada a Objetos
* 
* Autores: Anita Almeida e Daniela Naves
*/

/*
* Esta classe contém a estrutura de implementação de RoboRastreador
*/

public class RoboRastreador extends RoboTerrestre implements 
    Sensoreavel, Comunicavel, Diagnosticavel, Recarregavel, Autodesligavel {

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
        this.sensorMetal = new SensorMetal(7.0);

        // Adiciona o Sensor de Metal
        this.adicionarSensor(sensorMetal);
    }

    // Métodos para atualizar a localização do tesouro
    public void atualizarLocalizacaoTesouro(int x, int y, int z) {
        this.tesouroX = x;
        this.tesouroY = y;
        this.tesouroZ = z;
        System.out.println("Tesouro detectado nas coordenadas: (" + x + ", " + y + ", " + z + ")");
    }

    // Método para classificar o metal entre puro ou impiruro
    public void classificarMetal(Obstaculo obstaculo) {
        if (obstaculo == null) {
            System.out.println("Nenhum obstáculo para classificar.");
            return;
        }

        // Classificação do metal
        switch(obstaculo.getTipoObstaculo()) {
            case TESOURO:
                System.out.println("Tesouro identificado e capturado!");
                qtdeTesouro++;
                break;
            case LIXO:
                System.out.println("Metal lixo encontrado.");
                break;
            default:
                System.out.println("Objeto encontrado não é metal conhecido.");
        }
    }

    // Método para extrair tesouro do ambiente
    public void extrairTesouro(){
        Obstaculo obstaculo = sensorMetal.detectorTesouros(this);
        if (obstaculo != null){
            classificarMetal(obstaculo);
            ambiente.atualizarMapa(obstaculo.getX1(), obstaculo.getY1(), obstaculo.getZ1(), TipoEntidade.VAZIO);
            ambiente.removerEntidade(obstaculo);
            consumirBateria(10);
            desligarSeBateriaBaixa();
            tesouroX = -1; // Reseta a localização após extração
            tesouroY = -1;
            tesouroZ = -1;         
        } else {
            System.out.println("Não há tesouro para extrair.");
        }
    }

    // Método para verificar a quantidade de tesouros encontrados
    public void checkBauDeTesouros() {
        System.out.println("Tesouros encontrados até agora: " + qtdeTesouro);
    }

    public boolean temTesouroLocalizado() {
        return tesouroX != -1 && tesouroY != -1 && tesouroZ != -1;
    }

    // Implementação de Sensoreavel
    @Override
    public void acionarSensores() {
        System.out.println("Robo " + getId() + " acionando sensor de metais...");
        usarSensores();
        consumirBateria(10);
        desligarSeBateriaBaixa();
    }

    // Implementação de Comunicavel
    @Override
    public void enviarMensagem(Comunicavel destinatario, String mensagem) {
        System.out.println("Robo " + getId() + " enviando mensagem: '" + mensagem + "' para robo " + ((Robo) destinatario).getId());
    }

    @Override
    public void receberMensagem(String mensagem) {
        System.out.println("Robo " + getId() + " recebeu mensagem: '" + mensagem + "'");
    }

    // Implementação de Diagnosticavel
    @Override
    public void realizarDiagnostico() {
        System.out.println("Robo " + getId() + " realizando diagnóstico:");
        System.out.println("- Bateria: " + bateria + "%");
        System.out.println("- Tesouros encontrados: " + qtdeTesouro);
        System.out.println("- Sistema: " + estado);
    }

    // Implementação de Recarregavel
    @Override
    public void recarregar() {
        this.bateria = 100;
        System.out.println("Robo " + getId() + " recarregou totalmente.");
    }

    // Implementação de Autodesligavel
    @Override
    public void desligarSeBateriaBaixa() {
        if (bateria <= 10) {
            this.estado = EstadoRobo.DESLIGADO;
            System.out.println("Robo " + getId() + " está com bateria muito baixa e foi desligado automaticamente.");
        }
    }

    // Getters e Setters
    public int getQtdeTesouro() {
        return qtdeTesouro;
    }
    public void setQtdeTesouro(int qtdeTesouro) {
        this.qtdeTesouro = Math.max(qtdeTesouro, 0);
    }
}


