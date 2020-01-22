/*
 * BSD 3-Clause License
 *
 * Copyright (c) 2020, Tobias Briones
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package engineer.tobiasbriones.gencesk_2d_prototype_2018;

import engineer.tobiasbriones.gencesk_2d_prototype_2018.config.FPSConfig;
import engineer.tobiasbriones.gencesk_2d_prototype_2018.config.GameConfig;
import engineer.tobiasbriones.gencesk_2d_prototype_2018.io.KeyEventHandler;
import engineer.tobiasbriones.gencesk_2d_prototype_2018.view.RenderView;
import engineer.tobiasbriones.gencesk_2d_prototype_2018.view.RenderViewTickCallback;

import javax.swing.*;
import java.awt.*;

/**
 * This class is here to try results, it's not part of the prototype.
 */
public class Main {
    
    private static final class Window extends JFrame {
        
        private Window() {
            final Game game = new TestGame();
            
            game.run();
            game.displayOn(getContentPane());
            setPreferredSize(new Dimension(800, 400));
            pack();
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
        
    }
    
    private static final class TestScene extends Scene {
        
        private TestGame game;
        
        public TestScene(TestGame game) {
            super(game);
            this.game = game;
        }
        
        @Override
        protected void update(long timeMS) {
            // You can check the input keys in the console when updating
            // Set the FPS config to 1 to slow down the output but the key has to be
            // pressed during 1 second so the game detects the input
            // That's because 1 fps is unplayable of course!
            
            //System.out.println(timeMS);
            System.out.println("KEY EVENTS:");
            game.keyEventHandler.retrieve(System.out::println);
            System.out.println("-------------------------------");
        }
        
        @Override
        protected void composeFrameBuffer(Graphics2D frameBufferG2) {
            // draw bitmaps or something
        }
        
    }
    
    private static class TestGame extends Game {
        
        private GameConfig gc;
        private Scene mainScene;
        private KeyEventHandler keyEventHandler;
        
        private TestGame() {
            gc = new GameConfig();
            mainScene = new TestScene(this);
            
            gc.setFps(FPSConfig.LOW_FPS);
        }
        
        @Override
        protected RenderView onCreateRenderView(RenderViewTickCallback callback) {
            final RenderView rv = new RenderView(callback, gc);
            
            keyEventHandler = new KeyEventHandler(rv);
            return rv;
        }
        
        @Override
        protected void onPrepare() {
        
        }
        
        @Override
        protected GameConfig getGameConfig() {
            return gc;
        }
        
        @Override
        protected Scene getCurrentScene() {
            return mainScene;
        }
        
    }
    
    public static void main(String[] a) {
        new Window();
    }
    
}
