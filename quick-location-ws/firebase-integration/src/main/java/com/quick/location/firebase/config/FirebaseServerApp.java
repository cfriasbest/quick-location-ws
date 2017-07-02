package com.quick.location.firebase.config;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.quick.location.util.QuickLocationUtil;

/**
 * 
 * @author cfrias
 *
 */
@Component
public class FirebaseServerApp {

    /**
     * @throws IOException
     */
    @PostConstruct
    public static void initializeFirebaseApp() throws IOException {
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredential(FirebaseCredentials.fromCertificate(FirebaseServerApp.class.getResourceAsStream(QuickLocationUtil.URL_FIREBASE_ADMIN_SDK)))
                .setDatabaseUrl(QuickLocationUtil.URL_FIREBASE_APP).build();
        FirebaseApp.initializeApp(options);
    }

    /**
     * @param url
     * @return
     */
    public DatabaseReference getDatabaseReference(String url) {

        return FirebaseDatabase.getInstance().getReference(url);
    }

}
