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

    public void regarPlantinha() {
        boolean regou = false;
        for (Obstaculo obst : ambiente.getObstaculos()) {
            if (obst instanceof Plantinha && obst.contemPonto(posicaoX, posicaoY, altitude)) {
                ((Plantinha) obst).regar();
                regou = true;
            }
        }
        if (!regou) {
            System.out.println("Nenhuma plantinha encontrada para regar.");
        }
    }

    public void tratarPlantaDoente() {
        boolean tratou = false;
        for (Obstaculo obst : ambiente.getObstaculos()) {
            if (obst instanceof Plantinha && obst.contemPonto(posicaoX, posicaoY, altitude)) {
                Plantinha plantinha = (Plantinha) obst;
                if (!plantinha.isSaudavel()) {
                    plantinha.tratar();
                    tratou = true;
                }
            }
        }
        if (!tratou) {
            System.out.println("Nenhuma plantinha doente encontrada para tratar.");
        }
    }

    public void colherPlantinha() {
        boolean colheu = false;
        for (Obstaculo obst : ambiente.getObstaculos()) {
            if (obst instanceof Plantinha && obst.contemPonto(posicaoX, posicaoY, altitude)) {
                Plantinha plantinha = (Plantinha) obst;
                if (plantinha.podeColher()) {
                    System.out.println("🌿 Plantinha " + plantinha.getTipo() + " colhida com sucesso!");
                    ambiente.getObstaculos().remove(obst);
                    colheu = true;
                    break;
                }
            }
        }
        if (!colheu) {
            System.out.println("Nenhuma plantinha pronta para colheita encontrada.");
        }
    }

    public String getTipoPlantinha() {
        return tipoPlantinha;
    }

    public void setTipoPlantinha(String tipoPlantinha) {
        this.tipoPlantinha = tipoPlantinha;
    }
}


