/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.mediacenter.manager.mediadisplay;

import kkdev.kksystem.base.classes.display.pages.framesKeySet;
import kkdev.kksystem.base.classes.display.tools.infopage.MKPageItem;
import kkdev.kksystem.base.classes.display.tools.infopage.PageMaker;
import kkdev.kksystem.base.classes.display.tools.infopage.PageMaker.IPageMakerExecCommand;
import kkdev.kksystem.base.constants.SystemConsts;
import kkdev.kksystem.base.interfaces.IKKControllerUtils;
import kkdev.kksystem.base.interfaces.IPluginKKConnector;
import kkdev.kksystem.plugin.mediacenter.Global;
import kkdev.kksystem.plugin.mediacenter.configuration.kk_DefaultConfig;

/**
 *
 * @author blinov_is
 */
public class MediaDisplay {

    PageMaker pageManager;
    framesKeySet CurrentDisplayInfo;

    public MediaDisplay(IKKControllerUtils Utils, IPluginKKConnector BaseConnector) {
        kk_DefaultConfig.addDefaultSystemUIPages(Utils);
    }

    IPageMakerExecCommand PageExec = new IPageMakerExecCommand() {
        @Override
        public void execCommand(String PageCMD) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void pageSelected(String PageName) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };

    public void showMediaDisplay() {
        pageManager = new PageMaker(Global.MD.currentFeature.get(SystemConsts.KK_BASE_UICONTEXT_DEFAULT), SystemConsts.KK_BASE_UICONTEXT_DEFAULT, Global.MD.connector, PageExec);
        MKPageItem[] Page;
        Page = new MKPageItem[1];
        Page[0] = new MKPageItem();
        Page[0].pageName = "MEDIAPLAYER";
        Page[0].pageCommand = "PLAYER STOPSTART";
        Page[0].pageFrames = CurrentDisplayInfo;
        pageManager.addPages(Page);
        pageManager.showInfoPage();
    }

    private void updateCurrentDisplayInfo(String TrackInfo, String TrackInfo2, String TrackTime) {
        CurrentDisplayInfo.setValue("[MP_PLAYERTYPE]", "TEST");
        CurrentDisplayInfo.setValue("[MP_TRACKTITLE]", TrackInfo);
        CurrentDisplayInfo.setValue("[MP_TRACKTITLE_2]", TrackInfo2);
        CurrentDisplayInfo.setValue("[MP_TRACKTIME]", TrackTime);
        //
        pageManager.updatePageFrames("MEDIAPLAYER", CurrentDisplayInfo);
    }

}
