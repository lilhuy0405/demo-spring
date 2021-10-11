package t1908e.spring.springdemo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;
    private Date createdAt;
    private Date updatedAt;
    private int status;
    //navigate property
    //mapped by is the property'sname in the dependency table in this example is student property in StudentExtraInformation Class
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private StudentExtraInformation information;

    public String getStatusName() {
        switch (this.status) {
            case 1:
                return "active";
            case 0:
                return "deactive";
            case -1:
                return "deleted";
            default:
                return "invalid status code";
        }
    }
}
