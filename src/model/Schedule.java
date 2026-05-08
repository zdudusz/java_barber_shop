package model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Representa a agenda da barbearia, gerenciando os horários fixos e os horários disponíveis para agendamento.
 */
public class Schedule {
    // Lista imutável de horários fixos que a barbearia oferece.
    // Estes são os horários padrão que são restaurados quando o sistema é reiniciado.
    private final List<LocalTime> FIXED_HOURS = Arrays.asList(
            LocalTime.of(8,0), LocalTime.of(9,0),
            LocalTime.of(10,0), LocalTime.of(11,0),
            LocalTime.of(12,0), LocalTime.of(14,0),
            LocalTime.of(15,0), LocalTime.of(16,0),
            LocalTime.of(17,0), LocalTime.of(18,0)
    );

    // Lista mutável de horários atualmente disponíveis para agendamento.
    // Esta lista é modificada conforme os agendamentos são feitos e cancelados.
    private List<LocalTime> avaibleHours;

    /**
     * Construtor da classe Schedule.
     * Inicializa a lista de horários disponíveis com todos os horários fixos.
     */
    public Schedule(){
        this.avaibleHours = new ArrayList<LocalTime>(FIXED_HOURS);
    }

    /**
     * Retorna a lista de horários atualmente disponíveis.
     * @return Uma lista de objetos LocalTime representando os horários disponíveis.
     */
    public List<LocalTime>getAvaibleHours(){
        return this.avaibleHours;
    }

    /**
     * Reinicia a lista de horários disponíveis, restaurando-a para os horários fixos originais.
     * Isso efetivamente "limpa" a agenda de todos os agendamentos feitos.
     */
    public void resetToFixedHours() {
        this.avaibleHours = new ArrayList<LocalTime>(FIXED_HOURS);
    }
}
