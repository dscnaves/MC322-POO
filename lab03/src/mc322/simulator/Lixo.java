package mc322.simulator;

public class Lixo extends Obstaculo {

    private String tipoLixo;

    public Lixo(int x1, int y1, int z1, int x2, int y2, int altura, String tipoLixo) {
        super(x1, y1, z1, x2, y2, altura, TipoObstaculo.LIXO);
        this.tipoLixo = tipoLixo;
    }

    public String getTipoLixo() {
        return tipoLixo;
    }

    public void setTipoLixo(String tipoLixo) {
        this.tipoLixo = tipoLixo;
    }
}


