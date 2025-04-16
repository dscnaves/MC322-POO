package mc322.simulator;

public class TipoObstaculo {
    PAREDE(10,true,"▓▓"),
    AREA_MOVEDIÇA(0,false),
    APPLE_TREE(5,true,"🌳"),
    //PESSOA_ENFERMA(2,true),   // (?) Vamos colocar isso mesmo? E com esse nome?
    PORTAL_TELETRANSPORTE(3,false,"🕳"),
    OUTRO(-1, false, "�?");

    private final int alturaPadrao;
    private final boolean bloqueiaPassagem;
    private final String icone;

    private TipoObstaculo(int alturaPadrao, boolean bloqueiaPassagem, String icone) {
        this.alturaPadrao = alturaPadrao;
        this.bloqueiaPassagem = bloqueiaPassagem;
        this.icone = icone;
    }

    // Método para verificar se o Robo pode passar através do obstáculo
    public boolean podeTranspor(int alturaAgente) {
        return !bloqueiaPassagem || alturaAgente > alturaPadrao;
    }

    public int getAlturaPadrao() {return alturaPadrao;}
    public boolean getBloqueiaPassagem() {return bloqueiaPassagem;}
}
