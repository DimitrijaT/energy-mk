package mk.ukim.finki.energymk.model.service.module

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object FirebaseModule {
    @Provides
    fun firestore(): FirebaseFirestore = Firebase.firestore
}

