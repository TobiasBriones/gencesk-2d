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

package engineer.tobiasbriones.gencesk_2d_prototype_2018.io;

@SuppressWarnings("unused")
public final class Key {
    
    // ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ //
    //                                                                                                                //
    //                                                     CLASS                                                      //
    //                                                                                                                //
    // ______________________________________________________________________________________________________________ //
    
    public static final int KEY_ENTER = 10;
    public static final int KEY_SHIFT = 16;
    public static final int KEY_CONTROL = 17;
    public static final int KEY_CAPS = 20;
    public static final int KEY_ARROW_LEFT = 37;
    public static final int KEY_ARROW_UP = 38;
    public static final int KEY_ARROW_RIGHT = 39;
    public static final int KEY_ARROW_DOWN = 40;
    
    public static boolean isAccepted(int code, char key) {
        return Character.isAlphabetic(key)
            || Character.isDigit(key)
            || key == ' '
            || code == KEY_ENTER
            || code == KEY_SHIFT
            || code == KEY_CONTROL
            || code == KEY_CAPS
            || (code >= KEY_ARROW_LEFT && code <= KEY_ARROW_DOWN);
    }
    
    // ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ //
    //                                                                                                                //
    //                                                    INSTANCE                                                    //
    //                                                                                                                //
    // ______________________________________________________________________________________________________________ //
    
    private int code;
    private char character;
    
    Key(int code, char ch) {
        set(code, ch);
    }
    
    Key() {
        this(-1, ' ');
    }
    
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Key) && ((Key) obj).getCode() == code;
    }
    
    @Override
    public int hashCode() {
        return code;
    }
    
    @Override
    public String toString() {
        return code + " -> " + character;
    }
    
    public int getCode() {
        return code;
    }
    
    public char getCharacter() {
        return character;
    }
    
    void set(int code, char ch) {
        this.code = code;
        this.character = ch;
    }
    
}
