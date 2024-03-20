package engine.audio;

import engine.identification.Identifier;
import engine.sound.SoundEffect;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;


import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class AudioManager
{
    private final static Map<Identifier, Clip> sfxRegistry = new HashMap<>();

    public static void registerSoundEffect(Identifier soundID)
    {
        if (sfxRegistry.containsKey(soundID))
            throw new RuntimeException("ID already registered");

        Clip clip = createClip(soundID.getName());
        sfxRegistry.put(soundID, clip);
    }
    public static void registerAudio(Identifier soundID)
    {

    }
    static SoundEffect soundEffect;
    public static void play(Identifier soundID)
    {
        //soundEffect.play();

        Clip clip = sfxRegistry.get(soundID);

        if (clip == null)
            throw new RuntimeException("no audio associated with provided modifier");

        clip.setFramePosition(0);
        clip.start();
    }

    private static Clip createClip(String path)
    {
        try
        {
            var url = AudioManager.class.getClassLoader().getResource("sounds/" + path);

            if(url == null)
                return null;

            InputStream inputStream = AudioManager.class.getClassLoader().getResourceAsStream("collect_coin.mp3");

            try
            {
                AdvancedPlayer player = new AdvancedPlayer(inputStream);
                player.setPlayBackListener(new PlaybackListener()
                {
                    @Override
                    public void playbackFinished(PlaybackEvent evt) {
                        System.out.println("Playback finished at frame " + evt.getFrame());
                    }
                });


                player.play();
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);

            soundEffect = new SoundEffect(audioInputStream);

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            return clip;
        }

        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}