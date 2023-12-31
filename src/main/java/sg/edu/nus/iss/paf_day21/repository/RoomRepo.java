package sg.edu.nus.iss.paf_day21.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.paf_day21.model.Room;

@Repository
public class RoomRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private String countSQL = "select count(*) from room";
    private String findAllSQL = "select * from room";
    private String findByIdSQL = "select * from room where id = ?";
    private String deleteByIdSQL = "delete from room where id = ?";
    private String insertSQL = "insert into room (room_type, price) values (?,?)";
    private String updateSQL = "update room set price = ? where id = ?";

    public int count(){
        Integer result = 0;
        result = jdbcTemplate.queryForObject(countSQL, Integer.class);

        return result;
    }

    public List<Room> getAllRooms(){
        List<Room> roomList = new ArrayList<>();
        roomList = jdbcTemplate.query(findAllSQL, 
        BeanPropertyRowMapper.newInstance(Room.class));

        return roomList;
    }

    public Room getRoomById(int id){
        Room room = new Room();
        room = jdbcTemplate.queryForObject(findByIdSQL, 
        BeanPropertyRowMapper.newInstance(Room.class), id);

        return room;
    }
    
    public int deleteRoomById(int id){
        int deleted = 0; 

        deleted = jdbcTemplate.update(deleteByIdSQL, id);
        
        return deleted;
        
        
    }

    public Boolean save(Room room){
        Boolean saved = false;

        jdbcTemplate.execute(insertSQL, new PreparedStatementCallback<Boolean>() {

            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, 
            DataAccessException {
                ps.setString(1, room.getRoomType());
                ps.setInt(2, room.getPrice());
            
                return ps.execute();
            }
            
        });

        return saved;

    }

    public int update(Room room){
        int updated = 0; 

        updated = jdbcTemplate.update(updateSQL, room.getPrice(), room.getId());

        return updated;

    }
}
