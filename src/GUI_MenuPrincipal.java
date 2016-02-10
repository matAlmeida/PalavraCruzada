
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mat
 */
public class GUI_MenuPrincipal extends javax.swing.JFrame {

    private JFrame menu1;
    private JLabel bgMenu1;
    private JButton newGame;
    private JButton help;
    
    private JFrame menu2;
    private JTextArea inst;
    
    /**
     * Creates new form GUI_MenuPrincipal
     */
    public GUI_MenuPrincipal() {
        janela1();
        
    }
    
    /**
     * Cria janela de instruções
     */
    private void janela2()
    {
        menu2 = new JFrame();
        menu2.setSize(652, 448);
        menu2.setLocation(0, 0);
        menu2.setTitle("Cruzadas Palavras - Instruções");
        menu2.setVisible(true);
        menu2.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        inst = new JTextArea();
        inst.setSize(652, 448);
        inst.setLocation(0, 0);
        inst.setEditable(false);
        inst.setVisible(true);
        inst.setText(textInst());
        
        JScrollPane instS = new JScrollPane(inst);
        
        menu2.add(instS);
    }
    
    /**
     * Cria o menu principal
     */
    private void janela1()
    {
        menu1 = new JFrame();
        menu1.setSize(652, 448);
        menu1.setLocation(0, 0);
        menu1.setTitle("Cruzadas Palavras - Menu Principal");
        menu1.setVisible(true);
        menu1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        newGame = new JButton("Novo Jogo");
        newGame.setSize(100, 30);
        newGame.setLocation(68, 85);
        newGame.setVisible(true);
        newGame.addActionListener(novoJogo());
        menu1.add(newGame);
        
        help = new JButton("Instruções");
        help.setSize(100, 30);
        help.setLocation(68, 125);
        help.setVisible(true);
        help.addActionListener(abrirInst());
        menu1.add(help);
        
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("img/bgPrincipal3.png"));
        } catch (IOException ex) {
            Logger.getLogger(GUI_MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        ImageIcon bg = new ImageIcon(img);
        bgMenu1 = new JLabel();
        bgMenu1.setSize(652, 448);
        bgMenu1.setLocation(0, 0);
        bgMenu1.setIcon(bg);
        bgMenu1.setVisible(true);
        menu1.add(bgMenu1);
    }
    
    /**
     * 
     * @return Ação quando o botão Novo Jogo for pressionado
     */
    private ActionListener novoJogo()
    {
        ActionListener nG = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                GUI_MenuDeJogo menuNG = new GUI_MenuDeJogo();
                menuNG.setVisible(true);
            }
        };
        
        return nG;
    }
    
    /**
     * 
     * @return Ação quando o botão Instruções for pressionado
     */
    private ActionListener abrirInst()
    {
        ActionListener nG = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                janela2();
            }
        };
        
        return nG;
    }
    
    /**
     * 
     * @return String com as linhas do txt de Intruções
     */
    private String textInst()
    {
        //String road = String.valueOf(this.getClass().getResource("files/instrucoes.txt")).split(":")[1];
        
        String caminho = "";
        try {
            caminho = this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            caminho = caminho.substring(1, caminho.lastIndexOf('/') + 1);
            caminho =  "/"+ caminho + "files/instrucoes.txt";
	} catch (URISyntaxException ex) {
            Logger.getLogger(GUI_MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String texto = "";
        FileReader instrucoes;
        BufferedReader buffer;
        try {
            instrucoes = new FileReader(caminho);
            buffer = new BufferedReader(instrucoes);
            
            for(int i = 0; i < 51; i++)
                texto += buffer.readLine() + "\n";
            
            buffer.close();
            instrucoes.close();
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            Logger.getLogger(GUI_MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            Logger.getLogger(GUI_MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return texto;
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
            .addGap(0, 652, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 448, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(GUI_MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
