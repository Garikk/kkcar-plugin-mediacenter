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
    String play(int step);
    String playPlayListItem(int PlayListPosition);
    void stop();
    void pause();
    void resume();
    void seekForward();
    void seekBackward();
    String stepNextTrack();
    String stepBackTrack();
    String stepNextPlist();
    String stepPrevPlist();
    void shuffle();
    
    void addPlayList(PlayList PList);
    void setActivePlayList(String PlayListID);
    
    int increaseVolume(int Step);
    int decreaseVolume(int Step);
    
    PlayerInfo getPlayerInfo();
    
}
