/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.mediacenter.configuration;

import kkdev.kksystem.plugin.mediacenter.configuration.PlayList.MediaSourceType;

/**
 *
 * @author blinov_is
 */
public class MediaCenterConf {
   public PlayList[] PlayLists;
   public MediaSourceType[] ActivePlayers;
   public ControlCommands CommandsAssigment;
   
   public MediaSourceType ActivePlayer;
}
