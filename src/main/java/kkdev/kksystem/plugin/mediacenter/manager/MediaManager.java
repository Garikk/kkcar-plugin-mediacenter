/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.mediacenter.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import kkdev.kksystem.base.classes.controls.PinDataControl;
import kkdev.kksystem.base.classes.plugins.PluginMessage;
import kkdev.kksystem.base.classes.plugins.simple.managers.PluginManagerBase;
import static kkdev.kksystem.base.constants.PluginConsts.KK_PLUGIN_BASE_CONTROL_DATA;
import kkdev.kksystem.base.constants.SystemConsts;
import kkdev.kksystem.plugin.mediacenter.KKPlugin;
import kkdev.kksystem.plugin.mediacenter.configuration.MediaCenterConf.MediaProcessor;
import kkdev.kksystem.plugin.mediacenter.configuration.PlayList;
import kkdev.kksystem.plugin.mediacenter.configuration.PluginSettings;
import kkdev.kksystem.plugin.mediacenter.manager.mediadisplay.MediaDisplay;
import kkdev.kksystem.plugin.mediacenter.players.Bluetooth;
import kkdev.kksystem.plugin.mediacenter.players.FilesystemPlayer;
import kkdev.kksystem.plugin.mediacenter.players.IPlayer;
import kkdev.kksystem.plugin.mediacenter.players.InternetRadio;
import kkdev.kksystem.plugin.mediacenter.players.Radio;

/**
 *
 * @author Garikk
 */
public class MediaManager extends PluginManagerBase {

    private MediaProcessor CurrentMediaProcessor;
    private Map<MediaProcessor, IPlayer> Players;
    private MediaDisplay MDisplay;

    public void init(KKPlugin PluginConnector) {
        setPluginConnector(PluginConnector);
        PluginSettings.initConfig(PluginConnector.globalConfID, PluginConnector.pluginInfo.getPluginInfo().PluginUUID);
        this.currentFeature.put(SystemConsts.KK_BASE_UICONTEXT_DEFAULT, PluginSettings.mainConfiguration.featureID);
        Players = new HashMap<>();
        MDisplay = new MediaDisplay(PluginConnector.GetUtils(), PluginConnector);
        initProcessors();
        initPlaylists();
    }

    public void start() {
        MDisplay.showMediaDisplay();
        UpdateDisplay.start();
        Players.get(CurrentMediaProcessor).play(0);
    }

    private void initProcessors() {
        CurrentMediaProcessor = PluginSettings.mainConfiguration.activePlayer;
        for (MediaProcessor MP : PluginSettings.mainConfiguration.mediaProcessors) {
            switch (MP) {
                case INTERNET_RADIO:
                    Players.put(MP, new InternetRadio());
                    break;
                case BLUETOOTH:
                    Players.put(MP, new Bluetooth());
                    break;
                case FILESYSTEMP_PLAYER:
                    Players.put(MP, new FilesystemPlayer());
                    break;
                case RADIO:
                    Players.put(MP, new Radio());
                    break;
            }
        }
    }

    private void initPlaylists() {
        for (PlayList PL : PluginSettings.mainConfiguration.playLists) {
            Players.get(getMediaProcessorForMediaType(PL.mediaType)).addPlayList(PL);
        }
        //
        for (MediaProcessor MP:PluginSettings.mainConfiguration.activePList.keySet())
        {
            Players.get(MP).setActivePlayList(PluginSettings.mainConfiguration.activePList.get(MP));
        }
    }

    private MediaProcessor getMediaProcessorForMediaType(PlayList.MediaSourceType MST) {
        switch (MST) {
            case BLUETOOTH:
                return MediaProcessor.BLUETOOTH;
            case INTERNET_RADIO:
                return MediaProcessor.INTERNET_RADIO;
            case FILESYSTEM_PLAYER:
                return MediaProcessor.FILESYSTEMP_PLAYER;
            case RADIO:
                return MediaProcessor.RADIO;
        }
        //
        
        return null;
    }

    public void receivePin(PluginMessage PM) {
        switch (PM.pinName) {
            case KK_PLUGIN_BASE_CONTROL_DATA:
                processControlCommand((PinDataControl) PM.getPinData());
                break;
        }
    }
    Thread UpdateDisplay = new Thread(new Runnable() {
        @Override
        public void run() {
            IPlayer CheckPlayer;
            while (true) {
                try {
                    Thread.sleep(900);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MediaManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                CheckPlayer = Players.get(CurrentMediaProcessor);
                MDisplay.updateCurrentDisplayInfo(CheckPlayer.getPlayerInfo());

            }
        }
    });

    private void setPlaylist(IPlayer Player, PlayList PL) {
        Player.addPlayList(PL);
    }

    private void processControlCommand(PinDataControl PC) {
        PC.controlID.stream().forEach((Ctl) -> {
            switch (Ctl) {
                case PinDataControl.DEF_BTN_VOL_INC:
                    Players.get(CurrentMediaProcessor).increaseVolume(5);
                    break;
                case PinDataControl.DEF_BTN_VOL_DEC:
                    Players.get(CurrentMediaProcessor).decreaseVolime(5);
                    break;
                case PinDataControl.DEF_BTN_NEXT_TRACK:
                    Players.get(CurrentMediaProcessor).stepNextTrack();
                    break;
                case PinDataControl.DEF_BTN_PREV_TRACK:
                    Players.get(CurrentMediaProcessor).stepBackTrack();
                    break;
                case PinDataControl.DEF_BTN_NEXT_PLIST:
                    Players.get(CurrentMediaProcessor).stepBackTrack();
                    break;
                case PinDataControl.DEF_BTN_PREV_PLIST:
                    Players.get(CurrentMediaProcessor).stepBackTrack();
                    break;
                default:
                    break;
            }
        });

    }
}
