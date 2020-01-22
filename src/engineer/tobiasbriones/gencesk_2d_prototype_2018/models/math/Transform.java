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

package engineer.tobiasbriones.gencesk_2d_prototype_2018.models.math;

import engineer.tobiasbriones.gencesk_2d_prototype_2018.models.Point2D;

/**
 * Applies transformations.
 */
@SuppressWarnings("unused")
public final class Transform {
    
    /**
     * Returns the x coordinate of performing the specified rotation.
     *
     * @param x     x
     * @param y     y
     * @param cx    center of rotation x
     * @param cy    center of rotation y
     * @param angle angle
     * @return the x coordinate of performing the specified rotation
     */
    public static int rotateX(int x, int y, int cx, int cy, double angle) {
        return (int) ((x - cx) * Math.cos(angle) - (y - cy) * Math.sin(angle)) + cx;
    }
    
    /**
     * Returns the y coordinate of performing the specified rotation.
     *
     * @param x     x
     * @param y     y
     * @param cx    center of rotation x
     * @param cy    center of rotation y
     * @param angle angle
     * @return the y coordinate of performing the specified rotation
     */
    public static int rotateY(int x, int y, int cx, int cy, double angle) {
        return (int) ((x - cx) * Math.sin(angle) + (y - cy) * Math.cos(angle)) + cy;
    }
    
    /**
     * Returns the x coordinate of performing the specified rotation.
     *
     * @param x     x
     * @param y     y
     * @param angle angle
     * @return the x coordinate of performing the specified rotation
     */
    @SuppressWarnings("WeakerAccess")
    public static int rotateX(int x, int y, double angle) {
        return (int) (x * Math.cos(angle) - y * Math.sin(angle));
    }
    
    /**
     * Returns the y coordinate of performing the specified rotation.
     *
     * @param x     x
     * @param y     y
     * @param angle angle
     * @return the y coordinate of performing the specified rotation
     */
    @SuppressWarnings("WeakerAccess")
    public static int rotateY(int x, int y, double angle) {
        return (int) (x * Math.sin(angle) + y * Math.cos(angle));
    }
    
    /**
     * Rotates the point about (cx, cy) with the specified angle.
     *
     * @param point point to rotate
     * @param cx    center of rotation x
     * @param cy    center of rotation y
     * @param angle angle
     */
    public static void rotate(Point2D point, int cx, int cy, double angle) {
        final int a = point.getX() - cx;
        final int b = point.getY() - cy;
        
        point.set(rotateX(a, b, angle) + cx, rotateY(a, b, angle) + cy);
    }
    
    /**
     * Rotates the point about rotationAxis with the specified angle.
     *
     * @param point        point to rotate
     * @param rotationAxis center of rotation
     * @param angle        angle
     */
    public static void rotate(Point2D point, Point2D rotationAxis, double angle) {
        rotate(point, rotationAxis.getX(), rotationAxis.getY(), angle);
    }
    
}
