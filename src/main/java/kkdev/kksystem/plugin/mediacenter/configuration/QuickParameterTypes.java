/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.mediacenter.configuration;

/**
 *
 * @author blinov_is
 */
public enum QuickParameterTypes {
    INT_MAIN_VOLUME("VOL")
    ,
    BOOL_PLAYER_STOP("PLAYERSTOP")
    ,
    STRING_PLAYER_PLAYLISTID("PLAYLIST_ID"),
    STRING_PLAYER_TRACKID("TRACK_ID"),
    BOOLEAN_PLAYER_AUTOPLAY("AUTOPLAY");

    String myValue;

    QuickParameterTypes(String myVal) {
        myValue = myVal;
    }
    public String getVal()
    {
        return myValue;
    }

}
