package com.rie.mystore.data

import com.rie.mystore.model.OrderVivo
import com.rie.mystore.model.FakeDataVivo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
//Noor Saputri
class VivoRepository {

    private val orderVivos = mutableListOf<OrderVivo>()

    init {
        if(orderVivos.isEmpty()){
            FakeDataVivo.dummyVivo.forEach{
                orderVivos.add(OrderVivo(it,0))
            }
        }
    }

    fun getAllVivos(): Flow<List<OrderVivo>> {
        return flowOf(orderVivos)
    }
    fun getVivoById(vivoId:Long) : OrderVivo{
        return orderVivos.first{
            it.order.id == vivoId
        }
    }

    companion object{
        @Volatile
        private var istance :VivoRepository? = null

        fun getInstance():VivoRepository =
            istance ?: synchronized(this){
                VivoRepository().apply { istance = this }
            }
    }


}
