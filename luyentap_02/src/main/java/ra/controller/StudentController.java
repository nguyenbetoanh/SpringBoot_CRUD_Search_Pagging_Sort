package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.Student;
import ra.model.service.StudentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    List<Student> getAll() {
        return studentService.getAllStudent();
    }

    @GetMapping("/{studentId}")
    public Student getById(@PathVariable("studentId") int studentId) {
        return studentService.getById(studentId);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveOrUpdate(student);
    }

    @PutMapping("/{studentId}")
    public Student updateStudent(@PathVariable("studentId") int studentId, @RequestBody Student student) {
        Student updateStudent = studentService.getById(studentId);
        updateStudent.setStudentName(student.getStudentName());
        updateStudent.setAge(student.getAge());
        updateStudent.setStudentStatus(student.isStudentStatus());
        return studentService.saveOrUpdate(updateStudent);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable("studentId") int studentId) {
        studentService.deleteById(studentId);
    }

    @GetMapping("/search")
    public List<Student> searchNameOrId(@RequestParam("studentName") String studentName, @RequestParam("studentId") int studentId) {
        return studentService.searchNameOrId(studentName, studentId);
    }


    @GetMapping("/sortByName")
    public ResponseEntity<List<Student>> sortStudentByName(@RequestParam("direction") String direction) {
        List<Student> listStudent = studentService.sortByName(direction);
        return new ResponseEntity<>(listStudent, HttpStatus.OK);
    }
    @GetMapping("/getPagging")
    public ResponseEntity<Map<String,Object>> getPagging(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Student> pageStudent = studentService.getPagging(pageable);
        Map<String,Object> data = new HashMap<>();
        data.put("books",pageStudent.getContent());
        data.put("total",pageStudent.getSize());
        data.put("totalItems",pageStudent.getTotalElements());
        data.put("totalPages",pageStudent.getTotalPages());
        return  new ResponseEntity<>(data,HttpStatus.OK);
    }
}
