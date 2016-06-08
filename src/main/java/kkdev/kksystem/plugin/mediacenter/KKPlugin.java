package kkdev.kksystem.plugin.mediacenter;

import kkdev.kksystem.base.classes.plugins.PluginMessage;
import kkdev.kksystem.base.classes.plugins.simple.KKPluginBase;
import kkdev.kksystem.base.interfaces.IPluginBaseInterface;
import kkdev.kksystem.plugin.mediacenter.manager.MediaManager;


/**
 *
 * @author blinov_is
 */
public final class KKPlugin extends KKPluginBase {
    public KKPlugin() {
        super(new MediaPluginInfo());
        Global.MD=new MediaManager();
    }

    @Override
    public void pluginInit(IPluginBaseInterface BaseConnector, String GlobalConfUID) {
        super.pluginInit(BaseConnector, GlobalConfUID);
    //    Global.GM.Init(this);
    }

    
    @Override
    public PluginMessage executePin(PluginMessage Pin) {
        super.executePin(Pin);
        Global.MD.ReceivePIN(Pin);
        return null;
    }
    
    
     @Override
    public void pluginStart() {
         super.pluginStart();
       //  Global.GM.Start();
    }

}
