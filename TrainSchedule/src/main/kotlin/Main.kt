import java.time.LocalTime

class Train (
    val name: String,
    val departureTime: LocalTime,
    val departureStation: String,
    val finalStation: String,
    val intermediateStations: MutableList<String>
) {
    // Переопределение метода equals для сравнения объектов типа Train на основе их свойств
    override fun equals(other: Any?): Boolean {
        return (other is Train)
                && name == other.name
                && departureTime == other.departureTime
                && departureStation == other.departureStation
                && finalStation == other.finalStation
                && intermediateStations == other.intermediateStations
    }
}

class TrainSchedule (
    val trains: MutableList<Train>
) {
    // Метод для добавления поезда в расписание
    fun addTrain(name: String,
                 departureTime: LocalTime,
                 departureStation: String,
                 finalStation: String,
                 intermediateStations: MutableList<String>
    ) {
        val train = trains.find { it.name == name }
        if (train == null)
            trains.add(Train(name, departureTime, departureStation, finalStation, intermediateStations))
    }

    // Метод для удаления поезда из расписания
    fun removeTrain(name: String) {
        val train = trains.find { it.name == name }
        if (train != null)
            trains.remove(train)
    }

    // Метод для добавления станции на маршрут поезда
    fun addStation(name: String, stationName: String) {
        val train = trains.find { it.name == name }
        train?.intermediateStations?.add(stationName)
    }

    // Метод для удаления станции из маршрута поезда
    fun removeStation (name: String, stationName: String) {
        val train = trains.find { it.name == name }
        if (train?.intermediateStations?.contains(stationName) == true)
            train.intermediateStations.remove(stationName)
    }

    // Метод для поиска ближайшего поезда (по станции, куда едем, и текущему времени)
    fun findTrain (station: String, currentTime: LocalTime, destination: String): Train? {
        val trainFilter =
            trains.filter { it.departureStation == station }
                .filter { it.finalStation == destination || it.intermediateStations.contains(destination) }
                .filter { it.departureTime.isAfter(currentTime) || it.departureTime == currentTime }
                .sortedBy { it.departureTime }

        return if (trainFilter.isNotEmpty()) trainFilter[0] else null
    }
}