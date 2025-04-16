package mc322.simulator;
import mc322.simulator.robos.Robo;

public abstract class Sensor {
    protected double raio;

    public Sensor(double raio) {
        this.raio = raio;
    }

    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) {
        this.raio = raio;
    }

    public abstract void monitorar(Robo robo);
}
