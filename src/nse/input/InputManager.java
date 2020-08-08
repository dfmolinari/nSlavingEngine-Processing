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
        initializeControlDevices();
    }

    protected void initializeControlDevices()
    {
        control = ControlIO.getInstance(m_myApplet);
        gamepad = control.getMatchedDeviceSilent("gamepadData");
        if(gamepad == null) { System.out.println("no gamepad connected"); }
        /*else
        {
            for(int i = 0; i < gamepad.getNumberOfButtons()-1; i++)
            {
                gamepad.getButton(i).plug(this,"setButtonPress",ControlIO.ON_PRESS);
                gamepad.getButton(i).plug(this,"setButtonHold",ControlIO.WHILE_PRESS);
                gamepad.getButton(i).plug(this,"setButtonRelease",ControlIO.ON_RELEASE);
            }
        }*/
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
        ControlButton b;
        if(gamepad.getButton("Button 0").pressed() && !m_gamepadButtons.containsKey("Button 0"))
        {
            b = gamepad.getButton("Button 0");
            m_gamepadButtons.put(b.getName(),1);
            return;
        } else if (gamepad.getButton("Button 0").pressed() && m_gamepadButtons.containsKey("Button 0") && m_gamepadButtons.get("Button 0") == 1)
        {
            b = gamepad.getButton("Button 0");
            m_gamepadButtons.put(b.getName(),2);
            return;
        }

        if(gamepad.getButton("Button 1").pressed() && !m_gamepadButtons.containsKey("Button 1"))
        {
            b = gamepad.getButton("Button 1");
            m_gamepadButtons.put(b.getName(),1);
            return;
        } else if (gamepad.getButton("Button 1").pressed() && m_gamepadButtons.containsKey("Button 1") && m_gamepadButtons.get("Button 1") == 1)
        {
            b = gamepad.getButton("Button 1");
            m_gamepadButtons.put(b.getName(),2);
            return;
        }

        if(gamepad.getButton("Button 2").pressed() && !m_gamepadButtons.containsKey("Button 2"))
        {
            b = gamepad.getButton("Button 2");
            m_gamepadButtons.put(b.getName(),1);
            return;
        } else if (gamepad.getButton("Button 2").pressed() && m_gamepadButtons.containsKey("Button 2") && m_gamepadButtons.get("Button 2") == 1)
        {
            b = gamepad.getButton("Button 2");
            m_gamepadButtons.put(b.getName(),2);
            return;
        }

        if(gamepad.getButton("Button 3").pressed() && !m_gamepadButtons.containsKey("Button 3"))
        {
            b = gamepad.getButton("Button 3");
            m_gamepadButtons.put(b.getName(),1);
            return;
        } else if (gamepad.getButton("Button 3").pressed() && m_gamepadButtons.containsKey("Button 3") && m_gamepadButtons.get("Button 3") == 1)
        {
            b = gamepad.getButton("Button 3");
            m_gamepadButtons.put(b.getName(),2);
            return;
        }

        if(gamepad.getButton("Button 4").pressed() && !m_gamepadButtons.containsKey("Button 4"))
        {
            b = gamepad.getButton("Button 4");
            m_gamepadButtons.put(b.getName(),1);
            return;
        } else if (gamepad.getButton("Button 4").pressed() && m_gamepadButtons.containsKey("Button 4") && m_gamepadButtons.get("Button 4") == 1)
        {
            b = gamepad.getButton("Button 4");
            m_gamepadButtons.put(b.getName(),2);
            return;
        }

        if(gamepad.getButton("Button 5").pressed() && !m_gamepadButtons.containsKey("Button 5"))
        {
            b = gamepad.getButton("Button 5");
            m_gamepadButtons.put(b.getName(),1);
            return;
        } else if (gamepad.getButton("Button 5").pressed() && m_gamepadButtons.containsKey("Button 5") && m_gamepadButtons.get("Button 5") == 1)
        {
            b = gamepad.getButton("Button 5");
            m_gamepadButtons.put(b.getName(),2);
            return;
        }

        if(gamepad.getButton("Button 6").pressed() && !m_gamepadButtons.containsKey("Button 6"))
        {
            b = gamepad.getButton("Button 6");
            m_gamepadButtons.put(b.getName(),1);
            return;
        } else if (gamepad.getButton("Button 6").pressed() && m_gamepadButtons.containsKey("Button 6") && m_gamepadButtons.get("Button 6") == 1)
        {
            b = gamepad.getButton("Button 6");
            m_gamepadButtons.put(b.getName(),2);
            return;
        }

        if(gamepad.getButton("Button 7").pressed() && !m_gamepadButtons.containsKey("Button 7"))
        {
            b = gamepad.getButton("Button 7");
            m_gamepadButtons.put(b.getName(),1);
            return;
        } else if (gamepad.getButton("Button 7").pressed() && m_gamepadButtons.containsKey("Button 7") && m_gamepadButtons.get("Button 7") == 1)
        {
            b = gamepad.getButton("Button 7");
            m_gamepadButtons.put(b.getName(),2);
            return;
        }

        if(gamepad.getButton("Button 8").pressed() && !m_gamepadButtons.containsKey("Button 8"))
        {
            b = gamepad.getButton("Button 8");
            m_gamepadButtons.put(b.getName(),1);
            return;
        } else if (gamepad.getButton("Button 8").pressed() && m_gamepadButtons.containsKey("Button 8") && m_gamepadButtons.get("Button 8") == 1)
        {
            b = gamepad.getButton("Button 8");
            m_gamepadButtons.put(b.getName(),2);
            return;
        }

        if(gamepad.getButton("Button 9").pressed() && !m_gamepadButtons.containsKey("Button 9"))
        {
            b = gamepad.getButton("Button 9");
            m_gamepadButtons.put(b.getName(),1);
            return;
        } else if (gamepad.getButton("Button 9").pressed() && m_gamepadButtons.containsKey("Button 9") && m_gamepadButtons.get("Button 9") == 1)
        {
            b = gamepad.getButton("Button 9");
            m_gamepadButtons.put(b.getName(),2);
            return;
        }
    }

    public void setButtonRelease()
    {
        ControlButton b;
        if(!gamepad.getButton("Button 0").pressed() && m_gamepadButtons.containsKey("Button 0") && m_gamepadButtons.get("Button 0") < 3)
        {
            b = gamepad.getButton("Button 0");
            m_gamepadButtons.put(b.getName(),3);
            return;
        } else if(!gamepad.getButton("Button 0").pressed() && m_gamepadButtons.containsKey("Button 0") && m_gamepadButtons.get("Button 0") == 3)
        {
            b = gamepad.getButton("Button 0");
            m_gamepadButtons.put(b.getName(),4);
            return;
        }

        if(!gamepad.getButton("Button 1").pressed() && m_gamepadButtons.containsKey("Button 1") && m_gamepadButtons.get("Button 1") < 3)
        {
            b = gamepad.getButton("Button 1");
            m_gamepadButtons.put(b.getName(),3);
            return;
        } else if(!gamepad.getButton("Button 1").pressed() && m_gamepadButtons.containsKey("Button 1") && m_gamepadButtons.get("Button 1") == 3)
        {
            b = gamepad.getButton("Button 1");
            m_gamepadButtons.put(b.getName(),4);
            return;
        }

        if(!gamepad.getButton("Button 2").pressed() && m_gamepadButtons.containsKey("Button 2") && m_gamepadButtons.get("Button 2") < 3)
        {
            b = gamepad.getButton("Button 2");
            m_gamepadButtons.put(b.getName(),3);
            return;
        } else if(!gamepad.getButton("Button 2").pressed() && m_gamepadButtons.containsKey("Button 2") && m_gamepadButtons.get("Button 2") == 3)
        {
            b = gamepad.getButton("Button 2");
            m_gamepadButtons.put(b.getName(),4);
            return;
        }

        if(!gamepad.getButton("Button 3").pressed() && m_gamepadButtons.containsKey("Button 3") && m_gamepadButtons.get("Button 3") < 3)
        {
            b = gamepad.getButton("Button 3");
            m_gamepadButtons.put(b.getName(),3);
            return;
        } else if(!gamepad.getButton("Button 3").pressed() && m_gamepadButtons.containsKey("Button 3") && m_gamepadButtons.get("Button 3") == 3)
        {
            b = gamepad.getButton("Button 3");
            m_gamepadButtons.put(b.getName(),4);
            return;
        }

        if(!gamepad.getButton("Button 4").pressed() && m_gamepadButtons.containsKey("Button 4") && m_gamepadButtons.get("Button 4") < 3)
        {
            b = gamepad.getButton("Button 4");
            m_gamepadButtons.put(b.getName(),3);
            return;
        } else if(!gamepad.getButton("Button 4").pressed() && m_gamepadButtons.containsKey("Button 4") && m_gamepadButtons.get("Button 4") == 3)
        {
            b = gamepad.getButton("Button 4");
            m_gamepadButtons.put(b.getName(),4);
            return;
        }

        if(!gamepad.getButton("Button 5").pressed() && m_gamepadButtons.containsKey("Button 5") && m_gamepadButtons.get("Button 5") < 3)
        {
            b = gamepad.getButton("Button 5");
            m_gamepadButtons.put(b.getName(),3);
            return;
        } else if(!gamepad.getButton("Button 5").pressed() && m_gamepadButtons.containsKey("Button 5") && m_gamepadButtons.get("Button 5") == 3)
        {
            b = gamepad.getButton("Button 5");
            m_gamepadButtons.put(b.getName(),4);
            return;
        }

        if(!gamepad.getButton("Button 6").pressed() && m_gamepadButtons.containsKey("Button 6") && m_gamepadButtons.get("Button 6") < 3)
        {
            b = gamepad.getButton("Button 6");
            m_gamepadButtons.put(b.getName(),3);
            return;
        } else if(!gamepad.getButton("Button 6").pressed() && m_gamepadButtons.containsKey("Button 6") && m_gamepadButtons.get("Button 6") == 3)
        {
            b = gamepad.getButton("Button 6");
            m_gamepadButtons.put(b.getName(),4);
            return;
        }

        if(!gamepad.getButton("Button 7").pressed() && m_gamepadButtons.containsKey("Button 7") && m_gamepadButtons.get("Button 7") < 3)
        {
            b = gamepad.getButton("Button 7");
            m_gamepadButtons.put(b.getName(),3);
            return;
        } else if(!gamepad.getButton("Button 7").pressed() && m_gamepadButtons.containsKey("Button 7") && m_gamepadButtons.get("Button 7") == 3)
        {
            b = gamepad.getButton("Button 7");
            m_gamepadButtons.put(b.getName(),4);
            return;
        }

        if(!gamepad.getButton("Button 8").pressed() && m_gamepadButtons.containsKey("Button 8") && m_gamepadButtons.get("Button 8") < 3)
        {
            b = gamepad.getButton("Button 8");
            m_gamepadButtons.put(b.getName(),3);
            return;
        } else if(!gamepad.getButton("Button 8").pressed() && m_gamepadButtons.containsKey("Button 8") && m_gamepadButtons.get("Button 8") == 3)
        {
            b = gamepad.getButton("Button 8");
            m_gamepadButtons.put(b.getName(),4);
            return;
        }

        if(!gamepad.getButton("Button 9").pressed() && m_gamepadButtons.containsKey("Button 9") && m_gamepadButtons.get("Button 9") < 3)
        {
            b = gamepad.getButton("Button 9");
            m_gamepadButtons.put(b.getName(),3);
            return;
        } else if(!gamepad.getButton("Button 9").pressed() && m_gamepadButtons.containsKey("Button 9") && m_gamepadButtons.get("Button 9") == 3)
        {
            b = gamepad.getButton("Button 9");
            m_gamepadButtons.put(b.getName(),4);
            return;
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