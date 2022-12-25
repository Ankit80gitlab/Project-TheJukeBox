package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class database
{
    public database() {
    }

    static Connection con;
    static Statement stmt;

    public static void establishConnection()
    {
        try
        {
            //register the driver and load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("__________________________________________________________________________________________________________________________");
            System.out.println("DRIVER LOADED SUCCESSFULLY");

            // establish a connection
            String user="root";
            String pass="Root";
            String url="jdbc:mysql://localhost:3306/";

            con = DriverManager.getConnection(url,user,pass);
            System.out.println("CONNECTION ESTABLISHED");
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("CLASS NOT FOUND EXCEPTION - PLEASE CHECK CONNECTION");
        }
        catch (SQLException e)
        {
            System.out.println("CONNECTION ERROR - PLEASE CHECK CONNECTION");
        }
    }

    public static void createDatabase ()
    {
        try
        {
            //creating statement object
            stmt = con.createStatement();

            //creating database jukebox and using it
            String query = "create database jukebox;";
            int count = stmt.executeUpdate(query);
            System.out.println("DATABASE 'JUKEBOX' SUCCESSFULLY CREATED");

            String query2 = "use jukebox;";
            int count2 = stmt.executeUpdate(query2);
            System.out.println("__________________________________________________________________________________________________________________________");
        }
        catch(SQLException e)
        {
            System.out.println("ERROR CREATING DATABASE - DATABASE IS ALREADY CREATED");
        }
    }

    public static void createTableAndKey ()
    {
        try
        {
            // creating audio table
            String query1="create table audio(audioId int primary key, songId int, podcastId int, audioType varchar(20));";
            int count1 = stmt.executeUpdate(query1);
            System.out.println("TABLE 'AUDIO' CREATED SUCCESSFULLY");

            // creating song table
            String query2="create table song(songId int primary key, songName varchar(30), songGenre varchar(30), songDuration time, songAlbum varchar(30), songArtist varchar(30), songURL varchar(500), audioId int, audioType varchar(30));";
            int count2 = stmt.executeUpdate(query2);
            System.out.println("TABLE 'SONG' CREATED SUCCESSFULLY");

            // creating podcast table
            String query3="create table podcast(podId int primary key, podName varchar(30), podDate date, podDuration time, podDesc varchar(500), podAuthor varchar(30), podURL varchar(500), audioId int, audioType varchar(30));";
            int count3 = stmt.executeUpdate(query3);
            System.out.println("TABLE 'PODCAST' CREATED SUCCESSFULLY");

            String query8="create table userInfo (userId int primary key, passW varchar(30));";
            int count8 = stmt.executeUpdate(query8);

            String query6="create table MYPLAYLIST(iD int primary key ,aName varchar(30), aDuration time, aArtist varchar(30));";
            int count6 = stmt.executeUpdate(query6);

            String query7="create table FAVOURITES(iD int primary key ,aName varchar(30), aDuration time, aArtist varchar(30));";
            int count7 = stmt.executeUpdate(query7);

            System.out.println("__________________________________________________________________________________________________________________________");

            // creating foreign key for song table
            String query4="alter table song add foreign key (audioId) references audio(audioId) on delete cascade on update cascade;";
            stmt.executeUpdate(query4);

            // creating foreign key for podcast table
            String query5="alter table podcast add foreign key (audioId) references audio(audioId) on delete cascade on update cascade;";
            stmt.executeUpdate(query5);
            System.out.println("FOREIGN KEY GENERATED SUCCESSFULLY");
        }
        catch (SQLException e)
        {
            System.out.println("SQLEXCEPTION DATABASE ERROR");
        }
    }

    public static void createUniqueIndex () throws SQLException
    {
        try
        {
            String query="alter table audio add unique index(songId);";
            int count = stmt.executeUpdate(query);

            String query2="alter table audio add unique index(podcastId);";
            int count2 = stmt.executeUpdate(query2);

            String query3="alter table song add unique index (audioId);";
            int count3 = stmt.executeUpdate(query3);

            String query4="alter table podcast add unique index (audioId);";
            int count4 = stmt.executeUpdate(query4);
            {
                System.out.println("UNIQUE INDEX CREATED SUCCESSFULLY");
            }
        }
        catch (SQLException e)
        {
            System.out.println("SQLEXCEPTION DATABASE ERROR");
        }
        System.out.println("__________________________________________________________________________________________________________________________");
    }

    public static int dropDatabase () throws SQLException
    {
        int count=0;
        try
        {
            String query="drop database jukebox;";
            count = stmt.executeUpdate(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("SQLEXCEPTION DATABASE ERROR");
        }
        return count;
    }

    public static void closingConnection() throws SQLException {
        con.close();
        stmt.close();
    }
}

