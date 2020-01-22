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

package engineer.tobiasbriones.gencesk_2d_prototype_2018.config;

import engineer.tobiasbriones.gencesk_2d_prototype_2018.models.Dimension2D;

/**
 * Base to configure the game rendering and other options.
 */
@SuppressWarnings("unused")
public class GameConfig {
    
    private final Dimension2D resolution;
    private int fps;
    private boolean antiAliasing;
    private boolean lockFPS;
    
    /**
     * Default constructor for <code>GameConfig</code>. It sets a game
     * resolution to 800x600 pixels, {@link FPSConfig#HIGH_FPS}, anti aliasing
     * enabled, and lock fps to render the maximum specified number of fps.
     */
    public GameConfig() {
        this.resolution = new Dimension2D(800, 600);
        this.fps = FPSConfig.HIGH_FPS;
        this.antiAliasing = true;
        this.lockFPS = true;
    }
    
    // ―――――――――――――――――――――――――――――――――――――――――――――――――  SETTERS  ―――――――――――――――――――――――――――――――――――――――――――――――――― //
    
    /**
     * Sets the limit fps for rendering the game.
     *
     * @param fps maximum fps
     */
    public final void setFps(int fps) {
        this.fps = fps;
    }
    
    /**
     * Sets anti aliasing to tell the rendering whether to apply those settings.
     *
     * @param antiAliasing set anti aliasing
     */
    public final void setAntiAliasing(boolean antiAliasing) {
        this.antiAliasing = antiAliasing;
    }
    
    /**
     * Sets the maximum number of fps to be displayed disallowing any other fps
     * count as long as there is enough computational power. That means that if
     * the fps value is set to 60fps then the rendered will try to keep up with
     * those 60fps.
     *
     * @param lockFPS lock fps
     */
    public final void setLockFPS(boolean lockFPS) {
        this.lockFPS = lockFPS;
    }
    
    /**
     * Returns the game resolution.
     *
     * @return the game resolution
     */
    public final Dimension2D getResolution() {
        return resolution;
    }
    
    // ―――――――――――――――――――――――――――――――――――――――――――――――――  GETTERS  ―――――――――――――――――――――――――――――――――――――――――――――――――― //
    
    /**
     * Returns the game maximum fps to be rendered.
     *
     * @return the gam maximum fps
     */
    public final int getFps() {
        return fps;
    }
    
    /**
     * Returns <code>true</code> if and only if antialiasing is enabled.
     *
     * @return <code>true</code> if and only if antialiasing is enabled
     */
    public final boolean isAntiAliasingEnabled() {
        return antiAliasing;
    }
    
    /**
     * Returns <code>true</code> if and only if lock fps is enabled.
     *
     * @return <code>true</code> if and only if lock fps is enabled
     */
    public final boolean isLockFPSEnabled() {
        return lockFPS;
    }
    
    // ――――――――――――――――――――――――――――――――――――――――――――――  FINAL METHODS  ――――――――――――――――――――――――――――――――――――――――――――――― //
    
    /**
     * Sets the game resolution.
     *
     * @param width  width
     * @param height height
     */
    public final void setResolution(int width, int height) {
        resolution.set(width, height);
    }
    
}
