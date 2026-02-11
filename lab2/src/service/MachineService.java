package service;

import dao.MachineDao;
import entities.Machine;
import entities.Employe;
import dao.EmployeDao;



import java.util.List;

public class MachineService {
    private final MachineDao dao = new MachineDao();
    private final EmployeDao employeDao = new EmployeDao();


    public Machine getMachine(Machine m) throws Exception {
        return dao.findById(m.getId());
    }

    public List<Machine> listMachines() throws Exception {
        return dao.findAll();
    }
public List<Machine> listMachinesByEmploye(int employeId) throws Exception {
    if (employeId <= 0)
        throw new IllegalArgumentException("ID employe invalide");

    return dao.findByEmployeId(employeId);
}


    public Machine createMachine(Machine m) throws Exception {
        if (m == null) throw new IllegalArgumentException("Machine null");
if (m.getEmploye() == null || m.getEmploye().getId() <= 0)
    throw new IllegalArgumentException("Employe obligatoire");

  Employe emp = employeDao.findById(m.getEmploye().getId());
if (emp == null)
    throw new IllegalArgumentException("Employe inexistant");


        dao.insert(m);
        return m;
    }

    public boolean updateMachine(Machine m) throws Exception {
        return dao.update(m);
    }

    public boolean deleteMachine(Machine m) throws Exception {
        return dao.delete(m.getId());
    }
}