package provider.UserController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import provider.pojo.User;
import provider.repository.UserRepository;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private DiscoveryClient discoveryClient;
    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        User findOne = UserRepository.findOne(id);
        return findOne;
    }

    @GetMapping("/instance-info")
    public  List<String> showInfo( String id) {
       List<String> li =  this.discoveryClient.getServices();
//
//       for(String  srt : li){
//
//       }

        return li;
    }
}
