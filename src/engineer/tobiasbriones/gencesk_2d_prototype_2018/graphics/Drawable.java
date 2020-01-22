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

package engineer.tobiasbriones.gencesk_2d_prototype_2018.graphics;

import engineer.tobiasbriones.gencesk_2d_prototype_2018.models.Bounds;
import engineer.tobiasbriones.gencesk_2d_prototype_2018.models.Rect;

import java.awt.*;

/**
 * Base for any object that will be drawn on the screen.
 */
@SuppressWarnings("usused")
public abstract class Drawable {
    
    private final Bounds bounds;
    
    /**
     * Default constructor for Drawable.
     */
    @SuppressWarnings("WeakerAccess")
    public Drawable() {
        this.bounds = new Bounds();
    }
    
    /**
     * Returns the bounds of this object.
     *
     * @return the bounds of this object
     */
    @SuppressWarnings("WeakerAccess")
    public Bounds getBounds() {
        return bounds;
    }
    
    /**
     * Returns the rectangle of this object.
     *
     * @return the rectangle of this object
     */
    @SuppressWarnings("WeakerAccess")
    public Rect getRect() {
        return bounds.getRect();
    }
    
    /**
     * Called when it's required to draw this object into the passed graphics object.
     *
     * @param g2 graphics 2d
     */
    public abstract void draw(Graphics2D g2);
    
}
