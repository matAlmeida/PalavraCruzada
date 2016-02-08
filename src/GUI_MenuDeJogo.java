
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author mat
 */
public class GUI_MenuDeJogo extends javax.swing.JFrame {
    
    JRadioButton[] tipo;
    ButtonGroup gTipo;
    JRadioButton[] dif;
    ButtonGroup gLvl;
    JTextField[] nomeJ;
    JButton novoJogo;
    
    public GUI_MenuDeJogo() {
        initComponents();
        this.setTitle("Palavras Cruzadas");
        
        setTipoJogo();
        setDificuldade();
        getNomeJogador();
        
        novoJogo = new JButton("Novo Jogo");
        novoJogo.setLocation(300, 100);
        novoJogo.setSize(150, 30);
        novoJogo.setVisible(true);
        this.add(novoJogo);
        
        if(nomeJ[0].getText().equals(""))
            novoJogo.setEnabled(false);
        
        novoJogo.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                newGame();
            }
        });
        
    }

    private void newGame()
    {
        if(tipo[0].isSelected())
        {
            GUI_Partida partida = new GUI_Partida(nomeJ[0].getText(), nomeJ[1].getText(), 0);
            partida.setVisible(true);
            
        }
        if(tipo[1].isSelected())
        {
            if(dif[0].isSelected())
            {
            GUI_Partida partida = new GUI_Partida(nomeJ[0].getText(), nomeJ[1].getText(), 1);
            partida.setVisible(true);
            }
            else if(dif[1].isSelected())
            {
            GUI_Partida partida = new GUI_Partida(nomeJ[0].getText(), nomeJ[1].getText(), 2);
            partida.setVisible(true);
            }
            else if(dif[2].isSelected())
            {
            GUI_Partida partida = new GUI_Partida(nomeJ[0].getText(), nomeJ[1].getText(), 3);
            partida.setVisible(true);
            }
        }
    }
    
    private void getNomeJogador()
    {
        JLabel jogador1 = new JLabel("Jogador 1");
        JLabel jogador2 = new JLabel("Jogador 2");
        
        nomeJ = new JTextField[2];
        
        jogador1.setVisible(true);
        jogador1.setLocation(10, 10);
        jogador1.setSize(150, 30);
        this.add(jogador1);
        
        nomeJ[0] = new JTextField();
        nomeJ[0].setLocation(80, 10);
        nomeJ[0].setSize(200, 30);
        nomeJ[0].setVisible(true);
        this.add(nomeJ[0]);
        
        jogador2.setVisible(true);
        jogador2.setLocation(10, 50);
        jogador2.setSize(150, 30);
        this.add(jogador2);
        
        nomeJ[1] = new JTextField();
        nomeJ[1].setLocation(80, 50);
        nomeJ[1].setSize(200, 30);
        nomeJ[1].setVisible(true);
        this.add(nomeJ[1]);
        
        nomeJ[0].getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                if(!nomeJ[0].getText().equals(""))
                    novoJogo.setEnabled(true);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(!nomeJ[0].getText().equals(""))
                    novoJogo.setEnabled(true);
                else
                    novoJogo.setEnabled(false);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if(!nomeJ[0].getText().equals(""))
                    novoJogo.setEnabled(true);
            }
        });
    }
    
    private void setDificuldade()
    {
        JLabel txtIzi = new JLabel("Facíl");
        JLabel txtMedio = new JLabel("Medio");
        JLabel txtHard = new JLabel("Difícil");
        
        dif = new JRadioButton[3];
        
        txtIzi.setVisible(true);
        txtIzi.setLocation(490, 10);
        txtIzi.setSize(150, 30);
        this.add(txtIzi);
        
        dif[0] = new JRadioButton();
        dif[0].setLocation(470, 10);
        dif[0].setSize(30, 30);
        dif[0].setSelected(true);
        dif[0].setVisible(true);
        this.add(dif[0]);
        
        txtMedio.setVisible(true);
        txtMedio.setLocation(490, 50);
        txtMedio.setSize(150, 30);
        this.add(txtMedio);
        
        dif[1] = new JRadioButton();
        dif[1].setLocation(470, 50);
        dif[1].setSize(30, 30);
        dif[1].setVisible(true);
        this.add(dif[1]);
        
        txtHard.setVisible(true);
        txtHard.setLocation(490, 90);
        txtHard.setSize(150, 30);
        this.add(txtHard);
        
        dif[2] = new JRadioButton();
        dif[2].setLocation(470, 90);
        dif[2].setSize(30, 30);
        dif[2].setVisible(true);
        this.add(dif[2]);
        
        gLvl = new ButtonGroup();
        gLvl.add(dif[0]);
        gLvl.add(dif[1]);
        gLvl.add(dif[2]);
        
    }
    
    private void setTipoJogo()
    {
        JLabel txtTipo1 = new JLabel("Contra Jogador");
        JLabel txtTipo2 = new JLabel("Contra Computador");
        
        tipo = new JRadioButton[2];
        
        txtTipo1.setVisible(true);
        txtTipo1.setLocation(320, 10);
        txtTipo1.setSize(150, 30);
        this.add(txtTipo1);
        
        tipo[0] = new JRadioButton();
        tipo[0].setLocation(300, 10);
        tipo[0].setSize(30, 30);
        tipo[0].setSelected(true);
        tipo[0].setVisible(true);
        this.add(tipo[0]);
        
        txtTipo2.setVisible(true);
        txtTipo2.setLocation(320, 50);
        txtTipo2.setSize(150, 30);
        this.add(txtTipo2);
        
        tipo[1] = new JRadioButton();
        tipo[1].setLocation(300, 50);
        tipo[1].setSize(30, 30);
        tipo[1].setVisible(true);
        this.add(tipo[1]);
        
        gTipo = new ButtonGroup();
        gTipo.add(tipo[0]);
        gTipo.add(tipo[1]);
        
        tipo[1].addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                nomeJ[1].setText("Computador");
                nomeJ[1].setBackground(Color.LIGHT_GRAY);
                nomeJ[1].setEditable(false);
            }
        });
        
        tipo[0].addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                nomeJ[1].setText("");
                nomeJ[1].setBackground(Color.white);
                nomeJ[1].setEditable(true);
            }
        });
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
            .addGap(0, 545, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
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
            java.util.logging.Logger.getLogger(GUI_MenuDeJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_MenuDeJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_MenuDeJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_MenuDeJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_MenuDeJogo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
