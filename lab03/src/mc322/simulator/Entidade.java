public interface Entidade {
    // Métodos para obter as coordenadas das entidades
    int getX1();			
    int getY1();
    int getZ1();
    int getX2();			
    int getY2();
    int getZ2();

    // Métodos para obter o tipo e a representação das entidades
    TipoEntidade getTipo();
    char getRepresentacao();
}
