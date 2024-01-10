package gr.aueb.elearn.repository;

import gr.aueb.elearn.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    List<Teacher> findBySurname(String surname);

    List<Teacher> findBySurnameContainingIgnoreCaseOrFirstNameContainingIgnoreCase(String surname, String firstName);

    Teacher findById(int id);

    @Query("SELECT t FROM Teacher t WHERE CAST(t.id AS string) LIKE %:id%")
    List<Teacher> findByIdContaining(@Param("id") String id);

}