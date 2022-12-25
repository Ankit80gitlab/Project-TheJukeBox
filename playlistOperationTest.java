package operation;

import connection.dataInsertion;
import connection.database;
import dao.playlistDao;
import entity.playlist;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class playlistOperationTest {

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
    void playlistDetails() throws SQLException {
        connections();
        List<playlist> list = playlistOperation.playlistDetails("my");
        assertEquals(105,list.get(0).getiD());
    }


    @Test
    void checkingCode() throws UnsupportedAudioFileException, SQLException, LineUnavailableException, IOException {
        connections();
        int [] result = playlistOperation.checkingCode("my");
    }

}