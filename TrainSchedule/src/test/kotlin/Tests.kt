import java.time.LocalTime
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class Tests {

    @Test
    fun addingTrain() {
        val trainSchedule = TrainSchedule(mutableListOf())

        trainSchedule.addTrain("Поезд 1",
            LocalTime.of(12,0),
            "Станция 1",
            "Станция 4",
            mutableListOf("Станция 2", "Станция 3"))

        val expected = mutableListOf<Train>(Train("Поезд 1",
            LocalTime.of(12,0),
            "Станция 1",
            "Станция 4",
            mutableListOf("Станция 2", "Станция 3")))

        assertEquals(trainSchedule.trains, expected)
    }

    @Test
    fun removingTrain() {
        val train1 = Train("Поезд 1",
            LocalTime.of(12,0),
            "Станция 1",
            "Станция 4",
            mutableListOf("Станция 2", "Станция 3"))

        val train2 = Train("Поезд 2",
            LocalTime.of(12,0),
            "Станция 1",
            "Станция 4",
            mutableListOf("Станция 2", "Станция 3"))

        val trainSchedule = TrainSchedule(mutableListOf(train1, train2))

        trainSchedule.removeTrain("Поезд 1")

        val expected = mutableListOf<Train>(Train("Поезд 2",
            LocalTime.of(12,0),
            "Станция 1",
            "Станция 4",
            mutableListOf("Станция 2", "Станция 3")))

        assertEquals(trainSchedule.trains, expected)
    }

    @Test
    fun addingStation() {
        val train1 = Train("Поезд 1",
            LocalTime.of(12,0),
            "Станция 1",
            "Станция 5",
            mutableListOf("Станция 2", "Станция 3"))

        val trainSchedule = TrainSchedule(mutableListOf(train1))

        trainSchedule.addStation("Поезд 1", "Станция 4")

        val expected = mutableListOf<Train>(Train("Поезд 1",
            LocalTime.of(12,0),
            "Станция 1",
            "Станция 5",
            mutableListOf("Станция 2", "Станция 3", "Станция 4")))

        assertEquals(trainSchedule.trains, expected)
    }

    @Test
    fun removingStation() {
        val train1 = Train("Поезд 1",
            LocalTime.of(12,0),
            "Станция 1",
            "Станция 5",
            mutableListOf("Станция 2", "Станция 3", "Станция 4"))

        val trainSchedule = TrainSchedule(mutableListOf(train1))

        trainSchedule.removeStation("Поезд 1", "Станция 4")

        val expected = mutableListOf<Train>(Train("Поезд 1",
            LocalTime.of(12,0),
            "Станция 1",
            "Станция 5",
            mutableListOf("Станция 2", "Станция 3")))

        assertEquals(trainSchedule.trains, expected)
    }

    @Test
    fun findingTrain() {
        val train1 = Train("Поезд 1",
            LocalTime.of(12,0),
            "Станция 1",
            "Станция 3",
            mutableListOf("Станция 2"))

        val train2 = Train("Поезд 2",
            LocalTime.of(15,0),
            "Станция 2",
            "Станция 5",
            mutableListOf("Станция 3", "Станция 4"))

        val train3 = Train("Поезд 3",
            LocalTime.of(4,0),
            "Станция 1",
            "Станция 5",
            mutableListOf("Станция 2", "Станция 3", "Станция 4"))

        val train4 = Train("Поезд 4",
            LocalTime.of(18,0),
            "Станция 1",
            "Станция 5",
            mutableListOf("Станция 2", "Станция 3", "Станция 4"))

        val train5 = Train("Поезд 4",
            LocalTime.of(16,0),
            "Станция 1",
            "Станция 5",
            mutableListOf())

        val trainSchedule = TrainSchedule(mutableListOf(train1, train2, train3, train4, train5))

        val expected = Train("Поезд 4",
            LocalTime.of(18,0),
            "Станция 1",
            "Станция 5",
            mutableListOf("Станция 2", "Станция 3", "Станция 4"))

        assertEquals(trainSchedule.findTrain("Станция 1",
            LocalTime.of(15,32),
            "Станция 4"), expected)
    }

    @Test
    fun notFindingTrain() {
        val train1 = Train("Поезд 1",
            LocalTime.of(12,0),
            "Станция 1",
            "Станция 3",
            mutableListOf("Станция 2"))

        val train2 = Train("Поезд 2",
            LocalTime.of(15,0),
            "Станция 2",
            "Станция 5",
            mutableListOf("Станция 3", "Станция 4"))

        val train3 = Train("Поезд 3",
            LocalTime.of(4,0),
            "Станция 1",
            "Станция 5",
            mutableListOf("Станция 2", "Станция 3", "Станция 4"))

        val train4 = Train("Поезд 4",
            LocalTime.of(18,0),
            "Станция 1",
            "Станция 5",
            mutableListOf("Станция 2", "Станция 3", "Станция 4"))

        val train5 = Train("Поезд 4",
            LocalTime.of(16,0),
            "Станция 1",
            "Станция 5",
            mutableListOf())

        val trainSchedule = TrainSchedule(mutableListOf(train1, train2, train3, train4, train5))

        assertEquals(trainSchedule.findTrain("Станция 1",
            LocalTime.of(23,0),
            "Станция 4"), null)
    }
}