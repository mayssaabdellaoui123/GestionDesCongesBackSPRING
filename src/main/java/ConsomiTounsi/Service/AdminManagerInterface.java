package ConsomiTounsi.Service;

import ConsomiTounsi.entities.Admin;
import ConsomiTounsi.entities.Role;

import java.util.List;

import org.springframework.data.repository.query.Param;

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
