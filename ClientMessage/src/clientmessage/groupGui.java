/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientmessage;

import java.net.*;
import java.io.*;
import java.util.*;

public class groupGui extends javax.swing.JFrame {

    String username, serverIP = "192.168.5.99";
    int Port = 5000;
    Socket sock;
    BufferedReader reader;
    PrintWriter writer;
    ArrayList<String> userList = new ArrayList();
    Boolean isConnected = false;
    
    public groupGui() {
        initComponents();
    }
        public class IncomingReader implements Runnable{

        @Override
        public void run() 
        {
            String[] data;
            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat";

            try {
                while ((stream = reader.readLine()) != null) {

                    data = stream.split(":");

                     if (data[2].equals(chat)) {

                        chatTextArea.append(data[0] + ": " + data[1] + "\n");
                        chatTextArea.setCaretPosition(chatTextArea.getDocument().getLength());

                    } else if (data[2].equals(connect)){

                        chatTextArea.removeAll();
                        userAdd(data[0]);

                    } else if (data[2].equals(disconnect)) {


                        userRemove(data[0]);

                    } else if (data[2].equals(done)) {


                        usersList.setText("");
                        writeUsers();
                        userList.clear();

                    }
                 
                }
           }catch(Exception ex) {
           }
        }
    }

    public void ListenThread() {
         Thread IncomingReader = new Thread(new IncomingReader());
         IncomingReader.start();
    }

    public void userAdd(String data) {
         userList.add(data);
     }

    public void userRemove(String data) {
         chatTextArea.append(data + " has disconnected.\n");

     }

    public void writeUsers() {
         String[] tempList = new String[(userList.size())];
         userList.toArray(tempList);
         for (String token:tempList) {

             usersList.append(token + "\n");

         }

     }
    
    public void sendDisconnect() {
       String bye = (username + ": :Disconnect");
        try{
            writer.println(bye); // Sends server the disconnect signal.
            writer.flush(); // flushes the buffer
        } catch (Exception e) {
            chatTextArea.append("Could not send Disconnect message.\n");
        }

      }

    public void Disconnect() {

        try {
               chatTextArea.append("Disconnected.\n");
               sock.close();
        } catch(Exception ex) {
               chatTextArea.append("Failed to disconnect. \n");
        }
        isConnected = false;
        usernameField.setEditable(true);
        usersList.setText("");

      }                                                                                                                                                                                                         
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        connectButton = new javax.swing.JButton();
        disconnectButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        usersList = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        chatTextArea = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        inputTextArea = new javax.swing.JTextArea();
        sendButton = new javax.swing.JButton();
        dmButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Username");

        connectButton.setText("Connect");
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        disconnectButton.setText("Disconnect");
        disconnectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnectButtonActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Usernames");

        usersList.setEditable(false);
        usersList.setColumns(20);
        usersList.setLineWrap(true);
        usersList.setRows(5);
        jScrollPane1.setViewportView(usersList);

        chatTextArea.setEditable(false);
        chatTextArea.setColumns(20);
        chatTextArea.setLineWrap(true);
        chatTextArea.setRows(5);
        jScrollPane2.setViewportView(chatTextArea);

        inputTextArea.setColumns(20);
        inputTextArea.setLineWrap(true);
        inputTextArea.setRows(5);
        jScrollPane3.setViewportView(inputTextArea);

        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        dmButton.setText("Start Direct Message");
        dmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dmButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(connectButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(disconnectButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dmButton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(connectButton)
                    .addComponent(disconnectButton)
                    .addComponent(jLabel2)
                    .addComponent(dmButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                            .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dmButtonActionPerformed
        //this.dispose(); 
        new directGui().setVisible(true);
    }//GEN-LAST:event_dmButtonActionPerformed

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        String nothing = "";
        if ((inputTextArea.getText()).equals(nothing)) {
            inputTextArea.setText("");
            inputTextArea.requestFocus();
        } else {
            try {
               writer.println(username + ":" + inputTextArea.getText() + ":" + "Chat");
               writer.flush(); //flushes the buffer
            } catch (Exception ex) {
                chatTextArea.append("Message was not sent. \n");
            }
            inputTextArea.setText("");
            inputTextArea.requestFocus();
        }

        inputTextArea.setText("");
        inputTextArea.requestFocus();
    }//GEN-LAST:event_sendButtonActionPerformed

    private void disconnectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnectButtonActionPerformed
        sendDisconnect();
        Disconnect();
    }//GEN-LAST:event_disconnectButtonActionPerformed

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        if (isConnected == false) 
        {
            username = usernameField.getText();
            usernameField.setEditable(false);
            try {
                sock = new Socket(serverIP, Port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(username + ":has connected.:Connect"); //Sends to server the connnect signal
                writer.flush(); //flushes the buffer
                isConnected = true; //Checks if client is connected
            } catch (Exception ex) {
                chatTextArea.append("Cannot Connect! Try Again. \n");
                usernameField.setEditable(true);
            }
            ListenThread();
        } else if (isConnected == true) {
            chatTextArea.append("You are already connected. \n");
        }
    }//GEN-LAST:event_connectButtonActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() 
            {
                new groupGui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chatTextArea;
    private javax.swing.JButton connectButton;
    private javax.swing.JButton disconnectButton;
    private javax.swing.JButton dmButton;
    private javax.swing.JTextArea inputTextArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton sendButton;
    private javax.swing.JTextField usernameField;
    private javax.swing.JTextArea usersList;
    // End of variables declaration//GEN-END:variables
}
