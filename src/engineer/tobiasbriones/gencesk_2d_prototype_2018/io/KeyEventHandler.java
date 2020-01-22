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

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

/**
 * Handler for the key input events produced into a view. It implements {@link Iterable} to retrieve the current inputs
 * and then is needed to call {@link #KeyEventHandler(JComponent)#free()} to release the list of events.
 */
@SuppressWarnings("unused")
public final class KeyEventHandler {
    
    public interface KeyRetriever {
        
        void nextKey(Key key);
        
    }
    
    private final Set<Integer> inputKeys;
    private final List<Key> retrieverList;
    private final KeyPool pool;
    
    /**
     * Default constructor for <code>KeyEventHandler</code>.
     *
     * @param view view to handle the key input events
     */
    @SuppressWarnings("WeakerAccess")
    public KeyEventHandler(JComponent view) {
        this.inputKeys = new LinkedHashSet<>();
        this.retrieverList = Collections.synchronizedList(new ArrayList<>());
        this.pool = new KeyPool();
        
        view.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyPressed(KeyEvent e) {
                final int code = e.getKeyCode();
                final char ch = e.getKeyChar();
                
                if (Key.isAccepted(code, ch) && inputKeys.add(code)) {
                    retrieverList.add(pool.obtainKey(code, ch));
                }
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                final Key key = pool.free(e.getKeyCode());
                
                if (key != null) {
                    inputKeys.remove(e.getKeyCode());
                    retrieverList.remove(key);
                }
            }
        });
    }
    
    public void retrieve(KeyRetriever keyRetriever) {
        synchronized (retrieverList) {
            Iterator<Key> i = retrieverList.iterator();
            Key currentKey;
            
            while (i.hasNext()) {
                keyRetriever.nextKey(i.next());
            }
        }
    }
    
}
