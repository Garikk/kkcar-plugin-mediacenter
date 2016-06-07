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
    public void PluginInit(IPluginBaseInterface BaseConnector, String GlobalConfUID) {
        super.PluginInit(BaseConnector, GlobalConfUID);
    //    Global.GM.Init(this);
    }

    
    @Override
    public PluginMessage ExecutePin(PluginMessage Pin) {
        super.ExecutePin(Pin);
        Global.MD.ReceivePIN(Pin);
        return null;
    }
    
    
     @Override
    public void PluginStart() {
         super.PluginStart();
       //  Global.GM.Start();
    }

}
