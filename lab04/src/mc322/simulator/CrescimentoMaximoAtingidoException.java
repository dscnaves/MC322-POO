/*
* CrescimentoMaximoAtingidoException.java
* 
* Última modificação: 01/06/2025
* 
* Classe componente do Lab04 da disciplina MC322 - Programação Orientada a Objetos
* 
* Autores: Anita Almeida e Daniela Naves
*/

// Esta classe define uma exceção personalizada que é lançada quando uma plantinha atinge o crescimento máximo.

public class CrescimentoMaximoAtingidoException extends Exception {

    // Construtor da exceção
    public CrescimentoMaximoAtingidoException(String especie) {
        super("A plantinha " + especie + " já atingiu o crescimento máximo! Está pronta para colheita.");
    }
}
