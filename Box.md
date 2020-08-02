---
title: NSEBox
description: Wiki
---

### What is it?

An NSEBox represents a UI TextBox.

**Note: creating a box requires a *[BoxType](BoxType.md)***

### Method List

1. Constructor - `NSEBox(PVector position, PVector size, BoxType type, String label, int baseColor, int textColor, PImage img, float fontSize, PFont font, PApplet myApplet)`
  * The method creates a new box.
  * The "myApplet" parameter will represent the main app, filling it with a PApplet instance is recommended.
  * Example usage:
```java
NSEBox b = new NSEBox(new PVector(width/2,height/2),new PVector(300,100),BoxType .Default,"Play",color(255),color(0),null,50,null,this);
```

2. Constructor - `NSEBox(float x, float y, float w, float h, BoxType type, String label, int baseColor, int textColor, PImage img, float fontSize, PFont font, PApplet myApplet)`
  * The method creates a new box.
  * The "myApplet" parameter will represent the main app, filling it with a PApplet instance is recommended.
  * Example usage:
```java
NSEBox b = new NSEBox(width/2,height/2,300,100,BoxType.Default,"A cool game!",color(255),color(0),null,50,null,this);
```

Common practice: When creating a box with the **Default** type, make sure to insert `null` in the `img` parameter as it will not be used, if no special font is required for the box insert `null` in the `font` parameter.

3. Displaying the box - `NSEBox.display()`
  * Displays the box
  * Example usage:
```java
//imagine having a declared box b
b.display();
```
