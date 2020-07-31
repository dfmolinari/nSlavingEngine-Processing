package nse.objects;

import nse.levels.LevelHandler;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class NSEObject {

    private ArrayList<Object> m_components = new ArrayList<Object>();

    private String m_objectName;

    public NSEObject(String objectName)
    {
        m_objectName = objectName;
        addComponent(new NSETransform());
    }

    public void addComponent(Object o)
    {
        for(int i = 0; i < m_components.size(); i++)
        {
            if(m_components.get(i).getClass().isInstance(o)) throw new InvalidParameterException("Object already has a component of type " + o.getClass().getName());
        }
        if(o.getClass().isAssignableFrom(NSEScript.class))
            ((NSEScript)o).transform = this;
        m_components.add(o);
    }

    public <T> T getComponent(Class<T> comp)
    {
        for(Object o : m_components)
        {
            if(comp.isInstance(o)) return (T)o;
        }

        throw new InvalidParameterException("Cannot find component of type " + comp.getName());
    }

    public ArrayList<Object> getComponents() { return m_components; }

    public <T> boolean hasComponent(Class<T> comp)
    {
        for(Object o : m_components)
        {
            if(comp.isInstance(o)) return true;
        }
        return false;
    }
}
