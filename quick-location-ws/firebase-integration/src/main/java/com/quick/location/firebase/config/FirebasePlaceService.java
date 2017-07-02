package com.quick.location.firebase.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.quick.location.model.firebase.ReviewFirebase;
import com.quick.location.util.QuickLocationUtil;

/**
 * @author cfrias
 */
@Service
public class FirebasePlaceService {

    @Autowired
    FirebaseServerApp firebaseServerApp;

    /**
     * @param url
     * @return
     */
    public DatabaseReference getDatabaseReference(String url) {

        return FirebaseDatabase.getInstance().getReference(url);
    }

    /**
     * @param reviewsFirebaseDetail
     */
    public void setReviewsListOnFirebase(List<ReviewFirebase> reviewsFirebaseDetail) {

        for (ReviewFirebase reviewDetail : reviewsFirebaseDetail) {
            DatabaseReference ref = firebaseServerApp.getDatabaseReference(QuickLocationUtil.URL_FIREBASE_DATABASE_PLACES_REVIEW);
            ref.child(reviewDetail.getPlaceId()).push().setValue(reviewDetail);

        }

    }

    public void objectToFirebase(String path, String idNode, Object objectToSend) {

        firebaseServerApp.getDatabaseReference(path).child(idNode).setValue(objectToSend);

    }

    public void objectPushToFirebase(String path, String idNode, Object objectToSend) {

        firebaseServerApp.getDatabaseReference(path).child(idNode).push().setValue(objectToSend);

    }

    public void removeObjectToFirebase(String path, String idNode) {

        firebaseServerApp.getDatabaseReference(path).child(idNode).removeValue();

    }
}
