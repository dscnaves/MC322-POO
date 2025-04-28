public class RoboAgricultor extends RoboAereo {

    private String tipoPlantinha;
    private SensorColheita sensorColheita = new SensorColheita(7.0);
    private SensorSaude sensorSaude = new SensorSaude(8.0);
    private SensorIrrigacao sensorIrrigacao = new SensorIrrigacao(5.0);

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
                System.out.println("🌿 Plantinha " + plantinha.getEspecie() + " colhida com sucesso!");
                ambiente.getObstaculos().remove(plantinha);
                colheu = true;
            }
        }
        if (!colheu) {
            System.out.println("Nenhuma plantinha da especie " + tipoPlantinha + " pronta para colheita encontrada.");
        }
    }

    public String getTipoPlantinha() {
        return tipoPlantinha;
    }

    public void setTipoPlantinha(String tipoPlantinha) {
        this.tipoPlantinha = tipoPlantinha;
    }
}


