
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
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
public class GUI_Menu extends javax.swing.JFrame {

    /**
     * Creates new form GUI_Menu
     */
    
    JRadioButton[] tipo;
    JTextField[] nomeJ;
    JButton novoJogo;
    
    public GUI_Menu() {
        initComponents();
        this.setTitle("Palavras Cruzadas");
        
        setType();
        setName();
        
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
                startGame();
            }
        });
        
    }

    private void startGame()
    {
        if(tipo[0].isSelected())
        {
            GUI_Partida partida = new GUI_Partida(nomeJ[0].getText(), nomeJ[1].getText());
            partida.setVisible(true);
        }
        if(tipo[1].isSelected())
        {
            GUI_Partida partida = new GUI_Partida(nomeJ[0].getText(), nomeJ[1].getText());
            partida.setVisible(true);
        }
    }
    
    private void setName()
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
    
    private void setType()
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
        
        ButtonGroup gTipo = new ButtonGroup();
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
            .addGap(0, 500, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(GUI_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
