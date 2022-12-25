package operation;

import dao.podcastDao;
import dao.songDao;
import entity.podcast;
import entity.song;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class podcastOperation
{
    static Scanner sc = new Scanner(System.in);

    public static void displayAllPodcast () throws SQLException
    {
        List<podcast> list = podcastDao.gettingPodcastFromDatabase();
        System.out.println("__________________________________________________________________________________________________________________________");
        System.out.format("%-7s %-33s %-15s %-12s %-37s %-20s","ID","Podcast name","published date","Duration","Description","Created by");
        System.out.println();
        ListIterator<podcast> li = list.listIterator();
        while(li.hasNext())
        {
            podcast p = (podcast)li.next();
            System.out.format("%-7s %-33s %-15s %-12s %-37s %-20s",p.getPodId(),p.getPodName().toUpperCase(),p.getPodDate(),p.getPodDuration(),p.getPodDesc().toUpperCase(),p.getPodAuthor().toUpperCase());
            System.out.println();
        }
        System.out.println("__________________________________________________________________________________________________________________________");
    }

    public static void displayAllPodcastSortByName() throws SQLException
    {
        List<podcast> list = podcastDao.gettingPodcastFromDatabase();
        System.out.println("__________________________________________________________________________________________________________________________");
        System.out.format("%-7s %-33s %-15s %-12s %-37s %-20s","ID","Podcast name","published date","Duration","Description","Created by");
        System.out.println();
        Collections.sort(list, (o1, o2) -> o1.getPodName().compareTo(o2.getPodName()));
        for(podcast p : list)
        {
            System.out.format("%-7s %-33s %-15s %-12s %-37s %-20s",p.getPodId(),p.getPodName().toUpperCase(),p.getPodDate(),p.getPodDuration(),p.getPodDesc().toUpperCase(),p.getPodAuthor().toUpperCase());
            System.out.println();
        }
        System.out.println("__________________________________________________________________________________________________________________________");
    }

    public static int searchingPodcastUsingPodcastId() throws SQLException
    {
        // SEARCHING PODCAST USING PODCAST ID
        int c=0;
        List<podcast> list = podcastDao.gettingPodcastFromDatabase();
        System.out.println("ENTER THE PODCAST ID");
        int input = sc.nextInt();
        System.out.println("__________________________________________________________________________________________________________________________");
        ListIterator<podcast> li = list.listIterator();
        while(li.hasNext())
        {
            podcast p = (podcast)li.next();
            if (p.getPodId()==(input))
            {
                c++;
                System.out.format("%-7s %-33s %-15s %-12s %-37s %-20s",p.getPodId(),p.getPodName().toUpperCase(),p.getPodDate(),p.getPodDuration(),p.getPodDesc().toUpperCase(),p.getPodAuthor().toUpperCase());
                System.out.println();
            }
        }
        if(c==0)
        {
            System.out.println("NO PODCAST ASSOCIATED WITH THE PODCAST ID "+input);
        }
        System.out.println("__________________________________________________________________________________________________________________________");
        return c;
    }

    public static int searchingPodcastUsingPodcastName() throws SQLException
    {
        // SEARCHING PODCAST USING PODCAST NAME
        int c=0;
        List<podcast> list = podcastDao.gettingPodcastFromDatabase();
        System.out.println("ENTER THE PODCAST NAME");
        String input = sc.next();
        input = input.toLowerCase();
        System.out.println("__________________________________________________________________________________________________________________________");
        ListIterator<podcast> li = list.listIterator();
        while(li.hasNext())
        {
            podcast p = (podcast)li.next();
            if (p.getPodName().toLowerCase().contains(input))
            {
                c++;
                System.out.format("%-7s %-33s %-15s %-12s %-37s %-20s",p.getPodId(),p.getPodName().toUpperCase(),p.getPodDate(),p.getPodDuration(),p.getPodDesc().toUpperCase(),p.getPodAuthor().toUpperCase());
                System.out.println();
            }
        }
        if(c==0)
        {
            System.out.println("NO PODCAST ASSOCIATED WITH THE PODCAST NAME "+input.toUpperCase());
        }
        System.out.println("__________________________________________________________________________________________________________________________");
        return c;
    }

    public static int searchingPodcastUsingCreatorName() throws SQLException
    {
        // SEARCHING PODCAST USING CREATOR NAME
        int c=0;
        List<podcast> list = podcastDao.gettingPodcastFromDatabase();
        System.out.println("ENTER THE CREATOR NAME");
        String input = sc.next();
        input = input.toLowerCase();
        System.out.println("__________________________________________________________________________________________________________________________");
        ListIterator<podcast> li = list.listIterator();
        while(li.hasNext())
        {
            podcast p = (podcast)li.next();
            if (p.getPodAuthor().toLowerCase().contains(input))
            {
                c++;
                System.out.format("%-7s %-33s %-15s %-12s %-37s %-20s",p.getPodId(),p.getPodName().toUpperCase(),p.getPodDate(),p.getPodDuration(),p.getPodDesc().toUpperCase(),p.getPodAuthor().toUpperCase());
                System.out.println();
            }
        }
        if(c==0)
        {
            System.out.println("NO PODCAST ASSOCIATED WITH THE CREATOR NAME "+input.toUpperCase());
        }
        System.out.println("__________________________________________________________________________________________________________________________");
        return c;
    }

    public static int searchingPodcastUsingPublishedDate() throws ParseException, SQLException
    {
        int c=0;
        String input ="";
        try
        {
            // SEARCHING PODCAST USING PODCAST PUBLISHED DATE
            List<podcast> list = podcastDao.gettingPodcastFromDatabase();
            System.out.println("ENTER THE PUBLISHED DATE");
            input = sc.next();
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            Date d = sd.parse(input);
            System.out.println("__________________________________________________________________________________________________________________________");
            ListIterator<podcast> li = list.listIterator();
            while(li.hasNext())
            {
                podcast p = (podcast)li.next();
                if (p.getPodDate().compareTo(d)==0)
                {
                    c++;
                    System.out.format("%-7s %-33s %-15s %-12s %-37s %-20s",p.getPodId(),p.getPodName().toUpperCase(),p.getPodDate(),p.getPodDuration(),p.getPodDesc().toUpperCase(),p.getPodAuthor().toUpperCase());
                    System.out.println();
                }
            }
        }
        catch(ParseException e)
        {
            System.out.println("PARSE EXCEPTION");
        }

        if(c==0)
        {
            System.out.println("NO PODCAST ASSOCIATED WITH THE PUBLISHED DATE "+input.toUpperCase());
        }
        System.out.println("__________________________________________________________________________________________________________________________");
        return c;
    }
}












