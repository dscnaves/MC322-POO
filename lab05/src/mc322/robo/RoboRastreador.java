/*
* RoboRastreador.java
* 
* Última modificação: 01/06/2025
* 
* Classe componente do Lab04 da disciplina MC322 - Programação Orientada a Objetos
* 
* Autores: Anita Almeida e Daniela Naves
*/

/*
* Esta classe contém a estrutura de implementação de RoboRastreador
*/

package mc322.robo;

import mc322.ambiente.Ambiente;
import mc322.ambiente.Obstaculo;
import mc322.ambiente.TipoEntidade;
import mc322.interfaces.Autodesligavel;
import mc322.interfaces.Comunicavel;
import mc322.interfaces.Diagnosticavel;
import mc322.interfaces.Recarregavel;
import mc322.interfaces.Sensoreavel;
import mc322.sensores.SensorMetal;

public class RoboRastreador extends AgenteInteligente implements 
    Sensoreavel, Comunicavel, Diagnosticavel, Recarregavel, Autodesligavel {

    // Atributos
    private int tesouroX;
    private int tesouroY;
    private int tesouroZ;
    private int qtdeTesouro;
    private SensorMetal sensorMetal = new SensorMetal(7.0);

    // Construtor
    public RoboRastreador(String id, int posicaoX, int posicaoY, String direcao, int velocidadeMaxima, Ambiente ambiente) {
        super(id, posicaoX, posicaoY, direcao, velocidadeMaxima, ambiente);
        this.tesouroX = -1;
        this.tesouroY = -1;
        this.tesouroZ = -1;
        this.qtdeTesouro = 0;
        this.sensorMetal = new SensorMetal(7.0);

        // Adiciona o Sensor de Metal
        this.adicionarSensor(sensorMetal);
    }

     @Override
    // Método para funções bases do RoboRastreador
    public void executarTarefa(){
        Obstaculo obstaculo = sensorMetal.detectorTesouros(this);
        if (obstaculo != null) {
            classificarMetal(obstaculo);
            extrairTesouro();
        } else {
            System.out.println("Nenhum tesouro encontrado no ambiente.");
        }
    }

    @Override
    public void executarMissao(Ambiente a) {
        if (temMissao()) {
            System.out.println("RoboRastreador executando missão autônoma...");
            missao.executar(this, a);
        } else {
            System.out.println("Nenhuma missão atribuída a " + getId());
        }
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
            System.out.println("Nenhum metal precioso encontrado no raio de alcance do sensor.");
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
                // Enviar mensagem para Central de comunicação que informará o RoboLimpeza para classificar o lixo
                System.out.println("Enviando mensagem para RoboLimpeza para classificar o lixo localizado na posição (" + obstaculo.getX1() + ", " + obstaculo.getY1() + ", " + obstaculo.getZ1() + ")");
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

    @Override
    // Essa função é chamada quando o sensor de metal é acionado e detecta um objeto de metal gastando 10% da bateria
    public void acionarSensores() {
        System.out.println("Robo " + getId() + " acionando sensor de metais...");
        usarSensores();
        consumirBateria(10);
        desligarSeBateriaBaixa();
    }

    @Override
    // Essa função é chamada para enviar uma mensagem para outro robô
    public void enviarMensagem(Comunicavel destinatario, String mensagem) {
        System.out.println("Robo " + getId() + " enviando mensagem: '" + mensagem + "' para robo " + ((Robo) destinatario).getId());
    }

    @Override
    /// Essa função é chamada quando um robô recebe uma mensagem
    public void receberMensagem(String mensagem) {
        System.out.println("Robo " + getId() + " recebeu mensagem: '" + mensagem + "'");
    }

    @Override
    // Método para realizar diagnóstico do robo incluindo a bateria, o estado do sistema e a quantidade de tesouros encontrados
    public void realizarDiagnostico() {
        System.out.println("Robo " + getId() + " realizando diagnóstico:");
        System.out.println("- Bateria: " + bateria + "%");
        System.out.println("- Tesouros encontrados: " + qtdeTesouro);
        System.out.println("- Sistema: " + estado);
    }

    @Override
    // Método para recarregar a bateria do robô
    public void recarregar() {
        this.bateria = 100;
        System.out.println("Robo " + getId() + " recarregou totalmente.");
    }

    @Override
    // Método para desligar o robô se a bateria estiver baixa
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


