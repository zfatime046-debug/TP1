package service;

import dao.EmployeDao;
import entities.Employe;
import dao.MachineDao;
import entities.Machine;
import java.sql.Connection;
import util.Connexion;

import java.util.List;

public class EmployeService {
    private final EmployeDao dao = new EmployeDao();
    private final MachineDao machineDao = new MachineDao();


    public Employe getEmploye(Employe e) throws Exception {
        return dao.findById(e.getId());
    }

    public List<Employe> listEmployes() throws Exception {
        return dao.findAll();
    }

    public Employe createEmploye(Employe e) throws Exception {
        if (e == null) throw new IllegalArgumentException("Employe null");
if (e.getNom() == null || e.getNom().trim().isEmpty())
    throw new IllegalArgumentException("Nom obligatoire");
if (e.getPoste() == null || e.getPoste().trim().isEmpty())
    throw new IllegalArgumentException("Poste obligatoire");

        dao.insert(e);
        return e;
    }

    public boolean updateEmploye(Employe e) throws Exception {
        return dao.update(e);
    }

    public boolean deleteEmploye(Employe e) throws Exception {
        return dao.delete(e.getId());
    }

public void createEmployeAndMachine(Employe e, Machine m) throws Exception {

    Connection cn = Connexion.getInstance().getConnection();

    try {
        cn.setAutoCommit(false);

        // 1️⃣ créer employé
        dao.insert(e, cn);

        // 2️⃣ lier machine à l’employé
        m.setEmploye(e);

        // 3️⃣ créer machine
        machineDao.insert(m, cn);  // ⚠️ version DAO avec Connection

        cn.commit();

    } 
catch (Exception ex) {
        cn.rollback();
        throw ex;

    } finally {
        cn.setAutoCommit(true);
    }
}

    public void listEmploye() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}