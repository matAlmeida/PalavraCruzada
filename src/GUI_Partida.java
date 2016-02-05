
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
    Jogador[] jogador1 = new Jogador[2];
    int jogando = 0;
    int njogando = 1;
    int tipoPartida;
    JLabel turn[] = new JLabel[2];
    
    PalavrasCruzadas cWords;
    char[][] cWordsM;
    String[][] palavras;
    
    JTextField[][] campo; // Campo de quadrados
    JLabel[] idDica;
    JLabel[] lbDicas;
    
    int [] pontos = new int[2];
    JLabel[] jPontos = new JLabel[2];
    
    public GUI_Partida(String player1, String player2, int tipoJ) {
        initComponents();
        
        newGame(player1, player2, tipoJ);
    }
    
    private void newGame(String player1, String player2, int tipoJ)
    {
        jogador1[0] = new Jogador(player1);
        pontos[0] = 0;
        if(!player2.equals("Computador"))
        {
            jogador1[1] = new Jogador(player2);
            pontos[1] = 0;
        }
        
        this.setTitle(player1 + " X " + player2);
        
        this.tipoPartida = tipoJ;
        
        cWords = new PalavrasCruzadas();
        this.cWordsM = cWords.getCruzada();
        this.palavras = cWords.getPalavras();
        
        int altura = cWords.getAltura();
        int largura = cWords.getLargura();
        
        
        ImageIcon turnIcn = new ImageIcon("img/turn.png");
        int altura2 = (cWords.getAltura() + 1) * 40;
        
            turn[0] = new JLabel();
            turn[0].setLocation(580, altura2 - 27);
            turn[0].setSize(560, 136);
            turn[0].setIcon(turnIcn);
            this.add(turn[0]);
            
            turn[1] = new JLabel();
            turn[1].setIcon(turnIcn);
            turn[1].setLocation(580, altura2 + 40);
            turn[1].setSize(560, 136);
            this.add(turn[1]);
        
        setTurno();
        
        createTextBoxArea(altura, largura);
        createTips(altura, largura);
        createTable(player1, player2);
        
        setScreenSize();
        
    }
    
    private void createTable(String player1, String player2)
    {
        int altura = (cWords.getAltura() + 1) * 40;
        
        JLabel p1 = new JLabel(player1);
        jPontos[0] = new JLabel(String.valueOf(pontos[0]));
        JLabel p2 = new JLabel(player2);
        jPontos[1] = new JLabel(String.valueOf(pontos[1]));
        
        p1.setLocation(20, altura +30);
        p1.setSize(300, 30);
        p1.setFont(new Font("Courier New", Font.BOLD, 30));
        p1.setVisible(true);
        this.add(p1);
        
        jPontos[0].setLocation(500, altura +30);
        jPontos[0].setSize(300, 30);
        jPontos[0].setFont(new Font("Courier New", Font.BOLD, 30));
        jPontos[0].setVisible(true);
        this.add(jPontos[0]);
        
        p2.setLocation(20, altura +95);
        p2.setSize(300, 30);
        p2.setFont(new Font("Courier New", Font.BOLD, 30));
        p2.setVisible(true);
        this.add(p2);
        
        jPontos[1].setLocation(500, altura +95);
        jPontos[1].setSize(300, 30);
        jPontos[1].setFont(new Font("Courier New", Font.BOLD, 30));
        jPontos[1].setVisible(true);
        this.add(jPontos[1]);
        
        ImageIcon bgTable = new ImageIcon("img/fundoTabela.jpg");
        JLabel table = new JLabel();
        table.setIcon(bgTable);
        table.setLocation(10, altura + 7);
        table.setSize(560, 136);
        table.setVisible(true);
        this.add(table);
    }
    
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
    
    private void setPontos(int jogador)
    {
        jogador1[jogador].addPonto(tipoPartida);
        pontos[jogador]++;
        jPontos[jogador].setText(String.valueOf(pontos[jogador]));
    }

    private void setScreenSize()
    {
        int largura = cWords.getLargura()*60;
        int altura = (cWords.getAltura() + 6)*40;
        
        if(largura < 560)
            largura = 560 + 20 + 65;
        
        this.setSize(largura, altura);
    }
    
    private void createTextBoxArea(int altura, int largura)
    {
        campo = new JTextField[altura][largura];
        
        
        
        for(int i = 0, h = 40; i < altura; i++, h += 40)
            for(int j = 0, w = 10; j < largura; j++, w += 40)
                if(cWordsM[i][j] != '0')
                {
                    campo[i][j] = new JTextField();
                    campo[i][j].setLocation(w, h);
                    campo[i][j].setVisible(true);
                    campo[i][j].setSize(30, 30);
                    campo[i][j].setName(i + ":" + j);
                    this.add(campo[i][j]);
                    
                    checa(campo[i][j]);
                    
                }
    }
    
    private void checa(JTextField campinho)
    {
        campinho.getDocument().addDocumentListener(new DocumentListener() {
                        
                        @Override
                        public void insertUpdate(DocumentEvent e) 
                        {
                            
                            if(cWords.confere(campinho.getName().split(":"), campinho.getText().toLowerCase()))
                            {
                                setPontos(jogando);
                                campinho.setEnabled(false);
                                campinho.setForeground(Color.green);
                            }
                            else
                            {
                                int aux = jogando;
                                jogando = njogando;
                                njogando = aux;
                                setTurno();
                                campinho.setForeground(Color.red);
                            }
                        }

                        @Override
                        public void removeUpdate(DocumentEvent e) 
                        {
                        }

                        @Override
                        public void changedUpdate(DocumentEvent e) 
                        {
                        }
                    });
    }
    
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
                idDica[i].setText("DICA");
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
                idDica[i].setText("DICA");
                idDica[i].setFont(new Font("Courier New", Font.ITALIC, 12));
                idDica[i].setVisible(true);
                idDica[i].setSize(30, 30);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
