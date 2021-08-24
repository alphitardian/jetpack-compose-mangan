package com.alphitardian.mealsapp.utils

fun getFlag(country: String): String {
    var flag = ""
    when (country) {
        "American" -> flag =
            "https://upload.wikimedia.org/wikipedia/en/thumb/a/a4/Flag_of_the_United_States.svg/1280px-Flag_of_the_United_States.svg.png"
        "British" -> flag =
            "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f2/Flag_of_Great_Britain_%281707%E2%80%931800%29.svg/255px-Flag_of_Great_Britain_%281707%E2%80%931800%29.svg.png"
        "Canadian" -> flag =
            "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Flag_of_Canada_%28Pantone%29.svg/1200px-Flag_of_Canada_%28Pantone%29.svg.png"
        "Chinese" -> flag =
            "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fa/Flag_of_the_People%27s_Republic_of_China.svg/255px-Flag_of_the_People%27s_Republic_of_China.svg.png"
        "Croatian" -> flag =
            "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/Flag_of_Croatia.svg/2000px-Flag_of_Croatia.svg.png"
        "Dutch" -> flag =
            "https://upload.wikimedia.org/wikipedia/commons/thumb/2/20/Flag_of_the_Netherlands.svg/255px-Flag_of_the_Netherlands.svg.png"
        "Egyptian" -> flag =
            "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fe/Flag_of_Egypt.svg/220px-Flag_of_Egypt.svg.png"
        "French" -> flag =
            "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Flag_of_France_%281794%E2%80%931815%2C_1830%E2%80%931958%29.svg/250px-Flag_of_France_%281794%E2%80%931815%2C_1830%E2%80%931958%29.svg.png"
        "Greek" -> flag =
            "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Flag_of_Greece.svg/2000px-Flag_of_Greece.svg.png"
        "Indian" -> flag =
            "https://upload.wikimedia.org/wikipedia/en/thumb/4/41/Flag_of_India.svg/1200px-Flag_of_India.svg.png"
        "Irish" -> flag =
            "https://upload.wikimedia.org/wikipedia/commons/thumb/4/45/Flag_of_Ireland.svg/255px-Flag_of_Ireland.svg.png"
        "Italian" -> flag =
            "https://images.squarespace-cdn.com/content/v1/58ac7efb6b8f5b3bdce9a441/1560880713570-SHOGLAISOY3H7Y1S6E7H/Italian+Flag.JPG"
        "Jamaican" -> flag =
            "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0a/Flag_of_Jamaica.svg/255px-Flag_of_Jamaica.svg.png"
        "Japanese" -> flag =
            "https://upload.wikimedia.org/wikipedia/en/thumb/9/9e/Flag_of_Japan.svg/1200px-Flag_of_Japan.svg.png"
        "Kenyan" -> flag = "https://cdn.britannica.com/15/15-004-B5D6BF80/Flag-Kenya.jpg"
        "Malaysian" -> flag =
            "https://upload.wikimedia.org/wikipedia/commons/thumb/6/66/Flag_of_Malaysia.svg/1200px-Flag_of_Malaysia.svg.png"
        "Mexican" -> flag =
            "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fc/Flag_of_Mexico.svg/1200px-Flag_of_Mexico.svg.png"
        "Moroccan" -> flag =
            "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Flag_of_Morocco.svg/255px-Flag_of_Morocco.svg.png"
        "Polish" -> flag =
            "https://upload.wikimedia.org/wikipedia/en/thumb/1/12/Flag_of_Poland.svg/1200px-Flag_of_Poland.svg.png"
        "Portuguese" -> flag =
            "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Flag_of_Portugal.svg/255px-Flag_of_Portugal.svg.png"
        "Russian" -> flag =
            "https://upload.wikimedia.org/wikipedia/en/thumb/f/f3/Flag_of_Russia.svg/1200px-Flag_of_Russia.svg.png"
        "Spanish" -> flag =
            "https://upload.wikimedia.org/wikipedia/commons/thumb/8/89/Bandera_de_Espa%C3%B1a.svg/1200px-Bandera_de_Espa%C3%B1a.svg.png"
        "Thai" -> flag = "https://dbdzm869oupei.cloudfront.net/img/sticker/preview/4319.png"
        "Tunisian" -> flag =
            "https://upload.wikimedia.org/wikipedia/commons/thumb/c/ce/Flag_of_Tunisia.svg/225px-Flag_of_Tunisia.svg.png"
        "Turkish" -> flag =
            "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Flag_of_the_Ottoman_Empire_%281844%E2%80%931922%29.svg/220px-Flag_of_the_Ottoman_Empire_%281844%E2%80%931922%29.svg.png"
        "Vietnamese" -> flag =
            "https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Flag_of_Vietnam.svg/1024px-Flag_of_Vietnam.svg.png"
        "Unknown" -> flag =
            "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f6/Flag_of_the_United_Nations_%281945-1947%29.svg/250px-Flag_of_the_United_Nations_%281945-1947%29.svg.png"
    }
    return flag
}