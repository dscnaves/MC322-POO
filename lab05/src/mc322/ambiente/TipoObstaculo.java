/*
* TipoObstaculo.java
* 
* Última modificação: 01/06/2025
* 
* Classe componente do Lab04 da disciplina MC322 - Programação Orientada a Objetos
* 
* Autores: Anita Almeida e Daniela Naves
*/

/*
* Esta classe contém a estrutura de implementação de um tipo de obstáculo.
*/

package mc322.ambiente;

public enum TipoObstaculo {
    PLANTINHA(2, true, "P"),
    LIXO(1, true, "L"),
    TESOURO(1, true, "T"),
    PORTAL(1, true, "O"), 
    ALIENIGENA(2, true, "A"),
    PLANETA(3, false, "W");

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


