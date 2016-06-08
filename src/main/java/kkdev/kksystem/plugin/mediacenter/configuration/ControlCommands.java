/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.mediacenter.configuration;

import java.util.Map;

/**
 *
 * @author blinov_is
 */
public class ControlCommands {
    public enum ControlCommandTypes
    {
        CMD_PLAY,
        CMD_STOP,
        CMD_SEEKFWD,
        CMD_SEEKBWD,
        CMD_NEXT,
        CMD_BACK,
        CMD_VOLUP,
        CMD_VOLDOWN,
        CMD_MUTE,
        CMD_SHUFFLEMODE,
        CMD_REPEATMODE,
        CMD_PRESET_1,
        CMD_PRESET_2,
        CMD_PRESET_3,
        CMD_PRESET_4,
        CMD_PRESET_5,
        CMD_PRESET_6,
        CMD_PRESET_7,
        CMD_PRESET_8,
        CMD_PRESET_9,
        CMD_PRESET_10
        
        
    }
    
    public Map<ControlCommandTypes,String> controlsAssigment;
}
