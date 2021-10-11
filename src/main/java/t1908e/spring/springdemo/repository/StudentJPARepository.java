package t1908e.spring.springdemo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import t1908e.spring.springdemo.entity.Student;

public interface StudentJPARepository extends JpaRepository<Student, Integer> {

    @Query("Select s from Student s where s.name like %:name% and s.status = :status ")
    Page<Student> searchStudentPageable(@Param(value = "name") String name, @Param(value = "status") int status, Pageable pageable);

    Iterable<Student> findStudentByNameContains(String name);

    @Query("Select s from Student s where s.name like %:name% and s.status = :status ")
    Iterable<Student> searchStudent(String name, int status);
}
