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
   private static SettingsManager Settings;

   public String FeatureID;
   
    public static MediaCenterConf MainConfiguration;

    public static void InitConfig(String GlobalConfigUID, String MyUID) {
         RS_CONF=GlobalConfigUID+"_"+MyUID + ".json";
        
        Settings=new SettingsManager(RS_CONF,MediaCenterConf.class);
        
        
       // System.out.println("[BT][CONFIG] Load configuration");
        MainConfiguration=(MediaCenterConf)Settings.LoadConfig();

        if (MainConfiguration == null) {
            System.out.println("[MDC][CONFIG] Error Load configuration, try create default config");
            Settings.SaveConfig(kk_DefaultConfig.MakeDefaultConfig());
            MainConfiguration=(MediaCenterConf)Settings.LoadConfig();
        }
        if (MainConfiguration == null) {
            System.out.println("[MDC][CONFIG] Load configuration, fatal");
            return;
        }
        //
    }
}
