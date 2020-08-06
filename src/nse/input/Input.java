package nse.input;

import nse.levels.LevelHandler;
import org.gamecontrolplus.ControlButton;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Input Management class used to handle input locally
 * without using processing's methods.
 *
 * @author Davide Furfaro
 */
public class Input {

    private static InputManager m_manager;

    public static void setup(InputManager manager)
    {
        m_manager = manager;
    }

    public static boolean getKeyDown(int keyCode)
    {
        char k = Character.toUpperCase((char)keyCode);
        if(m_manager.m_keys.containsKey((int)k)) return m_manager.m_keys.get((int)k) == 1;
        return false;
    }

    public static boolean getKeyDown(String keyCode)
    {
        if(keyCode.length() == 1)
        {
            try{
                int value = Integer.parseInt(keyCode);
                if(m_manager.m_keys.containsKey(value+48)) return m_manager.m_keys.get(value+48) == 1;
            } catch (NumberFormatException nfe)
            {
                char k = keyCode.toUpperCase().charAt(0);
                if(m_manager.m_keys.containsKey((int)k)) return m_manager.m_keys.get((int)k) == 1;
            }
        } else
        {
            if(keyCode.equalsIgnoreCase("space")) if(m_manager.m_keys.containsKey(32)) return m_manager.m_keys.get(32) == 1;
            if(keyCode.equalsIgnoreCase("tab")) if(m_manager.m_keys.containsKey(9)) return m_manager.m_keys.get(9) == 1;
            if(keyCode.equalsIgnoreCase("esc")) if(m_manager.m_keys.containsKey(27)) return m_manager.m_keys.get(27) == 1;
            if(keyCode.equalsIgnoreCase("enter")) if(m_manager.m_keys.containsKey(10)) return m_manager.m_keys.get(10) == 1;

            //SHIFT
            if(keyCode.equalsIgnoreCase("shift")) if(m_manager.m_keys.containsKey(16)) return m_manager.m_keys.get(16) == 1;

            //CONTROL
            if(keyCode.equalsIgnoreCase("ctrl")) if(m_manager.m_keys.containsKey(17)) return m_manager.m_keys.get(17) == 1;

            //ALT
            if(keyCode.equalsIgnoreCase("alt")) if(m_manager.m_keys.containsKey(18)) return m_manager.m_keys.get(18) == 1;

            //ARROWS
            if(keyCode.equalsIgnoreCase("left")) if(m_manager.m_keys.containsKey(37)) return m_manager.m_keys.get(37) == 1;
            if(keyCode.equalsIgnoreCase("right")) if(m_manager.m_keys.containsKey(39)) return m_manager.m_keys.get(39) == 1;
            if(keyCode.equalsIgnoreCase("up")) if(m_manager.m_keys.containsKey(38)) return m_manager.m_keys.get(38) == 1;
            if(keyCode.equalsIgnoreCase("down")) if(m_manager.m_keys.containsKey(40)) return m_manager.m_keys.get(40) == 1;
        }
        return false;
    }

    public static boolean getKey(int keyCode)
    {
        char k = Character.toUpperCase((char)keyCode);
        if(m_manager.m_keys.containsKey((int)k)) return m_manager.m_keys.get((int)k) == 2;
        return false;
    }

