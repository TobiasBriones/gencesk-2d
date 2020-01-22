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
import engineer.tobiasbriones.gencesk_2d_prototype_2018.models.Dimension2D;
import engineer.tobiasbriones.gencesk_2d_prototype_2018.models.Rect;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Bitmap representation of an image to display on in the game. Initialized
 * through {@link Bitmap#createBitmap(File)}.
 */
@SuppressWarnings("unused")
public final class Bitmap extends Drawable {
    
    // ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ //
    //                                                                                                                //
    //                                                     CLASS                                                      //
    //                                                                                                                //
    // ______________________________________________________________________________________________________________ //
    
    /**
     * Creates an empty instance of a {@link Bitmap} with the specified image
     * size.
     *
     * @return the new bitmap
     */
    public static Bitmap createBitmap(int width, int height) {
        final int type = BufferedImage.TYPE_INT_ARGB;
        final BufferedImage image = new BufferedImage(width, height, type);
        return new Bitmap(image);
    }
    
    /**
     * Creates an empty instance of a {@link Bitmap} with the specified image
     * size.
     *
     * @return the new bitmap
     */
    public static Bitmap createBitmap(Dimension2D size) {
        final int width = size.getWidth();
        final int height = size.getHeight();
        final int type = BufferedImage.TYPE_INT_ARGB;
        final BufferedImage image = new BufferedImage(width, height, type);
        return new Bitmap(image);
    }
    
    /**
     * Creates an instance of a {@link Bitmap} with the specified image file.
     *
     * @param file file containing the image to load
     * @return the bitmap with the given image loaded
     * @throws IOException if an I/O error occurs
     */
    public static Bitmap createBitmap(File file) throws IOException {
        final BufferedImage in = ImageIO.read(file);
        final int width = in.getWidth();
        final int height = in.getHeight();
        final int type = BufferedImage.TYPE_INT_ARGB;
        final BufferedImage image = new BufferedImage(width, height, type);
        final Graphics2D g2 = image.createGraphics();
        
        g2.drawImage(in, 0, 0, null);
        g2.dispose();
        return new Bitmap(image);
    }
    
    // ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ //
    //                                                                                                                //
    //                                                    INSTANCE                                                    //
    //                                                                                                                //
    // ______________________________________________________________________________________________________________ //
    
    private final BufferedImage image;
    
    private Bitmap(BufferedImage image) {
        this.image = image;
        
        getRect().setWidth(getWidth());
        getRect().setHeight(getHeight());
    }
    
    // ―――――――――――――――――――――――――――――――――――――――――――――――――  DRAWABLE  ――――――――――――――――――――――――――――――――――――――――――――――――― //
    @Override
    public void draw(Graphics2D g2) {
        final Rect rect = getRect();
        
        if (getBounds().hasRotation()) {
            final Bounds bounds = getBounds();
            final AffineTransform original = g2.getTransform();
            final AffineTransform transform = AffineTransform.getRotateInstance(
                bounds.getAngle(),
                bounds.getRotationAxisX(),
                bounds.getRotationAxisY()
            );
            
            g2.transform(transform);
            g2.drawImage(image, rect.getLeft(), rect.getTop(), getWidth(), getHeight(), null);
            g2.setTransform(original);
        }
        else {
            g2.drawImage(image, rect.getLeft(), rect.getTop(), getWidth(), getHeight(), null);
        }
    }
    
    // ―――――――――――――――――――――――――――――――――――――――――――――――――  GETTERS  ―――――――――――――――――――――――――――――――――――――――――――――――――― //
    
    /**
     * Returns the width of this bitmap image.
     *
     * @return the width of this bitmap
     */
    @SuppressWarnings("WeakerAccess")
    public int getWidth() {
        return image.getWidth();
    }
    
    /**
     * Returns the height of this bitmap image.
     *
     * @return the height of this bitmap
     */
    @SuppressWarnings("WeakerAccess")
    public int getHeight() {
        return image.getHeight();
    }
    
    // ――――――――――――――――――――――――――――――――――――――――――――――  PUBLIC METHODS  ―――――――――――――――――――――――――――――――――――――――――――――― //
    
    /**
     * Creates a <code>Graphics2D</code> object to draw into this
     * <code>Bitmap</code>.
     *
     * @return {@link Graphics2D}
     */
    public Graphics2D createGraphics() {
        return image.createGraphics();
    }
    
}
