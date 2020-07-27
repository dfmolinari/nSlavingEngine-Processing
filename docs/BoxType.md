---
title: BoxType
description: Wiki
---

### What is it?

Represents a type of box, used to define which kind of box is supposed to be used on that instance.

### Types
  * **Default**
    * Defines a box with a colored rectangle and a label, no images involved
  * **Image**
    * Defines a box with an image and no label displayed
  * **Mixed**
    * Defines a box with an image background and a displayed label

Commonly used when creating a box and filling the `type` parameter by using `BoxType.type` where type stands for either `Default`, `Image` or `Mixed`
