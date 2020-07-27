---
title: NSEButton
description: Wiki
---

### What is it?

An NSEButton represents an interactable UI Button.

**Note: creating a button requires a *[ButtonType](ButtonType.md)***

### Method List

1. Constructor - `NSEButton(PVector position, PVector size, ButtonType type, String label, int baseColor, int textColor, PImage img, float fontSize, PFont font, PApplet main)`
  * The method creates a new button.
  * The "myApplet" parameter will represent the main app, filling it with a PApplet instance is recommended.
  * Example usage:
```java
NSEButton b = new NSEButton(new PVector(width/2,height/2),new PVector(300,100),ButtonType.Default,"Play",color(255),color(0),null,50,null,this);
```

2. Constructor - `NSEButton(float x, float y, float w, float h, ButtonType type, String label, int baseColor, int textColor, PImage img, float fontSize, PFont font, PApplet main)`
  * The method creates a new button.
  * The "myApplet" parameter will represent the main app, filling it with a PApplet instance is recommended.
  * Example usage:
```java
NSEButton b = new NSEButton(width/2,height/2,300,100,ButtonType.Default,"Play",color(255),color(0),null,50,null,this);
```

Common practice: When creating a button with the **Default** type, make sure to insert `null` in the `img` parameter as it will not be used, if no special font is required for the button insert `null` in the `font` parameter.

3. Set the active state - `NSEButton.setActive(boolean state)`
  * Sets the "active" state of the button
  * A button is only showed if it's active, if it's not it will not look for click events
  * Example usage:
```java
//imagine having a declared button b
b.setActive(false);
```

4. Check the active state - `NSEButton.isActive()`
  * Checks the "active" state of the button
  * Example usage:
```java
//imagine having a declared button b
if(b.isActive()) println("b is active");
```

5. Set the enabled state - `NSEButton.setEnabled(boolean state)`
  * Sets the "active" state of the button
  * If a button is not enabled it will not look for click events
  * Example usage:
```java
//imagine having a declared button b
b.setEnabled(false);
```

6. Check the enabled state - `NSEButton.isEnabled()`
  * Checks the "enabled" state of the button
  * Example usage:
```java
//imagine having a declared button b
if(b.isEnabled()) println("b is enabled");
```

7. Displaying the button - `NSEButton.display()`
  * Displays the button if it's active
  * Example usage:
```java
//imagine having a declared button b
b.display();
```

### Customizing a button's OnClick event

A level blueprint's content can be customized by creating methods with the following format `label+Click`.

Be careful with button naming, for example you might have different buttons in your game with the label `Back`, this is going to mean that every button with that label will point to that method. Easy fixes for that could be checking in which level the user is located into by using the **[LevelHandler](LevelHandler.md)** and make the button work accordingly. Other uses for this could also be a `Quit` button, every one of them pointing to the same method and acting the same without having to customize it for every button instance.

***WARNING: METHODS NAME ARE KEY SENSITIVE, MISTYPING A LEVEL NAME WILL RESULT IN THE ENGINE NOT FINDING THE METHOD AND RESULT IN AN ERROR***

```java
//customizing the click event for the "Play" button
void PlayClick()
{
    println("Loading game level...");
    LevelHandler.loadLevel("game");
}
```
