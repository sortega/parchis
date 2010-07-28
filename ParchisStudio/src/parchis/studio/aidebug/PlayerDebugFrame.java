/*
 * PlayerDebugFrame.java
 *
 * Created on 05-ene-2010, 20:13:35
 */

package parchis.studio.aidebug;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import parchis.*;
import parchis.ai.Player;
import parchis.studio.ParchisPanel;
import parchis.util.LocalizedItem;
import parchis.util.LocalizedListModel;
import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.ResourceMap;
import parchis.dices.MockedDice;

/**
 *
 * @author sortega
 */
public class PlayerDebugFrame extends javax.swing.JFrame {

    /** Creates new form PlayerDebugFrame */
    public PlayerDebugFrame() {
        this.players = new LinkedList<Player>();
        for (Player player: Application.getInstance(parchis.studio.ParchisStudioApp.class)
                .getRegisteredPlayers()) {
            if (!(player instanceof parchis.studio.HumanPlayer)) {
                this.players.add(player);
            }
        }
        
        
        initComponents();
        // post init
        ludoPanel = new ParchisPanel(null);
        split.add(ludoPanel, JSplitPane.LEFT);
        pawnsTexts = new EnumMap<Color, JTextField>(Color.class) {{
            put(Color.yellow, yellowPawnsText);
            put(Color.blue, bluePawnsText);
            put(Color.red, redPawnsText);
            put(Color.green, greenPawnsText);
        }};

        initModels();

        this.game = new Game();
        updateBoard();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        split = new javax.swing.JSplitPane();
        rightScroll = new javax.swing.JScrollPane();
        rightPanel = new javax.swing.JPanel();
        playerLabel = new javax.swing.JLabel();
        playerCombo = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        yellowPawnsLabel = new javax.swing.JLabel();
        yellowPawnsText = new javax.swing.JTextField();
        bluePawnsText = new javax.swing.JTextField();
        bluePawnsLabel = new javax.swing.JLabel();
        redPawnsText = new javax.swing.JTextField();
        redPawnsLabel = new javax.swing.JLabel();
        greenPawnsLabel = new javax.swing.JLabel();
        greenPawnsText = new javax.swing.JTextField();
        currentPlayerLabel = new javax.swing.JLabel();
        currentPlayerCombo = new javax.swing.JComboBox();
        rollLabel = new javax.swing.JLabel();
        rollCombo = new javax.swing.JComboBox();
        bonusLabel = new javax.swing.JLabel();
        bonusCombo = new javax.swing.JComboBox();
        jSeparator3 = new javax.swing.JSeparator();
        actionsLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        actionsList = new javax.swing.JList();
        chosenActionLabel = new javax.swing.JLabel();
        chosenActionText = new javax.swing.JLabel();
        updateCmd = new javax.swing.JButton();
        updateBoardCmd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(parchis.studio.ParchisStudioApp.class).getContext().getResourceMap(PlayerDebugFrame.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        split.setDividerSize(5);
        split.setResizeWeight(1.0);
        split.setName("split"); // NOI18N

        rightScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        rightScroll.setAlignmentY(1.0F);
        rightScroll.setMinimumSize(new java.awt.Dimension(220, 100));
        rightScroll.setName("rightScroll"); // NOI18N

        rightPanel.setBackground(resourceMap.getColor("rightPanel.background")); // NOI18N
        rightPanel.setName("rightPanel"); // NOI18N
        rightPanel.setPreferredSize(new java.awt.Dimension(200, 530));

        playerLabel.setLabelFor(playerCombo);
        playerLabel.setText(resourceMap.getString("playerLabel.text")); // NOI18N
        playerLabel.setName("playerLabel"); // NOI18N

        playerCombo.setName("playerCombo"); // NOI18N

        jSeparator1.setName("jSeparator1"); // NOI18N

        yellowPawnsLabel.setLabelFor(yellowPawnsText);
        yellowPawnsLabel.setText(resourceMap.getString("yellowPawnsLabel.text")); // NOI18N
        yellowPawnsLabel.setName("yellowPawnsLabel"); // NOI18N

        yellowPawnsText.setName("yellowPawnsText"); // NOI18N

        bluePawnsText.setName("bluePawnsText"); // NOI18N

        bluePawnsLabel.setLabelFor(bluePawnsText);
        bluePawnsLabel.setText(resourceMap.getString("bluePawnsLabel.text")); // NOI18N
        bluePawnsLabel.setName("bluePawnsLabel"); // NOI18N

        redPawnsText.setName("redPawnsText"); // NOI18N

        redPawnsLabel.setLabelFor(redPawnsText);
        redPawnsLabel.setText(resourceMap.getString("redPawnsLabel.text")); // NOI18N
        redPawnsLabel.setName("redPawnsLabel"); // NOI18N

        greenPawnsLabel.setLabelFor(greenPawnsText);
        greenPawnsLabel.setText(resourceMap.getString("greenPawnsLabel.text")); // NOI18N
        greenPawnsLabel.setName("greenPawnsLabel"); // NOI18N

        greenPawnsText.setName("greenPawnsText"); // NOI18N

        currentPlayerLabel.setLabelFor(currentPlayerCombo);
        currentPlayerLabel.setText(resourceMap.getString("currentPlayerLabel.text")); // NOI18N
        currentPlayerLabel.setName("currentPlayerLabel"); // NOI18N

        currentPlayerCombo.setName("currentPlayerCombo"); // NOI18N

        rollLabel.setLabelFor(rollCombo);
        rollLabel.setText(resourceMap.getString("rollLabel.text")); // NOI18N
        rollLabel.setName("rollLabel"); // NOI18N

        rollCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6" }));
        rollCombo.setName("rollCombo"); // NOI18N

        bonusLabel.setLabelFor(bonusCombo);
        bonusLabel.setText(resourceMap.getString("bonusLabel.text")); // NOI18N
        bonusLabel.setName("bonusLabel"); // NOI18N

        bonusCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "10", "20", "40" }));
        bonusCombo.setName("bonusCombo"); // NOI18N

        jSeparator3.setName("jSeparator3"); // NOI18N

        actionsLabel.setLabelFor(actionsList);
        actionsLabel.setText(resourceMap.getString("actionsLabel.text")); // NOI18N
        actionsLabel.setName("actionsLabel"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        actionsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        actionsList.setName("actionsList"); // NOI18N
        jScrollPane1.setViewportView(actionsList);

        chosenActionLabel.setLabelFor(chosenActionText);
        chosenActionLabel.setText(resourceMap.getString("chosenActionLabel.text")); // NOI18N
        chosenActionLabel.setName("chosenActionLabel"); // NOI18N

        chosenActionText.setBackground(resourceMap.getColor("chosenActionText.background")); // NOI18N
        chosenActionText.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        chosenActionText.setName("chosenActionText"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(parchis.studio.ParchisStudioApp.class).getContext().getActionMap(PlayerDebugFrame.class, this);
        updateCmd.setAction(actionMap.get("play")); // NOI18N
        updateCmd.setText(resourceMap.getString("updateCmd.text")); // NOI18N
        updateCmd.setName("updateCmd"); // NOI18N
        updateCmd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateCmdActionPerformed(evt);
            }
        });

        updateBoardCmd.setAction(actionMap.get("updateBoard")); // NOI18N
        updateBoardCmd.setText(resourceMap.getString("updateBoardCmd.text")); // NOI18N
        updateBoardCmd.setName("updateBoardCmd"); // NOI18N

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rightPanelLayout.createSequentialGroup()
                        .addComponent(playerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(playerCombo, 0, 100, Short.MAX_VALUE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addGroup(rightPanelLayout.createSequentialGroup()
                        .addComponent(yellowPawnsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yellowPawnsText, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightPanelLayout.createSequentialGroup()
                        .addComponent(bluePawnsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bluePawnsText, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightPanelLayout.createSequentialGroup()
                        .addComponent(redPawnsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(redPawnsText, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightPanelLayout.createSequentialGroup()
                        .addComponent(greenPawnsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(greenPawnsText, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                    .addComponent(updateCmd, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(rightPanelLayout.createSequentialGroup()
                        .addComponent(currentPlayerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(currentPlayerCombo, 0, 100, Short.MAX_VALUE))
                    .addGroup(rightPanelLayout.createSequentialGroup()
                        .addComponent(rollLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rollCombo, 0, 100, Short.MAX_VALUE))
                    .addGroup(rightPanelLayout.createSequentialGroup()
                        .addComponent(bonusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bonusCombo, 0, 100, Short.MAX_VALUE))
                    .addComponent(updateBoardCmd, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chosenActionText, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addComponent(actionsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addComponent(chosenActionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
                .addContainerGap())
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playerLabel)
                    .addComponent(playerCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yellowPawnsLabel)
                    .addComponent(yellowPawnsText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bluePawnsLabel)
                    .addComponent(bluePawnsText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(redPawnsLabel)
                    .addComponent(redPawnsText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(greenPawnsLabel)
                    .addComponent(greenPawnsText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentPlayerLabel)
                    .addComponent(currentPlayerCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rollLabel)
                    .addComponent(rollCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bonusLabel)
                    .addComponent(bonusCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateBoardCmd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(actionsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chosenActionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chosenActionText, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(updateCmd)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rightScroll.setViewportView(rightPanel);

        split.setRightComponent(rightScroll);

        getContentPane().add(split, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updateCmdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateCmdActionPerformed
        // TODO: invoke player
    }//GEN-LAST:event_updateCmdActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PlayerDebugFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel actionsLabel;
    private javax.swing.JList actionsList;
    private javax.swing.JLabel bluePawnsLabel;
    private javax.swing.JTextField bluePawnsText;
    private javax.swing.JComboBox bonusCombo;
    private javax.swing.JLabel bonusLabel;
    private javax.swing.JLabel chosenActionLabel;
    private javax.swing.JLabel chosenActionText;
    private javax.swing.JComboBox currentPlayerCombo;
    private javax.swing.JLabel currentPlayerLabel;
    private javax.swing.JLabel greenPawnsLabel;
    private javax.swing.JTextField greenPawnsText;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JComboBox playerCombo;
    private javax.swing.JLabel playerLabel;
    private javax.swing.JLabel redPawnsLabel;
    private javax.swing.JTextField redPawnsText;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JScrollPane rightScroll;
    private javax.swing.JComboBox rollCombo;
    private javax.swing.JLabel rollLabel;
    private javax.swing.JSplitPane split;
    private javax.swing.JButton updateBoardCmd;
    private javax.swing.JButton updateCmd;
    private javax.swing.JLabel yellowPawnsLabel;
    private javax.swing.JTextField yellowPawnsText;
    // End of variables declaration//GEN-END:variables
    private final ParchisPanel ludoPanel;
    private final Map<Color, javax.swing.JTextField> pawnsTexts;
    private Game game;
    private final List<Player> players;

    private void initModels() {
        // Player class combo
        playerCombo.setModel(new DefaultComboBoxModel(players.toArray()));

        // Current player combo
        DefaultComboBoxModel currentPlayerComboModel = new DefaultComboBoxModel();
        for (Color color: Color.values()) {
            currentPlayerComboModel.addElement(new LocalizedItem(getResourceMap(), color));
        }
        currentPlayerCombo.setModel(currentPlayerComboModel);
    }

    private ResourceMap getResourceMap() {
        return Application.getInstance(parchis.studio.ParchisStudioApp.class)
                .getContext().getResourceMap(PlayerDebugFrame.class);
    }


    private static List<Cell> parsePositions(Color color, String text) {
        List<Cell> positions = new LinkedList<Cell>();

        for (String token: text.split("(\\s|,)")) {
            Color positionColor;
            if (token.startsWith("_")) {
                positionColor = color;
                token = token.substring(1);
            } else {
                positionColor = null;
            }

            try {
                int number = Integer.parseInt(token);
                if (number >= 1 && ((number <= 68) || (number <= 8 && positionColor != null))) {
                    positions.add(new Cell(positionColor, number));
                } else {
                    // Out of range
                }
            } catch (NumberFormatException ex) {
                // not a number
            }
        }

        return positions;
    }

    @Action
    public void updateBoard() {
        Map<Color, List<Cell>> pawns = new HashMap<Color, List<Cell>>();

        for (Color color: Color.values()) {
                JTextField textField = this.pawnsTexts.get(color);
                pawns.put(color, parsePositions(color, textField.getText()));
        }

        Turn turn = new RollTurn(Color.values(), this.getCurrentPlayerColor());
        if (getBonus() != null) {
            turn = turn.move(getRoll(), getBonus());
        }
        this.game = new Game(turn, new Pawns(pawns));
        this.ludoPanel.setPawns(this.game.getPawns());
    }
    
    
    @Action
    public void play() {
        updateBoard();
        
        Player player = getPlayer();
        Choice choice = game.getChoice(new MockedDice(this.getRoll()));

        ListModel model = new LocalizedListModel(getResourceMap(), choice.getActions());
        actionsList.setModel(model);

        parchis.actions.Action chosenAction =
                player.chooseAction(game, choice.getActions());
        this.chosenActionText.setText(chosenAction.toLocalizedString(getResourceMap()));
    }

    private Color getCurrentPlayerColor() {
        return Color.values()[this.currentPlayerCombo.getSelectedIndex()];
    }

    private int getRoll() {
        return this.rollCombo.getSelectedIndex() + 1;
    }

    private Integer getBonus() {
        switch (bonusCombo.getSelectedIndex()) {
            case 1:
                return 10;

            case 2:
                return 20;

            case 3:
                return 40;

            default:
                return null;
        }
    }

    private Player getPlayer() {
        return this.players.get(playerCombo.getSelectedIndex());
    }
}
