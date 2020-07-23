package nse.ui;

import processing.core.*;

public class NSEBox {

    private PApplet myApplet;

    private float x, y;
    private float w, h;
    private BoxType type;
    private int baseColor, textColor;
    private String label;
    private PImage img;
    private PFont font;
    private float fontSize;

    NSEBox(float x, float y, float w, float h, BoxType type, String label, int baseColor, int textColor, PImage img, float fontSize, PFont font, PApplet myApplet){
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
    }

    public void display(){
        if(type == BoxType.Default){
            myApplet.rectMode(myApplet.CENTER);
            myApplet.fill(baseColor);
            myApplet.rect(x,y,w,h);
            myApplet.fill(textColor);
            myApplet.textAlign(myApplet.CENTER, myApplet.CENTER);

            if(font != null)
                myApplet.textFont(font);

            myApplet.textSize(fontSize);
            myApplet.text(label,x,y,w,h);
            myApplet.fill(255);
            myApplet.rectMode(myApplet.CORNER);
        } else if(type == BoxType.Image){
            myApplet.imageMode(myApplet.CENTER);
            myApplet.image(img,x,y,w,h);
            myApplet.imageMode(myApplet.CORNER);
        } else if(type == BoxType.Mixed){
            myApplet.imageMode(myApplet.CENTER);
            myApplet.image(img,x,y,w,h);
            myApplet.imageMode(myApplet.CORNER);
            myApplet.fill(textColor);
            myApplet.textAlign(myApplet.CENTER,myApplet.CENTER);

            if(font != null)
                myApplet.textFont(font);
            
            myApplet.textSize(fontSize);
            myApplet.rectMode(myApplet.CENTER);
            myApplet.text(label,x,y,w,h);
            myApplet.rectMode(myApplet.CORNER);
            myApplet.fill(255);
        }
    }
}
