import java.util.ArrayList;

/**
 *
 * @author mat
 */
public class BDJogadores {
    
    private ArrayList <Jogador> bancoJogadores;
    private int[] indexMelhores = new int[4];
    
    /**
     * Inicializa Banco de dados
     */
    public BDJogadores()
    {
        indexMelhores[0] = 0;
        indexMelhores[1] = 0;
        indexMelhores[2] = 0;
        bancoJogadores = new ArrayList<>();
    }
    
    /**
     * Adiciona jogador ao banco de dados
     * 
     * @param jogador Jogador 
     */
    public void addJogador(Jogador jogador)
    {            
        bancoJogadores.add(jogador);
    }
    
    /**
     * 
     * @param tipo Tipo de partida
     * @return Melhor jogador em dado tipo de partida
     */
    public Jogador getMelhor(int tipo)
    {
        int melhor = 0;
        
        for(int i = 0; i < bancoJogadores.size(); i++)
        {
            if(bancoJogadores.get(i).getRecord(tipo) > melhor)
            {
                indexMelhores[tipo] = i;
                melhor = bancoJogadores.get(i).getRecord(tipo);
            }
        }
        
        return bancoJogadores.get(indexMelhores[tipo]);
    }
}
