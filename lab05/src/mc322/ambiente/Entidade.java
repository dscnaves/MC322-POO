/*
* Entidade.java
* 
* Última modificação: 01/06/2025
* 
* Classe componente do Lab04 da disciplina MC322 - Programação Orientada a Objetos
* 
* Autores: Anita Almeida e Daniela Naves
*/

package mc322.ambiente;

// Interface a qual representará todos os atributo padrões de todos os elementos dentro de ambiente
public interface Entidade {
    int getX();
    int getY();
    int getZ();
    TipoEntidade getTipo();
    String getDescricao();
    char getRepresentacao();
}
