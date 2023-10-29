package id.go.ngawikab.siketan.utils.common

import id.go.ngawikab.siketan.domain.farm.model.response.ChartDataModel
import id.go.ngawikab.siketan.domain.farm.model.response.ChartModel


/**
 * Created by Wahid on 10/9/2023.
 * Github github.com/wahidabd.
 */


object Dummy {
    fun generateChartDummy(): ChartModel {
        val list = ArrayList<ChartDataModel>()
        list.add(
            ChartDataModel(
                label = "mangga",
                total = "100",
                count = 100,
                tahunPanen = 2022
            )
        )

        list.add(
            ChartDataModel(
                label = "mangga",
                total = "110",
                count = 90,
                tahunPanen = 2023
            )
        )

        list.add(
            ChartDataModel(
                label = "jeruk",
                total = "120",
                count = 150,
                tahunPanen = 2023
            )
        )

        list.add(
            ChartDataModel(
                label = "wortel",
                total = "100",
                count = 100,
                tahunPanen = 2022
            )
        )

        list.add(
            ChartDataModel(
                label = "wortel",
                total = "100",
                count = 100,
                tahunPanen = 2023
            )
        )
        return ChartModel(list)
    }
}