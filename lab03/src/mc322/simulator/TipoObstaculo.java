package mc322.simulator;

public enum TipoObstaculo {
    PLANTINHA(2, true, "🌱"),
    LIXO(1, true, "🗑️"),
    TESOURO(1, true, "💰"),
    PORTAL(1, true, "🔀"), 
    ALIENIGENA(2, true, "👽"),
    PLANETA(3, false, "🌍");

    private final int alturaPadrao;
    private final boolean bloqueiaPassagem;
    private final String icone;

    private TipoObstaculo(int alturaPadrao, boolean bloqueiaPassagem, String icone) {
        this.alturaPadrao = alturaPadrao;
        this.bloqueiaPassagem = bloqueiaPassagem;
        this.icone = icone;
    }

    public int getAlturaPadrao() {
        return alturaPadrao;
    }

    public boolean isBloqueiaPassagem() {
        return bloqueiaPassagem;
    }

    public String getIcone() {
        return icone;
    }

    public boolean podeTranspor(int alturaRobo) {
        return !bloqueiaPassagem || alturaRobo > alturaPadrao;
    }
}


