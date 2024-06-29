package mk.ukim.finki.energymk.model.service.impl

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import kotlinx.coroutines.flow.Flow
import mk.ukim.finki.energymk.model.EnergyResource
import mk.ukim.finki.energymk.model.service.EnergyResourceService
import javax.inject.Inject

class EnergyResourceServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
) : EnergyResourceService {

    override val resources: Flow<List<EnergyResource>>
        get() = firestore.collection("timeline").dataObjects<EnergyResource>()
}