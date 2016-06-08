/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.mediacenter.configuration;

import java.util.HashMap;
import kkdev.kksystem.plugin.mediacenter.configuration.MediaCenterConf.MediaProcessor;


/**
 *
 * @author blinov_is
 *
 * Creating default config
 *
 * Default configuration for mediacenter
 * MUST BE CHANGED IN PRODUCTION
 *
 */
public abstract class kk_DefaultConfig {

    public static MediaCenterConf MakeDefaultConfig() {

        MediaCenterConf DefConf = new MediaCenterConf();
        //
     ControlCommands CCmd=new ControlCommands();
     CCmd.controlsAssigment=new HashMap<>();
     CCmd.controlsAssigment.put(ControlCommands.ControlCommandTypes.CMD_VOLUP, "VU");
     CCmd.controlsAssigment.put(ControlCommands.ControlCommandTypes.CMD_VOLDOWN, "VD");
     
     CCmd.controlsAssigment.put(ControlCommands.ControlCommandTypes.CMD_PLAY, "CUSTOM_CHR_1");
     CCmd.controlsAssigment.put(ControlCommands.ControlCommandTypes.CMD_STOP, "CUSTOM_CHR_2");
     CCmd.controlsAssigment.put(ControlCommands.ControlCommandTypes.CMD_NEXT, "CUSTOM_CHR_3");
     CCmd.controlsAssigment.put(ControlCommands.ControlCommandTypes.CMD_BACK, "CUSTOM_CHR_4");
     CCmd.controlsAssigment.put(ControlCommands.ControlCommandTypes.CMD_SEEKBWD, "CUSTOM_CHR_5");
     CCmd.controlsAssigment.put(ControlCommands.ControlCommandTypes.CMD_SEEKFWD, "CUSTOM_CHR_6");
  
     
     CCmd.controlsAssigment.put(ControlCommands.ControlCommandTypes.CMD_PRESET_1, "CUSTOM_CHR_TRK_1");
     CCmd.controlsAssigment.put(ControlCommands.ControlCommandTypes.CMD_PRESET_2, "CUSTOM_CHR_TRK_2");
     CCmd.controlsAssigment.put(ControlCommands.ControlCommandTypes.CMD_PRESET_3, "CUSTOM_CHR_TRK_3");
     CCmd.controlsAssigment.put(ControlCommands.ControlCommandTypes.CMD_PRESET_4, "CUSTOM_CHR_TRK_4");
     CCmd.controlsAssigment.put(ControlCommands.ControlCommandTypes.CMD_PRESET_5, "CUSTOM_CHR_TRK_5");
     
     
     DefConf.CommandsAssigment=CCmd;
     DefConf.ActivePlayer=MediaCenterConf.MediaProcessor.INTERNET_RADIO;
     DefConf.ActiveProcessors=new MediaProcessor[1];
     DefConf.ActiveProcessors[0]=MediaCenterConf.MediaProcessor.INTERNET_RADIO;
     
     DefConf.PlayLists=new PlayList[2];
     PlayList PL = new PlayList();
     PL.PlayListEntry=new HashMap<>();
     PL.MediaType=PlayList.MediaSourceType.INTERNET_RADIO;
  
     PlayListEntry PLE=new PlayListEntry();
     PLE.Title="Radio Record";
     PLE.SourceAddr="http://air.radiorecord.ru/rr_128";
     
     PL.PlayListEntry.put("Record", PLE);
     
             
        
       return DefConf;
    }
}