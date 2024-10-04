package org.example.designpattern.adapterpattern.impl;

import org.example.designpattern.adapterpattern.AdvancedMediaPlayer;

/**
 * @author xiaolu
 * @date 2024/10/4 16:57
 */
public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing VLC file: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {

    }
}
