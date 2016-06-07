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
    public PluginInfo GetPluginInfo() {
        PluginInfo Ret = new PluginInfo();

        Ret.PluginName = "KKMediaCenter";
        Ret.PluginDescription = "Basic Media Center";
        Ret.PluginVersion = 1;
        Ret.Enabled = true;
        Ret.ReceivePins = GetMyReceivePinInfo();
        Ret.TransmitPins = GetMyTransmitPinInfo();
        Ret.PluginUUID = "5769c45c-cffd-4ba0-ace7-e35ceee13923";
        return Ret;
    }

    private String[] GetMyReceivePinInfo() {

        String[] Ret = new String[2];

        Ret[0] = PluginConsts.KK_PLUGIN_BASE_PIN_COMMAND;
        Ret[1] = PluginConsts.KK_PLUGIN_BASE_CONTROL_DATA;
        Ret[2] = PluginConsts.KK_PLUGIN_BASE_NOTIFY_DATA;

        return Ret;
    }

    private String[] GetMyTransmitPinInfo() {

        String[] Ret = new String[1];
        Ret[0] = PluginConsts.KK_PLUGIN_BASE_BASIC_TAGGEDOBJ_DATA;

        return Ret;
    }

}