    public static boolean getKey(String keyCode)
    {
        if(keyCode.length() == 1)
        {
            boolean isNumber = false;
            try{
                int value = Integer.parseInt(keyCode);
                isNumber = true;
                if(m_manager.m_keys.containsKey(value+48)) return m_manager.m_keys.get(value+48) == 2;
            } catch (NumberFormatException nfe)
            {
                char k = keyCode.toUpperCase().charAt(0);
                if(m_manager.m_keys.containsKey((int)k)) return m_manager.m_keys.get((int)k) == 2;
            }
        } else
        {
            if(keyCode.equalsIgnoreCase("space")) if(m_manager.m_keys.containsKey(32)) return m_manager.m_keys.get(32) == 2;
            if(keyCode.equalsIgnoreCase("tab")) if(m_manager.m_keys.containsKey(9)) return m_manager.m_keys.get(9) == 2;
            if(keyCode.equalsIgnoreCase("esc")) if(m_manager.m_keys.containsKey(27)) return m_manager.m_keys.get(27) == 2;
            if(keyCode.equalsIgnoreCase("enter")) if(m_manager.m_keys.containsKey(10)) return m_manager.m_keys.get(10) == 2;

            //SHIFT
            if(keyCode.equalsIgnoreCase("shift")) if(m_manager.m_keys.containsKey(16)) return m_manager.m_keys.get(16) == 2;

            //CONTROL
            if(keyCode.equalsIgnoreCase("ctrl")) if(m_manager.m_keys.containsKey(17)) return m_manager.m_keys.get(17) == 2;

            //ALT
            if(keyCode.equalsIgnoreCase("alt")) if(m_manager.m_keys.containsKey(18)) return m_manager.m_keys.get(18) == 2;

            //ARROWS
            if(keyCode.equalsIgnoreCase("left")) if(m_manager.m_keys.containsKey(37)) return m_manager.m_keys.get(37) == 2;
            if(keyCode.equalsIgnoreCase("right")) if(m_manager.m_keys.containsKey(39)) return m_manager.m_keys.get(39) == 2;
            if(keyCode.equalsIgnoreCase("up")) if(m_manager.m_keys.containsKey(38)) return m_manager.m_keys.get(38) == 2;
            if(keyCode.equalsIgnoreCase("down")) if(m_manager.m_keys.containsKey(40)) return m_manager.m_keys.get(40) == 2;
        }
        return false;
    }

    public static boolean getKeyUp(int keyCode)
    {
        char k = Character.toUpperCase((char)keyCode);
        if(m_manager.m_keys.containsKey((int)k)) return m_manager.m_keys.get((int)k) == 3;
        return false;
    }

    public static boolean getKeyUp(String keyCode)
    {
        if(keyCode.length() == 1)
        {
            boolean isNumber = false;
            try{
                int value = Integer.parseInt(keyCode);
                isNumber = true;
                if(m_manager.m_keys.containsKey(value+48)) return m_manager.m_keys.get(value+48) == 3;
            } catch (NumberFormatException nfe)
            {
                char k = keyCode.toUpperCase().charAt(0);
                if(m_manager.m_keys.containsKey((int)k)) return m_manager.m_keys.get((int)k) == 3;
            }
        } else
        {
            if(keyCode.equalsIgnoreCase("space")) if(m_manager.m_keys.containsKey(32)) return m_manager.m_keys.get(32) == 3;
            if(keyCode.equalsIgnoreCase("tab")) if(m_manager.m_keys.containsKey(9)) return m_manager.m_keys.get(9) == 3;
            if(keyCode.equalsIgnoreCase("esc")) if(m_manager.m_keys.containsKey(27)) return m_manager.m_keys.get(27) == 3;
            if(keyCode.equalsIgnoreCase("enter")) if(m_manager.m_keys.containsKey(10)) return m_manager.m_keys.get(10) == 3;

            //SHIFT
            if(keyCode.equalsIgnoreCase("shift")) if(m_manager.m_keys.containsKey(16)) return m_manager.m_keys.get(16) == 3;

            //CONTROL
            if(keyCode.equalsIgnoreCase("ctrl")) if(m_manager.m_keys.containsKey(17)) return m_manager.m_keys.get(17) == 3;

            //ALT
            if(keyCode.equalsIgnoreCase("alt")) if(m_manager.m_keys.containsKey(18)) return m_manager.m_keys.get(18) == 3;

            //ARROWS
            if(keyCode.equalsIgnoreCase("left")) if(m_manager.m_keys.containsKey(37)) return m_manager.m_keys.get(37) == 3;
            if(keyCode.equalsIgnoreCase("right")) if(m_manager.m_keys.containsKey(39)) return m_manager.m_keys.get(39) == 3;
            if(keyCode.equalsIgnoreCase("up")) if(m_manager.m_keys.containsKey(38)) return m_manager.m_keys.get(38) == 3;
            if(keyCode.equalsIgnoreCase("down")) if(m_manager.m_keys.containsKey(40)) return m_manager.m_keys.get(40) == 3;
        }
        return false;
    }

    public static void createAxis(String name, String a, String b, String c, String d)
    {
        m_manager.createAxis(name,a,b,c,d);
    }

    public static void createButton(String name, String a, String b)
    {
        m_manager.createButton(name,a,b);
    }

