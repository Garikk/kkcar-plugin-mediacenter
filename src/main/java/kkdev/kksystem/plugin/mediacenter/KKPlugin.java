package kkdev.kksystem.plugin.mediacenter;

import kkdev.kksystem.base.classes.plugins.PluginMessage;
import kkdev.kksystem.base.classes.plugins.simple.KKPluginBase;
import kkdev.kksystem.base.interfaces.IKKControllerUtils;
import kkdev.kksystem.base.interfaces.IPluginBaseInterface;
import kkdev.kksystem.plugin.mediacenter.manager.MediaManager;


/**
 *
 * @author blinov_is
 */
public final class KKPlugin extends KKPluginBase {
    IKKControllerUtils SysUtils;
    
    public KKPlugin() {
        super(new MediaPluginInfo());
        Global.MD=new MediaManager();
    }

    @Override
    public void pluginInit(IPluginBaseInterface BaseConnector, String GlobalConfUID) {
        super.pluginInit(BaseConnector, GlobalConfUID);
        SysUtils=BaseConnector.systemUtilities();
        Global.MD.init(this);
    }

    
    @Override
    public void executePin(PluginMessage Pin) {
        super.executePin(Pin);
        Global.MD.receivePin(Pin);
    }
    
    
     @Override
    public void pluginStart() {
         super.pluginStart();
         Global.MD.start();
    }
    
    public IKKControllerUtils GetUtils() {
        return SysUtils;
    }

}
