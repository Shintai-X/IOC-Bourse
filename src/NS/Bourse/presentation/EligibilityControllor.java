package NS.Bourse.presentation;


import NS.Bourse.metier.IEligibility_Calculation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class EligibilityControllor implements IEligibilityControllor {

    @Autowired
    IEligibility_Calculation std;
    @Override
    public void Show(Long id) throws Exception {
        var sd = std.Eligibilty_Calculation(id);
        System.out.println(sd);
    }
}
