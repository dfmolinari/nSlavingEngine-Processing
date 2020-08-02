---
title: ButtonType
description: Wiki
---

### What is it?

Represents a type of button, used to define which kind of button is supposed to be used on that instance.

### Types
  * **Default**
    * Defines a button with a colored rectangle and a label, no images involved
  * **Image**
    * Defines a button with an image and no label displayed, keep in mind that the label is still going to be used to find a click event
  * **Mixed**
    * Defines a button with an image background and a displayed label

Commonly used when creating a button and filling the `type` parameter by using `ButtonType.type` where type stands for either `Default`, `Image` or `Mixed`
