package com.LFS.Main.Services;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.LFS.Main.Controllers.*;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Pipeline.Snapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class Services {

	public String HelloService(String name) {
		return "hello world service" + name;
	}
	
	public User GetByIdService(String id) throws ExecutionException, InterruptedException{
		Firestore dbFireStore = FirestoreClient.getFirestore();
		DocumentReference documenteReference = dbFireStore.collection("user_name").document(id);
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
		Firestore dbFireStore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> writeResult = dbFireStore.collection("user_name").document(id).delete();
		
		return "deletado com sucesso" + id;
	}
	
	public String PostService(User user) throws ExecutionException, InterruptedException {
		Firestore dbFireStore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionsApiFuture = dbFireStore.collection("db_user").document(user.getName()).set(user);
				
		return collectionsApiFuture.get().getUpdateTime().toString();
	}
	
	public String UpdateService(User user) {
		return "";
	}
}
