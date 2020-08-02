package nse.ui;

import nse.input.InputManager;
import nse.objects.NSEObject;
import nse.objects.NSETransform;
import processing.core.*;

public class NSEButton {
    private PApplet m_myApplet;

    private InputManager m_inputManager;

    public NSEObject gameObject;
    public NSETransform transform;

    private ButtonType m_type;

    //as color
    private int m_baseColor, m_textColor;

    private PImage m_img;

    private PFont m_font;
    private float m_fontSize;
    private String m_label;

    private boolean m_isEnabled;
    private boolean m_isActive;

    public NSEButton(ButtonType type, String label, int baseColor, int textColor, PImage img, float fontSize, PFont font, PApplet main){
        m_type = type;
        m_label = label;
        m_baseColor = baseColor;
        m_textColor = textColor;
        m_img = img;
        m_fontSize = fontSize;
        m_font = font;
        m_myApplet = main;
        m_inputManager = new InputManager(m_myApplet,this);
        m_isEnabled = true;
        m_isActive = true;
    }

    public NSEButton(ButtonType type, String label, int baseColor, int textColor, PImage img, float fontSize, PApplet main){
        m_type = type;
        m_label = label;
        m_baseColor = baseColor;
        m_textColor = textColor;
        m_img = img;
        m_fontSize = fontSize;
        m_font = null;
        m_myApplet = main;
        m_inputManager = new InputManager(m_myApplet,this);
        m_isEnabled = true;
        m_isActive = true;
    }

    public void display(){
        if(m_isActive) {
            if (m_type == ButtonType.Default) {
                m_myApplet.rectMode(m_myApplet.CENTER);
                m_myApplet.fill(m_baseColor);
                m_myApplet.rect(transform.position.x, transform.position.y, transform.scale.x, transform.scale.y);
                m_myApplet.fill(m_textColor);
                m_myApplet.textAlign(m_myApplet.CENTER, m_myApplet.CENTER);

                if(m_font != null)
                    m_myApplet.textFont(m_font);

                m_myApplet.textSize(m_fontSize);
                m_myApplet.text(m_label, transform.position.x, transform.position.y, transform.scale.x, transform.scale.y);
                m_myApplet.fill(255);
                m_myApplet.rectMode(m_myApplet.CORNER);
            } else if (m_type == ButtonType.Image) {
                m_myApplet.imageMode(m_myApplet.CENTER);
                m_myApplet.image(m_img, transform.position.x, transform.position.y, transform.scale.x, transform.scale.y);
                m_myApplet.imageMode(m_myApplet.CORNER);
            } else if (m_type == ButtonType.Mixed) {
                m_myApplet.imageMode(m_myApplet.CENTER);
                m_myApplet.image(m_img, transform.position.x, transform.position.y, transform.scale.x, transform.scale.y);
                m_myApplet.imageMode(m_myApplet.CORNER);
                m_myApplet.fill(m_textColor);
                m_myApplet.textAlign(m_myApplet.CENTER, m_myApplet.CENTER);

                if(m_font != null)
                    m_myApplet.textFont(m_font);

                m_myApplet.textSize(m_fontSize);
                m_myApplet.rectMode(m_myApplet.CENTER);
                m_myApplet.text(m_label, transform.position.x, transform.position.y, transform.scale.x, transform.scale.y);
                m_myApplet.rectMode(m_myApplet.CORNER);
                m_myApplet.fill(255);
            }
        }
    }

    public void setActive(boolean state){
        m_isActive = state;
    }
    public boolean isActive(){
        return m_isActive;
    }

    public void setEnabled(boolean state) { m_isEnabled = state; }
    public boolean isEnabled() { return m_isEnabled; }

    public void nseMouseClicked()
    {
        if(m_isEnabled && m_isActive && mouseOver())
        {
            try
            {
                m_myApplet.getClass().getMethod(m_label+"Click").invoke(m_myApplet);
            } catch (ReflectiveOperationException e)
            {
                throw new RuntimeException(e);
            }

        }
    }

    private boolean mouseOver(){
        if(m_myApplet.mouseX >= transform.position.x-transform.scale.x/2 && m_myApplet.mouseX <= transform.position.x+transform.scale.x/2 && m_myApplet.mouseY >= transform.position.y-transform.scale.y/2 && m_myApplet.mouseY <= transform.position.y+transform.scale.y/2) return true;

        return false;
    }
}
