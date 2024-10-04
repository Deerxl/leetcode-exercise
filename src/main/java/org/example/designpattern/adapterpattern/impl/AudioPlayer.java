package org.example.designpattern.adapterpattern.impl;

import org.example.designpattern.adapterpattern.MediaAdapter;
import org.example.designpattern.adapterpattern.MediaPlayer;

/**
 * @author xiaolu
 * @date 2024/10/4 17:02
 */
public class AudioPlayer implements MediaPlayer {

    MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3 file: " + fileName);
        } else if (audioType.equalsIgnoreCase("vlc")
                || audioType.equalsIgnoreCase("mp4")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Unsupported audio type: " + audioType);
        }
    }
}
