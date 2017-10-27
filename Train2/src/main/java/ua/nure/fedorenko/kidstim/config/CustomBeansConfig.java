package ua.nure.fedorenko.kidstim.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.nure.fedorenko.kidstim.model.dao.ChildDao;
import ua.nure.fedorenko.kidstim.model.dao.ParentDao;
import ua.nure.fedorenko.kidstim.model.dao.RewardDao;
import ua.nure.fedorenko.kidstim.model.dao.TaskDao;
import ua.nure.fedorenko.kidstim.model.dao.impl.ChildDaoImpl;
import ua.nure.fedorenko.kidstim.model.dao.impl.ParentDaoImpl;
import ua.nure.fedorenko.kidstim.model.dao.impl.RewardDaoImpl;
import ua.nure.fedorenko.kidstim.model.dao.impl.TaskDaoImpl;
import ua.nure.fedorenko.kidstim.service.*;
import ua.nure.fedorenko.kidstim.service.impl.*;
import ua.nure.fedorenko.kidstim.service.mapper.ChildMapper;
import ua.nure.fedorenko.kidstim.service.mapper.ParentMapper;
import ua.nure.fedorenko.kidstim.service.mapper.RewardMapper;
import ua.nure.fedorenko.kidstim.service.mapper.TaskMapper;

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
    public ParentMapper parentMapper() {
        return new ParentMapper();
    }

    @Bean
    public ChildMapper childMapper() {
        return new ChildMapper();
    }



    @Bean
    public TaskMapper taskMapper() {
        return new TaskMapper();
    }

    @Bean
    public RewardMapper rewardMapper() {
        return new RewardMapper();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
}
