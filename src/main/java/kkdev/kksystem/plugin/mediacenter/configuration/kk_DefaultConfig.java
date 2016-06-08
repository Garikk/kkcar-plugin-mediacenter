/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.mediacenter.configuration;

import kkdev.kksystem.base.constants.SystemConsts;
import static kkdev.kksystem.base.constants.SystemConsts.*;


/**
 *
 * @author blinov_is
 *
 * Creating default config
 *
 * Default configuration for mediacenter
 * MUST BE CHANGED IN PRODUCTION
 *
 */
public abstract class kk_DefaultConfig {

    public static MediaCenterConf MakeDefaultConfig() {

        MediaCenterConf DefConf = new MediaCenterConf();
        //
       
        
       return DefConf;
    }
}