package nse.input;

import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.lang.reflect.Method;

/**
 * Input Management class used to handle input locally
 * without using processing's methods.
 *
 * @author Davide Furfaro
 */
public class InputManager {

    private PApplet m_myApplet;
    private Object m_myParent;

    /**
     * A constructor, usually called from setup. ||
     * WARNING: myApplet and myParent can be different!
     *
     * @param myApplet main applet reference
     * @param myParent the class that contains this instance of the input manager
     */
    public InputManager(PApplet myApplet, Object myParent)
    {
        m_myApplet = myApplet;
        m_myParent = myParent;
        m_myApplet.registerMethod("keyEvent",this);
        m_myApplet.registerMethod("mouseEvent", this);
    }

    /**
     * Handles key pressed and released events setting useful variables
     * such as the key that sent the event or whether a key is being
     * held or not.
     */
    public void keyEvent(KeyEvent e)
    {
        switch(e.getAction())
        {
            case KeyEvent.PRESS:
                onKeyPressed();
                break;
            case KeyEvent.RELEASE:
                onKeyReleased();
                break;
        }
    }

    /**
     * Handles the keyPressed custom event in case the parent class contains an
     * event called "nseKeyPressed".
     */
    private void onKeyPressed()
    {
        try {
            for(Method m : m_myParent.getClass().getMethods())
            {
                if(m.getName().equalsIgnoreCase("nseKeyPressed"))
                    m_myParent.getClass().getMethod("nseKeyPressed").invoke(m_myParent);
            }
        } catch(ReflectiveOperationException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles the keyReleased custom event in case the parent class contains an
     * event called "nseKeyReleased".
     */
    private void onKeyReleased()
    {
        try {
            for(Method m : m_myParent.getClass().getMethods())
            {
                if(m.getName().equalsIgnoreCase("nseKeyReleased"))
                    m_myParent.getClass().getMethod("nseKeyReleased").invoke(m_myParent);
            }
        } catch(ReflectiveOperationException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles mouse pressed, released and clicked events setting useful variables
     * such as the button that sent the event or whether a button is being
     * held or not.
     */
    public void mouseEvent(MouseEvent e)
    {
        switch(e.getAction())
        {
            case MouseEvent.PRESS:
                onMousePressed();
                break;
            case MouseEvent.RELEASE:
                onMouseReleased();
                break;
            case MouseEvent.CLICK:
                onMouseClicked();
                break;
        }
    }

    /**
     * Handles the mousePressed custom event in case the parent class contains an
     * event called "nseMousePressed".
     */
    private void onMousePressed()
    {
        try {
            for(Method m : m_myParent.getClass().getMethods())
            {
                if(m.getName().equalsIgnoreCase("nseMousePressed"))
                    m_myParent.getClass().getMethod("nseMousePressed").invoke(m_myParent);
            }
        } catch(ReflectiveOperationException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles the mouseReleased custom event in case the parent class contains an
     * event called "nseMouseReleased".
     */
    private void onMouseReleased()
    {
        try {
            for(Method m : m_myParent.getClass().getMethods())
            {
                if(m.getName().equalsIgnoreCase("nseMouseReleased"))
                    m_myParent.getClass().getMethod("nseMouseReleased").invoke(m_myParent);
            }
        } catch(ReflectiveOperationException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles the mouseClicked custom event in case the parent class contains an
     * event called "nseMouseClicked".
     */
    private void onMouseClicked()
    {
        try {
            for(Method m : m_myParent.getClass().getMethods())
            {
                if(m.getName().equalsIgnoreCase("nseMouseClicked"))
                    m_myParent.getClass().getMethod("nseMouseClicked").invoke(m_myParent);
            }
        } catch(ReflectiveOperationException e)
        {
            throw new RuntimeException(e);
        }
    }
}
