package com.LFS.Main.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LFS.Main.Controllers.*;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Pipeline.Snapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class Services {

	@Autowired
	private Firestore dbFireStore;
	
	public String HelloService(String name) {
		return "hello world service" + name;
	}
	
	public List<User> GetAllUsers() throws ExecutionException, InterruptedException {
		ApiFuture<QuerySnapshot> future = dbFireStore.collection("db_user").get();
		
		QuerySnapshot querySnapshot = future.get();
		
		List<User> userList = new ArrayList<>();
		
		for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
	        User user = document.toObject(User.class);
	        userList.add(user);
	    }
	    
	    return userList;
	
	}
	
	public User GetByIdService(String id) throws ExecutionException, InterruptedException{
		DocumentReference documenteReference = dbFireStore.collection("db_user").document(id);
		ApiFuture<DocumentSnapshot> future = documenteReference.get();
		DocumentSnapshot document = future.get();
		User user;
		if(document.exists()) {
			user = document.toObject(User.class);
			return user;
		}
		
		return null;
	}
	
	public String DeleteService(String id) {
		ApiFuture<WriteResult> writeResult = dbFireStore.collection("db_user").document(id).delete();
		
		return "deletado com sucesso" + id;
	}
	
	public String PostService(User user) throws ExecutionException, InterruptedException {
		ApiFuture<WriteResult> collectionsApiFuture = dbFireStore.collection("db_user").document(user.getName()).set(user);
				
		return collectionsApiFuture.get().getUpdateTime().toString();
	}
	
	public String UpdateService(User user) {
		return "";
	}
}
