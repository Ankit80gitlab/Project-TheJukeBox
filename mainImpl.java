package app;

import audio.SimpleAudioPlayer;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import connection.dataInsertion;
import connection.database;
import dao.playlistDao;
import entity.user;
import operation.playlistOperation;
import operation.podcastOperation;
import operation.songOperation;
import dao.songDao;
import dao.podcastDao;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import static java.lang.System.exit;

public class mainImpl {

    static Scanner sc = new Scanner(System.in);
    static int mainInput = 0;

    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException, UnsupportedAudioFileException, LineUnavailableException, IOException
    {
        //calling database class method
        database.establishConnection();
        database.createDatabase();
        database.createTableAndKey();
        database.createUniqueIndex();
        playlistDao.checkingConnection();

        //calling dataInsertion class method
        dataInsertion.dataInsertionIntoAudio();
        dataInsertion.dataInsertionIntoSong();
        dataInsertion.dataInsertionIntoPodcast();
        dataInsertion.dataInsertionIntoPlaylists();
        playlistDao.checkingConnection();

        System.out.println("ENTER USER ID");
        int us = sc.nextInt();
        System.out.println("ENTER PASSWORD");
        String passW = sc.next();
        int status = user.checkingCredentials(us,passW);
        if(status==0)
        {
            System.out.println("INCORRECT CREDENTIALS");
            database.dropDatabase();
            exit(0);
        }

        System.out.println("                                                     WELCOME TO JUKEBOX                                                   ");
        songOperation.displayAllSongs();
        podcastOperation.displayAllPodcast();

        while (mainInput != 8)
        {
            System.out.println("1. SHOW ALL SONGS");
            System.out.println("2. SHOW ALL PODCAST");
            System.out.println("3. SEARCH SONGS");
            System.out.println("4. SEARCH PODCAST");
            System.out.println("5. CREATE PLAYLIST");
            System.out.println("6. MANAGE PLAYLIST");
            System.out.println("7. PLAY SONG");
            System.out.println("8. EXIT");
            System.out.println();
            System.out.println("ENTER CHOICE");
            System.out.println();
            mainInput = sc.nextInt();

            switch (mainInput) {
                case 1:
                    int c1=0;
                    while(c1!=3) {
                        System.out.println("1. SHOW SONGS BY ID");
                        System.out.println("2. SORT SONG BY SONG NAME");
                        System.out.println("3. MAIN MENU / 4. EXIT");
                        System.out.println("ENTER CHOICE");
                        c1 = sc.nextInt();
                        if (c1 == 1) {
                            songOperation.displayAllSongs();
                        } else if (c1 == 2) {
                            songOperation.displayAllSongsSortByName();
                        }else if (c1 == 4) {
                            mainInput=8;
                            break;
                        }
                        else {
                            System.out.println("GIVEN INPUT IS WRONG");
                        }
                    }
                    break;

                case 2:
                    int c2=0;
                    while(c2!=3)
                    {

                    System.out.println("1. SHOW PODCAST BY ID");
                    System.out.println("2. SORT PODCAST BY PODCAST NAME");
                    System.out.println("3. MAIN MENU / 4. EXIT");
                    System.out.println("ENTER CHOICE");
                    c2 = sc.nextInt();
                    if (c2 == 1) {
                        podcastOperation.displayAllPodcast();
                    } else if (c2 == 2) {
                        podcastOperation.displayAllPodcastSortByName();
                    }
                    else if (c2 == 4) {mainInput=8;break;}
                    else {
                        System.out.println("GIVEN INPUT IS WRONG");
                    }
                    }break;

                case 3:
                    int c3=0;
                    while(c3!=5)
                    {
                        System.out.println("1. SEARCH SONG BY SONG ID");
                        System.out.println("2. SEARCH SONG BY SONG NAME");
                        System.out.println("3. SEARCH SONG BY ARTIST");
                        System.out.println("4. SEARCH SONG BY ALBUM");
                        System.out.println("5. MAIN MENU / 6. EXIT");
                        System.out.println("ENTER CHOICE");
                        c3 = sc.nextInt();

                        if (c3 == 1) {
                            songOperation.displayAllSongs();
                            int[] arr = songOperation.searchingSongsUsingSongId();
                            if (arr[0] == 0) break;
                            else {
                                System.out.println("ENTER ID TO PLAY SONG / 0 TO MAIN MENU");
                                int choice = sc.nextInt();
                                if (choice == 0) {
                                    break;
                                } else {
                                    SimpleAudioPlayer.searchIntoData(choice);
                                    break;
                                }
                            }

                        } else if (c3 == 2) {
                            songOperation.displayAllSongs();
                            int[] arr = songOperation.searchingSongsUsingName();
                            if (arr[0] == 0) break;
                            else {
                                System.out.println("ENTER ID TO PLAY SONG / 0 TO MAIN MENU");
                                int choice = sc.nextInt();
                                if (choice == 0) {
                                    break;
                                } else {
                                    SimpleAudioPlayer.searchIntoData(choice);
                                    break;
                                }
                            }

                        } else if (c3 == 3) {
                            songOperation.displayAllSongs();
                            int[] arr = songOperation.searchingSongsUsingArtist();
                            if (arr[0] == 0) break;
                            else {
                                System.out.println("ENTER ID TO PLAY SONG / 0 TO MAIN MENU");
                                int choice = sc.nextInt();
                                if (choice == 0) {
                                    break;
                                } else {
                                    SimpleAudioPlayer.searchIntoData(choice);
                                    break;
                                }
                            }

                        } else if (c3 == 4) {
                            songOperation.displayAllSongs();
                            int[] arr = songOperation.searchingSongsUsingAlbum();
                            if (arr[0] == 0) break;
                            else {
                                System.out.println("ENTER ID TO PLAY SONG / 0 TO MAIN MENU");
                                int choice = sc.nextInt();
                                if (choice == 0) {
                                    break;
                                } else {
                                    SimpleAudioPlayer.searchIntoData(choice);
                                    break;
                                }
                            }
                        } else if (c3==6)
                        {
                            mainInput=8;
                            break;
                        }
                        else {
                            System.out.println("GIVEN INPUT IS WRONG");
                            break;
                        }
                    }
                    break;

                case 4:
                    int c4=0;
                    while (c4!=5)
                    {
                    System.out.println("1. SEARCH PODCAST BY PODCAST ID");
                    System.out.println("2. SEARCH PODCAST BY PODCAST NAME ");
                    System.out.println("3. SEARCH PODCAST BY CREATOR NAME ");
                    System.out.println("4. SEARCH PODCAST BY PUBLISHED DATE");
                    System.out.println("5. MAIN MENU 6. EXIT");
                    System.out.println("ENTER CHOICE");
                    c4 = sc.nextInt();
                    if (c4 == 1) {
                        podcastOperation.displayAllPodcast();
                        int i = podcastOperation.searchingPodcastUsingPodcastId();
                        if(i==0)break;
                        else
                        {
                            System.out.println("ENTER PODCAST ID TO PLAY  / 0 TO MAIN MENU");
                            int choice = sc.nextInt();
                            if (choice == 0)
                            {
                                break;
                            }
                            else
                            {
                                SimpleAudioPlayer.searchIntoData(choice);
                                break;
                            }
                        }
                    }
                    else if (c4 == 2) {
                        podcastOperation.displayAllPodcast();
                        int i = podcastOperation.searchingPodcastUsingPodcastName();
                        if (i == 0) break;
                        else {
                            System.out.println("ENTER PODCAST ID TO PLAY  / 0 TO MAIN MENU");
                            int choice = sc.nextInt();
                            if (choice == 0) {
                                break;
                            } else {
                                SimpleAudioPlayer.searchIntoData(choice);
                                break;
                            }
                        }
                    }
                    else if (c4 == 3) {
                        podcastOperation.displayAllPodcast();
                        int i = podcastOperation.searchingPodcastUsingCreatorName();
                        if (i == 0) break;
                        else {
                            System.out.println("ENTER PODCAST ID TO PLAY  / 0 TO MAIN MENU");
                            int choice = sc.nextInt();
                            if (choice == 0) {
                                break;
                            } else {
                                SimpleAudioPlayer.searchIntoData(choice);
                                break;
                            }
                        }
                    } else if (c4 == 4) {
                        podcastOperation.displayAllPodcast();
                        int i = podcastOperation.searchingPodcastUsingPublishedDate();
                        if (i == 0) break;
                        else {
                            System.out.println("ENTER PODCAST ID TO PLAY  / 0 TO MAIN MENU");
                            int choice = sc.nextInt();
                            if (choice == 0) {
                                break;
                            } else {
                                SimpleAudioPlayer.searchIntoData(choice);
                                break;
                            }
                        }
                    }
                    else if (c4==6)
                    {
                        mainInput=8;
                        break;
                    }

                    else
                    {
                        System.out.println("GIVEN INPUT IS WRONG");
                        break;
                    }
                }break;

                case 5:
                    int c5 = 0;
                    while (c5!=4)
                    {

                        System.out.println("1. NEW PLAYLISTS");
                        System.out.println("2. ADD INTO PLAYLIST");
                        System.out.println("3. REMOVE FROM PLAYLIST");
                        System.out.println("4. MAIN MENU");
                        System.out.println("ENTER CHOICE");

                        c5 = sc.nextInt();
                        switch (c5)
                        {
                            case 1:
                                System.out.println("ENTER THE PLAYLIST NAME");
                                String ch = sc.next();
                                playlistOperation.createSongPlaylist(ch);

                                break;

                            case 2:
                                playlistOperation.checkingAddIntoPlaylist();
                                break;

                            case 3:
                                playlistOperation.checkingRemoveIntoPlaylist();
                                break;

                        }
                    }
                    break;

                case 6:
                    int c6 = 0;
                    while (c6!=4)
                    {

                    System.out.println("1. LIST PLAYLISTS");
                    System.out.println("2. SHOW PLAYLISTS");
                    System.out.println("3. DELETE PLAYLISTS");
                    System.out.println("4. MAIN MENU");
                    System.out.println("ENTER CHOICE");

                    c6 = sc.nextInt();
                    switch (c6)
                    {
                        case 1:
                            playlistOperation.disPlayPlaylist();
                            break;

                        case 2:
                            System.out.println("ENTER PLAYLIST NAME");
                            String pName = sc.next();
                            playlistOperation.playlistDetails(pName);
                            break;

                        case 3:
                            System.out.println("ENTER PLAYLIST NAME TO DELETE");
                            String p1Name = sc.next();
                            playlistOperation.removeCheckPlaylist(p1Name);
                            break;
                    }

                }
                break;

                case 7:

                    int c7=0;
                    while(c7!=3)
                    {
                    System.out.println("1. PLAY SONG BY ID");
                    System.out.println("2. PLAY SONG BY PLAYLIST");
                    System.out.println("3. MAIN MENU");
                    System.out.println("ENTER CHOICE");
                    c7= sc.nextInt();
                    switch (c7)
                    {
                        case 1:
                            int ch=0;
                            while (ch!=2){
                            System.out.println("1. ENTER SONG / PODCAST ID");
                            System.out.println("2. MAIN MENU");
                            int choice = sc.nextInt();
                            if (choice == 2) {
                                break;
                            } else {
                                SimpleAudioPlayer.searchIntoData(choice);
                            }
                            }break;

                        case 2:
                            playlistOperation.disPlayPlaylist();
                            System.out.println("ENTER PLAYLIST NAME");
                            String pName = sc.next();
                            playlistOperation.checkingCode(pName);
                    }

                    }
            }
        }
        System.out.println("THANK YOU FOR USING JUKEBOX");
        int c =database.dropDatabase();
        if(c!=0)
        {
            System.out.println("DATABASE DROPPED SUCCESSFULLY");
        }
        database.closingConnection();
        dataInsertion.closingConnection();
        playlistDao.closingConnection();
        podcastDao.closingConnection();
        songDao.closingConnection();
        System.out.println("__________________________________________________________________________________________________________________________");
    }
}
