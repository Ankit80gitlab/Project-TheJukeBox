package operation;

import connection.dataInsertion;
import connection.database;
import dao.playlistDao;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class songOperationTest {

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
    void searchingSongsUsingSongId() throws SQLException {
        connections();
        int total=0;
        int [] arr = songOperation.searchingSongsUsingSongId();
        for(int i : arr)
        {
            total=total+arr[i];
        }
        assertEquals(10,total);
    }
}