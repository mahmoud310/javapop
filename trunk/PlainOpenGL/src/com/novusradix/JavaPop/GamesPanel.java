/*
 * GamesPanel.java
 *
 * Created on November 17, 2008, 11:58 AM
 */

package com.novusradix.JavaPop;

import com.novusradix.JavaPop.Client.PlayerState;
import com.novusradix.JavaPop.Messaging.LobbyNewGame;
import com.novusradix.JavaPop.Server.GameInfo;
import java.util.Vector;
import javax.swing.DefaultListModel;

/**
 *
 * @author  mom
 */
public class GamesPanel extends javax.swing.JPanel {

    private DefaultListModel gameList;
    public LobbyFrame parent;
   
    /** Creates new form GamesPanel */
    public GamesPanel() {
        initComponents();
        gameList = new DefaultListModel();
        lstGames.setModel(gameList);
    }

    public void setGames(Vector<GameInfo> gs)
    {
        lstGames.setListData(gs);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstGames = new javax.swing.JList();
        btnNewGame = new javax.swing.JButton();
        btnJoinGame = new javax.swing.JButton();

        lstGames.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstGames);

        btnNewGame.setText("New Game");
        btnNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewGameActionPerformed(evt);
            }
        });

        btnJoinGame.setText("Join Game");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(btnNewGame)
                    .add(btnJoinGame))
                .addContainerGap(35, Short.MAX_VALUE))
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(btnNewGame)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnJoinGame))
        );
    }// </editor-fold>//GEN-END:initComponents

private void btnNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewGameActionPerformed
LobbyNewGame lng = new LobbyNewGame();
parent.p.sendMessage(lng);

}//GEN-LAST:event_btnNewGameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnJoinGame;
    private javax.swing.JButton btnNewGame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lstGames;
    // End of variables declaration//GEN-END:variables

}