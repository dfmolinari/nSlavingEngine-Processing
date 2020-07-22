package nse.ui;

import nse.input.InputManager;
import processing.core.*;

public class NSEButton {
    private PApplet myApplet;

    private InputManager im;

    private float x, y;
    private float w, h;

    private ButtonType type;

    private int baseColor, textColor;

    private PImage img;

    private PFont font;
    private float fontSize;
    private String label;

    private boolean isEnabled;
    private boolean isActive;

    public NSEButton(float x, float y, float w, float h, ButtonType type, String label, int baseColor, int textColor, PImage img, float fontSize, PFont font, PApplet main){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.type = type;
        this.label = label;
        this.baseColor = baseColor;
        this.textColor = textColor;
        this.img = img;
        this.fontSize = fontSize;
        this.font = font;
        this.myApplet = main;
        im = new InputManager(myApplet,this);
        isEnabled = true;
        isActive = true;
    }

    public void display(){
        if(isActive) {
            if (type == ButtonType.Default) {
                myApplet.rectMode(myApplet.CENTER);
                myApplet.fill(baseColor);
                myApplet.rect(x, y, w, h);
                myApplet.fill(textColor);
                myApplet.textAlign(myApplet.CENTER, myApplet.CENTER);

                if(font != null)
                    myApplet.textFont(font);

                myApplet.textSize(fontSize);
                myApplet.text(label, x, y, w, h);
                myApplet.fill(255);
                myApplet.rectMode(myApplet.CORNER);
            } else if (type == ButtonType.Image) {
                myApplet.imageMode(myApplet.CENTER);
                myApplet.image(img, x, y, w, h);
                myApplet.imageMode(myApplet.CORNER);
            } else if (type == ButtonType.Mixed) {
                myApplet.imageMode(myApplet.CENTER);
                myApplet.image(img, x, y, w, h);
                myApplet.imageMode(myApplet.CORNER);
                myApplet.fill(textColor);
                myApplet.textAlign(myApplet.CENTER, myApplet.CENTER);

                if(font != null)
                    myApplet.textFont(font);

                myApplet.textSize(fontSize);
                myApplet.rectMode(myApplet.CENTER);
                myApplet.text(label, x, y, w, h);
                myApplet.rectMode(myApplet.CORNER);
                myApplet.fill(255);
            }
        }
    }

    public void setActive(boolean state){
        isActive = state;
    }
    public boolean isActive(){
        return isActive;
    }

    public void setEnabled(boolean state) { isEnabled = state; }
    public boolean isEnabled() { return isEnabled; }

    public void nseMouseClicked()
    {
        if(isEnabled && isActive && mouseOver())
        {
            try
            {
                myApplet.getClass().getMethod(label+"Click").invoke(myApplet);
            } catch (ReflectiveOperationException e)
            {
                throw new RuntimeException(e);
            }

        }
    }

    public boolean mouseOver(){
        if(myApplet.mouseX >= x-w/2 && myApplet.mouseX <= x+w/2 && myApplet.mouseY >= y-h/2 && myApplet.mouseY <= y+h/2) return true;

        return false;
    }
}
