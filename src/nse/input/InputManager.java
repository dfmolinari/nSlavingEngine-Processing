package nse.input;

import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Input Management class used to handle input locally
 * without using processing's methods.
 *
 * @author Davide Furfaro
 */
public class InputManager {

    private PApplet myApplet;
    private Object myParent;

    /**
     * Indicates whether a key is being pressed or not.
     */
    public boolean keyPressed;

    /**
     * Indicates whether a mouse button is being pressed or not.
     */
    public boolean mousePressed;
    /**
     * Represents the button that is sending the event.
     */
    public int mouseButton;

    /**
     * A constructor, usually called from setup. ||
     * WARNING: myApplet and myParent can be different!
     *
     * @param myApplet main applet reference
     * @param myParent the class that contains this instance of the input manager
     */
    public InputManager(PApplet myApplet, Object myParent)
    {
        keyPressed = false;
        this.myApplet = myApplet;
        this.myParent = myParent;
        myApplet.registerMethod("keyEvent",this);
        myApplet.registerMethod("mouseEvent", this);
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
                keyPressed = true;
                onKeyPressed();
                break;
            case KeyEvent.RELEASE:
                keyPressed = false;
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
            for(Method m : myParent.getClass().getMethods())
            {
                if(m.getName().equalsIgnoreCase("nseKeyPressed"))
                    myParent.getClass().getMethod("nseKeyPressed").invoke(myParent);
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
            for(Method m : myParent.getClass().getMethods())
            {
                if(m.getName().equalsIgnoreCase("nseKeyReleased"))
                    myParent.getClass().getMethod("nseKeyReleased").invoke(myParent);
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
                mousePressed = true;
                mouseButton = e.getButton();
                onMousePressed();
                break;
            case MouseEvent.RELEASE:
                mousePressed = false;
                mouseButton = e.getButton();
                onMouseReleased();
                break;
            case MouseEvent.CLICK:
                mouseButton = e.getButton();
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
            for(Method m : myParent.getClass().getMethods())
            {
                if(m.getName().equalsIgnoreCase("nseMousePressed"))
                    myParent.getClass().getMethod("nseMousePressed").invoke(myParent);
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
            for(Method m : myParent.getClass().getMethods())
            {
                if(m.getName().equalsIgnoreCase("nseMouseReleased"))
                    myParent.getClass().getMethod("nseMouseReleased").invoke(myParent);
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
            for(Method m : myParent.getClass().getMethods())
            {
                if(m.getName().equalsIgnoreCase("nseMouseClicked"))
                    myParent.getClass().getMethod("nseMouseClicked").invoke(myParent);
            }
        } catch(ReflectiveOperationException e)
        {
            throw new RuntimeException(e);
        }
    }
}
