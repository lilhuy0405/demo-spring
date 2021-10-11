package t1908e.spring.springdemo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import t1908e.spring.springdemo.entity.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
    Iterable<Student> findStudentByNameContains(String name);
    @Query("Select s from Student s where s.name like %:name% and s.status = :status ")
    Iterable<Student> searchStudent(@Param(value = "name") String name, @Param(value = "status") int status);
}
