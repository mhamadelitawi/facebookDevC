package com.example.facebook.database.mySQL;

import com.example.facebook.manager.PlayListManager;
import com.example.facebook.model.AccessType;
import com.example.facebook.model.PlayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Service
public class PlayListManagerMySql implements PlayListManager {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<PlayList> getAllPlayLists() {

        return jdbcTemplate.query(
                "select * from playlist",  (rs, rowNum) -> new PlayList( rs.getInt("id"), rs.getString("title") ));

    }

    @Override
    public List<PlayList> getAllPlayListsByUserId(Integer id) {

        return jdbcTemplate.query(
                "select p.id as id , p.title as title , a.type as type from playlist p , access a , users u  " +
                        " where p.id = a.playlist_id and a.user_id = u.id and u.id = ?",
                new Object[]{ id },
                (rs, rowNum) ->
                        new PlayList( rs.getInt("id"),  rs.getString("title")  )
        );

    }

   @Override
    public PlayList createPlaylist(String title , Integer userId) {


        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into playlist(title) values( ? )" , Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, title);
            return ps;
        }, keyHolder);

        if(keyHolder == null || keyHolder.getKey() == null) return  null;


        Integer keyId =   ((BigInteger)keyHolder.getKey()).intValue(); //   Math.toIntExact((long) keyHolder.getKey()) ;

        if(keyId < 1)  return null;

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into access(type , user_id , playlist_id ) values( '"  + AccessType.OWNER.toString() +"' , ? , ? )" );
            ps.setInt(1, userId);
            ps.setInt(2, keyId);

            return ps;
        });


       return new PlayList(keyId , title);
    }

    @Override
    public void grantAccess(Integer playListId, Integer user) {

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into access(type , user_id , playlist_id ) values( '"  + AccessType.GRANT.toString() +"' , ? , ? )" );
            ps.setInt(1, user);
            ps.setInt(2, playListId);

            return ps;
        });


    }

    @Override
    public void removeAccess(Integer playListId, Integer userId) {
         jdbcTemplate.update(
                "update access set type = '"+AccessType.DENY.toString()+"' where user_id = ? and playlist_id = ? and type <> '"
                        +AccessType.OWNER.toString()+"'",
                userId, playListId);

    }



}
