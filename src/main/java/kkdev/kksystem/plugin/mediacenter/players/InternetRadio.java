/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.mediacenter.players;

import kkdev.kksystem.plugin.mediacenter.configuration.PlayList;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;

/**
 *
 * @author blinov_is
 */
public class InternetRadio implements IPlayer {
    private PlayerInfo currentTrack;
    
    final HeadlessMediaPlayer mediaPlayer = createPlayer();

    private HeadlessMediaPlayer createPlayer( ) {
        HeadlessMediaPlayer headlessMediaPlayer;
        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
        headlessMediaPlayer=mediaPlayerFactory.newHeadlessMediaPlayer();
        
        currentTrack=new PlayerInfo();
        currentTrack.PlayerName="Internet Radio";
        currentTrack.TitleArtist="===";
        currentTrack.TitleDescription="===";
        currentTrack.TrackTimeLine="===";
        return headlessMediaPlayer;
    }

    @Override
    public void play() {
        mediaPlayer.playMedia("http://air.radiorecord.ru/rr_128");
    }

    @Override
    public void play(int PlayListPosition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stop() {
        mediaPlayer.stop();
    }

    @Override
    public void pause() {
        mediaPlayer.pause();
    }

    @Override
    public void resume() {
       mediaPlayer.release();
    }

    @Override
    public void seekForward() {
       //
    }

    @Override
    public void seekBackward() {
      //
    }

    @Override
    public void stepNext() {
        //
    }

    @Override
    public void stepBack() {
        //
    }

    @Override
    public void shuffle() {
      //
    }

    @Override
    public void setPlayList(PlayList[] PList) {
     //
    }

    @Override
    public PlayerInfo getPlayerInfo() {
        return currentTrack;
    }

}
