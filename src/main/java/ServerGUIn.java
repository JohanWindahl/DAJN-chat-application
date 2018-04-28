/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author johan
 */
public class ServerGUIn extends javax.swing.JFrame {
    private static Integer port;


    public static Integer getPort() {
        return port;
    }

    public static java.awt.List getStatusArea() {
        return Status_area;
    }

    /**
     * Creates new form ServerGUIn
     */
    public ServerGUIn() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        Status_area = new java.awt.List();
        Start_button = new java.awt.Button();
        Stop_button = new java.awt.Button();
        Exit_button = new java.awt.Button();
        Port_textField = new java.awt.TextField();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        Port_label = new java.awt.Label();
        Status_label = new java.awt.Label();
        IP_label = new java.awt.Label();
        Clear_button = new java.awt.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server");
        setName("Server"); // NOI18N

        Status_area.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Status_areaActionPerformed(evt);
            }
        });

        Start_button.setActionCommand("Start_button");
        Start_button.setLabel("Start");
        Start_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Start_buttonActionPerformed(evt);
            }
        });

        Stop_button.setActionCommand("Stop_button");
        Stop_button.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Stop_button.setEnabled(false);
        Stop_button.setLabel("Stop");
        Stop_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Stop_buttonActionPerformed(evt);
            }
        });

        Exit_button.setActionCommand("Exit_button");
        Exit_button.setLabel("Exit");
        Exit_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Exit_buttonActionPerformed(evt);
            }
        });

        Port_textField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        Port_textField.setName(""); // NOI18N
        Port_textField.setText("1234");
        Port_textField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Port_textFieldActionPerformed(evt);
            }
        });

        label1.setText("Port:");

        label2.setText("IP:");

        label3.setText("Status:");

        Port_label.setName(""); // NOI18N

        Status_label.setText("Not Active");

        Clear_button.setActionCommand("Clear_button");
        Clear_button.setLabel("Clear");
        Clear_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Clear_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                        .addComponent(label2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(label3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(Status_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(IP_label, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(0, 0, Short.MAX_VALUE))))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(Port_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(Status_area, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(Start_button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(Stop_button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(Exit_button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(Port_textField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(Clear_button, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(Status_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(3, 3, 3)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(IP_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(Port_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(101, 101, 101)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(Port_textField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(31, 31, 31)
                                                                                .addComponent(Stop_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(Start_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(Clear_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(Exit_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(Status_area, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(44, Short.MAX_VALUE))
        );

        Start_button.getAccessibleContext().setAccessibleName("Start_button");
        Stop_button.getAccessibleContext().setAccessibleName("Stop_button");
        Exit_button.getAccessibleContext().setAccessibleName("Exit_button");


        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Start_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Start_buttonActionPerformed
        this.port = Integer.parseInt(Port_textField.getText());
        Stop_button.enable();
        Start_button.disable();
        Status_label.setText("Active");
        Port_label.setText(port.toString());
        try {
            String IP = Inet4Address.getLocalHost().getHostAddress();
            IP_label.setText(IP);
            Status_area.add("Server activated on: " + IP + ":" + this.port);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ServerGUIn.class.getName()).log(Level.SEVERE, null, ex);
        }
        Thread starter = (new Server());
        starter.start();

    }//GEN-LAST:event_Start_buttonActionPerformed

    private void Stop_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Stop_buttonActionPerformed
        Stop_button.disable();
        Start_button.enable();
        Status_label.setText("Not Active");
        IP_label.setText("");
        Port_label.setText("");
        Status_area.add("Server Deactivated!");
        Thread.currentThread().interrupt();


    }//GEN-LAST:event_Stop_buttonActionPerformed

    private void Exit_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Exit_buttonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_Exit_buttonActionPerformed

    private void Port_textFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Port_textFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Port_textFieldActionPerformed

    private void Status_areaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Status_areaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Status_areaActionPerformed

    private void Clear_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Clear_buttonActionPerformed
        Status_area.clear();
    }//GEN-LAST:event_Clear_buttonActionPerformed

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
            Logger.getLogger(ServerGUIn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ServerGUIn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServerGUIn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ServerGUIn.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerGUIn().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button Clear_button;
    private java.awt.Button Exit_button;
    private java.awt.Label IP_label;
    private java.awt.Label Port_label;
    private java.awt.TextField Port_textField;
    private java.awt.Button Start_button;
    private static java.awt.List Status_area;
    private java.awt.Label Status_label;
    private java.awt.Button Stop_button;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    // End of variables declaration//GEN-END:variables
}
