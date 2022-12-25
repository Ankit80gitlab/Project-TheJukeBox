package operation;

import dao.podcastDao;
import dao.songDao;
import dao.playlistDao;
import entity.playlist;
import entity.podcast;
import entity.song;
import audio.SimpleAudioPlayer;


import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class playlistOperation {
    static Scanner sc = new Scanner(System.in);

    public static void disPlayPlaylist() {
        System.out.println("__________________________________________________________________________________________________________________________");
        System.out.println("PLAYLIST AVAILABLE IN JUKEBOX");
        List<String> list = playlistDao.displayPlaylists();
        for (String s : list) {
            System.out.println("PLAYLIST NAME - " + s.toUpperCase());
        }
        System.out.println("__________________________________________________________________________________________________________________________");

    }

    public static void removeCheckPlaylist(String playlistName) {
        int count = 0;
        String n = null;
        List<String> p1List = playlistDao.displayPlaylists();
        for (String s : p1List) {
            if (s.toUpperCase().contains(playlistName.toUpperCase())) {
                count++;
                n = s;
                break;
            }
        }

        if (count != 0) {
            System.out.println("FOUND PLAYLIST - " + n.toUpperCase());
            System.out.println("1.CONFIRM 2.CANCEL");
            int ch = sc.nextInt();
            if (ch == 1) {
                playlistDao.removingPlaylist(n.toUpperCase());
                System.out.println("PLAYLIST " + n.toUpperCase() + " SUCCESSFULLY DELETED");
                disPlayPlaylist();
            } else if (ch == 2) {
                System.out.println("OPERATION CANCELLED");

            } else {
                System.out.println("INALID INPUT");
            }
        }
        if (count == 0) {
            System.out.println("ENTERED PLAYLIST IS NOT AVAILABLE IN JUKEBOX");
        }
    }

    public static List<playlist> playlistDetails(String playlistName) {
        System.out.println("__________________________________________________________________________________________________________________________");

        List<playlist> lifeSaver = new ArrayList<>();

        int count = 0;
        String n = null;
        List<String> p1List = playlistDao.displayPlaylists();
        for (String s : p1List) {
            if (s.toUpperCase().contains(playlistName.toUpperCase())) {
                count++;
                n = s;
                break;
            }
        }

        if (count != 0) {
            List<playlist> pList2 = playlistDao.fetchingDetailsForPlaylistName(n.toUpperCase());
            System.out.println("AVAILABLE AUDIO IN - " + n.toUpperCase() + " PLAYLIST");
            for (playlist p : pList2) {
                System.out.format("%-10s %-35s %-25s %-25s ", p.getiD(), p.getaName().toUpperCase(), p.getaDuration(), p.getaArtist().toUpperCase());
                System.out.println();
                lifeSaver.add(new playlist(p.getiD(), p.getaName().toUpperCase(), p.getaDuration(), p.getaArtist().toUpperCase()));
            }

        } else if (count == 0) {
            System.out.println("ENTERED PLAYLIST IS NOT AVAILABLE IN JUKEBOX");
        }
        System.out.println("__________________________________________________________________________________________________________________________");
        return lifeSaver;
    }

    public static void createSongPlaylist(String playlistName) throws SQLException {
        System.out.println("__________________________________________________________________________________________________________________________");
        int count = 0;
        String n = null;
        List<String> p1List = playlistDao.displayPlaylists();
        for (String s : p1List) {
            if (s.toUpperCase().equalsIgnoreCase(playlistName.toUpperCase())) {
                count++;
                break;
            }
        }
        if (count == 0) {
            playlistDao.createNewPlaylist(playlistName.toUpperCase());
            System.out.println("SUCCESSFULLY CREATED " + playlistName.toUpperCase() + " PLAYLIST");
            playlistOperation.disPlayPlaylist();
        } else if (count != 0) {
            System.out.println("PLAYLIST " + playlistName.toUpperCase() + " AVAILABLE IN JUKEBOX ALREADY");
        } else {
            //System.out.println("INVALID INPUT");
        }
        System.out.println("__________________________________________________________________________________________________________________________");
    }

    public static void checkingAddIntoPlaylist() throws SQLException
    {
        System.out.println("__________________________________________________________________________________________________________________________");

        int count = 0;
        String n = null;


        System.out.println("ENTER PLAYLIST NAME");
        String i1 = sc.next();
        List<String> p1List = playlistDao.displayPlaylists();
        for (String s : p1List)
        {
            if (s.toUpperCase().contains(i1.toUpperCase()))
            {
                count++;
                n = s;
                break;
            }
        }
        if (count != 0)
        {
            System.out.println("FOUND PLAYLIST - " + n.toUpperCase());
            System.out.println("1.CONFIRM 2.CANCEL");
            int choice = sc.nextInt();
            if (choice == 1)
            {
                System.out.println("1. ADD SONG");
                System.out.println("2. ADD PODCAST");
                int i2 = sc.nextInt();
                switch (i2)
                {
                    case 1:
                    System.out.println("ENTER SONG ID TO ADD");
                    int i3 = sc.nextInt();
                    String [] arr1 = checkingSongId(i3);

                    int check1=Integer.parseInt(arr1[4]);
                    if(check1!=0)
                    {
                        int identity = Integer.parseInt(arr1[0]);
                        Time duration = Time.valueOf(arr1[2]);
                        playlistDao.addIntoPlaylist(n.toUpperCase(),identity,arr1[1],duration,arr1[3]);
                        System.out.println("SONG WITH SONG ID "+i3+" SUCCESSFULLY ADDED IN PLAYLIST "+n.toUpperCase());
                        playlistOperation.playlistDetails(n.toUpperCase());
                    }
                    else if (check1==0)
                    {
                        System.out.println("NO SONG  WITH SONG ID " + i3 + " IN JUKEBOX");
                    }
                    else
                    {
                        //System.out.println("INVALID INPUT");
                    }
                    break;

                    case 2:
                    System.out.println("ENTER PODCAST ID TO ADD");
                    int i4 = sc.nextInt();
                    String [] arr2 = checkingPodcastId(i4);

                    int check2=Integer.parseInt(arr2[4]);
                    if(check2!=0)
                    {
                        int identity = Integer.parseInt(arr2[0]);
                        Time duration = Time.valueOf(arr2[2]);
                        playlistDao.addIntoPlaylist(n.toUpperCase(),identity,arr2[1],duration,arr2[3]);
                        System.out.println("PODCAST WITH PODCAST ID "+i4+" SUCCESSFULLY ADDED IN PLAYLIST "+n.toUpperCase());
                        playlistOperation.playlistDetails(n.toUpperCase());
                    }
                    else if (check2==0)
                    {
                        System.out.println("NO PODCAST WITH PODCAST ID " + i4 + " IN JUKEBOX");
                    }
                    else
                    {
                        //System.out.println("INVALID INPUT");
                    }
                    break;
                }
            }
            if (choice == 2)
            {
                System.out.println("OPERATION CANCELLED");
            }
            else
            {
                //System.out.println("INVALID INPUT");
            }
        }
        else if (count == 0)
        {
            System.out.println("UNABLE TO FIND PLAYLIST WITH NAME LIKE " + i1.toUpperCase());
        }
        System.out.println("__________________________________________________________________________________________________________________________");
    }

    public static void checkingRemoveIntoPlaylist() throws SQLException
    {
        System.out.println("__________________________________________________________________________________________________________________________");

        int count = 0;
        String n = null;


        System.out.println("ENTER PLAYLIST NAME");
        String i1 = sc.next();
        List<String> p1List = playlistDao.displayPlaylists();
        for (String s : p1List)
        {
            if (s.toUpperCase().contains(i1.toUpperCase()))
            {
                count++;
                n = s;
                break;
            }
        }
        if (count != 0)
        {
            System.out.println("FOUND PLAYLIST - " + n.toUpperCase());
            System.out.println("1.CONFIRM 2.CANCEL");
            int choice = sc.nextInt();
            if (choice == 1)
            {
                System.out.println("1. REMOVE SONG");
                System.out.println("2. REMOVE PODCAST");
                int i2 = sc.nextInt();
                switch (i2)
                {
                    case 1:
                        System.out.println("ENTER SONG ID TO REMOVE");
                        int i3 = sc.nextInt();
                        String [] arr1 = checkingSongId(i3);

                        int check1=Integer.parseInt(arr1[4]);
                        if(check1!=0)
                        {
                            int identity = Integer.parseInt(arr1[0]);
                            if(identity==i3)
                            {
                                playlistDao.removeIntoPlaylist(n.toUpperCase(),identity);
                                System.out.println("SONG WITH SONG ID "+i3+" SUCCESSFULLY REMOVED FROM PLAYLIST "+n.toUpperCase());
                                playlistOperation.playlistDetails(n.toUpperCase());
                            }
                        }
                        else if (check1==0)
                        {
                            System.out.println("NO SONG WITH SONG ID " + i3 + " AVAILABLE IN JUKEBOX");
                        }
                        break;

                    case 2:
                        System.out.println("ENTER PODCAST ID TO REMOVE");
                        int i4 = sc.nextInt();
                        String [] arr2 = checkingPodcastId(i4);

                        int check2=Integer.parseInt(arr2[4]);
                        if(check2!=0)
                        {
                            int identity = Integer.parseInt(arr2[0]);
                            if(identity==i4)
                            {
                                playlistDao.removeIntoPlaylist(n.toUpperCase(),identity);
                                System.out.println("PODCAST WITH PODCAST ID "+i4+" SUCCESSFULLY REMOVED FROM PLAYLIST "+n.toUpperCase());
                                playlistOperation.playlistDetails(n.toUpperCase());

                            }break;

                        }
                        else if (check2==0)
                        {
                            System.out.println("NO PODCAST WITH PODCAST ID " + i4 + " IN JUKEBOX");
                        }
                        break;
                }
            }
            if (choice == 2)
            {
                System.out.println("OPERATION CANCELLED");
            }

        }
        else if (count == 0)
        {
            System.out.println("UNABLE TO FIND PLAYLIST WITH NAME LIKE " + i1.toUpperCase());
        }
        System.out.println("__________________________________________________________________________________________________________________________");
    }

    public static String[] checkingPodcastId(int aId) throws SQLException
    {
        String[] arr = new String[5];
        List<podcast> list = podcastDao.gettingPodcastFromDatabase();
        int count = 0;

        ListIterator<podcast> li = list.listIterator();
        while (li.hasNext()) {
            podcast p = (podcast) li.next();
            if (p.getPodId() == aId) {
                count++;
                arr[0] = String.valueOf(p.getPodId());
                arr[1] = String.valueOf(p.getPodName());
                arr[2] = String.valueOf(p.getPodDuration());
                arr[3] = String.valueOf(p.getPodAuthor());
            }
        }
        arr[4] = String.valueOf(count);
        return arr;
    }

    public static String[] checkingSongId(int aId) throws SQLException
    {
        String[] arr = new String[5];
        List<song> list = songDao.gettingSongsFromDatabase();
        int count = 0;

        ListIterator<song> li = list.listIterator();
        while (li.hasNext()) {
            song s = (song) li.next();
            if (s.getSongId() == aId) {
                count++;
                arr[0] = String.valueOf(s.getSongId());
                arr[1] = String.valueOf(s.getSongName());
                arr[2] = String.valueOf(s.getSongDuration());
                arr[3] = String.valueOf(s.getSongArtist());
            }
        }
        arr[4] = String.valueOf(count);
        return arr;
    }

    public static int [] checkingCode(String playlistName) throws UnsupportedAudioFileException, SQLException, LineUnavailableException, IOException {


        int count=0;
        List<playlist> list = playlistDetails(playlistName);


        ListIterator<playlist> li = list.listIterator();
        while (li.hasNext())
        {
            playlist p = (playlist) li.next();
            count++;
        }

        int [] arr = new int[count];
        int i = 0;

        if(count==0)
        {
            System.out.println("PLAYLIST IS EMPTY");
        }
        else
        {
            ListIterator<playlist> li2 = list.listIterator();
            while (li2.hasNext())
            {
                playlist p = (playlist) li2.next();
                arr[i]=p.getiD();
                i++;

            }

            if(arr.length==0)
            {
                System.out.println("PLAYLIST IS EMPTY");
            }
            else
            {
                checkCode2(arr);
            }
        }

        return arr;
    }

    public static void checkCode2 (int [] arr) throws UnsupportedAudioFileException, SQLException, LineUnavailableException, IOException
    {
        int count=0;
        int init=0;
        int c=0;
        System.out.println("ENTER ID");
        int t = sc.nextInt();
        for(int n=0 ; n<arr.length; n++)
        {
            if(arr[n]==t)
            {
                init=n;
                count++;
            }
        }

        c=init;

        if(count!=0)
        {
            SimpleAudioPlayer.searchIntoData(arr[c]);

            int ch = 0;
            while (ch != 3) {
                System.out.println("1 FORWARD");
                System.out.println("2 BACKWARD");
                System.out.println("3 EXIT");
                ch = sc.nextInt();
                switch (ch) {
                    case 1:
                        if (c >= arr.length - 1)
                        {
                            System.out.println("NO FURTHER AUDIO IN PLAYLIST");
                        }
                        else
                        {
                            c++;
                            SimpleAudioPlayer.searchIntoData(arr[c]);
                        }
                        break;

                    case 2:
                        if (c == 0)
                        {
                            System.out.println("THIS IS FIRST SONG IN PLAYLIST");
                        }
                        else
                        {
                            c--;
                            SimpleAudioPlayer.searchIntoData(arr[c]);
                        }break;
                }
            }
        }
        else
        {
            System.out.println("SONG / PODCAST IS NOT IN PLAYLIST");
        }
        }
    }


