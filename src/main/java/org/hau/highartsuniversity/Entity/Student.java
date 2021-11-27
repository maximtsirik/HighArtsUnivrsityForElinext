package org.hau.highartsuniversity.Entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String fullName;
    private int studyYear;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

}
