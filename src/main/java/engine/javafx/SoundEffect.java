package engine.javafx;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SoundEffect
{
    private final byte[] audioData;
    public SoundEffect(AudioInputStream audioInputStream)
    {
        audioData = extractAudioData(audioInputStream);
    }
    public void play()
    {
        playSoundFromByteArray(audioData);
    }
    private static byte[] extractAudioData(AudioInputStream audioStream)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try
        {
            AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, byteArrayOutputStream);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        return byteArrayOutputStream.toByteArray();
    }

    private static void playSoundFromByteArray(byte[] audioData)
    {
        ByteArrayInputStream bis = new ByteArrayInputStream(audioData);
        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bis);
            final var clip = AudioSystem.getClip();
            clip.addLineListener(event ->
            {
                if (event.getType() == LineEvent.Type.STOP || event.getType()  == LineEvent.Type.CLOSE)
                {
                    clip.close();
                }
            });

            setVolume(clip,0.1d);
            clip.open(audioInputStream);
            clip.start();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    private static void setVolume(Clip clip, double volume)
    {
        if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN))
        {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            // Calculate gain value within the supported range
            float range = gainControl.getMaximum() - gainControl.getMinimum();
            float gain = (float) ((range * volume) + gainControl.getMinimum());

            // Set the calculated gain value
            gainControl.setValue(gain);
        }
    }
}
