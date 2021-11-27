package org.hau.highartsuniversity.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hau.highartsuniversity.Enums.LessonType;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @JsonIgnore
    @OneToOne()
    @JoinColumn(name = "auditorium_id", referencedColumnName = "id")
    private Auditorium auditorium;

    @JsonIgnore
    @ManyToMany(mappedBy = "lessons", fetch = FetchType.EAGER)
    private Set<Lecturer> lecturers;

    @JsonIgnore
    @ManyToMany(mappedBy = "lessons")
    private Set<Timetable> timetables;

    @Enumerated(EnumType.STRING)
    private LessonType lessonType;
}
