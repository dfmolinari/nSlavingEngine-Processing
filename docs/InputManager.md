---
title: InputManager
description: Wiki
---

### What is it?

The input manager will handle user Input, it is mainly used to setup custom input locally.

To import the InputManager into your project use `import nse.input.*`

***WARNING: WHEN USING THE INPUTMANAGER IN A CLASS, MAKE SURE THE CLASS IS DECLARED AS PUBLIC***

#### Method List
1. Constructor - `InputManager(PApplet myApplet, Object myParent)`
  * Will create a custom InputManager that can be used locally.
  * The "myApplet" parameter will represent the main app, filling it with a PApplet instance is recommended.
  * The "myParent" parameter will represent the parent class, usually filled with the `this` keyword.
  * Example usage:
```java
InputManager im = new InputManager(appRef, this);
```

### Customizing Events

To customize events as you would for example with processing's `void keyReleased()` the input manager will look for the following methods in it's parent class.

*Keep in mind that these methods are not necessary but will be used to customize different events*

1. Customizing the keyPressed event - `void nseKeyPressed()`
2. Customizing the keyReleased event - `void nseKeyReleased()`
3. Customizing the mousePressed event - `void nseMousePressed()`
4. Customizing the mouseReleased event - `void nseMouseReleased()`
5. Customizing the mouseClicked event - `void nseMouseClicked()`
