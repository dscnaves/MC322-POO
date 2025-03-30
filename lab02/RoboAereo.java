public class RoboAereo extends Robo {
    private int altitude;
    private int altitudeMaxima;

    public RoboAereo(String nome, int posicaoX, int posicaoY, String direcao, int altitudeMaxima, int altitude, Ambiente ambiente){
        super(nome, posicaoX, posicaoY, direcao, ambiente);
        this.altitudeMaxima = altitudeMaxima;
        
        // Define altitude inicial do robo aereo
        if (altitude >= 0 && altitude <= altitudeMaxima 
        && altitude <= ambiente.getAmbienteAltitude()) {
            this.altitude = altitude;
        } else {
            this.altitude = 0;
            System.out.println("Robo " + nome + " com altitude inicial invalida. Altitude inicial definida como 0.");
        }
    }

    public void subir(int metros){
        int newAltitude = altitude + metros;
        int limite = Math.min(altitudeMaxima, ambiente.getAmbienteAltitude());
        if (newAltitude <= limite){
            altitude = newAltitude;
            System.out.println(nome + " subiu " + metros + " metros. Altitude atual: " + altitude);
        } else {
            metros = limite - altitude;
            altitude = limite;
            System.out.println("Altura maxima do robo ou do ambiente excedida!");
            System.out.println(nome + " subiu " + metros + " metros. Altitude atual: " + altitude);
        }
    }

    public void descer(int metros) {
        int newAltitude = altitude - metros;
        if (newAltitude >= 0) {
            altitude = newAltitude;
            System.out.println(nome + " desceu " + metros + " metros. Altitude atual: " + altitude);
        } else {
            altitude = 0;
            System.out.println("Altura minima do ambiente excedida!");
            System.out.println(nome + " atingiu o solo");
        }
    }

    // Funções Getters e Setters
    public int getAltitude() { return altitude; }
    public int getAltitudeMaxima() { return altitudeMaxima; }

    public void setAltitude(int newAltitude){
        this.altitude = newAltitude;
    }
    public void setAltitudeMaxima(int newAltitudeMaxima){
        this.altitudeMaxima = newAltitudeMaxima;
    }
}
