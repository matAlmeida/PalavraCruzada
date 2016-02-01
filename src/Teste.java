
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mat
 */
public class Teste {
    
    public static void main(String[] args) throws IOException
    {

        DicPalavras dicionario = new DicPalavras();
        
        String [][] palavras = dicionario.escolhePalavras(dicionario.sorteiaPalavra());
        
        for(int i = 0; i <= palavras[0][1].length(); i++)
            System.out.println(Integer.parseInt(palavras[i][2])+1 + " " + palavras[i][0] + ": " + palavras[i][1]);
        
    }
    
}
