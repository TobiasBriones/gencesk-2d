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

import engineer.tobiasbriones.gencesk_2d_prototype_2018.models.math.Metrics;
import engineer.tobiasbriones.gencesk_2d_prototype_2018.models.math.Transform;

/**
 * Object representing a point.
 */
@SuppressWarnings("unused")
public final class Point2D {
    
    private int x;
    private int y;
    
    /**
     * Constructor for <code>Point2D</code>.
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    @SuppressWarnings("WeakerAccess")
    public Point2D(int x, int y) {
        set(x, y);
    }
    
    /**
     * Default constructor for <code>Point2D</code> set at the origin.
     */
    @SuppressWarnings("WeakerAccess")
    public Point2D() {
        set(0, 0);
    }
    
    // ―――――――――――――――――――――――――――――――――――――――――――――――――  GETTERS  ―――――――――――――――――――――――――――――――――――――――――――――――――― //
    
    /**
     * Returns the x coordinate.
     *
     * @return the x coordinate
     */
    @SuppressWarnings("WeakerAccess")
    public int getX() {
        return x;
    }
    
    /**
     * Returns the y coordinate.
     *
     * @return the y coordinate
     */
    @SuppressWarnings("WeakerAccess")
    public int getY() {
        return y;
    }
    
    // ――――――――――――――――――――――――――――――――――――――――――――――  PUBLIC METHODS  ―――――――――――――――――――――――――――――――――――――――――――――― //
    /**
     * Sets the point coordinates.
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    @SuppressWarnings("WeakerAccess")
    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Returns the measured euclidean distance from this point to the given
     * point.
     *
     * @param point point to measure the distance
     * @return the euclidean distance from this point to the given point
     * @see Metrics
     */
    @SuppressWarnings("WeakerAccess")
    public double distance(Point2D point) {
        return Metrics.distance(this, point);
    }
    
    /**
     * Increases the x coordinates by the given amount.
     *
     * @param moveX move in x
     */
    @SuppressWarnings("WeakerAccess")
    public void moveX(int moveX) {
        x += moveX;
    }
    
    /**
     * Increases the y coordinates by the given amount.
     *
     * @param moveY move in y
     */
    @SuppressWarnings("WeakerAccess")
    public void moveY(int moveY) {
        y += moveY;
    }
    
    /**
     * Rotates this point about the given center and angle.
     *
     * @param center rotation center
     * @param angle  rotation angle
     * @see Transform
     */
    @SuppressWarnings("WeakerAccess")
    public void rotate(Point2D center, double angle) {
        Transform.rotate(this, center, angle);
    }
    
    /**
     * Rotates this point about the origin and with the given angle.
     *
     * @param angle rotation angle
     * @see Transform
     */
    @SuppressWarnings("WeakerAccess")
    public void rotateAboutOrigin(double angle) {
        Transform.rotate(this, 0, 0, angle);
    }
    
}
