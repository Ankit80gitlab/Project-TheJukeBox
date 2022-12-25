package entity;

import connection.dataInsertion;
import connection.database;
import dao.playlistDao;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


class userTest {


    public void init()
    {
    }
    void connections() throws SQLException {
        database.establishConnection();
        database.createDatabase();
        database.createTableAndKey();
        database.createUniqueIndex();
        playlistDao.checkingConnection();
        dataInsertion.dataInsertionIntoAudio();
        dataInsertion.dataInsertionIntoSong();
        dataInsertion.dataInsertionIntoPodcast();
        dataInsertion.dataInsertionIntoPlaylists();
        playlistDao.checkingConnection();}

    @Test
    void checkingCredentials() throws SQLException {
        connections();
        int result = user.checkingCredentials(303127,"Ankit@123");
        assertEquals(1,result);
    }
}