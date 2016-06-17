/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.mediacenter.configuration;

import java.util.HashMap;
import kkdev.kksystem.base.classes.display.pages.DisplayPage;
import kkdev.kksystem.base.classes.display.pages.UIFrameData;
import kkdev.kksystem.base.classes.display.pages.UIFramePack;
import kkdev.kksystem.base.constants.SystemConsts;
import static kkdev.kksystem.base.constants.SystemConsts.KK_BASE_UICONTEXT_DEFAULT;
import kkdev.kksystem.base.interfaces.IKKControllerUtils;
import kkdev.kksystem.plugin.mediacenter.configuration.MediaCenterConf.MediaProcessor;
import static kkdev.kksystem.plugin.mediacenter.manager.mediadisplay.MediaDisplay.MEDIACENTER_PAGE;


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
     
     
     DefConf.commandsAssigment=CCmd;
     DefConf.activePlayer=MediaCenterConf.MediaProcessor.INTERNET_RADIO;
     DefConf.activeProcessors=new MediaProcessor[1];
     DefConf.activeProcessors[0]=MediaCenterConf.MediaProcessor.INTERNET_RADIO;
     
     DefConf.playLists=new PlayList[2];
     PlayList PL = new PlayList();
     PL.playListEntry=new HashMap<>();
     PL.mediaType=PlayList.MediaSourceType.INTERNET_RADIO;
  
     PlayListEntry PLE=new PlayListEntry();
     PLE.Title="Radio Record";
     PLE.SourceAddr="http://air.radiorecord.ru/rr_128";
     
     PL.playListEntry.put("Record", PLE);
     
             
        
       return DefConf;
    }
    
    public static void addDefaultSystemUIPages(IKKControllerUtils Utils) {
        DisplayPage DP;
        UIFramePack[] FramePack;
        //
        FramePack = getFramePack();
        //
        DP = new DisplayPage();
        DP.dynamicElements = false;
        DP.features = new String[1];
        DP.features[0] = SystemConsts.KK_BASE_FEATURES_MEDIAPLAYER_UID;
        DP.contexts = new String[1];
        DP.contexts[0] = SystemConsts.KK_BASE_UICONTEXT_DEFAULT;
        DP.pageName = MEDIACENTER_PAGE;
        DP.isDefaultPage = false;
        DP.isMultifeaturePage = true;
        DP.framesPack = FramePack[0];
        //
        Utils.DISPLAY_AddUIDisplayPage(DP);
        //
      

    }

    private static UIFramePack[] getFramePack() {
        UIFramePack[] Ret = new UIFramePack[1];
        Ret[0] = new UIFramePack();
        Ret[0].name = "Diag display pages";
        Ret[0].packID = "";
        Ret[0].data = new UIFrameData[1];
        Ret[0].data[0] = new UIFrameData();

        Ret[0].data[0].frameData = "[MP_PLAYERTYPE]\r\n\r\n[MP_TRACKTITLE]\r\n[MP_TRACKTITLE_2]\r\n\r\n[MP_TRACKTIME]";
        Ret[0].data[0].fontSize = 2;
        
        //
        return Ret;

    }

}
