package mc322.sistemas;

import java.util.ArrayList;
import mc322.robo.Robo;
import mc322.sensores.Sensor;
import mc322.exceptions.RoboDesligadoException;

public class GerenciadorSensores {
    
    private ArrayList<Sensor> sensores;

    public GerenciadorSensores() {
        this.sensores = new ArrayList<>();
    }

    public void adicionarSensor(Sensor s) {
        sensores.add(s);
    }

    public void usarSensores(Robo robo) {
        if (robo.getEstado() != Robo.EstadoRobo.LIGADO) {
            throw new RoboDesligadoException("Robo " + robo.getId() + " está desligado e não pode usar sensores.");
        }

        for (Sensor s : sensores) {
            s.monitorar(robo);
        }
    }

    public ArrayList<Sensor> getSensores() {
        return sensores;
    }
}

