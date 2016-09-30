/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.mediacenter.configuration;

import java.util.List;
import java.util.Map;
import kkdev.kksystem.base.classes.plugins.ManagedParameter;
import kkdev.kksystem.base.classes.plugins.PluginConfiguration;
import kkdev.kksystem.base.constants.SystemConsts;

/**
 *
 * @author blinov_is
 */
public class MediaCenterConf extends PluginConfiguration {
    public enum MediaProcessor
    {
        INTERNET_RADIO,
        FILESYSTEMP_PLAYER,
        RADIO,
        BLUETOOTH
    }
    
    
   public List<PlayList> playLists;
   public List<MediaProcessor> mediaProcessors;
   public Map<MediaProcessor,String> activePList;
      
   public ControlCommands commandsAssigment;
   
   public MediaProcessor activePlayer;
   
   public String featureID=SystemConsts.KK_BASE_FEATURES_MEDIAPLAYER_UID;
   
    public int savedVolumeLevel;
    public String savedPlayListId;
    public String savedTrackId;
    
    @ManagedParameter(DisplayName = "Auto Play", Description = "Auto play on powered")
    public boolean autoPlay;
}
