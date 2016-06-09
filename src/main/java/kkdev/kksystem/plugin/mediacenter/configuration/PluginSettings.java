/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.mediacenter.configuration;

import kkdev.kksystem.base.classes.plugins.simple.SettingsManager;

/**
 *
 * @author blinov_is
 */
public abstract class PluginSettings {

   public static  String RS_CONF;
   private static SettingsManager settings;

   public String featureID;
   
    public static MediaCenterConf mainConfiguration;

    public static void initConfig(String GlobalConfigUID, String MyUID) {
         RS_CONF=GlobalConfigUID+"_"+MyUID + ".json";
        
        settings=new SettingsManager(RS_CONF,MediaCenterConf.class);
        
        
       // System.out.println("[BT][CONFIG] Load configuration");
        mainConfiguration=(MediaCenterConf)settings.loadConfig();

        if (mainConfiguration == null) {
            System.out.println("[MDC][CONFIG] Error Load configuration, try create default config");
            settings.saveConfig(kk_DefaultConfig.MakeDefaultConfig());
            mainConfiguration=(MediaCenterConf)settings.loadConfig();
        }
        if (mainConfiguration == null) {
            System.out.println("[MDC][CONFIG] Load configuration, fatal");
            return;
        }
        //
    }
}
