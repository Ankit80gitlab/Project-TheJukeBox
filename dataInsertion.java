package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class dataInsertion {

    static String user="root";
    static String pass="Root";
    static String url="jdbc:mysql://localhost:3306/jukebox";
    static Connection con;
    static Statement stmt;


    public static void dataInsertionIntoAudio() throws SQLException
    {
        con = DriverManager.getConnection(url, user, pass);
        stmt = con.createStatement();

        int count1 = stmt.executeUpdate("insert ignore into audio values (1,101,null,'song');");
        int count2 = stmt.executeUpdate("insert ignore into audio values (2,102,null,'song');");
        int count3 = stmt.executeUpdate("insert ignore into audio values (3,103,null,'song');");
        int count4 = stmt.executeUpdate("insert ignore into audio values (4,104,null,'song');");
        int count5 = stmt.executeUpdate("insert ignore into audio values (5,105,null,'song');");
        int count6 = stmt.executeUpdate("insert ignore into audio values (6,106,null,'song');");
        int count7 = stmt.executeUpdate("insert ignore into audio values (7,107,null,'song');");
        int count8 = stmt.executeUpdate("insert ignore into audio values (8,108,null,'song');");
        int count9 = stmt.executeUpdate("insert ignore into audio values (9,109,null,'song');");
        int count10 = stmt.executeUpdate("insert ignore into audio values (10,110,null,'song');");
        int count11 = stmt.executeUpdate("insert ignore into audio values (11,null,201,'podcast');");
        int count12 = stmt.executeUpdate("insert ignore into audio values (12,null,202,'podcast');");
        int count13 = stmt.executeUpdate("insert ignore into audio values (13,null,203,'podcast');");
        int count14 = stmt.executeUpdate("insert ignore into audio values (14,null,204,'podcast');");
        int count15 = stmt.executeUpdate("insert ignore into audio values (15,null,205,'podcast');");
        System.out.println("SUCCESSFULLY INSERTED DATA INTO AUDIO");

    }

    public static void dataInsertionIntoSong() throws SQLException
    {
        String url1="src/main/resources/Read All Over - Nathan Moore.wav";
        String url2="src/main/resources/When Looking - Nathan Moore.wav";
        String url3="src/main/resources/Cafe Regrette - Asher Fulero.wav";
        String url4="src/main/resources/Forest Lullabye - Asher Fulero.wav";
        String url5="src/main/resources/Glass - Anno Domini Beats.wav";
        String url6="src/main/resources/Good Day (Wake Up) - NEFFEX.wav";
        String url7="src/main/resources/Skylines - Anno Domini Beats.wav";
        String url8="src/main/resources/Gunpowder Tea - Mini Vandals.wav";
        String url9="src/main/resources/Who Do You Think I Think You Are_ - Mini Vandals.wav";
        String url10="src/main/resources/March of the Hares - Nathan Moore.wav";

        int count1 = stmt.executeUpdate("insert ignore into song values ('101', 'Read All Over', 'Pop', '0:01:30', 'Evergreen', 'Nathan Moore','"+url1+"', 1, 'song');");
        int count2 = stmt.executeUpdate("insert ignore into song values ('102', 'When You Looking', 'pop rock', '0:01:30', 'Hell Razer', 'Mini Vandals','"+url2+"', 2, 'song');");
        int count3 = stmt.executeUpdate("insert ignore into song values ('103', 'Cafe Regrette', 'metal', '0:01:30', 'Mosaic', 'NEFFEX','"+url3+"', 3, 'song');");
        int count4 = stmt.executeUpdate("insert ignore into song values ('104', 'Forest Lullabye', 'hip hop', '0:01:30', 'Souled Out', 'Anno Domini Beats','"+url4+"', 4, 'song');");
        int count5 = stmt.executeUpdate("insert ignore into song values ('105', 'Glass', 'sad', '0:01:30', 'My Heroes', 'Nathan Moore','"+url5+"', 5, 'song');");
        int count6 = stmt.executeUpdate("insert ignore into song values ('106', 'Good Day (Wake Up)', 'hip hop', '0:01:30', 'Hell Razer', 'Yung Logos','"+url6+"', 6, 'song');");
        int count7 = stmt.executeUpdate("insert ignore into song values ('107','Skylines', 'rock', '0:01:30', 'Mosaic', 'Yung Logos','"+url7+"', 7, 'song');");
        int count8 = stmt.executeUpdate("insert ignore into song values ('108', 'Gunpowder Tea', 'dance', '0:01:30', 'Evergreen', 'Nathan Moore','"+url8+"', 8, 'song');");
        int count9 = stmt.executeUpdate("insert ignore into song values ('109', 'Who Do You Think', 'electronic', '0:01:30', 'Break Out', 'NEFFEX','"+url9+"', 9, 'song');");
        int count10 = stmt.executeUpdate("insert ignore into song values ('110', 'March of the Hares', 'rock', '0:01:30', 'Heaven Is', 'Anno Domini Beats','"+url10+"', 10, 'song');");
        System.out.println("SUCCESSFULLY INSERTED DATA INTO SONG");

    }

    public static void dataInsertionIntoPodcast() throws SQLException
    {
        String url1="src/main/resources/welcome to the meetings course.wav";
        String url2="src/main/resources/how to use the passive voice.wav";
        String url3="src/main/resources/how to use reflexive pronouns.wav";
        String url4="src/main/resources/english idioms for success in business.wav";
        String url5="src/main/resources/meet the intrepid english teachers joanne.wav";

        int count1 = stmt.executeUpdate("insert ignore into podcast values('201', 'welcome to the meetings course', '20200305', '0:03:48', 'skills can smooth your way', 'webTalkRadio','"+url1+"', 11, 'podcast');");
        int count2 = stmt.executeUpdate("insert ignore into podcast values('202', 'use of the passive voice', '20201118', '0:06:55', 'the ball was thrown by the pitcher', 'the journey','"+url2+"', 12, 'podcast');");
        int count3 = stmt.executeUpdate("insert ignore into podcast values('203', 'how to use reflexive pronouns', '20190207', '0:05:16', 'we often use reflexive pronouns', 'intrepid english','"+url3+"', 13, 'podcast');");
        int count4 = stmt.executeUpdate("insert ignore into podcast values('204', 'idioms for success in business', '20190824', '0:06:21', 'the phrase literal meaning', 'literatura','"+url4+"', 14, 'podcast');");
        int count5 = stmt.executeUpdate("insert ignore into podcast values('205', 'meet the teachers joanne', '20210713', '0:22:59', 'united kingdom of great britain', 'lets talk about','"+url5+"', 15, 'podcast');");
        System.out.println("SUCCESSFULLY INSERTED DATA INTO PODCAST");
        System.out.println("__________________________________________________________________________________________________________________________");
    }

        public static void dataInsertionIntoPlaylists()
    {
        try
        {
            stmt.executeUpdate("insert into userInfo values (303127,'Ankit@123');");
            stmt.executeUpdate("insert into myPlaylist values(108,'Gunpowder Tea','0:01:30','Nathan Moore');");
            stmt.executeUpdate("insert into myPlaylist values(109,'Who Do You Think','0:01:30','NEFFEX');");
            stmt.executeUpdate("insert into myPlaylist values(105,'Who Do You Think','0:01:30','Nathan Moore');");
            stmt.executeUpdate("insert into favourites values(202,'welcome to the meetings course','0:03:48','webTalkRadio');");
            stmt.executeUpdate("insert into favourites values(205,'meet the teachers joanne','0:06:48','lets talk about');");

        }
        catch (SQLException e)
        {
            System.out.println("PRIMARY KEY VIOLATION");
        }
        System.out.println("__________________________________________________________________________________________________________________________");

    }
    public static void closingConnection() throws SQLException {
        con.close();
        stmt.close();
    }
}
