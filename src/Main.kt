enum class CITY { KIEV, ODESSA, KHARKOV }

var hotels: ArrayList<Hotel> = ArrayList()
var users: HashMap<String, User> = HashMap()
fun hByName(name: String) = hotels.filter { h -> h.name == name }
fun hByCity(city: CITY) = hotels.filter { h -> h.city == city }

data class User(var name: String, var pass: String) {
    fun add(login: String) = users.putIfAbsent(login, this)
    fun del(login: String) = users.remove(login)
    fun edit(login: String) = users.computeIfPresent(login, { _, _ -> users.put(login, this)!! })
}

data class Hotel(var name: String, var city: CITY, var rooms: ArrayList<User?> = arrayListOf(null, null, null, null, null, null, null, null, null)) {
    fun add() = hotels.add(this)
    fun del() = hotels.removeAt(hotels.indexOf(this))
    fun edit(h: Hotel) = hotels.set(hotels.indexOf(this), h)
    fun roomAdd(number: Int) = this.rooms[number].let { this.rooms[number] = null }
    fun deleteRoom(number: Int) = this.rooms.removeAt(number)
    fun freeRooms() = this.rooms.filter { it == null }
    fun unbookRoom(number: Int) = this.rooms[number].let { this.rooms[number] = null }
    fun bookRoom(number: Int, user: User) = if (this.rooms[number] == null) this.rooms[number] = user else null
}
/**
 * Here are the examples of usage
 */
fun main(args: Array<String>) {
    /**
     * Now all Test Data is generated
     * 10 users , !0 hotels with 10 rooms each
     */
    for (i in 1..10) {
        User("name$i", "pass$i").add("login$i")
        Hotel("name$i", CITY.KHARKOV).add()
    }

    /**
     * We can create User with the name and pass and then add by login
     * users[id] gives us an instance of a user by id
     * users[id].add is a kind of making a copy of user with new login
     * del or edit is for deleting or editing
     */
    println(users["login1"])
    println(users["login1"]!!.add("newloginOldData"))
    println(users["login1"]!!.del("login1"))
    println(users)

    /**
     * We create hotel using name and city ... we also can set up roomlist in constructor
     * but if we dont 10 free rooms are created
     * hotels[id] is the method to get hotel by id
     * edit and del edits and deletes hotel
     */
    println(hotels[1].rooms)
    println(hotels[1].edit(Hotel("newName", CITY.KIEV)))
    println(hotels[1].del())
    println(hotels[1])

    /**
     * hByCity - finds hotel by city
     * hByName - finds hotel by name
     */
    println(hByCity(CITY.KHARKOV))
    println(hByName("name1"))

}

