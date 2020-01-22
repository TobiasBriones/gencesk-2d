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

import engineer.tobiasbriones.gencesk_2d_prototype_2018.models.math.Transform;

/**
 * Contains all the bounds for a graphic object with control of transformations.
 * For terminology, the "main rectangle" here means the {@link Rect} object used
 * by the {@link Bounds} instance to hold the values so the Bounds object will
 * apply any other process or transformation to the points to provide those
 * capabilities.
 */
@SuppressWarnings("unused")
public final class Bounds {
    
    @SuppressWarnings("WeakerAccess")
    public static final float ROTATION_AXIS_MIDDLE = 0.5F;
    @SuppressWarnings("WeakerAccess")
    public static final float NO_ROTATION_ANGLE = 0;
    private final Rect rect;
    private float rotationAxisX;
    private float rotationAxisY;
    private double angle;
    
    /**
     * Constructor for Bounds with its rectangle position and dimensions. It
     * sets no scale, rotation axis at the middle and no rotation.
     *
     * @param top    top
     * @param left   left
     * @param width  width
     * @param height height
     */
    @SuppressWarnings("WeakerAccess")
    public Bounds(int top, int left, int width, int height) {
        this.rect = new Rect(top, left, width, height);
        setRotationAxis(ROTATION_AXIS_MIDDLE, ROTATION_AXIS_MIDDLE);
        setAngle(NO_ROTATION_ANGLE);
    }
    
    /**
     * Constructor for Bounds with top = 0, left = 0 and the given dimensions.
     * It sets no scale, rotation axis at the middle and no rotation.
     *
     * @param width  width
     * @param height height
     */
    @SuppressWarnings("WeakerAccess")
    public Bounds(int width, int height) {
        this(0, 0, width, height);
    }
    
    /**
     * Default constructor for Bounds with top = 0, left = 0 and without
     * dimensions. It sets no scale, rotation axis at the middle and no
     * rotation.
     */
    @SuppressWarnings("WeakerAccess")
    public Bounds() {
        this(0, 0);
    }
    
    // ―――――――――――――――――――――――――――――――――――――――――――――――――  SETTERS  ―――――――――――――――――――――――――――――――――――――――――――――――――― //
    
    /**
     * Sets the angle to rotate these bounds, if the angle is multiple of 2pi then these bounds won't have rotation.
     *
     * @param angle angle
     */
    @SuppressWarnings("WeakerAccess")
    public void setAngle(double angle) {
        final double x = angle / (2 * Math.PI);
        final double floor = Math.floor(x);
        this.angle = (x != floor) ? angle : 0;
    }
    
    // ―――――――――――――――――――――――――――――――――――――――――――――――――  GETTERS  ―――――――――――――――――――――――――――――――――――――――――――――――――― //
    
    /**
     * Returns the main rectangle of these bounds.
     *
     * @return the rectangle of these bounds
     */
    @SuppressWarnings("WeakerAccess")
    public Rect getRect() {
        return rect;
    }
    
    /**
     * Returns the x coordinate of the rotation axis in the main rectangle of these bounds.
     *
     * @return the x coordinate of the rotation axis
     */
    public int getRotationAxisX() {
        return rect.getRelativeX(rotationAxisX);
    }
    
    /**
     * Returns the y coordinate of the rotation axis in the main rectangle of these bounds.
     *
     * @return the y coordinate of the rotation axis
     */
    public int getRotationAxisY() {
        return rect.getRelativeY(rotationAxisY);
    }
    
    /**
     * Returns the angle.
     *
     * @return angle
     */
    @SuppressWarnings("WeakerAccess")
    public double getAngle() {
        return angle;
    }
    
    // ――――――――――――――――――――――――――――――――――――――――――――――  PUBLIC METHODS  ―――――――――――――――――――――――――――――――――――――――――――――― //
    
    /**
     * Returns the coordinate x of the center of these bounds.
     *
     * @return center x
     */
    @SuppressWarnings("WeakerAccess")
    public int getCenterX() {
        final int rx = getRotationAxisX();
        final int ry = getRotationAxisY();
        return Transform.rotateX(rect.getCenterX(), rect.getCenterY(), rx, ry, angle);
    }
    
    /**
     * Returns the coordinate y of the center of these bounds.
     *
     * @return center y
     */
    @SuppressWarnings("WeakerAccess")
    public int getCenterY() {
        final int rx = getRotationAxisX();
        final int ry = getRotationAxisY();
        return Transform.rotateY(rect.getCenterX(), rect.getCenterY(), rx, ry, angle);
    }
    
    /**
     * Sets the relative components of the rotation axis in which the angle will be applied to rotate these bounds
     * about.
     * Think of these coordinate with respect to the main rectangle of these bounds.
     *
     * @param cx relative rotation axis x
     * @param cy relative rotation axis y
     * @throws IllegalArgumentException if cx or cy are not in [0, 1]
     */
    @SuppressWarnings("WeakerAccess")
    public void setRotationAxis(float cx, float cy) {
        if (cx < 0 || cx > 1 || cy < 0 || cy > 1)
            throw new IllegalArgumentException("Rotation axis parameters are in [0, 1]");
        this.rotationAxisX = cx;
        this.rotationAxisY = cy;
    }
    
    /**
     * Scales the main rect of these bounds. If sx or sy are negative then is applied the scale with sx = 0 and sy = 0.
     * The scale is performed from the rotation axis. A value 1 for sx and sy does not produce any effect.
     *
     * @param sx scale x
     * @param sy scale y
     */
    @SuppressWarnings("WeakerAccess")
    public void scale(float sx, float sy) {
        if (sx < 0 || sy < 0) {
            scale(0, 0);
            return;
        }
        final int rx = getRotationAxisX();
        final int ry = getRotationAxisY();
        final int newWidth = (int) (rect.getWidth() * sx);
        final int newHeight = (int) (rect.getHeight() * sy);
        
        rect.set(ry - (newHeight / 2), rx - (newWidth / 2), newWidth, newHeight);
    }
    
    /**
     * Sets the top value of these bounds to the given point considering any set rotation.
     *
     * @param point point
     */
    public void getTop(Point2D point) {
        point.set(rect.getCenterX(), rect.getTop());
        Transform.rotate(point, getRotationAxisX(), getRotationAxisY(), angle);
    }
    
    /**
     * Sets the left value of these bounds to the given point considering any set rotation.
     *
     * @param point point
     */
    public void getLeft(Point2D point) {
        point.set(rect.getLeft(), rect.getCenterY());
        Transform.rotate(point, getRotationAxisX(), getRotationAxisY(), angle);
    }
    
    /**
     * Sets the bottom value of these bounds to the given point considering any set rotation.
     *
     * @param point point
     */
    public void getBottom(Point2D point) {
        point.set(rect.getCenterX(), rect.getBottom());
        Transform.rotate(point, getRotationAxisX(), getRotationAxisY(), angle);
    }
    
    /**
     * Sets the right value of these bounds to the given point considering any set rotation.
     *
     * @param point point
     */
    public void getRight(Point2D point) {
        point.set(rect.getRight(), rect.getCenterY());
        Transform.rotate(point, getRotationAxisX(), getRotationAxisY(), angle);
    }
    
    /**
     * Returns <code>true</code> if and only if the given angle is not multiple of 2pi.
     *
     * @return <code>true</code> if and only if the given angle is not multiple of 2pi
     */
    @SuppressWarnings("WeakerAccess")
    public boolean hasRotation() {
        return angle != 0;
    }
    
}
