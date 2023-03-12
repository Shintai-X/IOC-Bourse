package NS.Bourse.presentation;


import NS.Bourse.metier.IEligibility_Calculation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EligibilityControllor implements IEligibilityControllor {

    IEligibility_Calculation std;
    @Override
    public void Show(Long id) throws Exception {
        var sd = std.Eligibilty_Calculation(id);
        System.out.println(sd);
    }
}
