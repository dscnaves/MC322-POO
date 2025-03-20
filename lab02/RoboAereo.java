public class RoboAereo extends Robo {
    private int altitude;
    private int altitudeMaxima;

    public Robo Aereo(String nome, int posicaoX, int posicaoY, int direcao, int altitudeMaxima, int altitude){
        super(nome, posicaoX, posicaoY, direcao);
        this.altitudeMaxima = altitudeMaxima;
        this.altitude = 0;
    }

    public void subir(int metros){
        if (altitude + metros <= altitudeMaxima){
            altitude += metros;
            System.out.println("Robô subiu " + metros + " metros. Altitude atual: " + altitude);
        } else {
            System.out.println("Altura máxima excedida!");
        }
    }

    public void descer(int metros) {
        if (altitude - metros >= 0) {
            altitude -= metros;
            System.out.println("Robô desceu " + metros + " metros. Altitude atual: " + altitude);
        } else {
            System.out.println("Altura não pode ser negativa!");
        }
    }

    public int getAltitude() {
        return this.altitude;
    }
}
