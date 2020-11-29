package com.example.projectmobilerestaurant.core.prepopulation

import com.example.projectmobilerestaurant.entities.*
import org.threeten.bp.LocalTime
import java.util.concurrent.ThreadLocalRandom

fun getNames(): List<String> = listOf(
    "Ainur",
    "Gaziz",
    "Kairat",
    "Dias",
    "Bekzat",
    "Chingiz",
    "Nurlybek",
    "Nursultan",
    "Almas",
    "Miras",
    "Erkebulan",
    "Nauryzbek",
    "Amina",
    "Laura",
    "Aigerim",
    "Zhanerke",
    "Dana",
    "Damelya",
    "Dariga",
    "Gulzara",
    "Oleg",
    "Abay",
    "Maks"
)

fun getCities(): List<String> = listOf(
    "Almaty",
    "Taraz",
    "Shymkent",
    "Nur-Sultan",
    "Aktau",
    "Aktobe",
    "Atyrau",
    "Kostanay",
    "Kokshetau",
    "Kyzylorda",
    "Karagandy",
    "Ekibastuz",
    "Arkalyk",
    "Baikonur",
    "Schuchinsk",
    "Balqash",
    "Zhezkazgan",
    "Kentau",
    "Pavlodar",
    "Petropavl",
    "Semey",
    "Taldykorgan",
    "Shakhtinsk",
    "Oskemen",
    "Oral",
    "Turkistan",
    "Temirtau"
)

fun getReviews(): List<Review> {
    val reviews = mutableListOf<Review>()
    (1..10).forEach { _ ->
        reviews.add(
            Review(
                getNames().random(),
                getCities().random(),
                "Great food and service.",
                "${getDay()} days ago",
                (0..20).random(),
                (0..10).random()
            )
        )
    }
    return reviews
}

fun getDay(): Int = (2..300).random()

fun getRestaurantType(): String = listOf(
    "Steakhouse",
    "Mexican",
    "Italian",
    "Hawaiian",
    "Cuban",
    "American",
    "Seafood",
    "Steak"
).random()

fun getMenus(): List<Menu> {
    val menus = mutableListOf<Menu>()
    (1..10).forEach { _ ->
        menus.add(
            Menu(
                getMenuMain(),
                "asparagus, toasted pine nuts, lemon vinaigrette",
                getMenuPrice().toString()
            )
        )
    }
    return menus
}

fun getMenuPrice(): Int = (1000..5000).random()

fun getMenuMain(): String =
    listOf(
        "Italian Flatbread",
        "Garden Greens",
        "Stuffed Mushrooms",
        "Crab Dip",
        "Hideaway Wings",
        "House Salad",
        "Caesar Salad",
        "Al's Garden Salad",
        "Hideway Tacos",
        "Fettuccini",
        "Lasagna",
        "Seafood Cannelloni"
    )
        .random()

fun getAllCategories(): List<String> = listOf(
    "Pizza",
    "Central Asian",
    "Burger",
    "Russian",
    "Georgian",
    "Sushi",
    "Pasta",
    "Breakfast",
    "Turkish",
    "Italian",
    "Kebab",
    "Ramen"
)

fun getRestaurantInfo(): RestaurantInfo =
    RestaurantInfo(
        "7785220105",
        "$50 and over",
        hours = "Daily 11:30 am - 11:00 pm",
        paymentOptions = "MasterCard, Visa, Rahmet",
        parking = "none",
        description = "Good restaurant"
    )

fun getPriceType(): Int = (1..4).random()

fun getReserveTime(): List<LocalTime> =
    listOf(
        LocalTime.of(10, 0),
        LocalTime.of(11, 0),
        LocalTime.of(12, 0),
        LocalTime.of(13, 0),
        LocalTime.of(14, 0),
        LocalTime.of(15, 0),
        LocalTime.of(16, 0),
        LocalTime.of(17, 0),
        LocalTime.of(18, 0),
        LocalTime.of(19, 0),
        LocalTime.of(20, 0)
    )

fun getCuisines(): List<Cuisine> =
    listOf(
        Cuisine(
            "01",
            "Italian",
            "https://forkfeeds.com/wp-content/uploads/2019/08/Italian-food.jpeg"
        ),
        Cuisine(
            "02",
            "Mediterranean",
            "https://www.qsrmagazine.com/sites/default/files/styles/story_page/public/story/more-diet.jpg?itok=y9x4cqn-"
        ),
        Cuisine(
            "03",
            "Asian",
            "https://news.cgtn.com/news/3d3d674d78637a4d34457a6333566d54/img/e82778b250d64946a655a0a2f2d049f1/e82778b250d64946a655a0a2f2d049f1.jpg"
        ),
        Cuisine(
            "04",
            "Wine Bar",
            "https://cdn.asiatatler.com/asiatatler/i/hk/2020/04/17122533-wines-asian-drink_cover_2000x1333.jpg"
        ),
        Cuisine(
            "05",
            "Chinese",
            "https://www.saporedicina.com/english/wp-content/uploads/2018/03/chinese-cuisine.jpg"
        ),
        Cuisine(
            "06",
            "Burger",
            "https://c.pxhere.com/photos/91/3d/potato_hamburger_fries_meal_cooked-157383.jpg!d"
        ),
        Cuisine(
            "07",
            "Indian",
            "https://francetravelblog.com/wp-content/uploads/2019/11/Best-Indian-Restaurant-in-Paris.jpg"
        )
    ).shuffled()

