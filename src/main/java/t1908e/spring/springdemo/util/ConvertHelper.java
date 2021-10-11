package t1908e.spring.springdemo.util;

import t1908e.spring.springdemo.dto.StudentDTO;
import t1908e.spring.springdemo.entity.Student;
import t1908e.spring.springdemo.entity.StudentExtraInformation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertHelper {
    private static SimpleDateFormat formater;
    public static String convertJavaDateToSqlDateTime(Date date) {
        if(date == null) {
            return null;
        }
        formater =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return formater.format(date);

    }

    public static String convertJavaDateToSqlDate(Date date) {
        if(date == null) {
            return null;
        }
        formater =  new SimpleDateFormat("yyyy-MM-dd");
        return formater.format(date);
    }

    public static Date convertSqlDateToJavaDate(java.sql.Date sqlDate) {
        if(sqlDate == null) {
            return null;
        }
        return new Date(sqlDate.getTime());
    }
    public static Date convertSqlTimeStampToJavaDate(java.sql.Timestamp sqlTime) {
        if(sqlTime == null) {
            return null;
        }
        return new Date(sqlTime.getTime());
    }

    public static String convertJavaDateToString(Date date) {
        formater =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formater.format(date);
    }
    public static StudentDTO convertStudentToStudentDTO(Student student) {
        return new StudentDTO(student);
    }

    public static Student convertStudentDtoToStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        if(studentDTO.getCreateAt() != null) {
            student.setCreatedAt(ConvertHelper.convertStringToJavaDate(studentDTO.getCreateAt()));
        }
        if(studentDTO.getStatus() != null) {
            if(studentDTO.getStatus().equalsIgnoreCase("active")) student.setStatus(1);
            if(studentDTO.getStatus().equalsIgnoreCase("deactive")) student.setStatus(0);
            if(studentDTO.getStatus().equalsIgnoreCase("deleted")) student.setStatus(-1);
        }
        StudentExtraInformation extraInformation  = new StudentExtraInformation();
        extraInformation.setAddress(studentDTO.getAddress());
        extraInformation.setIntroduction(studentDTO.getIntroduction());
        extraInformation.setPhone(studentDTO.getPhone());
        student.setInformation(extraInformation);
        return student;
    }

    private static Date convertStringToJavaDate(String dateString) {
        formater =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parse = formater.parse(dateString);
            return parse;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
