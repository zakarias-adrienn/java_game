/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

/**
 *
 * @author anwol
 */
public class View extends javax.swing.JFrame {
    private long startTime;
    private Timer timer;
    private boolean paused = false;
    private int i = 0;
    
    public View() {
        initComponents();
        URL url = View.class.getClassLoader().getResource("res/fugu.png");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(url));
        this.setTitle("Főképernyő");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.startTime =  System.currentTimeMillis();
        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!paused){
                    jLabel1.setText("" + i++);
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
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        timerStopButton = new javax.swing.JButton();
        timerContinueButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        helpButton.setBackground(new java.awt.Color(0, 204, 204));
        helpButton.setText("Súgó");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });
        getContentPane().add(helpButton);
        helpButton.setBounds(610, 40, 90, 40);

        jButton2.setBackground(new java.awt.Color(0, 204, 204));
        jButton2.setText("Kilépés");
        getContentPane().add(jButton2);
        jButton2.setBounds(710, 40, 90, 40);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("0");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(730, 330, 80, 22);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Pénz:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(610, 270, 60, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("0");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(730, 280, 40, 16);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Eltelt idő:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(610, 320, 80, 40);

        timerStopButton.setBackground(new java.awt.Color(0, 204, 204));
        timerStopButton.setText("Leállít");
        timerStopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timerStopButtonActionPerformed(evt);
            }
        });
        getContentPane().add(timerStopButton);
        timerStopButton.setBounds(710, 380, 90, 40);

        timerContinueButton.setBackground(new java.awt.Color(0, 204, 204));
        timerContinueButton.setText("Folytat");
        timerContinueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timerContinueButtonActionPerformed(evt);
            }
        });
        getContentPane().add(timerContinueButton);
        timerContinueButton.setBounds(610, 380, 90, 40);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/small-sand-castle.png"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(620, 90, 70, 100);

        setBounds(0, 0, 886, 519);
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

    private void timerStopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timerStopButtonActionPerformed
        timer.stop();
        this.paused = true;
    }//GEN-LAST:event_timerStopButtonActionPerformed

    private void timerContinueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timerContinueButtonActionPerformed
        this.paused = false;
        timer.start();
    }//GEN-LAST:event_timerContinueButtonActionPerformed

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
    private javax.swing.JButton helpButton;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton timerContinueButton;
    private javax.swing.JButton timerStopButton;
    // End of variables declaration//GEN-END:variables
}
