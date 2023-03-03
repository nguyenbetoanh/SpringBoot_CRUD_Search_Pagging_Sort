package ra.model.serviceImp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.model.entity.Student;

import java.util.List;

public interface StudentServiceImp {
    List<Student> getAllStudent();
    Student getById(int studentId);
    Student saveOrUpdate(Student student);
    void deleteById(int studentId);
    List<Student> searchNameOrId(String studentName,int studentId);
    List<Student> sortByName(String direction);
    Page<Student> getPagging(Pageable pageable);

}
