enum class CITY { KIEV, ODESSA, KHARKOV }

var hotelList: ArrayList<Hotel> = ArrayList()
var userMap: HashMap<String, User> = HashMap()

data class User(var name: String, var pass: String) {
    fun User(login: String) = userMap[login]
    fun add(login: String) = userMap.putIfAbsent(login, this)
    fun del(login: String) = userMap.remove(login)
    fun edit(login: String) = userMap.computeIfPresent(login, { _, _ -> userMap.put(login, this)!! })
}

data class Hotel(var name: String, var city: CITY, var rooms: ArrayList<User?> = arrayListOf(null, null, null, null, null, null, null, null, null)) {
    fun Hotel(id: Int) = hotelList.elementAtOrNull(id)
    fun Hotel(name: String) = hotelList.filter { h -> h.name == name }
    fun Hotel(city: CITY) = hotelList.filter { h -> h.city == city }
    fun add() = hotelList.add(this)
    fun del(id: Int) = hotelList[id].let { hotelList.removeAt(id) }
    fun edit(id: Int) = hotelList.add(id, this)
    fun roomAdd(number: Int) = this.rooms[number].let { this.rooms[number] = null }
    fun deleteRoom(number: Int) = this.rooms[number].let { this.rooms.removeAt(number) }
    fun getFreeRooms() = this.rooms.filter { it == null }
    fun unbookRoom(number: Int) = this.rooms[number].let { this.rooms[number] = null }
    fun bookRoom(number: Int, user: User) = if (this.rooms[number] == null) this.rooms[number] = user else null
}

fun main(args: Array<String>) {

    for (i in 1..10) {
        User("name$i", "pass$i").add("login$i")
        Hotel("name$i", CITY.KHARKOV).add()
    }
    println(hotelList)
}

