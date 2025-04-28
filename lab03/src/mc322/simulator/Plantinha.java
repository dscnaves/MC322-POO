public class Plantinha extends Obstaculo {

    private String especie;
    private double crescimento;
    private boolean saudavel;

    public Plantinha(int x1, int y1, int z1, int x2, int y2, int altura, String tipo) {
        super(x1, y1, z1, x2, y2, altura, TipoObstaculo.PLANTINHA);
        this.especie = especie;
        this.crescimento = 0.0;
        this.saudavel = true;
    }

    public void regar() {
        if (crescimento < 100) {
            crescimento = Math.min(100, crescimento + 10);
            System.out.println("🌱 " + especie + " regada! Crescimento atual: " + crescimento + "%");
        }
    }

    public void tratar() {
        saudavel = true;
        System.out.println("🌱 " + especie + " foi tratada e está saudável!");
    }

    public boolean podeColher() {
        return saudavel && crescimento >= 80;
    }

    public String getEspecie() {
        return especie;
    }

    public double getCrescimento() {
        return crescimento;
    }

    public boolean isSaudavel() {
        return saudavel;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setCrescimento(double crescimento) {
        this.crescimento = crescimento;
    }

    public void setSaudavel(boolean saudavel) {
        this.saudavel = saudavel;
    }
}


