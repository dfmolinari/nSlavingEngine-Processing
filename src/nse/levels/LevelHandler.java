package nse.levels;

import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * Level Management class used to Create, Load and Display levels for your game.
 *
 * @author Davide Furfaro
 */
public class LevelHandler {

    static protected ArrayList<LevelBlueprint> s_levelList = new ArrayList<LevelBlueprint>();

    static private LevelBlueprint s_currentLevel = null;

    /**
     * Sets the current level to the level with the corresponding ID ||
     * WARNING: Trying to load a level with a non-existing ID will result in an error!
     *
     * @param id the ID of the level to load
     */
    public static void loadLevel(int id)
    {
        s_currentLevel = null;
        for(LevelBlueprint n : s_levelList)
        {
            if(n.getID() == id)
            {
                s_currentLevel = n;
                s_currentLevel.m_resetLevel = true;
            }
        }
        if(s_currentLevel == null) throw new InvalidParameterException("Cannot load level: " + id + "; It does not exist");
    }

    /**
     * Sets the current level to the level with the corresponding name ||
     * WARNING: Trying to load a level with a non-existing name will result in an error!
     *
     * @param name the name of the level to load
     */
    public static void loadLevel(String name)
    {
        s_currentLevel = null;
        for(LevelBlueprint n : s_levelList)
        {
            if(n.getName().equalsIgnoreCase(name))
            {
                s_currentLevel = n;
                s_currentLevel.m_resetLevel = true;
            }
        }
        if(s_currentLevel == null) throw new InvalidParameterException("Cannot load level: " + name + "; It does not exist");
    }

    /**
     * Gets a new LevelBlueprint and adds it to the existing level list
     *
     * @param b the LevelBlueprint to add to the level list
     */
    public static void createLevel(LevelBlueprint b){
        s_levelList.add(b);
    }

    /**
     * Displays the currently loaded level
     */
    public static void displayLevel()
    {
        s_currentLevel.content();
    }

    public static int getCurrentLevelID() { return s_currentLevel.getID(); }
    public static String getCurrentLevelName() { return s_currentLevel.getName(); }
}
