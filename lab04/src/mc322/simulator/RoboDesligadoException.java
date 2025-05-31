/*
* RoboDesligadoException.java
* 
* Última modificação: 28/04/2025
* 
* Classe componente do Lab03 da disciplina MC322 - Programação Orientada a Objetos
* 
* Autores: Anita Almeida e Daniela Naves
*/

// Esta classe define uma exceção personalizada que é lançada quando um robô está desligado
public class RoboDesligadoException extends RuntimeException{
    
    // Construtor que recebe uma mensagem de erro
    public RoboDesligadoException(String mensagem) {
        super("Robo desligado: " + mensagem);
    }
}
