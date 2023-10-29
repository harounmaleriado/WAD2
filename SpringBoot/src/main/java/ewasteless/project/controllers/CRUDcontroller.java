package ewasteless.project.controllers;

import java.util.concurrent.ExecutionException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import com.google.common.util.concurrent.ExecutionError;

import ewasteless.project.classes.CRUD;
import ewasteless.project.service.CRUDservice;
import io.opencensus.tags.InternalUtils;

@RestController
public class CRUDcontroller {

    public CRUDservice crudService ;

    public CRUDcontroller(CRUDservice crudService) {
        this.crudService= crudService ;
    }

    
    

    @PostMapping("/create")
    public String createCRUD(@RequestBody CRUD crud) throws InterruptedException, ExecutionException {
        return crudService.createCRUD(crud) ;
    }

    @GetMapping("/get")
    public CRUD getCRUD(@RequestParam String name) throws InterruptedException, ExecutionException {
        return crudService.getCRUD(name) ;
    }

    @PutMapping("/put")
    public String updateCRUD(@RequestBody CRUD crud) throws InterruptedException, ExecutionException {
        return crudService.updateCRUD(crud) ;
    }
            
    @PutMapping("/delete")
    public String deleteCRUD(@RequestBody String name) throws InterruptedException, ExecutionException {
        return crudService.deleteCRUD(name) ;
    }

    // test if endpoint is working
    @GetMapping("/test")
    public ResponseEntity<String> testGetEndPoint() {
        return ResponseEntity.ok("Test endpoint is working");
    }

}
