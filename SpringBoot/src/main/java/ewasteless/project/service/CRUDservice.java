package ewasteless.project.service;

import java.util.concurrent.ExecutionException;

import javax.print.Doc;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
// import com.google.cloud.firestore.v1.FirestoreClient;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firestore.v1.Document;

import ewasteless.project.classes.CRUD;


@Service
public class CRUDservice {

    public String createCRUD(CRUD crud) 
    throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore() ;
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("users").document(crud.getName()).set(crud) ;
        return collectionsApiFuture.get().getUpdateTime().toString() ;
        
    }

    public CRUD getCRUD(String name) 
    throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore() ;
        DocumentReference documentReference = dbFirestore.collection("users").document(name) ;
        ApiFuture<DocumentSnapshot> future = documentReference.get() ;
        DocumentSnapshot document = future.get() ;
        CRUD crud;
        if(document.exists()){
            crud = document.toObject(CRUD.class) ;
            return crud ;
        }

        return null ;
    }

    public String updateCRUD(CRUD crud) {
        return "" ;
    }

    public String deleteCRUD(String name) {
        Firestore dbFirestore = FirestoreClient.getFirestore() ;
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("users").document(name).delete() ;
        return "Sucessfully created" + name ;
    }


    
}
