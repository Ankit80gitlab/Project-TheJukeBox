package dao;

import entity.podcast;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class podcastDao
{

    static Connection con1;
    static PreparedStatement ps;

    public static List<podcast> gettingPodcastFromDatabase() throws SQLException
    {
        // establish a connection
        String user="root";
        String pass="Root";
        String url="jdbc:mysql://localhost:3306/jukebox";
        con1 = DriverManager.getConnection(url,user,pass);
        ps = con1.prepareStatement("use jukebox;");
        ps.executeUpdate();
        List<podcast> podcastList = new ArrayList<>();

        try {

            // GETTING ALL PODCAST FROM PODCAST TABLE
            String query1 = "select podId, podName, podDate, podDuration, podDesc, podAuthor, podURL, audioId, audioType from podcast;";
            ps = con1.prepareStatement(query1);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                int podId = rs.getInt(1);
                String podName = rs.getString(2);
                Date podDate = rs.getDate(3);
                Time podDuration = rs.getTime(4);
                String podDesc = rs.getString(5);
                String podAuthor = rs.getString(6);
                String podURL = rs.getString(7);
                int audioId = rs.getInt(8);
                String audioType = rs.getString(9);

                podcastList.add(new podcast(podId, podName, podDate, podDuration, podDesc, podAuthor, podURL, audioId, audioType));
            }
        }
        catch(SQLException e)
        {
            System.out.println("SQLException, database error");
        }
        return podcastList;
    }
    public static void closingConnection() throws SQLException {
        con1.close();
        ps.close();
    }
}

