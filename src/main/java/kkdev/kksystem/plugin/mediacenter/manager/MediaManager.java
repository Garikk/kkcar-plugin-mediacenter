/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.mediacenter.manager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
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
import kkdev.kksystem.plugin.mediacenter.configuration.QuickParameterTypes;
import kkdev.kksystem.plugin.mediacenter.manager.mediadisplay.MediaDisplay;
import kkdev.kksystem.plugin.mediacenter.players.Bluetooth;
import kkdev.kksystem.plugin.mediacenter.players.FilesystemPlayer;
import kkdev.kksystem.plugin.mediacenter.players.IPlayer;
import kkdev.kksystem.plugin.mediacenter.players.InternetRadio;
import kkdev.kksystem.plugin.mediacenter.players.PlayerInfo;
import kkdev.kksystem.plugin.mediacenter.players.Radio;

/**
 *
 * @author Garikk
 */
public class MediaManager extends PluginManagerBase {

    private MediaProcessor CurrentMediaProcessor;
    private Map<MediaProcessor, IPlayer> Players;
    private MediaDisplay MDisplay;
    private Queue<PinDataControl> ControlDataQueue;
    


    public void init(KKPlugin PluginConnector) {
        setPluginConnector(PluginConnector);
        ControlDataQueue = new LinkedList<>();
        PluginSettings.initConfig(PluginConnector.globalConfID, PluginConnector.pluginInfo.getPluginInfo().PluginUUID);
        this.currentFeature.put(SystemConsts.KK_BASE_UICONTEXT_DEFAULT, PluginSettings.mainConfiguration.featureID);
        Players = new HashMap<>();
        MDisplay = new MediaDisplay(PluginConnector.GetUtils(), PluginConnector,MenuCallback);
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
        PluginSettings.mainConfiguration.playLists.stream().forEach((PL) -> {
            Players.get(getMediaProcessorForMediaType(PL.mediaType)).addPlayList(PL);
        });
        //
        PluginSettings.mainConfiguration.activePList.keySet().stream().forEach((MP) -> {
            Players.get(MP).setActivePlayList(PluginSettings.mainConfiguration.activePList.get(MP));
        });
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
                if (PM.FeatureID.contains(PluginSettings.mainConfiguration.featureID))
                    processControlCommand((PinDataControl) PM.getPinData());
                break;
        }
    }
    Thread UpdateDisplay = new Thread(new Runnable() {
        
        @Override
        public void run() {
            IPlayer CheckPlayer=null;
            MediaProcessor MPC=null;
            while (true) {
                Thread.yield();
                if (MPC!=CurrentMediaProcessor){
                    CheckPlayer = Players.get(CurrentMediaProcessor);
                    MPC=CurrentMediaProcessor;
                }
                MDisplay.updateCurrentDisplayInfo(CheckPlayer.getPlayerInfo());
                processControl();
            }
        }
    });

    private void setPlaylist(IPlayer Player, PlayList PL) {
        Player.addPlayList(PL);
    }

    private void processControlCommand(PinDataControl PC) {
        MDisplay.processControlCommand(PC);
       //
      ControlDataQueue.add(PC);
       //
    }
    
    private void processControl()
    {
        if (ControlDataQueue.isEmpty())
            return;
            
        while (ControlDataQueue.size() > 0) {
            PinDataControl PC = ControlDataQueue.poll();
            PC.controlID.stream().forEach((Ctl) -> {
                switch (Ctl) {
                    case PinDataControl.DEF_BTN_VOL_INC:
                        int currVolumeI = Players.get(CurrentMediaProcessor).increaseVolume(1);
                        PluginSettings.mainConfiguration.setParameterInteger(QuickParameterTypes.INT_MAIN_VOLUME.getValue(), currVolume);
                        break;
                    case PinDataControl.DEF_BTN_VOL_DEC:
                        int currVolumeD = Players.get(CurrentMediaProcessor).decreaseVolime(1);
                        PluginSettings.mainConfiguration.setParameterInteger(QuickParameterTypes.INT_MAIN_VOLUME.getValue(), currVolume);
                    break;
                case PinDataControl.DEF_BTN_NEXT_TRACK:
                    Players.get(CurrentMediaProcessor).stepNextTrack();
                    break;
                case PinDataControl.DEF_BTN_PREV_TRACK:
                    Players.get(CurrentMediaProcessor).stepBackTrack();
                    break;
                case PinDataControl.DEF_BTN_NEXT_PLIST:
                    Players.get(CurrentMediaProcessor).stepNextPlist();
                    break;
                case PinDataControl.DEF_BTN_PREV_PLIST:
                    Players.get(CurrentMediaProcessor).stepPrevPlist();
                    break;
                case PinDataControl.DEF_BTN_PLAY:
                    Players.get(CurrentMediaProcessor).play(0);
                    break;
                case PinDataControl.DEF_BTN_STOP:
                    Players.get(CurrentMediaProcessor).stop();
                    break;
                case PinDataControl.DEF_BTN_PAUSE:
                    Players.get(CurrentMediaProcessor).pause();
                    break;
                default:
                    break;
            }
        });
        }

    }
        private IPlayer MenuCallback=new IPlayer(){
        @Override
        public void stopstart() {
                Players.get(CurrentMediaProcessor).stopstart();
        }
        @Override
        public void play(int step) {
                Players.get(CurrentMediaProcessor).play(step);
        }

        @Override
        public void playPlayListItem(int PlayListPosition) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void stop() {
           Players.get(CurrentMediaProcessor).stop();
        }

        @Override
        public void pause() {
              Players.get(CurrentMediaProcessor).pause();
        }

        @Override
        public void resume() {
            Players.get(CurrentMediaProcessor).resume();
        }

        @Override
        public void seekForward() {
            Players.get(CurrentMediaProcessor).seekForward();
        }

        @Override
        public void seekBackward() {
            Players.get(CurrentMediaProcessor).seekBackward();
        }

        @Override
        public void stepNextTrack() {
            Players.get(CurrentMediaProcessor).stepNextTrack();
        }

        @Override
        public void stepBackTrack() {
            Players.get(CurrentMediaProcessor).stepBackTrack();
        }

        @Override
        public void stepNextPlist() {
            Players.get(CurrentMediaProcessor).stepNextPlist();
        }

        @Override
        public void stepPrevPlist() {
            Players.get(CurrentMediaProcessor).stepPrevPlist();
        }

        @Override
        public void shuffle() {
            Players.get(CurrentMediaProcessor).shuffle();
        }

        @Override
        public void addPlayList(PlayList PList) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void setActivePlayList(String PlayListID) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void increaseVolume(int Step) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void decreaseVolime(int Step) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public PlayerInfo getPlayerInfo() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
}
