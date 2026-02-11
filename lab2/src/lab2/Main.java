package lab2;

import entities.Employe;
import service.EmployeService;

public class Main {
    public static void main(String[] args) {
        try {
            EmployeService service = new EmployeService();

            for (Employe e : service.listEmployes()) {
                System.out.println(e);
            }

        } catch (Exception ex) {
            System.out.println("Erreur: " + ex.getMessage());
        }
    }
}

