/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.mediacenter.players;

import java.util.Map;
import java.util.TreeMap;
import kkdev.kksystem.plugin.mediacenter.configuration.PlayList;
import kkdev.kksystem.plugin.mediacenter.configuration.PlayListEntry;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

/**
 *
 * @author blinov_is
 */
public class InternetRadio implements IPlayer {
    private PlayerInfo currentTrackInfo;
    private PlayList currentPlayList;
    private Map<String,PlayList> PlayLists;
    
    final EmbeddedMediaPlayer  mediaPlayer = createPlayer();
    String[] VLC_ARGS = {
            "--intf", "dummy",          // no interface
            "--vout", "dummy",          // we don't want video (output)
            "--no-video-title-show",    // nor the filename displayed
            "--no-stats",               // no stats
            "--no-sub-autodetect-file", // we don't want subtitles
            "--no-inhibit",             // we don't want interfaces
            "--no-disable-screensaver", // we don't want interfaces
            "--no-snapshot-preview",    // no blending in dummy vout
            "--alsa-audio-device default",
            "-vvv"
    };
    private EmbeddedMediaPlayer  createPlayer( ) {
        EmbeddedMediaPlayer  headlessMediaPlayer;
        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory(VLC_ARGS);
        headlessMediaPlayer=mediaPlayerFactory.newEmbeddedMediaPlayer();
        return headlessMediaPlayer;
    }
    
    public InternetRadio(){
        PlayLists=new TreeMap<>();
        currentTrackInfo=new PlayerInfo();
        currentTrackInfo.PlayerName="Internet Radio";
        currentTrackInfo.TitleArtist="===";
        currentTrackInfo.TitleDescription="===";
        currentTrackInfo.TrackTimeLine="===";
        
    }
    @Override
    public void play(int step) {
        if (step > 0) {
            playPLItem(currentPlayList.getNextTrack());
        } else if (step == 0) {
            playPLItem(currentPlayList.getCurrentTrack());
        } else {
            playPLItem(currentPlayList.getPrevTrack());
        }
    }
    
    @Override
    public void playPlayListItem(int PlayListPosition) {
        playPLItem(currentPlayList.getTrack(PlayListPosition));
    }
    
    private void playPLItem(PlayListEntry PLE) {
        currentTrackInfo.TitleArtist = PLE.Title;
        currentTrackInfo.TitleDescription = PLE.OnlineTrackInfoArtist;
        mediaPlayer.playMedia(PLE.SourceAddr);
        currentTrackInfo.CurrentVolumeLevel = mediaPlayer.getVolume();
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
       //not supported for streaming
    }

    @Override
    public void seekBackward() {
      //not supported for streaming
    }

    @Override
    public void stepNextTrack() {
       play(1);
    }

    @Override
    public void stepBackTrack() {
         play(-1);
    }

    @Override
    public void shuffle() {
      //
    }

    @Override
    public void addPlayList(PlayList PList) {
        currentPlayList=PList;
        PlayLists.put(PList.PlayListID, PList);
    }

    @Override
    public PlayerInfo getPlayerInfo() {
        currentTrackInfo.isPlaying=mediaPlayer.isPlaying();
        currentTrackInfo.TitleDescription=mediaPlayer.getMediaMetaData().getTitle();
        return currentTrackInfo;
    }

    @Override
    public void increaseVolume(int Step) {
        mediaPlayer.setVolume(mediaPlayer.getVolume()+Step);
         currentTrackInfo.CurrentVolumeLevel=mediaPlayer.getVolume();
    }

    @Override
    public void decreaseVolime(int Step) {
        mediaPlayer.setVolume(mediaPlayer.getVolume()-Step);
        currentTrackInfo.CurrentVolumeLevel=mediaPlayer.getVolume();
    }

    @Override
    public void setActivePlayList(String playListID) {
        currentPlayList = PlayLists.get(playListID);
    }

    @Override
    public void stepNextPlist() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stepPrevPlist() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stopstart() {
        if (currentTrackInfo.isPlaying)
            stop();
        else
            play(0);
    }

}
