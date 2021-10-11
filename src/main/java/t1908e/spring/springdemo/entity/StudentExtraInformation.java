package t1908e.spring.springdemo.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
public class StudentExtraInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String address;
    private String introduction;
    private String phone;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //Join column will create an foreign key column name studentId ín this table referenced to students table
    @JoinColumn(name = "studentId")
    private Student student;
    @Column(updatable = false, insertable = false)
    private int studentId;
}
