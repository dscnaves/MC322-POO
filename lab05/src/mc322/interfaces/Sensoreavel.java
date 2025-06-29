/*
* Sensoreavel.java
* 
* Última modificação: 01/06/2025
* 
* Classe componente do Lab04 da disciplina MC322 - Programação Orientada a Objetos
* 
* Autores: Anita Almeida e Daniela Naves
*/

package mc322.interfaces;

import mc322.exceptions.RoboDesligadoException;

public interface Sensoreavel {
    void acionarSensores() throws RoboDesligadoException;
}
