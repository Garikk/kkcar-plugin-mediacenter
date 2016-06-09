/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.mediacenter.configuration;

import java.util.HashMap;

/**
 *
 * @author blinov_is
 */
public class PlayList {


    public enum MediaSourceType {
        INTERNET_RADIO,
        FILESYSTEM_PLAYER,
        RADIO,
        BLUETOOTH
    }
    MediaSourceType mediaType;

    HashMap playListEntry;

}
