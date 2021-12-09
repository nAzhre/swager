package edu.learn.cdi.demo.bean;

import edu.learn.cdi.demo.domain.models.Item;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ApplicationScoped
public class ServiceBean implements Serializable {
   
    private H2Repository repository;

    public ServiceBean() {
    }

    @Inject
    public ServiceBean(H2Repository repository) {
    	this.repository = repository;
    }

    @PostConstruct
    public void init() {
        System.out.println("ServiceBean->Cdi bean");
    }

    //Will be called when @SessionScoped
    @PreDestroy
    public void destroying() {
        System.out.println("ServiceBean->Cdi bean");
    }

    public int doWork(int a, int b) {
        return a + b;
    }

    public void create(Item item) {
        repository.create(item);
    }

    public List<Item> getAllItems() {
    	return repository.getAllItems();
    }

    public void update(Item item) {
        repository.update(item);
    }

    public void delete(Item item) {
        repository.delete(item);
    }
}

