package audio;

import dao.podcastDao;
import dao.songDao;
import entity.podcast;
import entity.song;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import javax.sound.sampled.*;

public class SimpleAudioPlayer
{

    static Long currentFrame;
    static String status="";
    static String filePath;
    static String url="";

    static Clip clip;
    static AudioInputStream audioInputStream;
    static Scanner sc = new Scanner(System.in);

    public static String searchIntoData (int iD) throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        int count=0;
        List<song> list = songDao.gettingSongsFromDatabase();
        List<podcast> list2 = podcastDao.gettingPodcastFromDatabase();

        ListIterator<song> l1 = list.listIterator();
        ListIterator<podcast> l2 = list2.listIterator();
        while (l1.hasNext())
        {
            while (l2.hasNext())
            {
                podcast p = (podcast) l2.next();
                if (p.getPodId() == iD)
                {
                    count++;
                    url = p.getPodURL();
                    break;
                }
            }
            song s = (song) l1.next();
            if (s.getSongId() == iD)
            {
                count++;
                url = s.getSongURL();
                break;
            }
        }
        if(count==0){System.out.println("GIVEN SONG / PODCAST WITH ID - "+iD+" IS NOT IN JUKEBOX");}
        else if (count!=0)
        {
            System.out.println("NOW PLAYING SONG  PODCAST WITH ID "+iD);
            playerAudio(url);
        }
        return url;
    }


    public static void playerAudio(String url) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        // create AudioInputStream object
        filePath = url;
        File file = new File(filePath);
        audioInputStream = AudioSystem.getAudioInputStream(file.getAbsoluteFile());

        // create clip reference
        clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);
        //clip.loop(Clip.LOOP_CONTINUOUSLY);

        try
        {
            SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer();

            int input=0;

            clip.start();
            status="playing";

            while (input!=5)
            {
                System.out.format("%-15s %-15s %-15s %-30s %-15s","1. PAUSE","2. RESUME","3. RESTART","4. JUMP TO SPECIFIC TIME","5. STOP");
                System.out.println();
                input = sc.nextInt();

                switch (input)
                {
                    case 0:
                        clip.start();
                        status="playing";
                        break;

                    case 1:
                        if (status.equals("paused"))
                        {
                            System.out.println("audio is already paused");
                        }
                        currentFrame = clip.getMicrosecondPosition();
                        clip.stop();
                        status = "paused";
                        break;

                    case 2:
                        if (status.equals("resumed"))
                        {
                            System.out.println("Audio is already being played");
                        }
                        clip.close();
                        resetAudioStream();
                        clip.setMicrosecondPosition(currentFrame);
                        clip.start();
                        status = "resumed";
                        break;

                    case 3:
                        clip.stop();
                        clip.close();
                        resetAudioStream();
                        currentFrame = 0L;
                        clip.setMicrosecondPosition(0);
                        clip.start();
                        break;

                    case 4:
                        System.out.println("Enter time (total time in bytes) "+clip.getMicrosecondLength());
                        Scanner sc = new Scanner(System.in);
                        long input2 = sc.nextLong();
                        if (input2 > 0 && input2 < clip.getMicrosecondLength())
                        {
                            clip.stop();
                            clip.close();
                            resetAudioStream();
                            clip.setMicrosecondPosition(input2);
                            clip.start();
                        }
                        break;

                    case 5:
                        if (status.equals("stopped"))
                        {System.out.println("Audio is already being stopped");}
                        currentFrame = 0L;
                        clip.stop();
                        clip.close();
                        status="stopped";
                        break;
                }
            }
            clip.close();
            audioInputStream.close();
        }

        catch (Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();

        }
    }

    // Method to reset audio stream
    public static void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        File file = new File(filePath);
        audioInputStream = AudioSystem.getAudioInputStream(file.getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
