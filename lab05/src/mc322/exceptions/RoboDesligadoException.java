/*
* RoboDesligadoException.java
* 
* Última modificação: 01/06/2025
* 
* Classe componente do Lab04 da disciplina MC322 - Programação Orientada a Objetos
* 
* Autores: Anita Almeida e Daniela Naves
*/

package mc322.exceptions;

// Esta classe define uma exceção personalizada que é lançada quando um robô está desligado
public class RoboDesligadoException extends RuntimeException{
    
    // Construtor que recebe uma mensagem de erro
    public RoboDesligadoException(String mensagem) {
        super("Robo desligado: " + mensagem);
    }
}
