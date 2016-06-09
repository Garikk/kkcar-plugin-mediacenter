/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.mediacenter.configuration;

import kkdev.kksystem.base.constants.SystemConsts;

/**
 *
 * @author blinov_is
 */
public class MediaCenterConf {
    public enum MediaProcessor
    {
        INTERNET_RADIO,
        FILESYSTEMP_PLAYER,
        RADIO,
        BLUETOOTH
    }
    
    
   public PlayList[] playLists;
   public MediaProcessor[] activeProcessors;
   public ControlCommands commandsAssigment;
   
   public MediaProcessor activePlayer;
   
   public String featureID=SystemConsts.KK_BASE_FEATURES_MEDIAPLAYER_UID;
}
