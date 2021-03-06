
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mat
 */
public class Computador{
    
    char[] alfabeto;
    Random seed;
    
    /**
     * Cria o computador
     */
    public Computador()
    {
        alfabeto = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        seed = new Random();
    }
    
    /**
     * 
     * @param palavra Palavra que o computador está jogando
     * @param i Index da letra na palavra que o computador esta jogando
     * @param nvl Dificuldade selecionada para ser jogada
     * @return Letra a ser jogada pelo computador de acordo com a dificuldade escolhida
     */
    public char jogadaComputador(String palavra, int i, int nvl)
    {
        if(nvl == 0)
            return jogadaFacil();
        else if (nvl == 1)
            return jogadaMedia(palavra);
        else if (nvl == 2)
            return jogadaDificil(palavra, i);
        else
            return '-';
    }
    
    /**
     * 
     * @return Letra a ser jogada pelo computador
     */
    public char jogadaFacil()
    {
        return alfabeto[seed.nextInt(alfabeto.length)];
    }
    
    /**
     * 
     * @param palavra Palavra a ser jogada
     * @return Letra a ser jogada pelo computador
     */
    public char jogadaMedia(String palavra)
    {
        return palavra.toCharArray()[seed.nextInt(palavra.length())];
    }
    
    /**
     * 
     * @param palavra Palavra a ser jogada
     * @param i Index da letra na palavra a ser jogada
     * @return Letra a ser jogada pelo computador
     */
    public char jogadaDificil(String palavra, int i)
    {
        int chance = seed.nextInt(2);
        
        if(chance == 0)
            return jogadaMedia(palavra);
        else
            return palavra.toCharArray()[i];
    }
    
}
