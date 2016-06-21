/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.mediacenter.configuration;

import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author blinov_is
 */
public class PlayList {

    public String PlayListID;
    public int CurrentPosition;

    public enum MediaSourceType {
        INTERNET_RADIO,
        FILESYSTEM_PLAYER,
        RADIO,
        BLUETOOTH
    }
    public MediaSourceType mediaType;
    public ArrayList<PlayListEntry> playListEntries;

    public PlayList() {
        PlayListID=UUID.randomUUID().toString();
        playListEntries = new ArrayList<>();
        CurrentPosition = 0;
    }

    public void addTrack(PlayListEntry PLE) {
        playListEntries.add(PLE);
    }

    public PlayListEntry getCurrentTrack() {
        return playListEntries.get(CurrentPosition);

    }

    public PlayListEntry getNextTrack() {
        if (playListEntries.size() == CurrentPosition) {
            CurrentPosition = 0;
        } else {
            CurrentPosition++;
        }
        return playListEntries.get(CurrentPosition);

    }

    public PlayListEntry getPrevTrack() {
        if (CurrentPosition == 0) {
            CurrentPosition = playListEntries.size();
        } else {
            CurrentPosition--;
        }
        return playListEntries.get(CurrentPosition);

    }

    public PlayListEntry getTrack(int position) {

        if (playListEntries.size() < CurrentPosition) {
            CurrentPosition = playListEntries.size();
        } else {
            CurrentPosition = position;
        }

        return playListEntries.get(CurrentPosition);

    }
}