    public static float getAxis(String axis)
    {
        if(m_manager.m_axes.containsKey(axis))
        {
            float axisValue = 0;
            String positive = m_manager.m_axes.get(axis).positive;
            String negative = m_manager.m_axes.get(axis).negative;
            String altPositive = m_manager.m_axes.get(axis).altPositive;
            String altNegative = m_manager.m_axes.get(axis).altNegative;
            if(m_manager.gamepad != null)
            {
                boolean gp = positive.contains("gamepad");
                boolean gn = negative.contains("gamepad");
                boolean gap = altPositive.contains("gamepad");
                boolean gan = altNegative.contains("gamepad");

                float posValue = gp ? m_manager.gamepad.getSlider(positive).getValue() : 0;
                float negValue = gn ? m_manager.gamepad.getSlider(negative).getValue() : 0;
                float altPosValue = gap ? m_manager.gamepad.getSlider(altPositive).getValue() : 0;
                float altNegValue = gan ? m_manager.gamepad.getSlider(altNegative).getValue() : 0;

                int pos = gp ? 0 : m_manager.m_axes.get(axis).axisToKey(positive);
                int neg = gn ? 0 : m_manager.m_axes.get(axis).axisToKey(negative);
                int altPos = gap ? 0 : m_manager.m_axes.get(axis).axisToKey(altPositive);
                int altNeg = gan ? 0 : m_manager.m_axes.get(axis).axisToKey(altNegative);

                float posTotal = gp ? posValue : pos > 0
                        ? m_manager.m_keys.containsKey(pos) ? 1 : 0 : 0;
                float negTotal = gn ? negValue : neg > 0
                        ? m_manager.m_keys.containsKey(neg) ? 1 : 0 : 0;
                float altPosTotal = gap ? altPosValue : altPos > 0
                        ? m_manager.m_keys.containsKey(altPos) ? 1 : 0 : 0;
                float altNegTotal = gan ? altNegValue : altNeg > 0
                        ? m_manager.m_keys.containsKey(altNeg) ? 1 : 0 : 0;

                axisValue = (posTotal - negTotal) + (altPosTotal - altNegTotal);
                axisValue = axisValue > 1 ? 1 : axisValue < -1 ? -1 : axisValue;
            } else {
                int pos = m_manager.m_axes.get(axis).axisToKey(positive);
                int neg = m_manager.m_axes.get(axis).axisToKey(negative);
                int altPos = m_manager.m_axes.get(axis).axisToKey(altPositive);
                int altNeg = m_manager.m_axes.get(axis).axisToKey(altNegative);

                axisValue = m_manager.m_keys.containsKey(pos)
                        || m_manager.m_keys.containsKey(altPos)
                        ? m_manager.m_keys.containsKey(neg)
                        || m_manager.m_keys.containsKey(altNeg) ? 0 : 1
                        : m_manager.m_keys.containsKey(neg)
                        || m_manager.m_keys.containsKey(altNeg) ? -1 : 0;
            }
            return axisValue;
        }
        return 0;
    }

    public static boolean getButtonDown(String button)
    {
        boolean returnValue = false;

        String main = m_manager.m_buttons.get(button).main;
        String alternative = m_manager.m_buttons.get(button).alt;
        if(m_manager.gamepad != null)
        {
            //System.out.print(m_manager.m_gamepadButtons.keySet());
            boolean gm = main.contains("Button");
            boolean ga = alternative.contains("Button");

            int km = gm ? 0 : m_manager.m_buttons.get(button).buttonToKey(main);
            int ka = ga ? 0 : m_manager.m_buttons.get(button).buttonToKey(alternative);

            boolean m = gm ? m_manager.m_gamepadButtons.containsKey(main) && m_manager.m_gamepadButtons.get(main) == 1
                           : getKeyDown(km);
            boolean a = ga ? m_manager.m_gamepadButtons.containsKey(alternative) && m_manager.m_gamepadButtons.get(alternative) == 1
                    : m_manager.m_buttons.get(button).buttonToKey(alternative) > 0 && m_manager.m_buttons.get(button).buttonToKey(alternative) == 1;

            returnValue = m || a;
        } else {
            int m = m_manager.m_buttons.get(button).buttonToKey(main);
            int a = m_manager.m_buttons.get(button).buttonToKey(alternative);

            returnValue = (m_manager.m_keys.containsKey(m) && m_manager.m_keys.get(m) == 1)  || (m_manager.m_keys.containsKey(a) && m_manager.m_keys.get(a) == 1);
        }

        return returnValue;
    }

