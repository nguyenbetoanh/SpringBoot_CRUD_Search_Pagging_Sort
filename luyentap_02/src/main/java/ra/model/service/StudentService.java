package ra.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.model.entity.Student;
import ra.model.repository.StudentRepository;
import ra.model.serviceImp.StudentServiceImp;

import java.util.List;

@Service
public class StudentService implements StudentServiceImp {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student getById(int studentId) {
        return studentRepository.findById(studentId).get();
    }

    @Override
    public Student saveOrUpdate(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteById(int studentId) {
        studentRepository.deleteById(studentId);

    }

    @Override

    public List<Student> searchNameOrId(String studentName, int studentId) {
        return studentRepository.findByStudentNameContainingOrStudentId(studentName,studentId);
    }

    @Override
    public List<Student> sortByName(String direction) {
        if (direction.equals("asc")) {
            return studentRepository.findAll(Sort.by("studentName").ascending());
        } else {
            return studentRepository.findAll(Sort.by("studentName").descending());
        }
    }

    @Override
    public Page<Student> getPagging(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }
}

