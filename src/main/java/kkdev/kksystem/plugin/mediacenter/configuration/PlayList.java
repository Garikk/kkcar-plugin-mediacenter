/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.mediacenter.configuration;

import java.util.ArrayList;

/**
 *
 * @author blinov_is
 */
public class PlayList {

    private int CurrentPosition;

    public enum MediaSourceType {
        INTERNET_RADIO,
        FILESYSTEM_PLAYER,
        RADIO,
        BLUETOOTH
    }
    MediaSourceType mediaType;

    ArrayList<PlayListEntry> playListEntrys;

    public PlayList() {
        playListEntrys = new ArrayList<>();
        CurrentPosition = 0;
    }

    public void addTrack(PlayListEntry PLE) {
        playListEntrys.add(PLE);
    }

    public PlayListEntry getTrack() {
        if (playListEntrys.size() == CurrentPosition) {
            CurrentPosition = 0;
        } else {
            CurrentPosition++;
        }
        return playListEntrys.get(CurrentPosition);

    }

    public PlayListEntry getTrack(int position) {

        if (playListEntrys.size() < CurrentPosition) {
            CurrentPosition = playListEntrys.size();
        } else {
            CurrentPosition = position;
        }

        return playListEntrys.get(CurrentPosition);


    }
}