    public static boolean getButton(String button)
    {
        boolean returnValue = false;

        String main = m_manager.m_buttons.get(button).main;
        String alternative = m_manager.m_buttons.get(button).alt;
        if(m_manager.gamepad != null)
        {
            //System.out.print(m_manager.m_gamepadButtons.keySet());
            boolean gm = main.contains("Button");
            boolean ga = alternative.contains("Button");

            int km = gm ? 0 : m_manager.m_buttons.get(button).buttonToKey(main);
            int ka = ga ? 0 : m_manager.m_buttons.get(button).buttonToKey(alternative);

            boolean m = gm ? m_manager.m_gamepadButtons.containsKey(main) && m_manager.m_gamepadButtons.get(main) == 2
                    : getKeyDown(km);
            boolean a = ga ? m_manager.m_gamepadButtons.containsKey(alternative) && m_manager.m_gamepadButtons.get(alternative) == 2
                    : m_manager.m_buttons.get(button).buttonToKey(alternative) > 0 && m_manager.m_buttons.get(button).buttonToKey(alternative) == 2;

            returnValue = m || a;
        } else {
            int m = m_manager.m_buttons.get(button).buttonToKey(main);
            int a = m_manager.m_buttons.get(button).buttonToKey(alternative);

            returnValue = (m_manager.m_keys.containsKey(m) && m_manager.m_keys.get(m) == 2)  || (m_manager.m_keys.containsKey(a) && m_manager.m_keys.get(a) == 2);
        }

        return returnValue;
    }

    public static boolean getButtonUp(String button)
    {
        boolean returnValue = false;

        String main = m_manager.m_buttons.get(button).main;
        String alternative = m_manager.m_buttons.get(button).alt;
        if(m_manager.gamepad != null)
        {
            //System.out.print(m_manager.m_gamepadButtons.keySet());
            boolean gm = main.contains("Button");
            boolean ga = alternative.contains("Button");

            int km = gm ? 0 : m_manager.m_buttons.get(button).buttonToKey(main);
            int ka = ga ? 0 : m_manager.m_buttons.get(button).buttonToKey(alternative);

            boolean m = gm ? m_manager.m_gamepadButtons.containsKey(main) && m_manager.m_gamepadButtons.get(main) == 3
                    : getKeyDown(km);
            boolean a = ga ? m_manager.m_gamepadButtons.containsKey(alternative) && m_manager.m_gamepadButtons.get(alternative) == 3
                    : m_manager.m_buttons.get(button).buttonToKey(alternative) > 0 && m_manager.m_buttons.get(button).buttonToKey(alternative) == 3;

            returnValue = m || a;
        } else {
            int m = m_manager.m_buttons.get(button).buttonToKey(main);
            int a = m_manager.m_buttons.get(button).buttonToKey(alternative);

            returnValue = (m_manager.m_keys.containsKey(m) && m_manager.m_keys.get(m) == 3)  || (m_manager.m_keys.containsKey(a) && m_manager.m_keys.get(a) == 3);
        }

        return returnValue;
    }

    public static boolean getMouseButtonDown(int button)
    {
        if(m_manager.m_mouseButtons.containsKey(button)) return m_manager.m_mouseButtons.get(button) == 1;
        return false;
    }

    public static boolean getMouseButton(int button)
    {
        if(m_manager.m_mouseButtons.containsKey(button)) return m_manager.m_mouseButtons.get(button) == 2;
        return false;
    }

    public static boolean getMouseButtonUp(int button)
    {
        if(m_manager.m_mouseButtons.containsKey(button)) return m_manager.m_mouseButtons.get(button) == 3;
        return false;
    }

    public static void updateKeyStatus()
    {
        for(Map.Entry<Integer,Integer> m : m_manager.m_keys.entrySet())
        {
            if(m_manager.m_keys.get(m.getKey()) == 1) m_manager.m_keys.put(m.getKey(),2);
        }

        for(Map.Entry<Integer,Integer> m : m_manager.m_mouseButtons.entrySet())
        {
            if(m_manager.m_mouseButtons.get(m.getKey()) == 1) m_manager.m_mouseButtons.put(m.getKey(),2);
        }

        if(m_manager.gamepad != null) m_manager.m_gamepadButtons.entrySet().removeIf(e -> e.getValue() == 3);

        m_manager.m_keys.entrySet().removeIf(e -> e.getValue() == 3);
        m_manager.m_mouseButtons.entrySet().removeIf(e -> e.getValue() == 3);
    }
}
