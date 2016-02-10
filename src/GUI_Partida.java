
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
public class GUI_Partida extends javax.swing.JFrame {

    /**
     * Creates new form InterfaceGrafica
     */
    private Jogador[] jogador1 = new Jogador[2];
    private Computador computador;
    private int jogando = 0;
    private int njogando = 1;
    private int compjogando = 0;
    private int tcompjogando = 0;
    private int tipoPartida;
    private JLabel turn[] = new JLabel[2];
    private ImageIcon turnIcn;
    private BDJogadores bdP;
    
    private JButton novoJogo;
    
    private PalavrasCruzadas cWords;
    private char[][] cWordsM;
    private String[][] palavras;
    private int letrasJogaveis;
    private int letrasJogadas = 0;
    
    private JTextField[][] campo; // Campo de quadrados
    private JLabel[] idDica;
    private JLabel[] lbDicas;
    
    private int [] letrasP;
    private int [] pontos = new int[2];
    private JLabel[] jPontos = new JLabel[2];
    private JLabel[] players = new JLabel[2];
    private JLabel table;
    private ImageIcon bgTable;
    
    /**
     * Constroi a tela da primeira Partida
     * 
     * @param player1 Nome do Jogador 1
     * @param player2 Nome do Jogador 2
     * @param tipoJ   Tipo de partida a ser jogado <br>
     *                [0] Jogador vs Jogador <br>
     *                [1] Jogador vs Computador Facil <br>
     *                [2] Jogador vs Computador Medio <br>
     *                [3] Jogador vs Computador Dificil <br>
     */
    public GUI_Partida(String player1, String player2, int tipoJ) {
        initComponents();
        Image imgbg = null;
        Image imgt = null;

        try {
            imgbg = ImageIO.read(getClass().getResource("img/fundoTabela.png"));
            imgt = ImageIO.read(getClass().getResource("img/turn.png"));
        } catch (IOException ex) {
            Logger.getLogger(GUI_MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        bgTable = new ImageIcon(imgbg);
        turnIcn = new ImageIcon(imgt);
        table = new JLabel();
        
        this.tipoPartida = tipoJ;
        
        jogador1[0] = new Jogador(player1);
        pontos[0] = 0;
        if(tipoJ == 0){
            jogador1[1] = new Jogador(player2);
            pontos[1] = 0;
        }
        else
        {
            this.compjogando = 1;
            computador = new Computador();
        }
        
        this.setTitle(player1 + " X " + player2);
//-------------------------------------------------------------------
        
        players[0] = new JLabel(player1);
        jPontos[0] = new JLabel(String.valueOf(pontos[0]));
        players[1] = new JLabel(player2);
        jPontos[1] = new JLabel(String.valueOf(pontos[1]));
        
        players[0].setSize(300, 30);
        players[0].setFont(new Font("Courier New", Font.BOLD, 30));
        players[0].setForeground(Color.white);
        players[0].setVisible(true);
        
        jPontos[0].setSize(300, 30);
        jPontos[0].setFont(new Font("Courier New", Font.BOLD, 30));
        jPontos[0].setVisible(true);
        
        players[1].setSize(300, 30);
        players[1].setFont(new Font("Courier New", Font.BOLD, 30));
        players[1].setVisible(true);
        
        jPontos[1].setSize(300, 30);
        jPontos[1].setFont(new Font("Courier New", Font.BOLD, 30));
        jPontos[1].setForeground(Color.white);
        jPontos[1].setVisible(true);
        
        table.setIcon(bgTable);
        table.setSize(560, 136);
        table.setVisible(true);
        
        turn[0] = new JLabel();
        turn[0].setSize(560, 136);
        turn[0].setIcon(turnIcn);
        
        turn[1] = new JLabel();
        turn[1].setIcon(turnIcn);
        turn[1].setSize(560, 136);
        
//------------------------------------------------------------------        
        
        novoJogo = new JButton("Nova Partida");
        novoJogo.setVisible(true);
        novoJogo.setSize(136, 136);
        novoJogo.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int confirma = JOptionPane.showConfirmDialog(rootPane, "Partida em andamento sera perdida!!", "Nova partida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(confirma == JOptionPane.YES_OPTION)
                    newGame(player1, player2);
            }
        });
        
        newGame(player1, player2);
    }
    
