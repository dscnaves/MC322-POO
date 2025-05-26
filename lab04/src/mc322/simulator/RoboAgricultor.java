/*
* RoboAgricultor.java
* 
* Última modificação: 28/04/2025
* 
* Classe componente do Lab03 da disciplina MC322 - Programação Orientada a Objetos
* 
* Autores: Anita Almeida e Daniela Naves
*/

/*
* Esta classe contém a estrutura de implementação de RoboAgricultor
* o RoboAgricultor é um robô aéreo que cuida de plantinhas.
*/

public class RoboAgricultor extends RoboAereo implements 
    Sensoreavel, Comunicavel, Diagnosticavel, Recarregavel {

    // Atributos específicos do RoboAgricultor
    private String tipoPlantinha;
    private SensorColheita sensorColheita = new SensorColheita(7.0);
    private SensorSaude sensorSaude = new SensorSaude(8.0);
    private SensorIrrigacao sensorIrrigacao = new SensorIrrigacao(5.0);

    // Construtor
    public RoboAgricultor(String nome, int posicaoX, int posicaoY, String direcao, int altitudeMaxima, int altitude, String tipoPlantinha, Ambiente ambiente) {
        super(nome, posicaoX, posicaoY, direcao, altitudeMaxima, altitude, ambiente);
        this.tipoPlantinha = tipoPlantinha;
        this.adicionarSensor(sensorColheita);
        this.adicionarSensor(sensorSaude);
        this.adicionarSensor(sensorIrrigacao);
    }

    // Método para regar uma plantinha
    public void regarPlantinha() {
        boolean regou = false;
        
        // Obtém plantinha pelo sensor de colheita
        Plantinha plantinha = sensorIrrigacao.checarIrrigacao(this);

        // Verifica se encontrou uma plantinha pronta para irrigar
        if (plantinha != null){
            // Verifica se a plantinha é do tipo que o agricultor cuida
            if(plantinha.getEspecie() == tipoPlantinha){
                plantinha.regar();
                regou = true;
                consumirBateria(10);
            } 
        }
        if (!regou) {
            System.out.println("Nenhuma plantinha da especie" + tipoPlantinha + " encontrada para regar.");
        }
    }

    // Método para tratar uma plantinha doente
    public void tratarPlantaDoente() {
        boolean tratou = false;
        
        // Obtém plantinha pelo sensor de colheita
        Plantinha plantinha = sensorSaude.checkup(this);

        // Verifica se encontrou uma plantinha pronta para colheita
        if (plantinha != null){
            // Verifica se a plantinha é do tipo que o agricultor cuida
            if(plantinha.getEspecie() == tipoPlantinha){
                
                // Trata a plantinha se ela estiver doente
                if (!plantinha.isSaudavel()) {
                    plantinha.tratar();
                    tratou = true;
                    consumirBateria(10);
                }
            }
        }
        
        // Verifica se alguma plantinha foi tratada
        if (!tratou) {
            System.out.println("Nenhuma plantinha doente da especie " + tipoPlantinha + " encontrada para tratar.");
        }
    }

    // Método para colher uma plantinha pronta para colheita
    public void colherPlantinha() {
        boolean colheu = false;

        // Obtém plantinha pelo sensor de colheita
        Plantinha plantinha = sensorColheita.checarColheita(this);

        // Verifica se encontrou uma plantinha pronta para colheita
        if (plantinha != null){

            // Verifica se a plantinha é do tipo que o agricultor cuida
            if (plantinha.getEspecie() == tipoPlantinha) {
                System.out.println("Plantinha " + plantinha.getEspecie() + " colhida com sucesso!");
                ambiente.removerEntidade(plantinha);
                ambiente.atualizarMapa(plantinha.getX1(), plantinha.getY1(), plantinha.getZ1(), TipoEntidade.VAZIO);
                colheu = true;
                consumirBateria(10);
            }
        }
        if (!colheu) {
            System.out.println("Nenhuma plantinha da especie " + tipoPlantinha + " pronta para colheita encontrada.");
        }
    }

    // Implementação de Sensoreavel
    @Override
    public void acionarSensores() {
        System.out.println("Robo " + getId() + " acionando sensores agrícolas...");
        usarSensores();
        consumirBateria(10);
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
        System.out.println("- Sistema: " + estado);
        System.out.println("- Especialidade: Plantas do tipo " + tipoPlantinha);
    }

    // Implementação de Recarregavel
    @Override
    public void recarregar() {
        this.bateria = 100;
        System.out.println("Robo " + getId() + " recarregou totalmente.");
    }

    // Getters e Setters
    public String getTipoPlantinha() {
        return tipoPlantinha;
    }

    public void setTipoPlantinha(String tipoPlantinha) {
        this.tipoPlantinha = tipoPlantinha;
    }
}


