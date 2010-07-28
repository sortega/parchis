package parchis.studio;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import parchis.ai.Player;
import org.jdesktop.application.Application;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class ParchisStudioApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new ParchisStudioView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    public List<Player> getRegisteredPlayers() {
        ResourceMap resourceMap = getContext().getResourceMap();
        String classNames = resourceMap.getString("App.Players.Classes");

        List<Player> players = new ArrayList<Player>();
        for (String className: classNames.split(",")) {
            try {
                Class clazz = Class.forName(className);
                Player player = (Player) clazz.getConstructor().newInstance();
                players.add(player);

            } catch (InstantiationException ex) {
                Logger.getLogger(NewGameDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(NewGameDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(NewGameDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(NewGameDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(NewGameDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(NewGameDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewGameDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return players;
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of ParchisStudioApp
     */
    public static ParchisStudioApp getApplication() {
        return Application.getInstance(ParchisStudioApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(ParchisStudioApp.class, args);
    }
}
