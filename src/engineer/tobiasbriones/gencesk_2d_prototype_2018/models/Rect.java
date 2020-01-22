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

import engineer.tobiasbriones.gencesk_2d_prototype_2018.graphics.Drawable;

/**
 * Basic rectangle to contain the dimensions of a graphic object.
 *
 * @see Drawable
 */
@SuppressWarnings("unused")
public final class Rect {
    
    private int top;
    private int left;
    private int bottom;
    private int right;
    
    /**
     * Constructor for <code>Rect</code>.
     *
     * @param top    top
     * @param left   left
     * @param width  width
     * @param height height
     */
    @SuppressWarnings("WeakerAccess")
    public Rect(int top, int left, int width, int height) {
        set(top, left, width, height);
    }
    
    /**
     * Constructor for <code>Rect</code>.
     *
     * @param width  width
     * @param height height
     */
    @SuppressWarnings("WeakerAccess")
    public Rect(int width, int height) {
        this(0, 0, width, height);
    }
    
    /**
     * Default constructor for <code>Rect</code> with no dimensions.
     */
    @SuppressWarnings("WeakerAccess")
    public Rect() {
        this(0, 0);
    }
    
    @Override
    public String toString() {
        return "t, l, b ,r: " + top + ", " + left + ", " + bottom + ", " + right;
    }
    
    /**
     * Sets the width for this rectangle, this width sets the right value for
     * these bounds.
     *
     * @param width width
     * @throws IllegalArgumentException if width < 0
     */
    @SuppressWarnings("WeakerAccess")
    public void setWidth(int width) {
        if (width < 0) {
            throw new IllegalArgumentException("Width can't be negative");
        }
        right = left + width;
    }
    
    /**
     * Sets the height for this rectangle, this height sets the bottom value for
     * these bounds.
     *
     * @param height height
     * @throws IllegalArgumentException if height < 0
     */
    @SuppressWarnings("WeakerAccess")
    public void setHeight(int height) {
        if (height < 0) {
            throw new IllegalArgumentException("Height can't be negative");
        }
        bottom = top + height;
    }
    
    /**
     * Returns the top of this rectangle.
     *
     * @return top
     */
    @SuppressWarnings("WeakerAccess")
    public int getTop() {
        return top;
    }
    
    /**
     * Returns the left of this rectangle.
     *
     * @return left
     */
    @SuppressWarnings("WeakerAccess")
    public int getLeft() {
        return left;
    }
    
    /**
     * Returns the bottom of this rectangle.
     *
     * @return bottom
     */
    @SuppressWarnings("WeakerAccess")
    public int getBottom() {
        return bottom;
    }
    
    /**
     * Returns the right of this rectangle.
     *
     * @return right
     */
    @SuppressWarnings("WeakerAccess")
    public int getRight() {
        return right;
    }
    
    /**
     * Returns the width of this rectangle.
     *
     * @return width
     */
    @SuppressWarnings("WeakerAccess")
    public int getWidth() {
        return right - left;
    }
    
    /**
     * Returns the height of this rectangle.
     *
     * @return height
     */
    @SuppressWarnings("WeakerAccess")
    public int getHeight() {
        return bottom - top;
    }
    
    /**
     * Returns the center x coordinate of this rectangle.
     *
     * @return the center x coordinate
     */
    @SuppressWarnings("WeakerAccess")
    public int getCenterX() {
        return left + (getWidth() / 2);
    }
    
    /**
     * Returns the center y coordinate of this rectangle.
     *
     * @return the center y coordinate
     */
    @SuppressWarnings("WeakerAccess")
    public int getCenterY() {
        return top + (getHeight() / 2);
    }
    
    /**
     * Sets this rectangle dimensions.
     *
     * @param top    top
     * @param left   left
     * @param width  width
     * @param height height
     */
    @SuppressWarnings("WeakerAccess")
    public void set(int top, int left, int width, int height) {
        this.top = top;
        this.left = left;
        setWidth(width);
        setHeight(height);
    }
    
