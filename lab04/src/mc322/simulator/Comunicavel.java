/*
* Comunicavel.java
* 
* Última modificação: 28/04/2025
* 
* Classe componente do Lab03 da disciplina MC322 - Programação Orientada a Objetos
* 
* Autores: Anita Almeida e Daniela Naves
*/

// Esta classe define a interface Comunicavel, que permite que objetos se comuniquem entre si
public interface Comunicavel {
    
    /**
     * Método para enviar uma mensagem a outro objeto que implementa a interface Comunicavel
     */
    void enviarMensagem(Comunicavel destinatario, String mensagem) throws RoboDesligadoException;

    
    /**
     * Método para receber uma mensagem de outro objeto que implementa a interface Comunicavel
     */
    void receberMensagem(String mensagem) throws RoboDesligadoException;
}
