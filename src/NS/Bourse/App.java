package NS.Bourse;

import NS.Bourse.dao.Eligibility_dao;
import NS.Bourse.dao.IEligibilty_dao;
import NS.Bourse.metier.Eeligibility_Calculation;
import NS.Bourse.metier.IEligibility_Calculation;
import NS.Bourse.model.Student;
import NS.Bourse.presentation.EligibilityControllor;
import NS.Bourse.presentation.IEligibilityControllor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;
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

    public static void test2() throws Exception {

        String daoClass;
        String serviceClass;
        String controllerClass;

        Properties properties = new Properties();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream("config.properties");

        if (propertiesFile == null) throw new Exception("fichier config introuvable");
        else {
            try {
                properties.load(propertiesFile);
                daoClass        = properties.getProperty("DAO");
                serviceClass    = properties.getProperty("SERVICE");
                controllerClass = properties.getProperty("CONTROLLER");
                propertiesFile.close();
            }
            catch (IOException e){
                throw new Exception("Problème de chargement des propriétés du fichier config");
            }
            finally {
                properties.clear();
            }
        }
        try {
            Class cDao          = Class.forName(daoClass);
            Class cMetier       = Class.forName(serviceClass);
            Class cController   = Class.forName(controllerClass);

            var dao = (IEligibilty_dao<Student,Long>) cDao.getDeclaredConstructor().newInstance();
            var metier = (IEligibility_Calculation) cMetier.getDeclaredConstructor().newInstance();
            var eligiblitycontrollor    = (IEligibilityControllor) cController.getDeclaredConstructor().newInstance();

            // cMetier.getMethod(nom de la methode , type du parametre)
            Method setDao       = cMetier.getMethod("setStudent", IEligibilty_dao.class);
            setDao.invoke(metier,dao);

            Method setMetier    = cController.getMethod("setStd", IEligibility_Calculation.class);
            setMetier.invoke(eligiblitycontrollor,metier);

            eligiblitycontrollor.Show(9901L);
        }
        catch (Exception e ){
            e.printStackTrace();
        }
    }

    public static void test3() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-ioc.xml");
        std = (IEligibilityControllor) context.getBean("controleur");
        std.Show(9901L);
    }

    public static void test4() throws Exception{
        ApplicationContext context = new AnnotationConfigApplicationContext("NS.Bourse.dao","NS.Bourse.metier","NS.Bourse.presentation");
        std = (IEligibilityControllor) context.getBean(IEligibilityControllor.class);
        std.Show(9901L);
    }



    public static void main(String[] args) throws Exception {
        test4();
    }
}
