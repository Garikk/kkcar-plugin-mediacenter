/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.mediacenter.players;

import kkdev.kksystem.plugin.mediacenter.configuration.PlayList;

/**
 *
 * @author blinov_is
 */
public interface IPlayer {
    void stopstart();
    void play(int step);
    void playPlayListItem(int PlayListPosition);
    void stop();
    void pause();
    void resume();
    void seekForward();
    void seekBackward();
    void stepNextTrack();
    void stepBackTrack();
    void stepNextPlist();
    void stepPrevPlist();
    void shuffle();
    
    void addPlayList(PlayList PList);
    void setActivePlayList(String PlayListID);
    
    void increaseVolume(int Step);
    void decreaseVolime(int Step);
    
    PlayerInfo getPlayerInfo();
    
}
