/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.mediacenter.manager.mediadisplay;

import kkdev.kksystem.base.classes.controls.PinDataControl;
import kkdev.kksystem.base.classes.display.pages.framesKeySet;
import kkdev.kksystem.base.classes.display.tools.infopage.MKPageItem;
import kkdev.kksystem.base.classes.display.tools.infopage.PageMaker;
import kkdev.kksystem.base.classes.display.tools.infopage.PageMaker.IPageMakerExecCommand;
import kkdev.kksystem.base.constants.SystemConsts;
import kkdev.kksystem.plugin.mediacenter.Global;
import kkdev.kksystem.plugin.mediacenter.configuration.kk_DefaultConfig;
import kkdev.kksystem.plugin.mediacenter.players.IPlayer;
import kkdev.kksystem.plugin.mediacenter.players.PlayerInfo;
import kkdev.kksystem.base.interfaces.IPluginConnection;
import kkdev.kksystem.base.interfaces.IControllerUtils;

/**
 *
 * @author blinov_is
 */
public class MediaDisplay {

    public final static String MEDIACENTER_PAGE = "MEDIAPLAYER";
    PageMaker pageManager;
    framesKeySet CurrentDisplayInfo;
    IPlayer PlayerManager;
    PlayerInfo PI;

    public MediaDisplay(IControllerUtils Utils, IPluginConnection BaseConnector, IPlayer Callback) {
        PlayerManager=Callback;
        kk_DefaultConfig.addDefaultSystemUIPages(Utils);
        CurrentDisplayInfo = new framesKeySet();
        CurrentDisplayInfo.setValue("MP_PLAYERTYPE", "Dingo Media");
        CurrentDisplayInfo.setValue("MP_TRACKTITLE", "Loading");
        CurrentDisplayInfo.setValue("MP_TRACKTITLE_2", "Wait");
        CurrentDisplayInfo.setValue("MP_TRACKTIME", "Wait");
        CurrentDisplayInfo.setValue("MP_PLAYERSTATE", "Stop");
    }

    IPageMakerExecCommand PageExec = new IPageMakerExecCommand() {
        @Override
        public void execCommand(String PageCMD) {
            
            String[] CMD = PageCMD.split(" ");

            if (CMD[0].equals("PLAYER")) {
                if (CMD[1].equals("STOPSTART"))
                    PlayerManager.stopstart();
            }

        }

        @Override
        public void pageSelected(String PageName) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void pageStepFwd() {
            System.out.println("CMD F");
            PlayerManager.stepNextTrack();
        }

        @Override
        public void pageStepBwd() {
            System.out.println("CMD B");
            PlayerManager.stepBackTrack();
        }
    };

    public void showMediaDisplay() {
        pageManager = new PageMaker(Global.MD.currentFeature.get(SystemConsts.KK_BASE_UICONTEXT_DEFAULT), SystemConsts.KK_BASE_UICONTEXT_DEFAULT, Global.MD.getPluginConnector(), PageExec);
        MKPageItem[] Page;
        Page = new MKPageItem[1];
        Page[0] = new MKPageItem();
        Page[0].pageName = MEDIACENTER_PAGE;
        Page[0].pageCommand = "PLAYER STOPSTART";
        Page[0].pageFrames = CurrentDisplayInfo;
        pageManager.addPages(Page);
        pageManager.showInfoPage();
        
    }

    public void updateCurrentDisplayInfo(String PlayerName,String TrackInfo, String TrackInfo2, String TrackTime, Integer VolumeLevel,String PlayerState) {
        CurrentDisplayInfo.setValue("MP_PLAYERTYPE", PlayerName);
        CurrentDisplayInfo.setValue("MP_TRACKTITLE", TrackInfo);
        CurrentDisplayInfo.setValue("MP_TRACKTITLE_2", TrackInfo2);
        CurrentDisplayInfo.setValue("MP_TRACKTIME", TrackTime);
        CurrentDisplayInfo.setValue("MP_VOL_LEVEL", VolumeLevel.toString());
        CurrentDisplayInfo.setValue("MP_PLAYERSTATE", PlayerState);
        //
        pageManager.updatePageFrames(MEDIACENTER_PAGE, CurrentDisplayInfo);
    }
     public void updateCurrentDisplayInfo(PlayerInfo Info) {
        if (!PI.equals(Info))
            updateCurrentDisplayInfo(Info.PlayerName,Info.TitleArtist,Info.TitleDescription,Info.TrackTimeLine,Info.CurrentVolumeLevel,Info.isPlaying ? "Playing" : "Stop");
        //
        PI=Info;
    }

    public void processControlCommand(PinDataControl PC) {
        pageManager.processControlCommand(PC.controlID);
    }
   

}
