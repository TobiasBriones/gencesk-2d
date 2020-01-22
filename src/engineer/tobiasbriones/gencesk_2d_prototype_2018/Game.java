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

import engineer.tobiasbriones.gencesk_2d_prototype_2018.config.GameConfig;
import engineer.tobiasbriones.gencesk_2d_prototype_2018.view.RenderView;
import engineer.tobiasbriones.gencesk_2d_prototype_2018.view.RenderViewTickCallback;

import java.awt.*;

/**
 * Entry point of a game. It manages the game presentation and general
 * procedures.
 */
public abstract class Game {
    
    // ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ //
    //                                                                                                                //
    //                                                     CLASS                                                      //
    //                                                                                                                //
    // ______________________________________________________________________________________________________________ //
    
    private static final class RenderViewHandler implements RenderViewTickCallback {
        
        private final Game game;
        
        private RenderViewHandler(Game game) {
            this.game = game;
        }
        
        @Override
        public void update(long previousTickTimeMS) {
            game.getCurrentScene().update(previousTickTimeMS);
        }
        
        @Override
        public void paint(Graphics2D g2) {
            game.getCurrentScene().paint(g2);
        }
        
    }
    
    // ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ //
    //                                                                                                                //
    //                                                    INSTANCE                                                    //
    //                                                                                                                //
    // ______________________________________________________________________________________________________________ //
    
    private RenderView renderView;
    
    /**
     * Default constructor for <code>Game</code>.
     */
    public Game() {
        this.renderView = null;
    }
    
    /**
     * Returns the game configuration.
     *
     * @return the game configuration
     */
    protected abstract GameConfig getGameConfig();
    
    // TODO some playing options
    
    /**
     * Returns the current game's displaying scene.
     *
     * @return the current game's scene
     */
    protected abstract Scene getCurrentScene();
    
    /**
     * Adds the game's render view to the given view to display on.
     *
     * @param container view to display the game
     */
    public final void displayOn(Container container) {
        if (renderView == null) return;
        container.add(renderView);
    }
    
    /**
     * Runs the game.
     */
    public final void run() {
        final RenderViewTickCallback callback = new RenderViewHandler(this);
        this.renderView = onCreateRenderView(callback);
        
        onPrepare();
        renderView.play();
    }
    
    /**
     * Called when the game is going to be presented so the render view is requested to be created.
     *
     * @param callback render view thick callback which must be passed to the {@link RenderView} returned instance
     * @return the game's render view
     */
    protected abstract RenderView onCreateRenderView(RenderViewTickCallback callback);
    
    /**
     * Called when the game is being prepared to run, the first scene to show will be ready to go at this point and also
     * any other process prior the game start.
     */
    protected abstract void onPrepare();
    
}
