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
import kkdev.kksystem.base.classes.controls.PinControlData;
import kkdev.kksystem.base.classes.plugins.PluginMessage;
import kkdev.kksystem.base.classes.plugins.simple.managers.PluginManagerBase;
import static kkdev.kksystem.base.constants.PluginConsts.KK_PLUGIN_BASE_CONTROL_DATA;
import kkdev.kksystem.base.constants.SystemConsts;
import kkdev.kksystem.plugin.mediacenter.KKPlugin;
import kkdev.kksystem.plugin.mediacenter.configuration.MediaCenterConf.MediaProcessor;
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

    public void init(KKPlugin BaseConnector) {
        connector = BaseConnector;
        PluginSettings.initConfig(BaseConnector.globalConfID, BaseConnector.pluginInfo.getPluginInfo().PluginUUID);
        this.currentFeature.put(SystemConsts.KK_BASE_UICONTEXT_DEFAULT, PluginSettings.mainConfiguration.featureID);
        Players = new HashMap<>();
        MDisplay = new MediaDisplay(BaseConnector.GetUtils(), connector);
        initProcessors();
    }

    public void start() {
        MDisplay.showMediaDisplay();
        UpdateDisplay.start();
        //Players.get(CurrentMediaProcessor).play();
    }

    private void initProcessors() {
        CurrentMediaProcessor = PluginSettings.mainConfiguration.activePlayer;
        for (MediaProcessor MP : PluginSettings.mainConfiguration.activeProcessors) {
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

    public void receivePin(PluginMessage PM) {
        switch (PM.PinName) {
            case KK_PLUGIN_BASE_CONTROL_DATA:
                processControlCommand((PinControlData) PM.PinData);
                break;
        }
    }
    Thread UpdateDisplay = new Thread(new Runnable() {
        @Override
        public void run() {
            IPlayer CheckPlayer;
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MediaManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                CheckPlayer = Players.get(CurrentMediaProcessor);
                MDisplay.updateCurrentDisplayInfo(CheckPlayer.getPlayerInfo());

            }
        }
    });

    private void activatePlayer() {
    }

    private void processControlCommand(PinControlData PC) {

    }
}
