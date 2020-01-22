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

package engineer.tobiasbriones.gencesk_2d_prototype_2018.view;

import engineer.tobiasbriones.gencesk_2d_prototype_2018.config.GameConfig;
import engineer.tobiasbriones.gencesk_2d_prototype_2018.models.Playable;

import javax.swing.*;
import java.awt.*;

/**
 * View rendering the whole game graphics into the screen, it
 * is updated and presents the game final outcome.
 */
@SuppressWarnings("unused")
public class RenderView extends JPanel implements Playable {
    
    // ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ //
    //                                                                                                                //
    //                                                     CLASS                                                      //
    //                                                                                                                //
    // ______________________________________________________________________________________________________________ //
    
    private static final int STRESSED_CPU_FREE_TIME_MS = 10;
    private static final int PAUSED_WAITING_TIME_MS = 60;
    
    public static int getFPSCount(long tickTimeMS) {
        return (int) (1 / ((System.currentTimeMillis() - tickTimeMS) / 1000.0F));
    }
    
    // ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ //
    //                                                                                                                //
    //                                                    INSTANCE                                                    //
    //                                                                                                                //
    // ______________________________________________________________________________________________________________ //
    
    private final RenderViewTickCallback callback;
    private final boolean hasAntiAliasing;
    private final float maxTimeMS;
    private final Runnable renderRunnable;
    private Thread renderThread;
    private volatile boolean isRunning;
    private volatile boolean isPaused;
    
    /**
     * Default <code>RenderView</code> constructor. It constructs a render view
     * to display the final graphic outcome of the game.
     *
     * @param callback callback for requesting each tick to update the rendering
     */
    @SuppressWarnings("WeakerAccess")
    public RenderView(RenderViewTickCallback callback, GameConfig gameConfig) {
        this.callback = callback;
        this.hasAntiAliasing = gameConfig.isAntiAliasingEnabled();
        this.maxTimeMS = 1000 / ((float) gameConfig.getFps());
        this.renderRunnable = () -> {
            isRunning = true;
            isPaused = false;
            long lastTickMS = System.currentTimeMillis();
            long lastTickTimeMS = 0;
            int currentTimeDifferenceMS;
            
            try {
                while (isRunning) {
                    if (isPaused) {
                        Thread.sleep(PAUSED_WAITING_TIME_MS);
                        lastTickMS = System.currentTimeMillis();
                        continue;
                    }
                    refresh(lastTickTimeMS);
                    lastTickTimeMS = (int) (System.currentTimeMillis() - lastTickMS);
                    currentTimeDifferenceMS = (int) (maxTimeMS - lastTickTimeMS);
                    Thread.sleep((currentTimeDifferenceMS > 0) ? currentTimeDifferenceMS : STRESSED_CPU_FREE_TIME_MS);
                    lastTickTimeMS = (int) (System.currentTimeMillis() - lastTickMS);
                    lastTickMS = System.currentTimeMillis();
                }
            }
            catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        };
        this.renderThread = null;
        this.isRunning = false;
        this.isPaused = false;
        
        setFocusable(true);
    }
    
    // ―――――――――――――――――――――――――――――――――――――――――――――――  BASE METHODS  ――――――――――――――――――――――――――――――――――――――――――――――― //
    @Override
    protected final void paintComponent(Graphics g) {
        final Graphics2D g2 = (Graphics2D) g;
        
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, getWidth(), getHeight());
        if (!isPlaying()) return;
        if (hasAntiAliasing) {
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        }
        g2.setColor(Color.BLACK);
        callback.paint(g2);
    }
    
    // ―――――――――――――――――――――――――――――――――――――――――――――――――  PLAYABLE  ――――――――――――――――――――――――――――――――――――――――――――――――― //
    @Override
    public boolean isPlaying() {
        return isRunning && !isPaused;
    }
    
    @Override
    public boolean isPaused() {
        return isPaused;
    }
    
    @Override
    public boolean isStopped() {
        return !isRunning;
    }
    
    @Override
    public boolean play() {
        if (isPlaying()) return false;
        if (isStopped()) {
            renderThread = new Thread(renderRunnable);
            
            renderThread.start();
        }
        else {
            isPaused = false;
        }
        return true;
    }
    
    @Override
    public boolean pause() {
        if (!isPlaying()) return false;
        isPaused = true;
        return true;
    }
    
    @Override
    public boolean stop() {
        if (isStopped()) return false;
        isRunning = false;
        
        try {
            renderThread.join();
        }
        catch (InterruptedException e) {
            System.err.println("Fail when joining render thread: " + e.getMessage());
        }
        isPaused = false;
        return true;
    }
    
    // ――――――――――――――――――――――――――――――――――――――――――――――  FINAL METHODS  ――――――――――――――――――――――――――――――――――――――――――――――― //
    private void refresh(long previousTickTimeMS) {
        callback.update(previousTickTimeMS);
        repaint();
    }
    
}