    /**
     * Sets this rectangle top and left values with its current dimensions.
     *
     * @param top  top
     * @param left left
     */
    @SuppressWarnings("WeakerAccess")
    public void set(int top, int left) {
        final int width = getWidth();
        final int height = getHeight();
        this.top = top;
        this.left = left;
        setWidth(width);
        setHeight(height);
    }
    
    /**
     * Returns the x position of the given relative point
     * <code>horizontalSize</code> from left to right.
     *
     * @param horizontalSize horizontal factor to measure from left to right
     * @return the x value of the given relative point
     */
    @SuppressWarnings("WeakerAccess")
    public int getRelativeX(float horizontalSize) {
        return (int) (left + horizontalSize * getWidth());
    }
    
    /**
     * Returns the y position of the given relative point
     * <code>verticalSize</code> from top to bottom.
     *
     * @param verticalSize horizontal factor to measure from left to right
     * @return the y value of the given relative point
     */
    @SuppressWarnings("WeakerAccess")
    public int getRelativeY(float verticalSize) {
        return (int) (top + verticalSize * getHeight());
    }
    
    /**
     * Returns <code>true</code> if and only if the given point is inside this
     * rectangle, with inclusion.
     *
     * @param x x
     * @param y y
     * @return <code>true</code> if and only if the given point is inside this
     * rectangle
     */
    public boolean isInRect(int x, int y) {
        return (x >= left && x <= right) && (y >= top && y <= bottom);
    }
    
    /**
     * Returns <code>true</code> if and only if the given rectangle is inside
     * this rect, with inclusion.
     *
     * @param rect rectangle
     * @return <code>true</code> if and only if the given rectangle is inside
     * this rect
     */
    public boolean isInRect(Rect rect) {
        return ((rect.getLeft() >= left && rect.getRight() <= right)
            && (rect.getTop() >= top && rect.getBottom() <= bottom));
    }
    
    /**
     * Returns <code>true</code> if and only if the given rectangle overlaps
     * this rect, with inclusion.
     *
     * @param rect rectangle
     * @return <code>true</code> if and only if the given rectangle overlaps
     * this rect
     */
    public boolean overlapsRect(Rect rect) {
        return ((
            rect.getLeft() >= left
                && rect.getLeft() <= right
                && rect.getTop() >= top
                && rect.getTop() <= bottom
        ) || (
            rect.getRight() <= right
                && rect.getRight() >= left
                && rect.getTop() >= top
                && rect.getTop() <= bottom
        ) || (
            rect.getLeft() >= left &&
                rect.getLeft() <= right &&
                rect.getBottom() <= bottom &&
                rect.getBottom() >= top
        ) || (
            rect.getRight() <= right &&
                rect.getRight() >= left &&
                rect.getBottom() <= bottom &&
                rect.getBottom() >= top
        ));
    }
    
    /**
     * Returns <code>true</code> if and only if this rect is under the given
     * rectangle.
     *
     * @param rect rectangle
     * @return <code>true</code> if and only if this rect is under the given
     * rectangle
     */
    public boolean isUnder(Rect rect) {
        return ((left >= rect.getLeft() && right <= rect.getRight())
            && top > rect.getBottom());
    }
    
    /**
     * Returns <code>true</code> if and only if this rect is above the given
     * rectangle.
     *
     * @param rect rectangle
     * @return <code>true</code> if and only if this rect is above the given
     * rectangle
     */
    public boolean isAbove(Rect rect) {
        return ((left >= rect.getLeft() && right <= rect.getRight())
            && bottom < rect.getTop());
    }
    
    /**
     * Translates this rectangle in the x and y given values.
     *
     * @param tx translation x
     * @param ty translation y
     */
    @SuppressWarnings("WeakerAccess")
    public void translate(int tx, int ty) {
        set(top + ty, left + tx, getWidth(), getHeight());
    }
    
    /**
     * Translates this rectangle in the x given value.
     *
     * @param tx translation x
     */
    @SuppressWarnings("WeakerAccess")
    public void translateX(int tx) {
        translate(tx, 0);
    }
    
    /**
     * Translates this rectangle in the y given value.
     *
     * @param ty translation y
     */
    @SuppressWarnings("WeakerAccess")
    public void translateY(int ty) {
        translate(0, ty);
    }
    
}
