# Gencesk 2D Prototype
[![License](https://img.shields.io/github/license/TobiasBriones/gencesk-2d)](https://github.com/TobiasBriones/gencesk-2d/blob/master/LICENSE)

Gencesk 2D Prototype is a 2D game framework I wrote in Java back in 2018 for developing a simple 2D video game with AI algorithms.

Gencesk 2D is a game engine and framework to create native cross-platform 2D video games.

### Getting started
First, download the v0.1.0 jar file from the release [v0.1.0](https://github.com/TobiasBriones/gencesk-2d/releases/tag/v0.1.0) and add it as a dependency of your project.

to be continued...

### Disclaimer
Gencesk 2D Prototype 2018 is a prototype I wrote at that time, it should work really well at least in most of its usages but it might not fully work or it might have bugs. The project may also be incomplete as a game framework. For the actual game framework visit the master branch in this repository.

### Some documentation
The following images are informal representations of what I did. Let's start with the package structure of the project.

[![package-structure](https://raw.githubusercontent.com/TobiasBriones/images/master/gencesk-2d/gencesk-2d-prototype-2018/package-structure.png)](https://github.com/TobiasBriones/images/tree/master/gencesk-2d)
<p align=center><strong>Package Structure</strong></p>

It contains the main packages and classes in the source code. The packages are filled in yellow and a description of each item is given in green text.

------

The following image represents the objects that interact with main objects in the framework.

[![instance-object-hierarchy](https://raw.githubusercontent.com/TobiasBriones/images/master/gencesk-2d/gencesk-2d-prototype-2018/instance-object-hierarchy.png)](https://github.com/TobiasBriones/images/tree/master/gencesk-2d)
<p align=center><strong>Instance Object Hierarchy</strong></p>

------

This diagram shows the flowchart since starting the game through the EDT until rendering the graphics. It is basically how the game flows in its life cycle.

[![flowchart](https://raw.githubusercontent.com/TobiasBriones/images/master/gencesk-2d/gencesk-2d-prototype-2018/basic-2d-game.png)](https://github.com/TobiasBriones/images/tree/master/gencesk-2d)
<p align=center><strong>Basic 2D Game</strong></p>

------

Finally I have something interesting which is the key pool algorithm. When running the game, many keys from the input can be registered by the io module, since the keys may be duplicated and there are many inputs for just one key being pressed, that is, the system constantly sends inputs for just one key to use in the game, we only care if the key was pressed and take an action with that user input, so in the end many key objects are instantiated and what we have to do is to create a pool that collects *instances* of keys to reuse them and avoid creating and garbage collecting a ridiculous amount of keys entering and being read by the game for many times per second. If the pool is empty create new objects, if not, take one and reuse it.

[![keypool-algorithm](https://raw.githubusercontent.com/TobiasBriones/images/master/gencesk-2d/gencesk-2d-prototype-2018/keypool-algorithm.png)](https://github.com/TobiasBriones/images/tree/master/gencesk-2d)
<p align=center><strong>KeyPool Algorithm</strong></p>

### My Story
After several experiences of mine in the past about developing games and studying how a game engine works in general, in 2018 I started to read about AI for games and started to develop a simple game to be able to create an AI algorithm supposed to learn how to play and complete the game through evolution, I particularly rather write my own code than accepting that of others that I don't completely agree with, so I was like, I don't develop games and don't want to mess up with stuff like Unity or game libraries, at that point I was willing to write the game framework and the game and the AI for the game, so great, right there, I just added one interesting project more to my valuable professional portfolio. I needed some technical basis and I had them, although I've never been a game developer but I've been interested in the engines architectures and surrounding engineering, since back in the time (about 2013) when I started adventuring in programming with Java and Android development I also tried with silly games and rendering things with threads and all that, so I was able to create this framework and now in 2020 I decided to review it, collect my old diagrams about the project, publish it and create Gencesk 2D with a new horizon chock full of possibilities.
