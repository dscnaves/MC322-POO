package mc322.simulator;

public class Planeta extends Obstaculo {

    private String nome;

    public Planeta(int x1, int y1, int z1, int x2, int y2, int altura) {
        super(x1, y1, z1, x2, y2, altura, TipoObstaculo.PLANETA);
        this.nome = ""; // Começa sem nome
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}


