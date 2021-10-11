package t1908e.spring.springdemo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import t1908e.spring.springdemo.entity.Student;
import t1908e.spring.springdemo.util.ConvertHelper;

@NoArgsConstructor
@ToString
@Getter
@Setter
public class StudentDTO {
    private int id;
    private String name;
    private String email;
    private String status;
    private String createAt;
    //extra
    private String address;
    private String introduction;
    private String phone;

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.email = student.getEmail();
        this.status = student.getStatusName();
        this.createAt = ConvertHelper.convertJavaDateToString(student.getCreatedAt());
        if(student.getInformation() == null) return;
        this.address = student.getInformation().getAddress();
        this.introduction = student.getInformation().getIntroduction();
        this.phone = student.getInformation().getPhone();
    }


}
