
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mat
 */
public class PalavrasCruzadas {
    
    private char[][] palavrasCruzadas;
    private int altura;
    private int largura;
    private int distCentro;
    private String[][] palavras;
    private int[] qntEsq;
    
    public PalavrasCruzadas()
    {
        
        Palavras dicionario = new Palavras();
        this.palavras = dicionario.escolhePalavras(dicionario.sorteiaPalavra());// SORTEIA TRONCO E PALAVRAS SECUNDARIAS
        
        this.altura = palavras[0][1].length();
        int esquerda = 0;
        int direita = 0;
        int restoD;
        this.qntEsq = new int[altura];
        int[] qntDir = new int[altura];
        
        for(int i = 0; i < altura; i++)
        {
            if(Integer.parseInt(palavras[i+1][2]) > esquerda)
                esquerda = Integer.parseInt(palavras[i+1][2]);
            
            restoD = palavras[i+1][1].length() - Integer.parseInt(palavras[i+1][2]);
            
            if (restoD > direita)
                direita = restoD;
        }
        
        this.largura = direita + esquerda + 1;
        
        for(int i = 0; i < altura; i++)
        {
            qntEsq[i] = esquerda - Integer.parseInt(palavras[i+1][2]);
            qntDir[i] = direita - (palavras[i+1][1].length() - (Integer.parseInt(palavras[i+1][2]) + 1));
        }
        
        palavrasCruzadas = new char[altura][largura];
        char[] letras;
        
        for(int i = 0; i < altura; i++)
        {
            letras = palavras[i+1][1].toLowerCase().toCharArray();
            for(int j = 0; j < largura; j++){
                
                if(palavras[i+1][3].equals("0") && (j-qntEsq[i]-1) == 0)
                    distCentro = j;
                if(j <= qntEsq[i])
                    palavrasCruzadas[i][j] = '0';
                else if(j > qntEsq[i] + letras.length)
                    palavrasCruzadas[i][j] = '0';
                else
                    palavrasCruzadas[i][j] = letras[j-qntEsq[i]-1];
                
            }
            
        }
    }
    
    public int getAltura()
    {
        return altura;
    }
    
    public int getLargura()
    {
        return largura;
    }
    
    public char[][] getCruzada()
    {
        return palavrasCruzadas;
    }
    
    public String[][] getPalavras()
    {
        return palavras;
    }
    
    public int getDistCentro()
    {
        return distCentro;
    }
    
    public int[] getQntEsq()
    {
        return qntEsq;
    }
    
    public boolean confere(String[] coord, String c)
    {
        int i = Integer.parseInt(coord[0]);
        int j = Integer.parseInt(coord[1]);
        
        return c.charAt(0) == palavrasCruzadas[i][j];
    }
    
//    public boolean confere(JTextField campo)
//    {
//        
//        System.out.println(campo.getText());
//        System.out.println(campo.getName());
//        System.out.println(campo.getName().split(":")[0]);
//        System.out.println(campo.getName().split(":")[1]);
//        String[] coord = campo.getName().split(":");
//        int i = Integer.parseInt(campo.getName().split(":")[0]);
//        int j = Integer.parseInt(campo.getName().split(":")[1]);
//        
//        String letra = campo.getText();
//        System.out.println(letra);
//        
//        return letra.toCharArray()[0] == palavrasCruzadas[i][j];
//    }
}
