package ITCEQConge.controllers.accounts;

import ITCEQConge.Service.AdminManagerInterface;
import ITCEQConge.entities.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminManagerInterface adminS;

    @PutMapping("/update")
    public void updateAdmin(@RequestBody Admin a){
         adminS.updateAdmin(a);
    }
}