    /**
     * 
     * @param player1 Nome do jogador 1
     * @param player2 Nome do jogador 2
     */
    private void newGame(String player1, String player2)
    {
        this.getContentPane().removeAll();
        
        this.add(players[0]);
        this.add(jPontos[0]);
        this.add(players[1]);
        this.add(jPontos[1]);
        this.add(table);
        this.add(turn[0]);
        this.add(turn[1]);
        this.add(novoJogo);
        
        this.cWords = new PalavrasCruzadas();
        this.cWordsM = cWords.getCruzada();
        this.palavras = cWords.getPalavras();
        this.letrasJogaveis = cWords.getLetrasJogaveis();
        
        int altura = cWords.getAltura();
        int largura = cWords.getLargura();
        
        letrasP = new int[altura+1];
        for(int i = 0; i < altura+1; i++)
        {
            letrasP[i] = palavras[i][1].length();
        }
        
        int altura2 = (cWords.getAltura() + 1) * 40; //Altura da tabela
        
        turn[0].setLocation(580, altura2 - 27);
        turn[1].setLocation(580, altura2 + 40);
        novoJogo.setLocation(650, altura2 + 7);
        
        setTurno();
        
        createTextBoxArea(altura, largura);
        createTips(altura, largura);
        createTable(player1, player2, altura2);
        
        setScreenSize();
        
    }
    
    /**
     * Coloca a tabela de pontuação na tela
     * 
     * @param player1 Nome jogador 1
     * @param player2 Nome jogador 2
     * @param altura Distancia do topo da tela que a tebela será localizada
     */
    private void createTable(String player1, String player2, int altura)
    {
        
        players[0].setLocation(20, altura +30);
        
        jPontos[0].setLocation(500, altura +30);
        
        players[1].setLocation(20, altura +95);
        
        jPontos[1].setLocation(500, altura +95);
        
        table.setLocation(10, altura + 7);
    }
    
    /**
     * Muda o turno, visualmente, da jogada
     */
    private void setTurno()
    {
        
        if(jogando == 0){
            turn[0].setVisible(true);
            turn[1].setVisible(false);
        }
        else
        {
            turn[0].setVisible(false);
            turn[1].setVisible(true);
        }
    }
    
    /**
     * Adiciona ponto pra o jogador na Tabela e caso não seja o computador adiciona tambem ao Jogador
     * 
     * @param jogador Index do jogador
     */
    private void setPontos(int jogador)
    {
        if(jogando == 0)
            jogador1[jogador].addPonto(tipoPartida);
        pontos[jogador]++;
        jPontos[jogador].setText(String.valueOf(pontos[jogador]));
    }

    /**
     * Muda o tamanho da tela do jogo de acordo com a quantidade de palavras sendo jogada
     */
    private void setScreenSize()
    {
        int largura = cWords.getLargura()*60;
        int altura = (cWords.getAltura() + 6)*40;
        
        if(largura < 830)
            largura = 830;
        
        this.setSize(largura, altura);
    }
    
    /**
     * Cria as areas jogaveis da partida
     * 
     * @param altura Altura da matriz de Palavras Cruzadas
     * @param largura Largura da matriz de Palavras Cruzadas
     */
    private void createTextBoxArea(int altura, int largura)
    {
        campo = new JTextField[altura][largura];
        
        
        
        for(int i = 0, h = 40; i < altura; i++, h += 40)
            for(int j = 0, w = 10; j < largura; j++, w += 40)
                if(cWordsM[i][j] != '0')
                {
                    campo[i][j] = new JTextField(2);
                    campo[i][j].setLocation(w, h);
                    campo[i][j].setVisible(true);
                    campo[i][j].setSize(30, 30);
                    campo[i][j].setName(i + ":" + j);
                    campo[i][j].setDocument(new JTextFieldLimit(1));
                    campo[i][j].setFocusTraversalKeysEnabled(false);
                    this.add(campo[i][j]);
                    
                    //campo[i][j].getDocument().addDocumentListener(checa(campo[i][j]));
                    campo[i][j].addKeyListener(checaK());
                    
                }
    }
    
    /**
     * Faz a jogada do computador
     * 
     * @param i Index da palavra a ser jogada
     * @param j Index da Area de Texto a ser jogada
     */
    private void jogadaComp(int i, int j)
    {
        String jogar = palavras[i + 1][1];
        int esq = cWords.getQntEsq()[i];
        campo[i][j].setText(String.valueOf(computador.jogadaComputador(jogar, j - esq - 1, tipoPartida - 1)).toLowerCase());
        jogar(campo[i][j]);
    }
    
