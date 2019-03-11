package provider.UserController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import provider.pojo.DataPassage;
import provider.pojo.User;
import provider.repository.DataPassageRepository;
import provider.repository.UserRepository;
import provider.service.TaskService;

@RestController
public class DataPassageController {

    @Autowired
    private TaskService TaskService;


    @GetMapping("/{id}")
    public DataPassage findById(@PathVariable Long id) {



        return findOne;
    }


}
