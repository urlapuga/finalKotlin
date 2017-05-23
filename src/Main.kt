enum class CITY { KIEV, ODESSA, KHARKOV }
data class User(var name: String, var pass: String)
data class Hotel(var name: String, val city: CITY, var rooms: ArrayList<User?>) {
    data class Room(val number: Int, var user: User?)
}

var hotelList: ArrayList<Hotel> = ArrayList()
var userMap:HashMap<String,User> = HashMap()
var roomList: ArrayList<User?> = arrayListOf(null, null, null, null, null, null, null, null, null)
var hotel: Hotel? = null

class Finder() {
    fun findHotelByName(name: String) = hotelList.filter { h -> h.name == name }
    fun findHotelByCity(city: CITY) = hotelList.filter { h -> h.city == city }
    fun findHotelById(id: Int) = hotelList.elementAtOrNull(id)
    fun findUserByLogin(login:String) = userMap.get(login)

    fun userAdd(login:String,user: User) = if (userMap.containsKey(login)) false else userMap.put(login,user)
    fun userDelete(login:String) = if(userMap.containsKey(login))userMap.remove(login)else false
    fun userEdit(login:String,user:User) = if(userMap.containsKey(login))userMap.put(login,user)else false
}

class Control(var h: Hotel) {
    fun hotelAdd() = if (hotelList.contains(h)) false else hotelList.add(h)
    fun hotelDelete() = if (hotelList.contains(h)) hotelList.remove(h) else false
    fun roomAdd(number: Int) = h.rooms[number].let { h.rooms[number] = null }
    fun deleteRoom(number: Int) = h.rooms[number].let { h.rooms.removeAt(number) }
    fun getFreeRooms() = h.rooms.filter { r -> r == null }
    fun unbookRoom(number: Int) = h.rooms[number]?.let { h.rooms[number] = null }
    fun bookRoom(number: Int, user: User) = if (h.rooms[number] == null) h.rooms[number] = user else false
}

fun main(args: Array<String>) {
    for (i in 1..10) {
        userMap.put("login$i",User("name$i","pass$i"));
        hotelList.add(Hotel("name$i", CITY.KHARKOV, roomList))
    }
    //println(Finder().findHotelByCity(CITY.KHARKOV))
    //hotel = Finder().findHotelById(1)
    // hotel?.let { Control(hotel!!).hotelDelete() }
    //hotel?.let { Control(hotel!!).hotelAdd()}
    //hotel?.let { Control(hotel!!).hotelAdd()}
    //hotel?.let { Control(hotel!!).getFreeRooms()}
    //println(hotelList)
}