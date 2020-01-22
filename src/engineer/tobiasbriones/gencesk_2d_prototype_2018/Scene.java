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

import engineer.tobiasbriones.gencesk_2d_prototype_2018.graphics.Bitmap;

import java.awt.*;

/**
 * Game scene handles the logic of a particular scene of the game, containing
 * the frame buffer to draw the graphic outcome on it and so update the game's
 * render view.
 */
public abstract class Scene {
    
    private final Bitmap frameBuffer;
    private final Graphics2D frameBufferG2;
    
    /**
     * Default constructor for <code>Scene</code>, it builds the scene to later
     * display as the main content of the game.
     *
     * @param game game
     */
    public Scene(Game game) {
        this.frameBuffer = Bitmap.createBitmap(game.getGameConfig().getResolution());
        this.frameBufferG2 = frameBuffer.createGraphics();
        
        if (game.getGameConfig().isAntiAliasingEnabled()) {
            frameBufferG2.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY
            );
            frameBufferG2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
            );
            frameBufferG2.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR
            );
        }
    }
    
    final void paint(Graphics2D renderViewG2) {
        composeFrameBuffer(frameBufferG2);
        frameBuffer.draw(renderViewG2);
    }
    
    /**
     * Called when the scene is requested to be updated for this current tick.
     *
     * @param previousTickTimeMS time the last tick lasted
     */
    protected abstract void update(long previousTickTimeMS);
    
    /**
     * Called when is requested to compose or draw the scene graphic content to
     * the frame buffer which be automatically drawn into the render view.
     *
     * @param frameBufferG2 frame buffer graphics
     */
    protected abstract void composeFrameBuffer(Graphics2D frameBufferG2);
    
}
