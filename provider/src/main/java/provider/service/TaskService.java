package provider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import provider.config.ConstantWords;
import provider.pojo.DataPassage;
import provider.repository.DataPassageRepository;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class TaskService {

    @Autowired
    private DataPassageRepository  dataPassageRepository;
    public LinkedBlockingQueue<DataPassage> linkedBlockingQueue  = new LinkedBlockingQueue<DataPassage>(200);;



    public void init(){

    }

    public  void getTask_h(){
        DataPassage user = new DataPassage(ConstantWords.TASK_H, ConstantWords.STATUS);

        Example<DataPassage> DataPassageExample = Example.of(user);
        List<DataPassage> li = dataPassageRepository.findAll(DataPassageExample);
        for(DataPassage data:li){
            try {
                linkedBlockingQueue.put(data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }


}
