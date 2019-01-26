package player;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Player {

    public static void play(String filename)
    {
        try
        {
            Clip clip;
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(filename));
            DataLine.Info info = new DataLine.Info(Clip.class, inputStream.getFormat());
            clip = (Clip)AudioSystem.getLine(info);
            clip.open(inputStream);
            clip.start();
        }
        catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex)
        {
            ex.printStackTrace();
        }
    }

}
