/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mat
 */
public class Jogador {
 
    private String nome;
    private int[] record = new int[4]; //0 - vs Jogador
                                       //1 - vs Facil
                                       //2 - vs Medio
                                       //3 - vs Dificil
    
    private int[] jogos = new int[4]; //0 - vs Jogador
                                      //1 - vs Facil
                                      //2 - vs Medio
                                      //3 - vs Dificil
    
    public Jogador(String nome)
    {
        this.nome = nome;
        record[0] = 0;
        record[1] = 0;
        record[2] = 0;
        record[3] = 0;
    }
    
    public void addGame(int tipo)
    {
        jogos[tipo]++;
    }
    
    public void addPonto(int tipo)
    {
        record[tipo]++;
    }
    
    public int getRecord(int tipo)
    {
        return record[tipo];
    }
    
    public int getJogos(int tipo)
    {
        return jogos[tipo];
    }
    
}
