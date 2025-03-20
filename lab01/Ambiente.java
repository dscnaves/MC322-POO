public class Ambiente{
        //Atributoos necessários para definir a classe Ambiente
    private int largura;
    private int altura;

    // Método construtor para inicialização dos atributos da classe Ambiente
    public Ambiente(int altura, int largura){
        this.largura = largura;
        this.altura = altura;
    }

    // Função responsável por verificar se a posição de destino do robo está dentro dos limites do ambiente
    public boolean dentroDosLimites(int x, int y){
        return ((x>=0 && x<=largura) && (y>=0 && y<=altura));
    }
    
    // Funções Getters e Setting
    public void setAmbiente(int newLargura, int newAltura){
        this.largura = newLargura;
        this.altura = newAltura;
    }

    public int getAmbienteLargura(){
        return largura;
    }

    public int getAmbienteAltura(){
        return altura;
    }

}