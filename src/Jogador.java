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
    
    /**
     * Inicializa as variaveis
     * 
     * @param nome Nome do Jogador
     */
    public Jogador(String nome)
    {
        this.nome = nome;
        record[0] = 0;
        record[1] = 0;
        record[2] = 0;
        record[3] = 0;
    }
    
    /**
     * Adiciona uma partida jogada pelo jogador em um dado tipo de partida
     * 
     * @param tipo Tipo de partida sendo jogada<br>
     *             [0] - vs Jogador<br>
     *             [1] - vs Facil<br>
     *             [2] - vs Medio<br>
     *             [3] - vs Dificil<br>
     */
    public void addGame(int tipo)
    {
        jogos[tipo]++;
    }
    
    /**
     * Adiciona a pontuação do jogador em um dado tipo de partida
     * 
     * @param tipo Tipo de partida sendo jogada<br>
     *             [0] - vs Jogador<br>
     *             [1] - vs Facil<br>
     *             [2] - vs Medio<br>
     *             [3] - vs Dificil<br>
     */
    public void addPonto(int tipo)
    {
        record[tipo]++;
    }
    
    /**
     * @param tipo Tipo de partida sendo jogada<br>
     *             [0] - vs Jogador<br>
     *             [1] - vs Facil<br>
     *             [2] - vs Medio<br>
     *             [3] - vs Dificil<br>
     * @return Quantidade de pontos do jogador em um dado tipo de jogo
     */
    public int getRecord(int tipo)
    {
        return record[tipo];
    }
    
    /**
     * @param tipo Tipo de partida sendo jogada<br>
     *             [0] - vs Jogador<br>
     *             [1] - vs Facil<br>
     *             [2] - vs Medio<br>
     *             [3] - vs Dificil<br>
     * @return Quantidade de partidas jogadas pelo jogador em um dado tipo de jogo
     */
    public int getJogos(int tipo)
    {
        return jogos[tipo];
    }
    
}
