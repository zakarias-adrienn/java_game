/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.border.Border;

/**
 *
 * @author anwol
 */
public class View extends javax.swing.JFrame {

    private long startTime;
    private Timer timer;
    private boolean paused = false;
    private int i = 0;

    boolean selected = false;

    public View() {
        initComponents();
        URL url = View.class.getClassLoader().getResource("res/fugu.png");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(url));
        this.setTitle("Főképernyő");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.startTime = System.currentTimeMillis();
        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!paused) {
                    timeView.setText("" + i++);
                }

            }
        });
        timer.start();
    }

    public long elapsedTime() {
        return (System.currentTimeMillis() - this.startTime) / 1000;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        helpButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        timeView = new javax.swing.JLabel();
        moneyView = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        timerContinuePanel = new javax.swing.JPanel();
        timerStopPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        helpButton.setBackground(new java.awt.Color(250, 215, 172));
        helpButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        helpButton.setForeground(new java.awt.Color(0, 0, 51));
        helpButton.setText("Súgó");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });
        getContentPane().add(helpButton);
        helpButton.setBounds(1050, 20, 100, 40);

        exitButton.setBackground(new java.awt.Color(250, 215, 172));
        exitButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        exitButton.setForeground(new java.awt.Color(0, 0, 51));
        exitButton.setText("Kilépés");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(exitButton);
        exitButton.setBounds(1160, 20, 110, 40);

        timeView.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 48)); // NOI18N
        timeView.setForeground(new java.awt.Color(255, 206, 159));
        timeView.setText("0");
        getContentPane().add(timeView);
        timeView.setBounds(1180, 430, 130, 50);

        moneyView.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 48)); // NOI18N
        moneyView.setForeground(new java.awt.Color(255, 206, 159));
        moneyView.setText("0");
        getContentPane().add(moneyView);
        moneyView.setBounds(1210, 490, 100, 50);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/small-sand-castle.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel5);
        jLabel5.setBounds(1050, 150, 70, 100);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/bg.png"))); // NOI18N
        getContentPane().add(jLabel6);
        jLabel6.setBounds(0, -10, 1520, 860);

        timerContinuePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                timerContinuePanelMouseClicked(evt);
            }
        });
        getContentPane().add(timerContinuePanel);
        timerContinuePanel.setBounds(1050, 690, 60, 50);

        timerStopPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                timerStopPanelMouseClicked(evt);
            }
        });
        getContentPane().add(timerStopPanel);
        timerStopPanel.setBounds(1140, 690, 50, 50);

        setBounds(0, 0, 1397, 872);
    }// </editor-fold>//GEN-END:initComponents

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        JDialog d = new JDialog(this, "Súgó");
        JTextArea txtAreaDetail = new JTextArea("TORNYOK\n\n- GoldTower: Ezzel a toronnyal a játékban eltelt idővel arányosan  \n"
                + "  lehet pénz gyűjteni. Aktív támadásra nem használható, azonban \n"
                + "  minden - más torony által - kilőtt Goldfish után a pénztermelés \n"
                + "  gyorsasága a kétszeresére nő.\n\n"
                + "- BubbleTower: Az ElectricTower-rel ellentétben ez a toronytípus\n"
                + "  pontatlanabbul, de sokkal nagyobb hatőkörben képes lőni maga \n"
                + "  körül a fejlesztésektől függő sugarú körben. Enemy, akire\n"
                + "  leginkább hatásos: Fugu.\n\n"
                + "- IceTower: Ez a torony a hatókörébe érkező gyors Swordfish-eket \n"
                + "  képes lelassítani, fejlesztések után akár megállítani, \n"
                + "  befagyasztani, hogy minél nagyobb arányban ki tudja őket lőni.\n\n"
                + "- ElectricTower: Ez a torony nagyon gyors ütemben, de egyszerre\n"
                + "  csak egy enemy-t képes lőni nagy pontossággal, messziről. Enemy, \n"
                + "  akire leginkább hatásos: Eel.\n\n"
                + "ELLENSÉGEK\n\n"
                + "- Goldfish: Lassú, de életerős hal, ami megnehezíti a kilövését. \n"
                + "  Minden kilőtt egyed után megkétszereződik a GoldTower által \n"
                + "  termelt pénz.\n\n"
                + "- Swordfish: Nagyon gyors, könnyen mozgó hal. Az ElectricTower\n"
                + "  képes lelassítani, megállítani.\n\n"
                + "- Fugu: Nagytestű, lomha hal, de sokáig él. Legkönnyebben a\n"
                + "  BubbleTower tudja kilőni.\n\n"
                + "- Eel: Gyors, áramvonalas hal. Nehéz messziről eltalálni, ezt \n"
                + "  legkönnyebben az ElectricTower tudja megtenni.");
        txtAreaDetail.setEditable(false);
        txtAreaDetail.setBackground(new Color(240, 248, 255));
        txtAreaDetail.setForeground(Color.BLACK);
        Font f = txtAreaDetail.getFont();
        Font f2 = new Font(f.getFontName(), f.getStyle(), f.getSize() + 3);
        txtAreaDetail.setFont(f2);
        JScrollPane txtAreaScroll = new JScrollPane();
        txtAreaScroll.setViewportView(txtAreaDetail);
        d.add(txtAreaScroll);
        d.pack();
        d.setVisible(true);
        d.setLocationRelativeTo(null);
    }//GEN-LAST:event_helpButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        this.setVisible(false);
        Menu menu = new Menu();
        menu.setPreferredSize(new Dimension(1397, 842));
        menu.pack();
        menu.setVisible(true);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void timerContinuePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timerContinuePanelMouseClicked
        this.paused = false;
        timer.start();
    }//GEN-LAST:event_timerContinuePanelMouseClicked

    private void timerStopPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timerStopPanelMouseClicked
        // TODO add your handling code here:
        timer.stop();
        this.paused = true;
    }//GEN-LAST:event_timerStopPanelMouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        //this.setBorder(BorderFactory.createLineBorder(Color.black));
        if (!selected) {
            Border border = BorderFactory.createLineBorder(Color.yellow, 2, true);
            jLabel5.setBorder(border);
            selected = true;
            return;
        } else {
            Border border = BorderFactory.createLineBorder(Color.yellow, 0, true);
            jLabel5.setBorder(border);
            selected = false;
        }
    }//GEN-LAST:event_jLabel5MouseClicked

//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new View().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitButton;
    private javax.swing.JButton helpButton;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel moneyView;
    private javax.swing.JLabel timeView;
    private javax.swing.JPanel timerContinuePanel;
    private javax.swing.JPanel timerStopPanel;
    // End of variables declaration//GEN-END:variables
}
