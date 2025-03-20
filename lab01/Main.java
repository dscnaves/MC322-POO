public class Main{
    public static void main(String[] args){
        // Criação do objeto ambiente
        Ambiente ambiente = new Ambiente(50,50);

        // Criação dos objetos robos que "habitarão" o objeto ambiente
        Robo robo_merida = new Robo("Merida", 27, 6);
        Robo robo_mulan = new Robo("Mulan", 18, 6);
        Robo robo_tiana = new Robo("Tiana", 1, 3);

        //Exibir posição atual dos objetos robos inicializados
        robo_merida.exibirPosicao();
        robo_mulan.exibirPosicao();
        robo_tiana.exibirPosicao();
        
        // Testando a função mover da classe robo
        int deltaX = 5, deltaY = 7;
        int newx = robo_merida.getPosicaoX() + deltaX;
        int newy = robo_merida.getPosicaoY() + deltaY;

        System.out.println();

        if(ambiente.dentroDosLimites(newx, newy)){
            System.out.println("Movimento valido!");
            robo_merida.mover(deltaX, deltaY);
            robo_merida.exibirPosicao();
            System.out.println();
        }
        else{
            System.out.println("Movimento invalido!");
            System.out.println();
        }

        deltaX = 8;
        deltaY = 2;
        newx = robo_tiana.getPosicaoX() + deltaX;
        newy = robo_tiana.getPosicaoY() + deltaY;
    
        if(ambiente.dentroDosLimites(newx, newy)){
            System.out.println("Movimento valido!");
            robo_tiana.mover(deltaX, deltaY);
            robo_tiana.exibirPosicao();
            System.out.println();
        }
        else{
            System.out.println("Movimento invalido!");
            System.out.println();
        }

        deltaX = 80;
        deltaY = 2;
        newx = robo_mulan.getPosicaoX() + deltaX;
        newy = robo_mulan.getPosicaoY() + deltaY;
    
        if(ambiente.dentroDosLimites(newx, newy)){
            System.out.println("Movimento valido!");
            robo_mulan.mover(deltaX, deltaY);
            robo_mulan.exibirPosicao();
            System.out.println();
        }
        else{
            System.out.println("Movimento invalido!");
            System.out.println();
        }
    }
}