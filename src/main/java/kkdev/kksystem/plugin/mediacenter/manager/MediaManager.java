/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.mediacenter.manager;

import kkdev.kksystem.base.classes.base.PinBaseData;
import kkdev.kksystem.base.classes.base.PinBaseDataTaggedObj;
import kkdev.kksystem.base.classes.geo.PinGeoData;
import kkdev.kksystem.base.classes.plugins.PluginMessage;
import kkdev.kksystem.base.classes.plugins.simple.managers.PluginManagerBase;
import kkdev.kksystem.base.constants.PluginConsts;
import kkdev.kksystem.base.constants.SystemConsts;
import kkdev.kksystem.plugin.mediacenter.KKPlugin;

/**
 *
 * @author blinov_is
 */
public class MediaManager extends PluginManagerBase {
    public static final String GEO_TAG="GEODATA";
   
    public void Init(KKPlugin BaseConnector)
    {
        connector=BaseConnector;
    }
    
    public void ReceivePIN(PluginMessage PM)
    {
        if (!PM.PinName.equals(PluginConsts.KK_PLUGIN_BASE_BASIC_TAGGEDOBJ_DATA))
            return;
        
        PinBaseDataTaggedObj Dat=(PinBaseDataTaggedObj)PM.PinData;
        //===
        if (!Dat.tag.equals(GEO_TAG))
            return;
        if (Dat.dataType!=PinBaseData.BASE_DATA_TYPE.TAGGED_OBJ)
            return;
        //===
        
       PinGeoData PBG=new  PinGeoData();
       
       PBG.FillNMEAData((String)Dat.value);
        
       this.BASE_SendPluginMessage(SystemConsts.KK_BASE_FEATURES_SYSTEM_MULTIFEATURE_UID, PluginConsts.KK_PLUGIN_BASE_GEO_DATA, PBG);
    }
}
