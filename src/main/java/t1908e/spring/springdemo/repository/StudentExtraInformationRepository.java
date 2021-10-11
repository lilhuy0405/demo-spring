package t1908e.spring.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import t1908e.spring.springdemo.entity.StudentExtraInformation;
@Repository
public interface StudentExtraInformationRepository extends JpaRepository<StudentExtraInformation, Integer> {

}
