/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mat
 */
public class Palavras {
    private static final String nomeArq = "files/dicionarioPalCruz.txt";
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
            String caminho = this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            System.out.println(caminho);
            caminho = caminho.substring(1, caminho.lastIndexOf('/') + 1);
            System.out.println(caminho);
            caminho =  "/"+ caminho + nomeArq;
            
            //String road = String.valueOf(this.getClass().getResource("files/instrucoes.txt")).split(":")[1];
            
            arquivo1 = new FileReader(caminho);
            BufferedReader buffer1 = new BufferedReader(arquivo1);
            String linha1 = buffer1.readLine();
            int j = 0;
            while(linha1 != null)
            {
                j++;
                linha1 = buffer1.readLine();
            }
            buffer1.close();
            arquivo1.close();
            tamArq = j;
            
            FileReader arquivo = new FileReader(caminho);
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
        } catch (URISyntaxException ex) {
            Logger.getLogger(Palavras.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * 
     * @return Valor da quantidade de linhas do txt
     */
    public int getTamArq()
    {
        return tamArq;
    }
    
    /**
     * Sorteia a primeira palavra que sera a palavra tronco
     * 
     * @return Retorna a palavra que será usada como tronco para o jogo<br>
     *         [0] A dica da palavra
     *         [1] A palavra sorteada
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
     * @param letra Letra que a palavra sorteada deve conter
     * @param compara Palavra tronco
     * @param idTronco  Index na palavra tronco que a palavra sorteada deverá ficar
     * @return [0] Corresponde a dica<br>
     *         [1] Corresponde a palavra<br>
     *         [2] Corresponde ao index da letra que corresponde a palavra tronco<br>
     *         [3] Corresponde ao index da palavra tronco que corresponde a letra<br>
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
     * @param palavraChave
     * @return [0][i] Corresponde a Palavra Tronco<br>
     *         [i][0] Corresponde a Dica<br>
     *         [i][1] Corresponde a Palavra<br>
     *         [i][2] Corresponde ao Index na Palavra Tronco<br>
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
