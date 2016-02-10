
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
    private int letrasjogaveis = 0;
    
    /**
     * Cria Matriz de caracteres com as palavras sorteadas, com '0' nos espaços vazios
     */
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
                {
                    palavrasCruzadas[i][j] = letras[j-qntEsq[i]-1];
                    letrasjogaveis++;
                }
                
            }
            
        }
    }
    
    /**
     * 
     * @return Altura da Matriz(Tamanho da palavra Tronco)
     */
    public int getAltura()
    {
        return altura;
    }
    
    /**
     * 
     * @return Largura da Matriz
     */
    public int getLargura()
    {
        return largura;
    }
    
    /**
     * 
     * @return Matriz ja cruzada
     */
    public char[][] getCruzada()
    {
        return palavrasCruzadas;
    }
    
    /**
     * 
     * @return Dicas, Palavras, Index na palavra Tronco
     */
    public String[][] getPalavras()
    {
        return palavras;
    }
    
    /**
     * 
     * @return Distancia da palavra tronco da lateral esquerda da matriz
     */
    public int getDistCentro()
    {
        return distCentro;
    }
    
    /**
     * 
     * @return Distancia da palavra para a lateral esquerda<br>
     *         [i] Palavras sorteadas excluindo a Palavra Tronco
     */
    public int[] getQntEsq()
    {
        return qntEsq;
    }
    
    /**
     * 
     * @return Quantidade Total de letras Jogaveis na matriz
     */
    public int getLetrasJogaveis()
    {
        return letrasjogaveis;
    }
    
    /**
     * 
     * @param coord Indices da matriz que será comparado com "c"
     * @param c Letra a ser comparada com a Matriz
     * @return Caso a letra estejá contida no Indice indicado, retorna TRUE
     */
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
