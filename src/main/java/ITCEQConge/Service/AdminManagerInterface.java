package ITCEQConge.Service;

import ITCEQConge.entities.Admin;
import ITCEQConge.entities.Role;

import java.util.List;

public interface AdminManagerInterface {

    List<Admin> retrieveAllAdmin();
    void deleteAdminById(Long id);
    void deleteAdminById(String id);
    Admin updateAdmin(Admin A);
    Admin FindAdminById(Long id);
    Admin FindAdminById(String id);
    List<Admin> FindAdminByRole(Role role);
    long getNbAdminByRole(Role role);
    long getNbAdmin();

    Admin AddAdmin(Admin user);
    void resetAbsence(int nb);
    int addAbsence(long id);
}
