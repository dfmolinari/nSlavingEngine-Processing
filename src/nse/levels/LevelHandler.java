package nse.levels;

import nse.collisions.BoxCollider2D;
import nse.input.Input;
import nse.input.InputManager;
import nse.objects.NSEObject;
import nse.resizing.Rescaler;
import processing.core.PApplet;
import processing.core.PImage;

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

    static private InputManager s_im = null;

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
                return;
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
                return;
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
        if(s_im == null)
        {
            s_im = new InputManager(b.m_myApplet);
            Input.setup(s_im);
            Rescaler.setSize(b.m_myApplet.width,b.m_myApplet.height);
            s_currentLevel = b;
        }
        s_levelList.add(b);
    }

    /**
     * Displays the currently loaded level
     */
    public static void displayLevel()
    {
        Rescaler.setSize(s_currentLevel.m_myApplet.width,s_currentLevel.m_myApplet.height);
        float time = s_currentLevel.m_myApplet.millis();
        float deltaTime = (time - s_currentLevel.m_lastFrameTime)/1000;
        s_currentLevel.m_lastFrameTime = time;
        s_currentLevel.content(deltaTime);
        Input.updateKeyStatus();
    }

    public static void isManagerNull(){ if(s_im == null) System.out.print("null"); else System.out.println("not null");}

    public static int getCurrentLevelID() { return s_currentLevel.getID(); }
    public static String getCurrentLevelName() { return s_currentLevel.getName(); }

    public static void addObject(int id, NSEObject obj)
    {
        for(int i = 0; i < s_levelList.size(); i++)
        {
            if(s_levelList.get(i).getID() == id)
            {
                s_levelList.get(i).addGameObject(obj);
                return;
            }
        }
        throw new InvalidParameterException("Cannot add object to level: " + id + "; It does not exist");
    }

    public static void addObject(String name, NSEObject obj)
    {
        for(int i = 0; i < s_levelList.size(); i++)
        {
            if(s_levelList.get(i).getName() == name)
            {
                s_levelList.get(i).addGameObject(obj);
                return;
            }
        }
        throw new InvalidParameterException("Cannot add object to level: " + name + "; It does not exist");
    }

    public static NSEObject findObject(String name, String obj)
    {
        for(int i = 0; i < s_levelList.size(); i++)
        {
            if(s_levelList.get(i).getName() == name)
            {
                for(NSEObject ob : s_levelList.get(i).m_gameObjects)
                {
                    if(ob.getName().equalsIgnoreCase(obj)) return ob;
                }
                throw new InvalidParameterException("Cannot find object " + obj + "; It does not exist");
            }
        }
        throw new InvalidParameterException("Cannot add object to level: " + name + "; It does not exist");
    }

    public static NSEObject findObject(int id, String obj)
    {
        for(int i = 0; i < s_levelList.size(); i++)
        {
            if(s_levelList.get(i).getID() == id)
            {
                for(NSEObject ob : s_levelList.get(i).m_gameObjects)
                {
                    if(ob.getName().equalsIgnoreCase(obj)) return ob;
                }
                throw new InvalidParameterException("Cannot find object " + obj + "; It does not exist");
            }
        }
        throw new InvalidParameterException("Cannot add object to level: " + id + "; It does not exist");
    }

    public static void setBackground(String name, PImage image)
    {
        for(int i = 0; i < s_levelList.size(); i++)
        {
            if(s_levelList.get(i).getName() == name)
            {
                s_levelList.get(i).setBackground(image);
                return;
            }
        }
        throw new InvalidParameterException("Cannot set background to level: " + name + "; It does not exist");
    }

    public static void setBackground(int id, PImage image)
    {
        for(int i = 0; i < s_levelList.size(); i++)
        {
            if(s_levelList.get(i).getID() == id)
            {
                s_levelList.get(i).setBackground(image);
                return;
            }
        }
        throw new InvalidParameterException("Cannot set background to level: " + id + "; It does not exist");
    }

    public static void setBackground(String name, int color)
    {
        for(int i = 0; i < s_levelList.size(); i++)
        {
            if(s_levelList.get(i).getName() == name)
            {
                s_levelList.get(i).setBackground(color);
                return;
            }
        }
        throw new InvalidParameterException("Cannot add object to level: " + name + "; It does not exist");
    }

    public static void setBackground(int id, int color)
    {
        for(int i = 0; i < s_levelList.size(); i++)
        {
            if(s_levelList.get(i).getID() == id)
            {
                s_levelList.get(i).setBackground(color);
                return;
            }
        }
        throw new InvalidParameterException("Cannot set background to level: " + id + "; It does not exist");
    }

    public static PApplet getApp()
    {
        return s_currentLevel.m_myApplet;

    }
}
