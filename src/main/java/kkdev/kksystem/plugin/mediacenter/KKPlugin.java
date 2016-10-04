package kkdev.kksystem.plugin.mediacenter;

import kkdev.kksystem.base.classes.plugins.PluginConfiguration;
import kkdev.kksystem.base.classes.plugins.PluginMessage;
import kkdev.kksystem.base.classes.plugins.simple.KKPluginBase;
import kkdev.kksystem.plugin.mediacenter.manager.MediaManager;
import kkdev.kksystem.base.interfaces.IControllerUtils;
import kkdev.kksystem.plugin.mediacenter.configuration.PluginSettings;
import kkdev.kksystem.base.interfaces.IBaseConnection;


/**
 *
 * @author blinov_is
 */
public final class KKPlugin extends KKPluginBase {
    IControllerUtils SysUtils;
    
    public KKPlugin() {
        super(new MediaPluginInfo());
        Global.MD=new MediaManager();
    }

    @Override
    public void pluginInit(IBaseConnection BaseConnector, String GlobalConfUID) {
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
    
    public IControllerUtils GetUtils() {
        return SysUtils;
    }
 @Override
    public PluginConfiguration getPluginSettings() {
       return PluginSettings.mainConfiguration;
    }
}
