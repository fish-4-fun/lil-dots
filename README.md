# lil-dots

Experimental concepts using [AnimatedVectorDrawable](https://developer.android.com/reference/android/graphics/drawable/AnimatedVectorDrawable.html) and [ShapeShifter](https://github.com/alexjlockwood/ShapeShifter).

We are lucky as Android developers that vectors and animated vectors are finally now supported. Before this _playful_ API was introduced, achieving the results below would likely involve subsclassing View, drawing to a canvas, managing animation listeners etc. The resutling code could be plentiful and verbose. For the most part, not even worth the hassle. Now, we can easily add these subtle improvements to our UI's and they are more easily managed to satisfy changing constraints. Imagine you need 4 dots, or triangles instead?

Progress indicator | Chat app concept
------------ | -------------
<img src="https://raw.githubusercontent.com/fish-4-fun/lil-dots/master/assets/screenshot.gif" width="320"> | <img src="https://raw.githubusercontent.com/fish-4-fun/lil-dots/master/assets/chat-concept.gif" width="320">