    /**
     * Ação quando uma nova letra é digitada num campo de texto
     * 
     * @param campoJ Campo de texto jogado
     */
    private void jogar(JTextField campoJ)
    {
        int i = Integer.parseInt(campoJ.getName().split(":")[0]);
        int j = Integer.parseInt(campoJ.getName().split(":")[1]);
        
        if(cWords.confere(campoJ.getName().split(":"), campoJ.getText().toLowerCase()))
        {
            letrasP[i+1]--;
            if(j == cWords.getDistCentro())
                letrasP[0]--;
            if(letrasP[0] == 0)
            {
                setPontos(jogando);
                letrasP[0] += palavras[0][1].length() +1;
            }
            if(letrasP[i+1] == 0)
                setPontos(jogando);
            
            campoJ.setEnabled(false);
            campoJ.setBackground(Color.green);
            this.letrasJogadas++;
            if(letrasJogadas < letrasJogaveis && jogando == 1 && compjogando == 1)
            {
                if((j - cWords.getQntEsq()[i] - 1) < palavras[i+1][1].length()-1)
                {
                    jogadaComp(i, j+1);
                }
                else
                {
                    jogadaComp(i+1, cWords.getQntEsq()[i+1]+1);
                }
            }
            else if(letrasJogadas == letrasJogaveis)
            {
                    if(pontos[0] > pontos[1])
                    {
                        String textCampeao = "O jogador " + players[0].getText() + " ganhou a partida\nDeseja continuar jogando?";
                        
                        int confirma = JOptionPane.showConfirmDialog(rootPane, textCampeao, "Fim da Partida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if(confirma == JOptionPane.YES_OPTION)
                            newGame(players[0].getText(), players[1].getText());
                    }
                    else if(pontos[1] > pontos[0])
                    {
                        String textCampeao = "O jogador " + players[1].getText() + " ganhou a partida\nDeseja continuar jogando?";
                        
                        int confirma = JOptionPane.showConfirmDialog(rootPane, textCampeao, "Fim da Partida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if(confirma == JOptionPane.YES_OPTION)
                            newGame(players[0].getText(), players[1].getText());
                    }
                    else if (pontos[0] == pontos[1])
                    {
                        int confirma = JOptionPane.showConfirmDialog(rootPane, "Jogo empatado\n" + "Uma nova partida será iniciada!", "Fim da Partida", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if(confirma == JOptionPane.OK_OPTION)
                            newGame(players[0].getText(), players[1].getText());
                    }
                
            }
        }
        else
        {
            int aux = jogando;
            jogando = njogando;
            njogando = aux;
            setTurno();
            campoJ.setForeground(Color.red);
            
            if (jogando == 1 && compjogando == 1)
                jogadaComp(i, j);
        }
    }
    
    /**
     * 
     * @return Ação que deve acontecer quando uma letra for digitada
     */
    private KeyListener checaK()
    {
         KeyListener key = new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) 
            {
                JTextField campoJ = (JTextField) e.getComponent();
                
                if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                {
                    campoJ.setForeground(Color.black);
                }
                
                if(e.getKeyCode() == KeyEvent.VK_ENTER && campoJ.getText() != null)
                {
                    jogar(campoJ);
                }
                
                if(e.getKeyCode() == KeyEvent.VK_TAB  && campoJ.getText() != null)
                {
                    jogar(campoJ);
                }
            }
        };
         
         return key;
    }
    
//    private DocumentListener checa(JTextField campoJ)
//    {
//        DocumentListener doc = new DocumentListener() {
//                        
//                        @Override
//                        public void insertUpdate(DocumentEvent e) 
//                        {
//                            jogar(campoJ);
//                        }
//
//                        @Override
//                        public void removeUpdate(DocumentEvent e) 
//                        {
//                        }
//
//                        @Override
//                        public void changedUpdate(DocumentEvent e) 
//                        {
//                        }
//                    };
//        
//        return doc;
//    }
    
    /**
     * Coloca as Jlabels com as dicas, disponiveis em forma de ToolTipsText
     * @param altura Altura da matriz de Palavras Cruzadas
     * @param largura Largura da matriz de Palavras Cruzadas
     */
    private void createTips(int altura, int largura)
    {
        int distCentro = cWords.getDistCentro()+1;
        int[] qntEsq = cWords.getQntEsq();
        
        idDica = new JLabel[altura+1];
        
        for(int i = 0; i < altura+1; i++)
        {
            System.out.println(i + ": " + palavras[i][1]);
            
            if(i == 0)
            {
                idDica[i] = new JLabel();
                idDica[i].setLocation((40*distCentro)-29, 10);
                idDica[i].setText("DICA\n↓");
                idDica[i].setFont(new Font("Courier New", Font.ITALIC, 12));
                idDica[i].setVisible(true);
                idDica[i].setSize(45, 30);
                idDica[i].setToolTipText(palavras[i][0]);
                this.add(idDica[i]);
            }
            else
            {
                idDica[i] = new JLabel();
                idDica[i].setLocation(((qntEsq[i-1]+1)*40)-25, (40*i)+2);
                idDica[i].setText("DICA→");
                idDica[i].setFont(new Font("Courier New", Font.ITALIC, 12));
                idDica[i].setVisible(true);
                idDica[i].setSize(45, 30);
                idDica[i].setToolTipText(palavras[i][0]);
                this.add(idDica[i]);
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI_Partida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Partida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Partida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Partida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_Partida("test", "test2", 0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
