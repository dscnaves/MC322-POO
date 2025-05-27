/*
* Ambiente.java
* 
* Última modificação: 28/04/2025
* 
* Classe componente do Lab03 da disciplina MC322 - Programação Orientada a Objetos
* 
* Autores: Anita Almeida e Daniela Naves
*/

// Esta classe define a exceção ColisaoException, que é lançada quando dois objetos colidem
public class ColisaoException extends RuntimeException {
    public ColisaoException(String mensagem) {
        super(mensagem);
    }
}
