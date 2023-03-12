package NS.Bourse.model;

import lombok.*;

import java.util.ArrayList;

@Data @AllArgsConstructor @NoArgsConstructor
public class Student {

    private Long id;
    private String fullname;
    private boolean state;
    private ArrayList<Exam>  exams = new ArrayList<Exam>() ;


    public String states(boolean state){
        if(state){
           return  "This student is eligible for the scholarship ";
        }
        else{
            return "This student is not eligible for the scholarship ";
        }
    }
    @Override
    public String toString(){
        var creditStr = "==================================================================== \n";
        creditStr+= "=> Students n°                  : " + getId() + "                        \n";
        creditStr+= "=> Student full name            : " + getFullname() + "           \n";
        creditStr+= "=> Eligibility                  : "+ states(state) + "        \n";
        creditStr+= "==================================================================== \n";
        return creditStr;
    }

}
