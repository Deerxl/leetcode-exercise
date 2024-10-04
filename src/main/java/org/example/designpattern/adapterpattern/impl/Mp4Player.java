package org.example.designpattern.adapterpattern.impl;

import org.example.designpattern.adapterpattern.AdvancedMediaPlayer;

/**
 * @author xiaolu
 * @date 2024/10/4 16:57
 */
public class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {

    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file: " + fileName);
    }
}
