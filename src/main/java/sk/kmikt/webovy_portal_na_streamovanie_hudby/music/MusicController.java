package sk.kmikt.webovy_portal_na_streamovanie_hudby.music;

import sk.kmikt.webovy_portal_na_streamovanie_hudby.helper.DataSourceConnection;
import sk.kmikt.webovy_portal_na_streamovanie_hudby.user.User;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MusicController {
    public boolean insertMusic(Music music) throws SQLException, NamingException {
        String sql = "INSERT INTO songs (title, artist, genre, year, url) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DataSourceConnection.getConnection())
        {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, music.getTitle());
            ps.setString(2, music.getArtist());
            ps.setString(3, music.getGenre());
            ps.setInt(4, music.getYear());
            ps.setString(5, music.getUrl());

            return ps.executeUpdate() > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateMusic(Music music) throws SQLException, NamingException
    {
        String sqlUpdateMusic = "UPDATE songs SET title = ?, artist = ?, genre = ?, year = ? WHERE song_id = ?";

        try (Connection conn = DataSourceConnection.getConnection())
        {
            PreparedStatement ps = conn.prepareStatement(sqlUpdateMusic);

            ps.setString(1, music.getTitle());
            ps.setString(2, music.getArtist());
            ps.setString(3, music.getGenre());
            ps.setInt(4, music.getYear());
            ps.setInt(5, music.getSong_id());

            return ps.executeUpdate() > 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public boolean changeFile(Music music) throws SQLException, NamingException
    {
        String sqlChangeFile = "UPDATE songs SET url = ? WHERE song_id = ?";

        try (Connection conn = DataSourceConnection.getConnection())
        {
            PreparedStatement ps = conn.prepareStatement(sqlChangeFile);

            ps.setString(1, music.getUrl());
            ps.setInt(2, music.getSong_id());

            return ps.executeUpdate() > 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteMusic(int id) throws SQLException, NamingException
    {
        String sqlDeleteMusic = "DELETE FROM songs WHERE song_id = ?";

        try (Connection conn = DataSourceConnection.getConnection())
        {
            PreparedStatement ps = conn.prepareStatement(sqlDeleteMusic);

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<Music> getAllSongs() throws SQLException, NamingException {
        ArrayList<Music> allSongs = new ArrayList<>();
        String sql = "SELECT * FROM songs";
        try (Connection conn = DataSourceConnection.getConnection())
        {
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Music song = new Music(
                        rs.getInt("song_id"),
                        rs.getString("title"),
                        rs.getString("artist"),
                        rs.getString("genre"),
                        rs.getInt("year"),
                        rs.getString("url")
                );

                allSongs.add(song);
            }

            return allSongs;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return allSongs;
        }
    }

    public Music getMusicById(int id) throws SQLException, NamingException
    {
        String sqlGetMusicById = "SELECT * FROM songs WHERE song_id = ?";

        try (Connection conn = DataSourceConnection.getConnection())
        {
            PreparedStatement ps = conn.prepareStatement(sqlGetMusicById);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                int song_id = rs.getInt("song_id");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                String genre = rs.getString("genre");
                int year = rs.getInt("year");
                String url = rs.getString("url");

                return id == song_id ? new Music(song_id, title, artist, genre, year, url) : null;
            }

            return null;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
