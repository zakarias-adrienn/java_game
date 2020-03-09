/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
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

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        timerStopButton = new javax.swing.JButton();
        timerContinueButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jButton1.setText("Hint");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(479, 30, 140, 25);

        jButton2.setText("Exit");
        getContentPane().add(jButton2);
        jButton2.setBounds(640, 30, 150, 25);

        jLabel1.setText("0");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(650, 260, 180, 16);

        jLabel2.setText("Money:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(560, 320, 50, 20);

        jLabel3.setText("0");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(650, 320, 150, 16);

        jLabel4.setText("Time:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(570, 260, 53, 16);

        timerStopButton.setText("Leállít");
        timerStopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timerStopButtonActionPerformed(evt);
            }
        });
        getContentPane().add(timerStopButton);
        timerStopButton.setBounds(650, 370, 90, 30);

        timerContinueButton.setText("Folytat");
        timerContinueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timerContinueButtonActionPerformed(evt);
            }
        });
        getContentPane().add(timerContinueButton);
        timerContinueButton.setBounds(520, 370, 90, 30);

        setBounds(0, 0, 886, 519);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton timerContinueButton;
    private javax.swing.JButton timerStopButton;
    // End of variables declaration//GEN-END:variables
}
