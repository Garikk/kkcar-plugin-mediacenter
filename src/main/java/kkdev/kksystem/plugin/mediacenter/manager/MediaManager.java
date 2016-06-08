/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.mediacenter.manager;

import kkdev.kksystem.base.classes.controls.PinControlData;
import kkdev.kksystem.base.classes.plugins.PluginMessage;
import kkdev.kksystem.base.classes.plugins.simple.managers.PluginManagerBase;
import static kkdev.kksystem.base.constants.PluginConsts.KK_PLUGIN_BASE_CONTROL_DATA;
import kkdev.kksystem.plugin.mediacenter.KKPlugin;
import kkdev.kksystem.plugin.mediacenter.configuration.MediaCenterConf.MediaProcessor;

/**
 *
 * @author blinov_is
 */
public class MediaManager extends PluginManagerBase {
   private MediaProcessor CurrentMediaProcessor;
    
    public void Init(KKPlugin BaseConnector)
    {
        connector=BaseConnector;
    }
    
    public void ReceivePIN(PluginMessage PM)
    {
       switch (PM.PinName)
       {
           case KK_PLUGIN_BASE_CONTROL_DATA:
               processControlCommand((PinControlData)PM.PinData);
               break;
           
       }
    }
    
    
    private void processControlCommand(PinControlData PC)
    {
    
    }
}
