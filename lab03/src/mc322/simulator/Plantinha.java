package mc322.simulator;

public class Plantinha extends Obstaculo {

    private String tipo;
    private double crescimento;
    private boolean saudavel;

    public Plantinha(int x1, int y1, int z1, int x2, int y2, int altura, String tipo) {
        super(x1, y1, z1, x2, y2, altura, TipoObstaculo.PLANTINHA);
        this.tipo = tipo;
        this.crescimento = 0.0;
        this.saudavel = true;
    }

    public void regar() {
        if (crescimento < 100) {
            crescimento = Math.min(100, crescimento + 10);
            System.out.println("🌱 " + tipo + " regada! Crescimento atual: " + crescimento + "%");
        }
    }

    public void tratar() {
        saudavel = true;
        System.out.println("🌱 " + tipo + " foi tratada e está saudável!");
    }

    public boolean podeColher() {
        return saudavel && crescimento >= 80;
    }

    public String getTipo() {
        return tipo;
    }

    public double getCrescimento() {
        return crescimento;
    }

    public boolean isSaudavel() {
        return saudavel;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCrescimento(double crescimento) {
        this.crescimento = crescimento;
    }

    public void setSaudavel(boolean saudavel) {
        this.saudavel = saudavel;
    }
}


