# COEN275Group1
Java Swing 3D Game

to enable opengl rendering, add this flag to your run configurations
-Dsun.java2d.opengl=True

to fix java bug with key inputs use
```bash
defaults write -g ApplePressAndHoldEnabled -bool false
```
to reset use
```bash
defaults write -g ApplePressAndHoldEnabled -bool true
```