/*
* TipoObstaculo.java
* 
* Última modificação: 28/04/2025
* 
* Classe componente do Lab03 da disciplina MC322 - Programação Orientada a Objetos
* 
* Autores: Anita Almeida e Daniela Naves
*/

/*
* Esta classe contém a estrutura de implementação de um tipo de obstáculo.
*/

public enum TipoObstaculo {
    PLANTINHA(2, true, "🌱"),
    LIXO(1, true, "🗑️"),
    TESOURO(1, true, "💰"),
    PORTAL(1, true, "🔀"), 
    ALIENIGENA(2, true, "👽"),
    PLANETA(3, false, "🌍");

    // Atributos
    private final int alturaPadrao;
    private final boolean bloqueiaPassagem;
    private final String icone;

    // Construtor
    private TipoObstaculo(int alturaPadrao, boolean bloqueiaPassagem, String icone) {
        this.alturaPadrao = alturaPadrao;
        this.bloqueiaPassagem = bloqueiaPassagem;
        this.icone = icone;
    }

    // Getters e Setters
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


