package ra.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StudentId")
    private int studentId ;
    @Column(name="StudentName",columnDefinition = "nvarchar(50)")
    private String studentName;
    @Column(name = "Age")
    private int age;
    @Column(name="StudentStatus")
    private boolean studentStatus;
}
