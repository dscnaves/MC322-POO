package mc322.simulator.robos;

import mc322.simulator.Ambiente;
import mc322.simulator.Obstaculo;
import mc322.simulator.Plantinha;
import mc322.simulator.SensorColheita;
import mc322.simulator.SensorSaude;

public class RoboAgricultor extends RoboAereo {

    private String tipoPlantinha;

    public RoboAgricultor(String nome, int posicaoX, int posicaoY, String direcao, int altitudeMaxima, int altitude, String tipoPlantinha, Ambiente ambiente) {
        super(nome, posicaoX, posicaoY, direcao, altitudeMaxima, altitude, ambiente);
        this.tipoPlantinha = tipoPlantinha;
        this.adicionarSensor(new SensorColheita(3.0));
        this.adicionarSensor(new SensorSaude(3.0));
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
                ambiente.getObstaculos().remove(obst);
                colheu = true;
                break;
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


