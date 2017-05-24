enum class CITY { KIEV, ODESSA, KHARKOV }

var hotelList: ArrayList<Hotel> = ArrayList()
var userMap: HashMap<String, User> = HashMap()
var roomList: ArrayList<User?> = arrayListOf(null, null, null, null, null, null, null, null, null)
var hotel: Hotel? = null

data class User(var name: String, var pass: String) {
    fun add(login: String) = if (userMap.containsKey(login)) null else userMap.put(login, this)
    fun del(login: String) = if (userMap.containsKey(login)) Finder().findUserByLogin(login)?.let { userMap.remove(login, it) } else false
    fun edit(login: String) = if (userMap.containsKey(login)) userMap.put(login, this) else null
}

data class Hotel(var name: String, val city: CITY, var rooms: ArrayList<User?>) {
    fun add() = hotelList.add(this)
    fun del() = if (hotelList.contains(this)) hotelList.remove(this) else false
    fun edit(id: Int) = hotelList.add(id, this)
}

class Finder() {
    fun findHotelByName(name: String) = hotelList.filter { h -> h.name == name }
    fun findHotelByCity(city: CITY) = hotelList.filter { h -> h.city == city }
    fun findHotelById(id: Int) = hotelList.elementAtOrNull(id)
    fun findUserByLogin(login: String) = userMap.get(login)
}

class Control(var h: Hotel) {
    fun roomAdd(number: Int) = h.rooms[number].let { h.rooms[number] = null }
    fun deleteRoom(number: Int) = h.rooms[number].let { h.rooms.removeAt(number) }
    fun getFreeRooms() = h.rooms.filter { r -> r == null }
    fun unbookRoom(number: Int) = h.rooms[number]?.let { h.rooms[number] = null }
    fun bookRoom(number: Int, user: User) = if (h.rooms[number] == null) h.rooms[number] = user else null
}

fun main(args: Array<String>) {
    for (i in 1..10) {
        userMap.put("login$i", User("name$i", "pass$i"))
        hotelList.add(Hotel("name$i", CITY.KHARKOV, roomList))
    }
}
