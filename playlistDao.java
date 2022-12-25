package dao;

import entity.playlist;
import entity.user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class playlistDao {

    static Connection con1;
    static PreparedStatement ps;

    public static void checkingConnection() throws SQLException
    {
        // establish a connection
        String user = "root";
        String pass = "Root";
        String url = "jdbc:mysql://localhost:3306/jukebox";
        con1 = DriverManager.getConnection(url, user, pass);
    }

    public static List<String> displayPlaylists()
    {
        List<String> p1List = new ArrayList<>();
        try
        {
            String query = "SHOW TABLES in jukebox where tables_in_jukebox not LIKE 'song' and tables_in_jukebox not LIKE 'podcast' and tables_in_jukebox not LIKE 'userInfo' and tables_in_jukebox not LIKE 'audio';";
            ps = con1.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                String playlistName = rs.getString(1);
                p1List.add(playlistName);
            }
        }
        catch(SQLException e)
        {
            System.out.println("SQLEXCEPTION DATABASE ERROR");
        }
        return p1List;
    }


    public static List<playlist> fetchingDetailsForPlaylistName(String playlistName)
    {
        List<playlist> pList = new ArrayList<>();
        try
        {
            String query = "select iD, aName, aDuration, aArtist from "+playlistName+";";
            ps = con1.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                int iD = rs.getInt(1);
                String aName = rs.getString(2);
                Time aDuration = rs.getTime(3);
                String aArtist = rs.getString(4);

                pList.add(new playlist(iD,aName,aDuration,aArtist));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return pList;
    }


    public static void removingPlaylist(String playlistName)
    {
        try
        {
            String query = "drop table "+playlistName+";";
            ps = con1.prepareStatement(query);
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("SQLEXCEPTION DATABASE ERROR");
        }
        System.out.println("__________________________________________________________________________________________________________________________");
    }

    public static void createNewPlaylist(String playlistName)
    {
        try
        {
            String query="create table "+playlistName+"(iD int primary key ,aName varchar(30), aDuration time, aArtist varchar(30));";
            ps = con1.prepareStatement(query);
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("SQLEXCEPTION DATABASE ERROR");
        }
    }

    public static void addIntoPlaylist(String playlistName, int iD, String name,Time duration,String artist)
    {
        try
        {
            String query = "insert into "+playlistName+" values("+iD+",'"+name+"','"+duration+"','"+artist+"');";
            ps = con1.prepareStatement(query);
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("AUDIO ID IS ALREADY IN THE PLAYLIST");
        }
    }

    public static void removeIntoPlaylist(String playlistName, int iD)
    {
        try
        {
            String query = "delete from "+playlistName+" where iD = "+iD+";";
            ps = con1.prepareStatement(query);
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("PROBABLY NEVER GONNA PRINT IT :) ");
        }
    }

    public static List<user> checkingPassword()
    {
        List<user> userList = new ArrayList<>();
        try
        {
            String query = "select userId,passW from userInfo;";
            ps = con1.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                int userId = rs.getInt(1);
                String passW = rs.getString(2);
                userList.add(new user(userId,passW));
            }
        }
        catch(SQLException e)
        {
            System.out.println("SQLEXCEPTION DATABASE ERROR");
        }
        return userList;
    }

    public static void closingConnection() throws SQLException {
        con1.close();
    }
}
