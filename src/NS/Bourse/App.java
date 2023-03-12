package NS.Bourse;

import NS.Bourse.dao.Eligibility_dao;
import NS.Bourse.metier.Eeligibility_Calculation;
import NS.Bourse.presentation.EligibilityControllor;
import NS.Bourse.presentation.IEligibilityControllor;

import java.util.Scanner;

public class App {

    static Scanner clavier = new Scanner(System.in);
    static IEligibilityControllor std;

    private static boolean NS_estUnNombre(String input){
        try {
            Integer.parseInt(input);
            return true;
        }
        catch (Exception e ){
            return false;
        }
    }
    public static void test1(){
        var dao = new Eligibility_dao();
        var metier = new Eeligibility_Calculation();
        var controllor = new EligibilityControllor();
        metier.setStudent(dao);
        controllor.setStd(metier);
        String rep="";
        do {
            System.out.println("=> [Test 1] Eligibility Calculation <= \n");
            try {
                String input = "";
                while (true){
                    System.out.println("=> Please insert the student id : ");
                    input = clavier.nextLine();
                    if (NS_estUnNombre(input)) break;
                    System.err.println("Error !!!");
                }
                long id = Long.parseLong(input);
                controllor.Show(id);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            System.out.println("Quit ?(y/n) ? ");
            rep = clavier.nextLine();
        }while (!rep.equalsIgnoreCase("y"));
        System.out.println("Good bye");
    }

    public static void main(String[] args) {
        test1();
    }
}
