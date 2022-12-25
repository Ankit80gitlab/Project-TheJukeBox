package dao;

import entity.song;
import entity.user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class songDao {

    static Connection con1;
    static PreparedStatement ps;

    public static List<song> gettingSongsFromDatabase() throws SQLException
    {
        // establish a connection
        String user="root";
        String pass="Root";
        String url="jdbc:mysql://localhost:3306/jukebox";
        con1 = DriverManager.getConnection(url,user,pass);
        ps = con1.prepareStatement("use jukebox;");
        ps.executeUpdate();
        List<song> songList = new ArrayList<>();

        try
        {
            // GETTING ALL THE SONGS FROM SONG TABLE
            String query = "select songId, songName, songGenre, songDuration, songAlbum, songArtist, songURL, audioId, audioType from song;";
            ps = con1.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                int songId = rs.getInt(1);
                String songName = rs.getString(2);
                String songGenre = rs.getString(3);
                Time songDuration = rs.getTime(4);
                String songAlbum = rs.getString(5);
                String songArtist = rs.getString(6);
                String songURL = rs.getString(7);
                int audioId = rs.getInt(8);
                String audioType = rs.getString(9);
                songList.add(new song(songId, songName, songGenre, songDuration, songAlbum, songArtist, songURL, audioId, audioType));
            }
        }
        catch(SQLException e)
        {
            System.out.println("SQLException, database error");
        }
        return songList;
    }



    public static void closingConnection() throws SQLException {
        con1.close();
        ps.close();
    }
}


