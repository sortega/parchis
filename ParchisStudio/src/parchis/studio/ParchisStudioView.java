/*
 * ParchisStudioView.java
 */
package parchis.studio;

import java.awt.BorderLayout;
import java.util.Set;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import parchis.*;
import parchis.ai.Player;
import parchis.dices.SeedDice;
import parchis.dices.Dice;
import parchis.studio.aidebug.PlayerDebugFrame;

/**
 * The application's main frame.
 */
public class ParchisStudioView extends FrameView {
    private Game game;
    private Choice choice;
    private Thread autoPlayThread;
    private NewGameDialog newGameDialog;
    private Map<Color, Player> players;

    public ParchisStudioView(SingleFrameApplication app) {
        super(app);

        initComponents();

        ludoPanel = new ParchisPanel(null);
        mainPanel.add(ludoPanel, BorderLayout.CENTER);

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });


        this.game = null;
        this.dice = new SeedDice() {

            @Override
            public int roll() {
                int r = super.roll();
                rollLabel.setText(getResourceMap().getString("rollLabel.text", r));
                return r;
            }
        };
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = ParchisStudioApp.getApplication().getMainFrame();
            aboutBox = new LudoStudioAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        ParchisStudioApp.getApplication().show(aboutBox);
    }

    @Action
    public void showPlayerDebugFrame() {
        PlayerDebugFrame playerDebugFrame = new PlayerDebugFrame();
        ParchisStudioApp.getApplication().show(playerDebugFrame);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        rightPanel = new javax.swing.JPanel();
        turnLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        actionList = new javax.swing.JList();
        playCmd = new javax.swing.JButton();
        rollLabel = new javax.swing.JLabel();
        autoPlayProgress = new javax.swing.JProgressBar();
        autoPlayCheck = new javax.swing.JCheckBox();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        newGameItem = new javax.swing.JMenuItem();
        debugPlayerItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setLayout(new java.awt.BorderLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(parchis.studio.ParchisStudioApp.class).getContext().getResourceMap(ParchisStudioView.class);
        rightPanel.setBackground(resourceMap.getColor("rightPanel.background")); // NOI18N
        rightPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        rightPanel.setName("rightPanel"); // NOI18N
        rightPanel.setPreferredSize(new java.awt.Dimension(250, 247));

        turnLabel.setText(" "); // NOI18N
        turnLabel.setName("turnLabel"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        actionList.setName("actionList"); // NOI18N
        jScrollPane1.setViewportView(actionList);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(parchis.studio.ParchisStudioApp.class).getContext().getActionMap(ParchisStudioView.class, this);
        playCmd.setAction(actionMap.get("play")); // NOI18N
        playCmd.setText(resourceMap.getString("playCmd.text")); // NOI18N
        playCmd.setName("playCmd"); // NOI18N

        rollLabel.setText(" "); // NOI18N
        rollLabel.setName("rollLabel"); // NOI18N

        autoPlayProgress.setDoubleBuffered(true);
        autoPlayProgress.setEnabled(false);
        autoPlayProgress.setName("autoPlayProgress"); // NOI18N

        autoPlayCheck.setText(resourceMap.getString("autoPlayCheck.text")); // NOI18N
        autoPlayCheck.setName("autoPlayCheck"); // NOI18N

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                    .addComponent(rollLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                    .addComponent(turnLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rightPanelLayout.createSequentialGroup()
                        .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(autoPlayProgress, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                            .addComponent(autoPlayCheck, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(playCmd)))
                .addContainerGap())
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(turnLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rollLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(rightPanelLayout.createSequentialGroup()
                        .addComponent(autoPlayCheck)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(autoPlayProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(rightPanelLayout.createSequentialGroup()
                        .addComponent(playCmd, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        mainPanel.add(rightPanel, java.awt.BorderLayout.LINE_END);

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setAction(actionMap.get("Nuevo juego")); // NOI18N
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        newGameItem.setAction(actionMap.get("newGame")); // NOI18N
        newGameItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.META_MASK));
        newGameItem.setText(resourceMap.getString("newGameItem.text")); // NOI18N
        newGameItem.setName("newGameItem"); // NOI18N
        fileMenu.add(newGameItem);

        debugPlayerItem.setAction(actionMap.get("showPlayerDebugFrame")); // NOI18N
        debugPlayerItem.setText(resourceMap.getString("debugPlayerItem.text")); // NOI18N
        debugPlayerItem.setName("debugPlayerItem"); // NOI18N
        fileMenu.add(debugPlayerItem);

        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 347, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    @Action()
    public void newGame() {
        if (newGameDialog == null) {
            JFrame mainFrame = ParchisStudioApp.getApplication().getMainFrame();
            newGameDialog = new NewGameDialog(mainFrame, true);
            newGameDialog.setLocationRelativeTo(mainFrame);
        }

        ParchisStudioApp.getApplication().show(newGameDialog);
        if (!newGameDialog.isCancelled()) {
            this.players= newGameDialog.getPlayers();
            this.setGame(new Game(Color.ALL_PLAYERS, Color.yellow));
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList actionList;
    private javax.swing.JCheckBox autoPlayCheck;
    private javax.swing.JProgressBar autoPlayProgress;
    private javax.swing.JMenuItem debugPlayerItem;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem newGameItem;
    private javax.swing.JButton playCmd;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JLabel rollLabel;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JLabel turnLabel;
    // End of variables declaration//GEN-END:variables
    private final ParchisPanel ludoPanel;
    private final Dice dice;

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;

    private void updateActionList() {
        if (this.choice == null) {
            this.actionList.setModel(new DefaultListModel());
            return;
        }
        Set<parchis.actions.Action> actions = choice.getActions();

        Player currentPlayer = players.get(game.getCurrentPlayer());
        GameActionsModel model;
        if (!currentPlayer.getClass().equals(HumanPlayer.class)) {
            parchis.actions.Action selectedAction = currentPlayer.chooseAction(game, actions);
            model = new GameActionsModel(selectedAction);
        } else {
            model = new GameActionsModel(actions);
        }
        this.actionList.setModel(model);
        this.actionList.setSelectedIndex(0);

        if (model.size() <= 1 && autoPlayCheck.isSelected()) {
            ParchisStudioView.this.getRootPane().setEnabled(false);
            autoPlayThread = new Thread() {
                private final int WAITING_TIME = 500;
                private final int STEP = 20;

                @Override
                public void run() {
                    autoPlayProgress.setEnabled(true);
                    autoPlayProgress.setMaximum(WAITING_TIME);

                    for (int i=0; i< WAITING_TIME / STEP; i++) {
                        autoPlayProgress.setValue(i*STEP);
                        try {
                            Thread.sleep(STEP);
                        } catch (InterruptedException ex) {}
                    }

                    autoPlayProgress.setEnabled(false);
                    autoPlayProgress.setValue(0);
                    ParchisStudioView.this.getRootPane().setEnabled(true);
                    play();
                }
            };
            autoPlayThread.start();
        }
    }

    private void setGame(Game game) {
        Color previousPlayer = null;
        if (this.game != null) {
            previousPlayer = this.game.getCurrentPlayer();
        }

        this.game = game;
        this.ludoPanel.setPawns(game.getPawns());

        if(game != null) {
            if (previousPlayer != game.getCurrentPlayer()) {
                this.rollLabel.setText("-");
            }

            this.turnLabel.setText(
                    getResourceMap().getString("turnLabel.text",
                    game.getCurrentPlayer().toLocalizedString(getResourceMap())));
            if (game.isFinished()) {
                this.choice = null;
            } else {
                this.choice = game.getChoice(dice);
            }
            updateActionList();

        } else {
            this.rollLabel.setText("-");
            this.turnLabel.setText("-");
            this.actionList.setModel(new DefaultListModel());
        }
    }

    @Action
    public void play() {
        if (actionList.getSelectedValue() != null) {
            GameActionItem actionItem = (GameActionItem) actionList.getSelectedValue();
            setGame(choice.choose(actionItem.getAction()));
        }
    }

    private class GameActionsModel extends DefaultListModel {
        public GameActionsModel(Set<parchis.actions.Action> actions) {
            for (parchis.actions.Action action: actions) {
                addElement(new GameActionItem(action));
            }
        }

        private GameActionsModel(parchis.actions.Action selectedAction) {
            addElement(new GameActionItem(selectedAction));
        }
    }

    private class GameActionItem { // TODO: replace with localized item
        private final parchis.actions.Action action;

        public GameActionItem(parchis.actions.Action action) {
            this.action = action;
        }

        @Override
        public String toString() {
            return action.toLocalizedString(getResourceMap());
        }

        public parchis.actions.Action getAction() {
            return action;
        }
    }

}
