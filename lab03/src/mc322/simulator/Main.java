/*
 * Main.java
 * 
 * Última modificação: 30/03/2025
 * 
 * Classe componente do Lab02 da disciplina MC322 - Programação Orientada a Objetos
 * 
 * Autores: Anita Almeida e Daniela Naves
 */

/*
 * Esta classe contém a estrutura de implementação da Main de um
 * simulador de robôs, responsável por testar e simular o 
 * funcionamento das demais classes envolvidas.
 */

package mc322.simulator;

public class Main{
    public static void main(String[] args){

        // Criação do objeto ambiente
        Ambiente ambiente = new Ambiente(10, 10, 50);
        
        // Adicionando obstáculos
        ambiente.adicionarObstaculo(new Obstaculo(3, 2));
        ambiente.adicionarObstaculo(new Obstaculo(4, 4));
        ambiente.adicionarObstaculo(new Obstaculo(5, 5));

        // Criando robôs
        RoboLimpeza wallE = new RoboLimpeza("Wall-E", 1, 1, "Norte", 3, 5, ambiente);
        RoboProfessor esther = new RoboProfessor("Esther", 2, 2, "Leste", 2, 3, ambiente);
        RoboAgricultor baymax = new RoboAgricultor("Baymax", 0, 0, "Sul", 30, 10, 90, ambiente);
        RoboCientista r2d2 = new RoboCientista("R2-D2", 4, 0, "Oeste", 100, 15, 'p', ambiente);

        // Adicionando os Robos na lista de Robos dentro de ambiente
        ambiente.adicionarRobo(wallE);
        ambiente.adicionarRobo(esther);
        ambiente.adicionarRobo(baymax);
        ambiente.adicionarRobo(r2d2);

        // Exibindo mapa inicial
        System.out.println("\n--- Mapa Inicial ---");
        ambiente.exibirMapa();
        System.out.println();

        // Movimentações
        wallE.mover(2, 1);  // deve parar se encontrar obstáculo
        esther.mover(0, 3);
        baymax.mover(-1, 0); // nao deve se mover por sair do ambiente
        r2d2.mover(0, 4);
        System.out.println();

        // Teste velocidade máxima do Wall-E (terrestre)
        wallE.mover(10, 0); // tentar mover mais do que sua velocidade permite
        System.out.println();

        // Teste de movimentos aéreos do Baymax (aéreo)
        baymax.subir(15); // Deve subir até altura = 25 (se altitude inicial = 10)
        baymax.subir(50); // Testa limite de ambiente (máximo = 50)
        baymax.descer(30); // Testa descida normal
        baymax.descer(100); // Testa descida abaixo do solo
        System.out.println();

        // Funções específicas de cada robô
        wallE.compactarLixo(4);  // dentro do limite
        System.out.println();
        wallE.compactarLixo(10); // ultrapassa o limite
        System.out.println();

        esther.darAula(2); // dentro do limite
        System.out.println();
        esther.darAula(5); // excede aulas
        System.out.println();

        baymax.fazerCheckup(2); // dor leve
        System.out.println();
        baymax.fazerCheckup(8); // dor intensa
        System.out.println();

        r2d2.decodificarMensagem("dpipnpopspspapuprpop"); // deve virar dinossauro
        System.out.println();

        // Checando posição e obstáculos ao redor
        wallE.identificarObstaculo();
        esther.identificarObstaculo();
        baymax.identificarObstaculo();
        r2d2.identificarObstaculo();

        // Exibir posições finais
        System.out.println("\n--- Posições Finais ---");
        wallE.exibirPosicao();
        esther.exibirPosicao();
        baymax.exibirPosicao();
        r2d2.exibirPosicao();

        // Exibir mapa final
        System.out.println("\n--- Mapa Final ---");
        ambiente.exibirMapa();
    }

}