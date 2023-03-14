package NS.Bourse.metier;


import NS.Bourse.dao.IEligibilty_dao;
import NS.Bourse.model.Exam;
import NS.Bourse.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Eeligibility_Calculation implements IEligibility_Calculation {

    @Autowired
        @Qualifier("d1")


    IEligibilty_dao<Student,Long> student;


    @Override
    public Student Eligibilty_Calculation(Long id) throws Exception {

        var std = student.findbyId(id);

        if (std == null)
        {
            throw new Exception("Student Not found");
        }

        else {
            Double avg= 0.0;
            for(Exam ex: std.getExams()){

                avg += ex.getMark();
            }
            if(avg>150.0){
                std.setState(true);
            }
        }

            return std;
    }
}
