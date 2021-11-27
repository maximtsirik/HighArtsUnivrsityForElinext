package org.hau.highartsuniversity.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDate date;

    @ManyToMany
    @JoinTable(
            name = "timetable_lesson",
            joinColumns = {@JoinColumn(name = "timetable_id")},
            inverseJoinColumns = {@JoinColumn(name = "lesson_id")}
    )
    private Set<Lesson> lessons;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
}
