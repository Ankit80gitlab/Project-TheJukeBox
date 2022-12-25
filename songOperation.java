package operation;

import entity.song;
import dao.songDao;

import java.sql.*;
import java.util.*;

public class songOperation {

    static Scanner sc = new Scanner(System.in);

    public static void displayAllSongs() throws SQLException
    {
        List<song> list = songDao.gettingSongsFromDatabase();
        System.out.println("__________________________________________________________________________________________________________________________");
        System.out.format("%-10s %-20s %-20s %-20s %-20s %-20s", "ID", "Name", "Genre", "Duration", "Album", "Artist");
        System.out.println();
        ListIterator<song> li = list.listIterator();
        while(li.hasNext())
        {
            song s = (song)li.next();
            System.out.format("%-10s %-20s %-20s %-20s %-20s %-20s ", s.getSongId(), s.getSongName().toUpperCase(), s.getGenre().toUpperCase(), s.getSongDuration(), s.getSongAlbum().toUpperCase(), s.getSongArtist().toUpperCase());
            System.out.println();
        }
        System.out.println("__________________________________________________________________________________________________________________________");
    }

    public static void displayAllSongsSortByName() throws SQLException
    {
        List<song> list = songDao.gettingSongsFromDatabase();
        System.out.println("__________________________________________________________________________________________________________________________");
        System.out.format("%-10s %-20s %-20s %-20s %-20s %-20s", "ID", "Name", "Genre", "Duration", "Album", "Artist");
        System.out.println();
        Collections.sort(list, (o1, o2) -> o1.getSongName().compareTo(o2.getSongName()));
        for(song s : list)
        {
            System.out.format("%-10s %-20s %-20s %-20s %-20s %-20s ", s.getSongId(), s.getSongName().toUpperCase(), s.getGenre().toUpperCase(), s.getSongDuration(), s.getSongAlbum().toUpperCase(), s.getSongArtist().toUpperCase());
            System.out.println();
        }
        System.out.println("__________________________________________________________________________________________________________________________");
    }

    public static int[] searchingSongsUsingSongId() throws SQLException
    {
        int [] array = new int[2];
        try
        {
            // SEARCHING SONG USING SONG ID
            int c=0;
            List<song> list = songDao.gettingSongsFromDatabase();
            System.out.println("ENTER THE SONG ID");
            int input = sc.nextInt();
            ListIterator<song> li = list.listIterator();
            System.out.println("__________________________________________________________________________________________________________________________");
            while(li.hasNext())
            {
                song s = (song)li.next();
                if (s.getSongId()==(input))
                {
                    c++;
                    System.out.format("%-10s %-20s %-20s %-20s %-20s %-20s ", s.getSongId(), s.getSongName().toUpperCase(), s.getGenre().toUpperCase(), s.getSongDuration(), s.getSongAlbum().toUpperCase(), s.getSongArtist().toUpperCase());
                    System.out.println();
                }
            }
            if(c==0)
            {
                System.out.println("NO SONG ASSOCIATED WITH THE SONG ID "+input);
            }

            array[0]=c;
            array[1]=input;
            System.out.println("__________________________________________________________________________________________________________________________");
        }
        catch (SQLException e){
            System.out.println("SQL Exception");
        }
        catch (InputMismatchException e){
            System.out.println("Input Mismatch");
        }
        return array;
    }

    public static int[] searchingSongsUsingName() throws SQLException
    {
        int [] array = new int[1];
        // SEARCHING SONG USING SONG NAME
        int c=0;
        List<song> list = songDao.gettingSongsFromDatabase();
        System.out.println("ENTER THE SONG NAME");
        String input = sc.next();
        input = input.toLowerCase();
        ListIterator<song> li = list.listIterator();
        System.out.println("__________________________________________________________________________________________________________________________");
        while(li.hasNext())
        {
            song s = (song)li.next();
            if (s.getSongName().toLowerCase().contains(input))
            {
                c++;
                System.out.format("%-10s %-20s %-20s %-20s %-20s %-20s ", s.getSongId(), s.getSongName().toUpperCase(), s.getGenre().toUpperCase(), s.getSongDuration(), s.getSongAlbum().toUpperCase(), s.getSongArtist().toUpperCase());
                System.out.println();
            }

        }
        if(c==0)
        {
            System.out.println("NO SONG ASSOCIATED WITH THE SONG NAME "+input.toUpperCase());
        }
        array[0]=c;
        System.out.println("__________________________________________________________________________________________________________________________");
        return array;
    }

    public static int [] searchingSongsUsingArtist() throws SQLException
    {
        int [] array = new int[1];
        // SEARCHING SONG USING SONG ARTIST
        int c=0;
        List<song> list = songDao.gettingSongsFromDatabase();
        System.out.println("ENTER THE ARTIST NAME");
        String input = sc.next();
        input = input.toLowerCase();
        ListIterator<song> li = list.listIterator();
        System.out.println("__________________________________________________________________________________________________________________________");
        while(li.hasNext())
        {
            song s = (song)li.next();
            if (s.getSongArtist().toLowerCase().contains(input))
            {
                c++;
                System.out.format("%-10s %-20s %-20s %-20s %-20s %-20s ", s.getSongId(), s.getSongName().toUpperCase(), s.getGenre().toUpperCase(), s.getSongDuration(), s.getSongAlbum().toUpperCase(), s.getSongArtist().toUpperCase());
                System.out.println();
            }

        }
        if(c==0)
        {
            System.out.println("NO SONG ASSOCIATED WITH THE ARTIST NAME "+input.toUpperCase());
        }
        array[0]=c;
        System.out.println("__________________________________________________________________________________________________________________________");
        return array;
    }

    public static int []searchingSongsUsingAlbum() throws SQLException
    {
        int [] array = new int[1];
        // displaying song details by song name
        int c=0;
        List<song> list = songDao.gettingSongsFromDatabase();

        System.out.println("ENTER THE ALBUM NAME");
        String input = sc.next();
        input = input.toLowerCase();
        ListIterator<song> li = list.listIterator();
        System.out.println("__________________________________________________________________________________________________________________________");
        while(li.hasNext())
        {
            song s = (song)li.next();
            if (s.getSongAlbum().toLowerCase().contains(input))
            {
                c++;
                System.out.format("%-10s %-20s %-20s %-20s %-20s %-20s ", s.getSongId(), s.getSongName().toUpperCase(), s.getGenre().toUpperCase(), s.getSongDuration(), s.getSongAlbum().toUpperCase(), s.getSongArtist().toUpperCase());
                System.out.println();
            }

        }
        if(c==0)
        {
            System.out.println("NO SONG ASSOCIATED WITH THE ALBUM NAME "+input.toUpperCase());
        }
        array[0]=c;
        System.out.println("__________________________________________________________________________________________________________________________");
        return array;
    }
}
