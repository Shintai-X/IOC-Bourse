package NS.Bourse.dao;

import NS.Bourse.model.Exam;
import NS.Bourse.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor

public class Eligibility_dao implements IEligibilty_dao<Student,Long> {


    public static Set<Student> BDStudents(){
        ArrayList<Exam> exams = new ArrayList<>();
        exams.add(new Exam("CS", 90.0) );
        exams.add(new Exam("CS", 90.0) );
        exams.add(new Exam("CS", 90.0) );
        ArrayList<Exam> exams2 = new ArrayList<>();
        exams.add(new Exam("IA", 20.0));
        exams.add(new Exam("IA", 20.0));
        exams.add(new Exam("IA", 20.0));
        ArrayList<Exam> exams3 = new ArrayList<>();
        exams.add(new Exam("BI", 50.0));
        exams.add(new Exam("BI", 50.0));
        exams.add(new Exam("BI", 50.0));
        return new HashSet<Student>(
                Arrays.asList(
                        new Student(9901L,"Student one",false, exams),
                        new Student(9902L,"Student two",false, exams2),
                        new Student(9903L,"Student three",false, exams3)




                )
        );
    }
    @Override
    public Student findbyId(Long aLong) {
        System.out.println("MYSQL DATA: Students n° : " + aLong);
        return BDStudents()
                .stream()
                .filter(Student -> Student.getId().equals(aLong))
                .findFirst()
                .get();
    }
}
