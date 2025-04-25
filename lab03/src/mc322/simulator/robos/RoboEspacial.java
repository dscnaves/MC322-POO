package mc322.simulator.robos;

import mc322.simulator.Ambiente;
import mc322.simulator.SensorPortal;
import mc322.simulator.SensorPovoamento;
import mc322.simulator.TipoObstaculo;
import mc322.simulator.Sensor;

public class RoboEspacial extends RoboAereo {

    private int qtdePlanetasDescobertos;

    public RoboEspacial(String nome, int posicaoX, int posicaoY, String direcao, int altitudeMaxima, int altitude, Ambiente ambiente) {
        super(nome, posicaoX, posicaoY, direcao, altitudeMaxima, altitude, ambiente);
        this.qtdePlanetasDescobertos = 0;
        // Adicionar sensores ao RoboEspacial
        this.adicionarSensor(new SensorPortal(3.0));
        this.adicionarSensor(new SensorPovoamento(3.0));
    }

    @Override
    public boolean podeAtravessar(TipoObstaculo tipo) {
        if (tipo == TipoObstaculo.PORTAL) {
            return true;
        }
        return super.podeAtravessar(tipo);
    }

    public void nomearPlaneta(String nomePlaneta) {
        System.out.println("🌍 Planeta nomeado como: " + nomePlaneta);
        qtdePlanetasDescobertos++;
    }

    public int getQtdePlanetasDescobertos() {
        return qtdePlanetasDescobertos;
    }
}