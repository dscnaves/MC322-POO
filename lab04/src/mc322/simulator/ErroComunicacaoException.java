/*
* ErroComunicacaoException.java
* 
* Última modificação: 28/04/2025
* 
* Classe componente do Lab03 da disciplina MC322 - Programação Orientada a Objetos
* 
* Autores: Anita Almeida e Daniela Naves
*/

// que estende RuntimeException (uma exceção de tempo de execução)
public class ErroComunicacaoException extends RuntimeException {

    // Construtor da classe que recebe uma mensagem de erro
    public ErroComunicacaoException(String mensagem) {

        /*
        Chama o construtor da superclasse (RuntimeException)
        e passa a mensagem, que pode ser acessada com getMessage()
        */
        super(mensagem);
    }
}

