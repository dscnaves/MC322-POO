/*
* ForaDosLimitesException.java
* 
* Última modificação: 28/04/2025
* 
* Classe componente do Lab03 da disciplina MC322 - Programação Orientada a Objetos
* 
* Autores: Anita Almeida e Daniela Naves
*/

// Essa classe é responsável por lançar o erro caso a entidade esteja fora dos limites de ambiente
public class ForaDosLimitesException extends RuntimeException {
    public ForaDosLimitesException(String mensagem) {
        super("Fora dos limites: " + mensagem);
    }
}