fun getListOfRestaurantCoffeeAndBreakfast(): List<Restaurant> =
    listOf(
        Restaurant(
            "01",
            "Traveler's Coffee",
            image = "https://restolife.kz/upload/information_system_6/1/2/6/item_1261/information_items_property_548.jpg",
            info = getRestaurantInfo(),
            priceType = getPriceType(),
            reserveTime = getReserveTime(),
            restaurantType = getRestaurantType(),
            rating = getRating(),
            numberOfReviews = getNumberOfReviews(),
            latitude = 43.246543,
            longitude = 76.940449
        ),
        Restaurant(
            "02",
            "Double coffee",
            priceType = getPriceType(),
            image = "https://restolife.kz/upload/information_system_6/1/6/7/item_1670/small_information_items_property_888.jpg",
            restaurantType = getRestaurantType(),
            info = getRestaurantInfo(),
            reserveTime = getReserveTime(),
            rating = getRating(),
            numberOfReviews = getNumberOfReviews(),
            latitude = 43.253983,
            longitude = 76.936672
        ),
        Restaurant(
            "03",
            "Big Apple Coffee Shop",
            priceType = getPriceType(),
            image = "https://restolife.kz/upload/information_system_6/2/1/5/item_21595/information_items_property_24339.jpg",
            restaurantType = getRestaurantType(),
            info = getRestaurantInfo(),
            reserveTime = getReserveTime(),
            rating = getRating(),
            numberOfReviews = getNumberOfReviews(),
            latitude = 43.244168,
            longitude = 76.942515
        ),
        Restaurant(
            "04",
            "Daily coffee",
            priceType = getPriceType(),
            image = "https://restolife.kz/upload/information_system_6/1/1/3/item_11353/information_items_property_14785.jpg",
            restaurantType = getRestaurantType(),
            info = getRestaurantInfo(),
            reserveTime = getReserveTime(),
            rating = getRating(),
            numberOfReviews = getNumberOfReviews(),
            latitude = 43.243088,
            longitude = 76.942870
        ),
        Restaurant(
            "05",
            "Marrone Rosso",
            priceType = getPriceType(),
            image = "https://restolife.kz/upload/information_system_6/6/6/4/item_664/information_items_property_246.jpg",
            restaurantType = getRestaurantType(),
            info = getRestaurantInfo(),
            reserveTime = getReserveTime(),
            rating = getRating(),
            numberOfReviews = getNumberOfReviews(),
            latitude = 43.248000,
            longitude = 76.947646
        ),
        Restaurant(
            "06",
            "RUMI",
            priceType = getPriceType(),
            image = "https://www.voxpopuli.kz/img/article/128/52_main.jpg",
            restaurantType = getRestaurantType(),
            info = getRestaurantInfo(),
            reserveTime = getReserveTime(),
            rating = getRating(),
            numberOfReviews = getNumberOfReviews(),
            latitude = 43.251344,
            longitude = 76.942776
        ),
        Restaurant(
            "07",
            "Hong Kong Soy",
            priceType = getPriceType(),
            image = "https://restolife.kz/upload/information_system_6/2/2/4/item_22481/information_items_property_26060.jpg",
            restaurantType = getRestaurantType(),
            info = getRestaurantInfo(),
            reserveTime = getReserveTime(),
            rating = getRating(),
            numberOfReviews = getNumberOfReviews(),
            latitude = 43.248543,
            longitude = 76.933149
        ),
        Restaurant(
            "08",
            "United BRGRS",
            priceType = getPriceType(),
            image = "https://avatars.mds.yandex.net/get-altay/1971563/2a0000016d6f66e0263e6be1c4fc9b095f19/XXL",
            restaurantType = getRestaurantType(),
            info = getRestaurantInfo(),
            reserveTime = getReserveTime(),
            rating = getRating(),
            numberOfReviews = getNumberOfReviews(),
            latitude = 43.237443,
            longitude = 76.884493
        ),
        Restaurant(
            "09",
            "Bauyrdaq Satpayev",
            priceType = getPriceType(),
            image = "https://i1.photo.2gis.com/images/branch/67/9429411768509216_dfee.jpg",
            restaurantType = getRestaurantType(),
            info = getRestaurantInfo(),
            reserveTime = getReserveTime(),
            rating = getRating(),
            numberOfReviews = getNumberOfReviews(),
            latitude = 43.237443,
            longitude = 76.884493
        )
    ).shuffled()

fun getRating(): Double = ThreadLocalRandom.current().nextDouble(1.0, 5.0)

fun getNumberOfReviews(): Long = (0L..500L).random()

fun getOccasions(): List<String> =
    listOf("Birthday", "Anniversary", "Date", " Business Meal", "Special Occasion")