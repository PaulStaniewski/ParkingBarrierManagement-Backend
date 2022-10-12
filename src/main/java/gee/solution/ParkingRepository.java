package gee.solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParkingRepository extends Service {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public ParkingRepository(TwilioSmsSender smsSender) {
        super(smsSender);
    }

    public List<Parking> getAll() {
        return jdbcTemplate.query("SELECT id, name, number FROM parking;",
                BeanPropertyRowMapper.newInstance(Parking.class));


    }
    public Parking getById(int id) {
        return jdbcTemplate.queryForObject("Select id, name, number FROM parking WHERE " +
                "id = ?;", BeanPropertyRowMapper.newInstance(Parking.class), id);
    }

    public int
    save(List<Parking> parkings) {
        parkings.forEach(parking -> jdbcTemplate
                .update("INSERT INTO parking(name, number) VALUES(?, ?)",
                        parking.getName(), parking.getNumber() ));
        smsSender.sendSms(new SmsRequest("+48690130831", "yo"));
        
        return 1;
    }
   public int update(Parking parking) {
        return jdbcTemplate.update("UPDATE parking SET name=?, number=? WHERE id=?",
                parking.getName(), parking.getNumber(), parking.getId());




   }
  public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM parking WHERE id=?", id);
  }







}


