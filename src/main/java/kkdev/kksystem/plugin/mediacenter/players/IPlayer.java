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
    void play();
    void play(int PlayListPosition);
    void stop();
    void pause();
    void resume();
    void seekForward();
    void seekBackward();
    void stepNext();
    void stepBack();
    void shuffle();
    
    void setPlayList(PlayList[] PList);
    
    PlayerInfo getPlayerInfo();
    
}
