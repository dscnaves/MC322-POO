public class RoboAereo extends Robo {
    private int altitude;
    private int altitudeMaxima;

    public Robo Aereo(String nome, int posicaoX, int posicaoY, int direcao, int altitudeMaxima, int altitude, Ambiente ambiente){
        super(nome, posicaoX, posicaoY, direcao, ambiente);
        this.altitudeMaxima = altitudeMaxima;
        this.altitude = 0;
    }

    public void subir(int metros){
        if (altitude + metros <= altitudeMaxima){
            altitude += metros;
            System.out.println(nome + " subiu " + metros + " metros. Altitude atual: " + altitude);
        } else {
            altitude = altitudeMaxima;
            System.out.println("Altura máxima excedida!");
            System.out.println(nome + " subiu " + metros + " metros. Altitude atual: " + altitude);
        }
    }

    public void descer(int metros) {
        if (altitude - metros >= 0) {
            altitude -= metros;
            System.out.println(nome + " desceu " + metros + " metros. Altitude atual: " + altitude);
        } else {
            altitude = 0;
            System.out.println("Altura não pode ser negativa!");
            System.out.println(nome + " atingiu o solo");
        }
    }

    public int getAltitude() { return altitude; }
    public int getAltitudeMaxima() { return altitudeMaxima; }

    public void setAltitude(int newAltitude){
        this.altitude = newAltitude;
    }
    public void setAltitudeMaxima(int newAltitudeMaxima){
        this.altitudeMaxima = newAltitudeMaxima;
    }
}
