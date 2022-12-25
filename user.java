package entity;

import dao.playlistDao;
import dao.songDao;

import java.util.List;
import java.util.ListIterator;

public class user
{
    private int userId;
    private String passW;

    public user(int userId, String passW) {
        this.userId = userId;
        this.passW = passW;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassW() {
        return passW;
    }

    public void setPassW(String passW) {
        this.passW = passW;
    }

    @Override
    public String toString() {
        return "user{" +
                "userId=" + userId +
                ", passW='" + passW + '\'' +
                '}';
    }

    public static int checkingCredentials(int userId, String passW)
    {
        int count=0;
        List<user> list=  playlistDao.checkingPassword();
        ListIterator li = list.listIterator();

        while (li.hasNext())
        {
            user u = (user) li.next();
            if(u.userId==userId && u.passW.equals(passW))
            {
                count=1;
            }
        }
        return count;
    }
}
