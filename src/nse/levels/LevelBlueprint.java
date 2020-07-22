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

    private Object myParent;
    private Object[] args = new Object[1];

    protected String name;
    protected int id;

    protected boolean resetLevel;

    protected String start;
    protected String update;

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
        this.myParent = myParent;
        this.id = id;
        this.name = name;

        start = name+"Start";
        update = name+"Update";

        resetLevel = true;

        for(LevelBlueprint n : LevelHandler.levelList)
        {
            if(id == n.id) throw new InvalidParameterException("Cannot create level: " + id + "; A level with the same ID is already existing");
            else if(name.equalsIgnoreCase(n.name)) throw new InvalidParameterException("Cannot create level: " + name + "; A level with the same Name is already existing");
        }
    }

    /**
     * Executes the start method once then executes the update method.
     */
    public void content()
    {
        if(resetLevel)
        {
            try {
                myParent.getClass().getMethod(start).invoke(myParent);
            } catch(ReflectiveOperationException e)
            {
                throw new RuntimeException(e);
            }
            resetLevel = false;
        }
        try {
            myParent.getClass().getMethod(update).invoke(myParent);
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
    public int getID() { return id; }

    /**
     * Returns the level name.
     *
     * @return String
     */
    public String getName() { return name; }
}
