package kkdev.kksystem.plugin.mediacenter;

import kkdev.kksystem.base.classes.plugins.PluginInfo;
import kkdev.kksystem.base.classes.plugins.simple.IPluginInfoRequest;
import kkdev.kksystem.base.constants.PluginConsts;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author blinov_is e
 */
public class MediaPluginInfo implements IPluginInfoRequest {

    @Override
    public PluginInfo getPluginInfo() {
        PluginInfo ret = new PluginInfo();

        ret.PluginName = "KKMediaCenter";
        ret.PluginDescription = "Basic Media Center";
        ret.PluginVersion = 1;
        ret.Enabled = true;
        ret.ReceivePins = GetMyReceivePinInfo();
        ret.TransmitPins = GetMyTransmitPinInfo();
        ret.PluginUUID = "5769c45c-cffd-4ba0-ace7-e35ceee13923";
        return ret;
    }

    private String[] GetMyReceivePinInfo() {

        String[] ret = new String[2];

        ret[0] = PluginConsts.KK_PLUGIN_BASE_PIN_COMMAND;
        ret[1] = PluginConsts.KK_PLUGIN_BASE_CONTROL_DATA;
        ret[2] = PluginConsts.KK_PLUGIN_BASE_NOTIFY_DATA;

        return ret;
    }

    private String[] GetMyTransmitPinInfo() {

        String[] ret = new String[1];
        ret[0] = PluginConsts.KK_PLUGIN_BASE_BASIC_TAGGEDOBJ_DATA;

        return ret;
    }

}
