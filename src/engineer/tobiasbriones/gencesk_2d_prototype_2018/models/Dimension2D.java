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

package engineer.tobiasbriones.gencesk_2d_prototype_2018.models;

/**
 * Holder of bi-dimensional size values.
 */
@SuppressWarnings("unused")
public final class Dimension2D {
    
    private int width;
    private int height;
    
    /**
     * Constructor for <code>Dimension2D</code>.
     *
     * @param width  width
     * @param height height
     */
    @SuppressWarnings("WeakerAccess")
    public Dimension2D(int width, int height) {
        set(width, height);
    }
    
    /**
     * Default constructor for <code>Dimension2D</code> with no size.
     */
    @SuppressWarnings("WeakerAccess")
    public Dimension2D() {
        set(0, 0);
    }
    
    // ―――――――――――――――――――――――――――――――――――――――――――――――――  GETTERS  ―――――――――――――――――――――――――――――――――――――――――――――――――― //
    
    /**
     * Returns the width.
     *
     * @return the width
     */
    @SuppressWarnings("WeakerAccess")
    public int getWidth() {
        return width;
    }
    
    /**
     * Returns the height.
     *
     * @return the height
     */
    @SuppressWarnings("WeakerAccess")
    public int getHeight() {
        return height;
    }
    
    // ――――――――――――――――――――――――――――――――――――――――――――――  PUBLIC METHODS  ―――――――――――――――――――――――――――――――――――――――――――――― //
    
    /**
     * Sets the values for this dimension.
     *
     * @param width  width
     * @param height height
     */
    @SuppressWarnings("WeakerAccess")
    public void set(int width, int height) {
        if (width < 0 || height < 0) {
            final String msg = String.format(
                "The dimensions are non-negative integers, width: %d, height: %d",
                width,
                height
            );
            throw new IllegalArgumentException(msg);
        }
        this.width = width;
        this.height = height;
    }
    
}
