package mc322.simulator;
import mc322.simulator.robos.Robo;

public abstract class Sensor {
    protected double raio;

    public Sensor(double raio) {
        this.raio = raio;
    }

    public abstract void monitorar(Robo robo);

    // Método para verificar alcance de utilização do sensor
    public boolean dentroDoAlcance(int xLixo, int yLixo, int zLixo, int xRobo, int yRobo, int zRobo){
        
        // Obtendo o ambiente e a posição do robo
        Ambiente ambiente = robo.getAmbiente();
        int xRobo = robo.getPosicaoX();
        int yRobo = robo.getPosicaoY();
        int zRobo = robo.getAltitude();

        // Convertendo a variável raio para o número de quadrados em volta do robo
        int alcance = (int) Math.ceil(raio);

        // Verifica se o ponto (xLixo, yLixo, zLixo) está dentro do alcance do sensor
        if(xLixo >= xRobo - alcance && xLixo <= xRobo + alcance &&
           yLixo >= yRobo - alcance && yLixo <= yRobo + alcance &&
           zLixo >= zRobo - alcance && zLixo <= zRobo + alcance){
            return true;
        } else {
            return false;
        }
    }

    // Funções Getters e Setters
    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) {
        this.raio = raio;
    }
}
