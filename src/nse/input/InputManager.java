package nse.input;

import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import org.gamecontrolplus.*;

import java.util.HashMap;
import java.util.Map;

public class InputManager {

    private PApplet m_myApplet;
    protected final Map<Integer,Integer> m_keys = new HashMap<>();
    protected final Map<Integer,Integer> m_mouseButtons = new HashMap<>();
    protected final Map<String,Integer> m_gamepadButtons = new HashMap<>();
    protected final Map<String,Axis> m_axes = new HashMap<>();
    protected final Map<String,Button> m_buttons = new HashMap<>();

    //Controller input
    ControlIO control;
    ControlDevice gamepad;

    public InputManager(PApplet myApplet)
    {
        m_myApplet = myApplet;
        m_myApplet.registerMethod("keyEvent",this);
        m_myApplet.registerMethod("mouseEvent",this);
        control = ControlIO.getInstance(m_myApplet);
        initializeControlDevices();
    }

    private void initializeControlDevices()
    {
        gamepad = control.getMatchedDeviceSilent("gamepadData");
        if(gamepad == null) { System.out.println("no gamepad connected"); }
        else
        {
            for(int i = 0; i < gamepad.getNumberOfButtons()-1; i++)
            {
                gamepad.getButton(i).plug(this,"setButtonPress",ControlIO.ON_PRESS);
                gamepad.getButton(i).plug(this,"setButtonHold",ControlIO.WHILE_PRESS);
                gamepad.getButton(i).plug(this,"setButtonRelease",ControlIO.ON_RELEASE);
            }
        }
    }

    public void keyEvent(KeyEvent e)
    {
        switch(e.getAction())
        {
            case KeyEvent.PRESS:
                int k = e.getKeyCode();
                if(k == 27) m_myApplet.key = 0;
                Integer i = m_keys.putIfAbsent(k,1);
                break;
            case KeyEvent.RELEASE:
                m_keys.put(e.getKeyCode(),3);
                break;
        }
    }

    public void mouseEvent(MouseEvent e)
    {
        switch(e.getAction())
        {
            case MouseEvent.PRESS:
                Integer i = m_mouseButtons.putIfAbsent(e.getButton(),1);
                break;
            case MouseEvent.RELEASE:
                m_mouseButtons.put(e.getButton(),3);
                break;
        }
    }

    public void setButtonPress()
    {
        for(int i = 0; i < gamepad.getNumberOfButtons()-1; i++)
        {
            ControlButton b = gamepad.getButton(i);
            if(b.pressed())m_gamepadButtons.put(b.getName(),1);
        }
    }

    public void setButtonHold()
    {
        for(int i = 0; i < gamepad.getNumberOfButtons()-1; i++)
        {
            ControlButton b = gamepad.getButton(i);
            if(b.pressed())m_gamepadButtons.put(b.getName(),2);
        }
    }

    public void setButtonRelease()
    {
        for(int i = 0; i < gamepad.getNumberOfButtons()-1; i++)
        {
            ControlButton b = gamepad.getButton(i);
            if(!b.pressed() && m_gamepadButtons.containsKey(b.getName()))m_gamepadButtons.put(b.getName(),3);
        }
    }

    protected void createAxis(String name, String a, String b, String c, String d)
    {
        m_axes.put(name,new Axis(a,b,c,d));
    }

    protected void createButton(String name, String a, String b)
    {
        m_buttons.put(name,new Button(a,b));
    }

    protected class Axis
    {
        String positive, negative, altPositive, altNegative;

        Axis(String positive, String negative, String altPositive, String altNegative)
        {
            this.positive = positive;
            this.negative = negative;
            this.altPositive = altPositive;
            this.altNegative = altNegative;
        }

        public int axisToKey(String axis)
        {
            int key = 0;
            if(axis.length() == 1)
            {
                try{
                    int value = Integer.parseInt(axis);
                    key = value+48;
                } catch (NumberFormatException nfe)
                {
                    char k = axis.toUpperCase().charAt(0);
                    key = k;
                }
            } else
            {
                if(axis.equalsIgnoreCase("space")) key = 32;
                if(axis.equalsIgnoreCase("tab")) key = 9;
                if(axis.equalsIgnoreCase("esc")) key = 27;
                if(axis.equalsIgnoreCase("enter")) key = 10;

                //SHIFT
                if(axis.equalsIgnoreCase("shift")) key = 16;

                //CONTROL
                if(axis.equalsIgnoreCase("ctrl")) key = 17;

                //ALT
                if(axis.equalsIgnoreCase("alt")) key = 18;

                //ARROWS
                if(axis.equalsIgnoreCase("left")) key = 37;
                if(axis.equalsIgnoreCase("right")) key = 39;
                if(axis.equalsIgnoreCase("up")) key = 38;
                if(axis.equalsIgnoreCase("down")) key = 40;
            }
            return key;
        }
    }

    protected class Button
    {
        String main, alt;

        Button(String main, String alt)
        {
            this.main = main;
            this.alt = alt;
        }

        public int buttonToKey(String button)
        {
            int key = 0;
            if(button.length() == 1)
            {
                try{
                    int value = Integer.parseInt(button);
                    key = value+48;
                } catch (NumberFormatException nfe)
                {
                    char k = button.toUpperCase().charAt(0);
                    key = k;
                }
            } else
            {
                if(button.equalsIgnoreCase("space")) key = 32;
                if(button.equalsIgnoreCase("tab")) key = 9;
                if(button.equalsIgnoreCase("esc")) key = 27;
                if(button.equalsIgnoreCase("enter")) key = 10;

                //SHIFT
                if(button.equalsIgnoreCase("shift")) key = 16;

                //CONTROL
                if(button.equalsIgnoreCase("ctrl")) key = 17;

                //ALT
                if(button.equalsIgnoreCase("alt")) key = 18;

                //ARROWS
                if(button.equalsIgnoreCase("left")) key = 37;
                if(button.equalsIgnoreCase("right")) key = 39;
                if(button.equalsIgnoreCase("up")) key = 38;
                if(button.equalsIgnoreCase("down")) key = 40;
            }
            return key;
        }
    }
}