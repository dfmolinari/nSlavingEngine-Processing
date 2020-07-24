package nse.levels;

import processing.core.PApplet;

import java.lang.reflect.Method;
import java.security.InvalidParameterException;

/**
 * Level class used to define levels and their content.
 *
 * @author Davide Furfaro
 */
public class LevelBlueprint {

    private Object m_myParent;

    private String m_name;
    private int m_nId;

    protected boolean m_resetLevel;

    protected String m_start;
    protected String m_update;

    /**
     * A constructor, usually called when adding a new level from the LevelHandler
     * with LevelHandler.createLevel() ||
     * WARNING: Creating a level with an ID or name that already exists will result in an error!
     *
     * @param id the level ID
     * @param name the level name
     * @param myParent the main applet used
     */
    public LevelBlueprint(int id, String name, Object myParent){
        m_myParent = myParent;
        m_nId = id;
        m_name = name;

        m_start = name+"Start";
        m_update = name+"Update";

        m_resetLevel = true;

        for(LevelBlueprint n : LevelHandler.s_levelList)
        {
            if(id == n.m_nId) throw new InvalidParameterException("Cannot create level: " + id + "; A level with the same ID is already existing");
            else if(name.equalsIgnoreCase(n.m_name)) throw new InvalidParameterException("Cannot create level: " + name + "; A level with the same Name is already existing");
        }
    }

    /**
     * Executes the start method once then executes the update method.
     */
    public void content()
    {
        if(m_resetLevel)
        {
            try {
                m_myParent.getClass().getMethod(m_start).invoke(m_myParent);
            } catch(ReflectiveOperationException e)
            {
                throw new RuntimeException(e);
            }
            m_resetLevel = false;
        }
        try {
            m_myParent.getClass().getMethod(m_update).invoke(m_myParent);
        } catch(ReflectiveOperationException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the level ID.
     *
     * @return int
     */
    protected int getID() { return m_nId; }

    /**
     * Returns the level name.
     *
     * @return String
     */
    protected String getName() { return m_name; }
}
