import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Schedule {
    private final List<LocalTime> FIXED_HOURS = Arrays.asList(
            //horarios disponiveis para marcar agendamento, criei uma lista de horarios com a classe LocalTime
            LocalTime.of(8,0), LocalTime.of(9,0),
            LocalTime.of(10,0), LocalTime.of(11,0),
            LocalTime.of(12,0), LocalTime.of(14,0),
            LocalTime.of(15,0), LocalTime.of(16,0),
            LocalTime.of(17,0), LocalTime.of(18,0)
    );
    private List<LocalTime> avaibleHours;

    public Schedule(){
        this.avaibleHours = new ArrayList<LocalTime>(FIXED_HOURS);
        // a schedule começa com todos os horarios disponiveis
    }

    public List<LocalTime>getAvaibleHours(){
        return this.avaibleHours;
    }
}
