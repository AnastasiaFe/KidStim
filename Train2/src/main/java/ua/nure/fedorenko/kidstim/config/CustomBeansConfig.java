package ua.nure.fedorenko.kidstim.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.nure.fedorenko.kidstim.model.dao.ChildDao;
import ua.nure.fedorenko.kidstim.model.dao.ParentDao;
import ua.nure.fedorenko.kidstim.model.dao.RewardDao;
import ua.nure.fedorenko.kidstim.model.dao.TaskDao;
import ua.nure.fedorenko.kidstim.model.dao.impl.ChildDaoImpl;
import ua.nure.fedorenko.kidstim.model.dao.impl.ParentDaoImpl;
import ua.nure.fedorenko.kidstim.model.dao.impl.RewardDaoImpl;
import ua.nure.fedorenko.kidstim.model.dao.impl.TaskDaoImpl;
import ua.nure.fedorenko.kidstim.service.ChildService;
import ua.nure.fedorenko.kidstim.service.ParentService;
import ua.nure.fedorenko.kidstim.service.RewardService;
import ua.nure.fedorenko.kidstim.service.TaskService;
import ua.nure.fedorenko.kidstim.service.impl.ChildServiceImpl;
import ua.nure.fedorenko.kidstim.service.impl.ParentServiceImpl;
import ua.nure.fedorenko.kidstim.service.impl.RewardServiceImpl;
import ua.nure.fedorenko.kidstim.service.impl.TaskServiceImpl;

@Configuration
@ComponentScan({"ua.nure.fedorenko.kidstim"})
public class CustomBeansConfig {

    @Bean
    public ParentDao parentDao() {
        return new ParentDaoImpl();
    }

    @Bean
    public ChildDao childDao() {
        return new ChildDaoImpl();
    }

    @Bean
    public TaskDao taskDao() {
        return new TaskDaoImpl();
    }

    @Bean
    public RewardDao rewardDao() {
        return new RewardDaoImpl();
    }

    @Bean
    public ParentService parentService() {
        return new ParentServiceImpl();
    }

    @Bean
    public ChildService childService() {
        return new ChildServiceImpl();
    }

    @Bean
    public TaskService taskService() {
        return new TaskServiceImpl();
    }

    @Bean
    public RewardService rewardService() {
        return new RewardServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
