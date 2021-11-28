package org.hau.highartsuniversity.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students_group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int number;

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private Set<Timetable> timetable;


    @OneToMany(mappedBy = "group")
    private Set<Student> students;
}
