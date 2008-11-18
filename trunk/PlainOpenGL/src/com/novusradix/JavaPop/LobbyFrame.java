/*
 * LobbyFrame.java
 *
 * Created on November 17, 2008, 11:09 AM
 */
package com.novusradix.JavaPop;

import com.novusradix.JavaPop.Client.PlayerState;

/**
 *
 * @author  mom
 */
public class LobbyFrame extends javax.swing.JFrame {

    public PlayerState p;
    
    /** Creates new form LobbyFrame */
    public LobbyFrame() {
        initComponents();
        serverPanel1.parent = this;
        gamesPanel1.parent = this;
        gamePanel1.parent = this;
        gamesPanel1.setEnabled(false);
        gamePanel1.setEnabled(false);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        serverPanel1 = new com.novusradix.JavaPop.ServerPanel();
        gamesPanel1 = new com.novusradix.JavaPop.GamesPanel();
        gamePanel1 = new com.novusradix.JavaPop.GamePanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(serverPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 166, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(gamesPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 201, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(gamePanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, gamePanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                    .add(gamesPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                    .add(serverPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public com.novusradix.JavaPop.GamePanel getGamePanel() {
        return gamePanel1;
    }

    public com.novusradix.JavaPop.GamesPanel getGamesPanel() {
        return gamesPanel1;
    }

    public com.novusradix.JavaPop.ServerPanel getServerPanel() {
        return serverPanel1;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.novusradix.JavaPop.GamePanel gamePanel1;
    private com.novusradix.JavaPop.GamesPanel gamesPanel1;
    private com.novusradix.JavaPop.ServerPanel serverPanel1;
    // End of variables declaration//GEN-END:variables
}
