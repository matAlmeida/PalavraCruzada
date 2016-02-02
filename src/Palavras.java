/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mat
 */
public class Palavras {
    private static final String nomeArq = "dicionarioPalCruz.txt";
    private int tamArq = 0;
    private String[] linha = null;
    
    /**
     * Copia o dicionario de palavras para um vetor
     * 
     */
    public Palavras()
    {
        FileReader arquivo1 = null;          
        try {
            //////////////////////////////////////////////////////////
            arquivo1 = new FileReader(nomeArq); //
            BufferedReader buffer1 = new BufferedReader(arquivo1);  //
            String linha1 = buffer1.readLine();                     //
            int j = 0;                                              //
            while(linha1 != null)                                   //PEGA A QNT
            {                                                       //DE LINHAS
                j++;                                                //NO ARQUIVO
                linha1 = buffer1.readLine();                        //
            }                                                       //
            buffer1.close();                                        //
            arquivo1.close();                                       //
            tamArq = j;                                             //
            //////////////////////////////////////////////////////////
            FileReader arquivo = new FileReader(nomeArq);
            BufferedReader buffer = new BufferedReader(arquivo);
            this.linha = new String[tamArq];
            for(int i = 0; i < tamArq; i++)
                this.linha[i] = buffer.readLine();
            buffer.close();
            arquivo.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Palavras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Palavras.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public int getTamArq()
    {
        return tamArq;
    }
    
    /**
     * Sorteia a primeira palavra que sera a palavra tronco
     * 
     * @return 
     */
    public String[] sorteiaPalavra()
    {
        Random seed = new Random();
        String[] linhas = new String[2];
        linhas[1] = "";
        
        while(linhas[1].length() < 5)
            linhas = linha[seed.nextInt(tamArq)].split(": ");
        
        return linhas;
    }
    
    /**
     * Sorteia a palavra que contenha tal letra
     * 
     * NO VETOR DE STRING palavra[]:
     *      [0] --> Corresponde a dica
     *      [1] --> Corresponde a palavra
     *      [2] --> Corresponde ao index da letra que corresponde a palavra tronco
     *      [3] --> Corresponde ao index da palavra tronco que corresponde a letra
     * 
     * @param letra
     * @param compara
     * @return 
     */
    public String[] sorteiaPalavra(char letra, String compara, int idTronco)
    {
        Random seed = new Random();
        String palavra[] = new String[4];
        String sorteada;
        boolean teste = false;
        
        while(!teste || palavra[1].equals(compara))
        {
            sorteada = linha[seed.nextInt(tamArq)];
            palavra[0] = sorteada.split(": ")[0];
            palavra[1] = sorteada.split(": ")[1];
            
            
            for(int j = 0; j < palavra[1].length(); j++)
            {
                teste = palavra[1].charAt(j) == letra;
                if(teste)
                {
                    palavra[2] = Integer.toString(j);
                    palavra[3] = Integer.toString(idTronco);
                    break;
                }
            }
        }
        
        return palavra;
    }
    
    /**
     * 
     * NA MATRIZ DE STRING palavrasHor[][]
     *      [0][i] --> Corresponde a palavra tronco
     * 
     * @param palavraChave
     * @return 
     */
    public String[][] escolhePalavras(String[] palavraChave)
    {
        int tam = palavraChave[1].length();
        String[][] palavrasHor = new String[tam+1][];
        
        palavrasHor[0] = new String[3];
        palavrasHor[0][0] = palavraChave[0];
        palavrasHor[0][1] = palavraChave[1];
        palavrasHor[0][2] = "-1";
        
        for(int i = 1; i <= tam; i++)
        {
            palavrasHor[i] = sorteiaPalavra(palavraChave[1].charAt(i-1), palavraChave[1], i-1);
        }
        
        return palavrasHor;
    }
    
}
