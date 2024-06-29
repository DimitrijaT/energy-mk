package mk.ukim.finki.energymk.model.service

import kotlinx.coroutines.flow.Flow
import mk.ukim.finki.energymk.model.EnergyResource

interface EnergyResourceService {

    val resources: Flow<List<EnergyResource>>
}