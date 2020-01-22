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
 * Implemented by any object/resource which allows to play content. I contains the main controls for the media play.
 */
@SuppressWarnings("unused")
public interface Playable {
    
    /**
     * Returns <code>true</code> if and only if this media is being played.
     *
     * @return <code>true</code> if and only if this media is being played
     */
    boolean isPlaying();
    
    /**
     * Returns <code>true</code> if and only if this media is paused.
     *
     * @return <code>true</code> if and only if this media is paused
     */
    boolean isPaused();
    
    /**
     * Returns <code>true</code> if and only if this media is stopped.
     *
     * @return <code>true</code> if and only if this media is stopped
     */
    boolean isStopped();
    
    /**
     * Returns <code>true</code> if and only if this media is played when calling this method. If this media is already
     * being played it should be return <code>false</code>.
     *
     * @return <code>true</code> if and only if this media is played when calling this method
     */
    boolean play();
    
    /**
     * Returns <code>true</code> if and only if this media is paused when calling this method. If the state of the media
     * does not allow to pause maybe because is already paused or stopped it should be return <code>false</code>.
     *
     * @return <code>true</code> if and only if this media is paused when calling this method
     */
    boolean pause();
    
    /**
     * Returns <code>true</code> if and only if this media is stopped when calling this method. If the state of the
     * media does not allow to stop maybe because is already stopped it should be return <code>false</code>.
     *
     * @return <code>true</code> if and only if this media is stopped when calling this method
     */
    boolean stop();
    
}
