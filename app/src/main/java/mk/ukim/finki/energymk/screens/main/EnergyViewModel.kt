package mk.ukim.finki.energymk.screens.main

import dagger.hilt.android.lifecycle.HiltViewModel
import mk.ukim.finki.energymk.model.service.EnergyResourceService
import javax.inject.Inject


@HiltViewModel
class EnergyViewModel @Inject constructor(
    private val energyResourceTimelineService: EnergyResourceService
) : BaseViewModel() {

    val resources = energyResourceTimelineService.resources

    fun refresh() {
//        launchCatching {
//            energyResourceTimelineService.refresh()
//        }
    }
}