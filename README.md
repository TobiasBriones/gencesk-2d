# Gencesk 2D Prototype
[![License](https://img.shields.io/github/license/TobiasBriones/gencesk-2d)](https://github.com/TobiasBriones/gencesk-2d/blob/master/LICENSE)

Gencesk 2D Prototype is a 2D game framework I wrote in Java back in 2018 for developing a simple 2D video game with AI algorithms.

Gencesk 2D is a game engine and framework to create native cross-platform 2D video games.

### Getting started
First, download the v0.1.0 jar file from the release [v0.1.0](https://github.com/TobiasBriones/gencesk-2d/releases/tag/v0.1.0) and add it as a dependency of your project.

Next, create a JFrame which will be the window to render the game.

```
public final class MainWindow extends JFrame {
    public MainWindow() {
        super("Fun game");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public Container getOutputContainer() {
        return getContentPane();
    }
    
    public void createUI() {
        final int width = FunGame.GAME_SIZE.getWidth();
        final int height = FunGame.GAME_SIZE.getWidth();
        
        setPreferredSize(new Dimension(width, height));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
```

Create a Game class to define your game in general.

```
public final class FunGame extends Game {
    public static final Dimension2D GAME_SIZE = new Dimension2D(960, 540);
    private final GameConfig gameConfig;
    private KeyEventHandler keyHandler;
    private Scene currentScene;
    
    public FunGame() {
        this.gameConfig = new GameConfig();
        this.keyHandler = null;
        this.currentScene = null;
        
        gameConfig.setResolution(GAME_SIZE.getWidth(), GAME_SIZE.getHeight());
        gameConfig.setFps(FPSConfig.HIGH_FPS);
    }
    
    @Override
    protected GameConfig getGameConfig() {
        return gameConfig;
    }
    
    @Override
    protected RenderView onCreateRenderView(RenderViewTickCallback renderViewTickCallback) {
        final RenderView renderView = new RenderView(renderViewTickCallback, gameConfig);
        keyHandler = new KeyEventHandler(renderView);
        return renderView;
    }
    
    @Override
    protected void onPrepare() {
        try {
            currentScene = new MainScene(this);
        }
        catch (Exception e) {
            final String msg = "Fail to load resources. " + e;
            
            JOptionPane.showMessageDialog(null, msg);
            e.printStackTrace();
            System.exit(-1);
        }
    }
    
    @Override
    protected Scene getCurrentScene() {
        return currentScene;
    }
    
    KeyEventHandler getKeyHandler() {
        return keyHandler;
    }
}
```

Create a Scene which will take care of loading assets, managing the game logic and rendering. You can have MenuScene, MainScene or other depending on what part of the game will appear on that scene.

```
public final class MainScene extends Scene {
    
    // Declare any resource or member required by this scene
    /*
        ...
     */

    public MainScene(Game game) {
        super(game);
        // Init your game and assets like images or audios
        /*
            ...
         */
    }
    
    @Override
    protected void update(long l) {
        // Define the logic of the game, it updates a certain amount of time per
        // second if the game goes smooth
        // For 60 FPS, this method is called every 16ms to update the game logic
        /*
            ...
         */
    }
    
    @Override
    protected void composeFrameBuffer(Graphics2D graphics2D) {
        // Paint your graphics in the graphics2D object!
        /*
            ...
         */
    }
    
}
```

To create a bitmap you can use.

```
this.background = Bitmap.createBitmap(new File("assets/background.png"));
```

and to render it in the composeFrameBuffer method just paint it.

```
background.draw(graphics2D);
```

You will be able to do many things like  playing audio, transforming the bitmap, add obstacles, handle input events and more.

#### Losnot in paradise
[![Losnot in paradise sample 1](https://raw.githubusercontent.com/TobiasBriones/images/master/gencesk-2d/gencesk-2d-prototype-2018/lostnot-in-paradise-sample-1.gif)](https://github.com/TobiasBriones/images/tree/master/gencesk-2d)
<p align=center><strong>Losnot in paradise - First version of the game created in Gencesk 2D Prototype 2018</strong></p>

This is the game I mention in *My Story* section below, I was able to create the game with little to no overhead about managing game rendering and all the underlying process since the framework prototype does its job quite decently. Later I was able to add the AI algorithm so that the game plays by itself.

Losnot in paradise is open source and you may check the project to see how easily a game like this is created, after checking this documentation you should be able to understand the framework prototype and the game.

[Link to the Losnot in paradise repository](https://github.com/TobiasBriones/losnot-in-paradise)

### Some documentation
I will provide next, definition, description and diagrams of the Gencesk 2D Prototype.

#### Definition
It gives the capabilities when being implemented of:
- Build a complete 2D game
- Focus on just the game as project and not on the logic to render the game or any other kind of game engine task
- Allow the native usage of machine learning algorithms to create research from the games created in this framework

#### Description
Framework that provides a simple but powerful frame to build any kind of 2D game on Java.

Also recall that this prototype was not finished due the creation of the new Gencesk 2D framework, so some definitions won't be satisfied in this prototype.

#### Diagrams
The following images are informal representations of what I did. Let's start with the package structure of the project.

[![Package Structure](https://raw.githubusercontent.com/TobiasBriones/images/master/gencesk-2d/gencesk-2d-prototype-2018/package-structure.png)](https://github.com/TobiasBriones/images/tree/master/gencesk-2d)
<p align=center><strong>Package Structure</strong></p>

It contains the main packages and classes in the source code. The packages are filled in yellow and a description of each item is given in green text.

------

The following image represents the objects that interact with main objects in the framework.

[![Instance Object Hierarchy](https://raw.githubusercontent.com/TobiasBriones/images/master/gencesk-2d/gencesk-2d-prototype-2018/instance-object-hierarchy.png)](https://github.com/TobiasBriones/images/tree/master/gencesk-2d)
<p align=center><strong>Instance Object Hierarchy</strong></p>

------

This diagram shows the flowchart since starting the game through the EDT until rendering the graphics. It is basically how the game flows in its life cycle.

[![Flowchart](https://raw.githubusercontent.com/TobiasBriones/images/master/gencesk-2d/gencesk-2d-prototype-2018/basic-2d-game.png)](https://github.com/TobiasBriones/images/tree/master/gencesk-2d)
<p align=center><strong>Basic 2D Game</strong></p>

------

Finally I have something interesting which is the key pool algorithm. When running the game, many keys from the input can be registered by the io module, since the keys may be duplicated and there are many inputs for just one key being pressed, that is, the system constantly sends inputs for just one key to use in the game, we only care if the key was pressed and take an action with that user input, so in the end many key objects are instantiated and what we have to do is to create a pool that collects *instances* of keys to reuse them and avoid creating and garbage collecting a ridiculous amount of keys entering and being read by the game for many times per second. If the pool is empty create a new key object, if not, take one and reuse it.

[![KeyPool Algorithm](https://raw.githubusercontent.com/TobiasBriones/images/master/gencesk-2d/gencesk-2d-prototype-2018/keypool-algorithm.png)](https://github.com/TobiasBriones/images/tree/master/gencesk-2d)
<p align=center><strong>KeyPool Algorithm</strong></p>

### Disclaimer
Gencesk 2D Prototype 2018 is a prototype I wrote at that time, it should work really well at least in most of its usages but it might not fully work or it might have bugs. The project may also be incomplete as a game framework. For the actual game framework visit the master branch in this repository.

### License
Gencesk 2D and Gencesk 2D Prototype 2018 are licensed under the [BSD 3-Clause License](https://github.com/TobiasBriones/gencesk-2d/blob/prototype-2018/LICENSE).

Images are licensed under the [CC-BY-4.0 License](https://github.com/TobiasBriones/images/blob/master/LICENSE) and available at [Gencesk 2D Prototype 2018 Images](https://github.com/TobiasBriones/images/tree/master/gencesk-2d/gencesk-2d-prototype-2018).

### My Story
After several experiences of mine in the past about developing games and studying how a game engine works in general, in 2018 I started to read about AI for games and started to develop a simple game to be able to create an AI algorithm supposed to learn how to play and complete the game through evolution, I particularly rather write my own code than accepting that of others that I don't completely agree with, so I was like, I don't develop games and don't want to mess up with stuff like Unity or game libraries, at that point I was willing to write the game framework and the game and the AI for the game, so great, right there, I just added one interesting project more to my valuable professional portfolio. I needed some technical basis and I had them, although I've never been a game developer but I've been interested in the engines architectures and surrounding engineering, since back in the time (about 2013) when I started adventuring in programming with Java and Android development I also tried with silly games and rendering things with threads and all that, so I was able to create this framework and now in 2020 I decided to review it, collect my old diagrams about the project, publish it and create Gencesk 2D with a new horizon chock full of possibilities.
