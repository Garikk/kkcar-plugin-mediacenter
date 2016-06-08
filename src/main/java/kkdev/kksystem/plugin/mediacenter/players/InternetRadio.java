/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.mediacenter.players;

import java.util.ArrayList;
import java.util.List;
import kkdev.kksystem.plugin.mediacenter.configuration.PlayList;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

/**
 *
 * @author blinov_is
 */
public class InternetRadio implements IPlayer {

    final List<String> vlcArgs = new ArrayList<>();
    final EmbeddedMediaPlayer mediaPlayer = createPlayer(vlcArgs);

    private EmbeddedMediaPlayer createPlayer(final List<String> vlcArgs) {
        EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        EmbeddedMediaPlayer embeddedMediaPlayer = mediaPlayerComponent.getMediaPlayer();

        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory(vlcArgs.toArray(new String[vlcArgs.size()]));
        mediaPlayerFactory.setUserAgent("vlcj test player");
        embeddedMediaPlayer.setPlaySubItems(true);

        return embeddedMediaPlayer;
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

}