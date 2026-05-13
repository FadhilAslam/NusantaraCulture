package com.nusantara.culture.data

import androidx.annotation.DrawableRes
import com.nusantara.culture.R

enum class CultureCategory(val displayName: String, val displayNameId: String) {
    TARIAN("Traditional Dance", "Tarian Tradisional"),
    MAKANAN("Traditional Food", "Makanan Khas"),
    RUMAH_ADAT("Traditional House", "Rumah Adat"),
    PAKAIAN_ADAT("Traditional Clothing", "Pakaian Adat")
}

data class CultureItem(
    val id: Int,
    val name: String,
    val nameId: String,
    val origin: String,
    val originId: String,
    val shortDescription: String,
    val shortDescriptionId: String,
    val fullDescription: String,
    val fullDescriptionId: String,
    val category: CultureCategory,
    @param:DrawableRes val imageRes: Int,
    val readMoreUrl: String
)

object CultureRepository {

    val allItems: List<CultureItem> = listOf(
        // ── TARIAN ──────────────────────────────────────────────────────────────
        CultureItem(
            id = 1,
            name = "Saman Dance",
            nameId = "Tari Saman",
            origin = "Aceh",
            originId = "Aceh",
            shortDescription = "A fast-paced group dance from Aceh known for its synchronized movements.",
            shortDescriptionId = "Tarian kelompok cepat dari Aceh yang terkenal dengan gerakan sinkronnya.",
            fullDescription = "The Saman Dance is a traditional dance from the Gayo people of Aceh province. " +
                    "It is performed by a group of men sitting in rows, featuring rapid hand clapping, " +
                    "chest patting, and synchronized body movements. The dance is accompanied by songs " +
                    "in the Gayo language and is recognized by UNESCO as an Intangible Cultural Heritage.",
            fullDescriptionId = "Tari Saman adalah tarian tradisional dari suku Gayo di provinsi Aceh. " +
                    "Tarian ini dibawakan oleh sekelompok pria yang duduk berbaris, menampilkan tepukan tangan " +
                    "yang cepat, tepukan dada, dan gerakan tubuh yang sinkron. Tarian ini diiringi lagu " +
                    "dalam bahasa Gayo dan diakui oleh UNESCO sebagai Warisan Budaya Tak Benda.",
            category = CultureCategory.TARIAN,
            imageRes = R.drawable.tari_saman,
            readMoreUrl = "https://en.wikipedia.org/wiki/Saman_dance"
        ),
        CultureItem(
            id = 2,
            name = "Kecak Dance",
            nameId = "Tari Kecak",
            origin = "Bali",
            originId = "Bali",
            shortDescription = "A dramatic fire dance from Bali performed by a large chorus of men.",
            shortDescriptionId = "Tarian api dramatis dari Bali yang dibawakan oleh paduan suara pria.",
            fullDescription = "Kecak is a form of Balinese Hindu dance and music drama that was developed " +
                    "in the 1930s. A group of 50 or more performers sit in concentric circles and chant " +
                    "'cak' rhythmically while acting out scenes from the Ramayana epic. The dance is " +
                    "typically performed at sunset and is one of Bali's most iconic cultural attractions.",
            fullDescriptionId = "Kecak adalah bentuk drama tari dan musik Hindu Bali yang dikembangkan " +
                    "pada tahun 1930-an. Sekelompok 50 atau lebih penari duduk dalam lingkaran konsentris " +
                    "dan melantunkan 'cak' secara ritmis sambil memerankan adegan dari epos Ramayana. " +
                    "Tarian ini biasanya dipentaskan saat matahari terbenam.",
            category = CultureCategory.TARIAN,
            imageRes = R.drawable.tari_kecak,
            readMoreUrl = "https://en.wikipedia.org/wiki/Kecak"
        ),
        CultureItem(
            id = 3,
            name = "Pendet Dance",
            nameId = "Tari Pendet",
            origin = "Bali",
            originId = "Bali",
            shortDescription = "A welcoming dance from Bali originally performed as a temple ritual.",
            shortDescriptionId = "Tarian penyambutan dari Bali yang awalnya dipentaskan sebagai ritual kuil.",
            fullDescription = "Pendet is a traditional Balinese dance that was originally a religious " +
                    "offering dance performed in temples. It involves graceful movements and the " +
                    "scattering of flower petals. Today it is also performed as a welcoming dance " +
                    "for guests and at cultural events throughout Indonesia.",
            fullDescriptionId = "Pendet adalah tarian tradisional Bali yang awalnya merupakan tarian " +
                    "persembahan keagamaan yang dipentaskan di kuil. Tarian ini melibatkan gerakan " +
                    "anggun dan penaburan kelopak bunga. Kini juga dipentaskan sebagai tarian " +
                    "penyambutan tamu dan di acara budaya.",
            category = CultureCategory.TARIAN,
            imageRes = R.drawable.tari_pendet,
            readMoreUrl = "https://en.wikipedia.org/wiki/Pendet"
        ),

        // ── MAKANAN ─────────────────────────────────────────────────────────────
        CultureItem(
            id = 4,
            name = "Rendang",
            nameId = "Rendang",
            origin = "West Sumatra",
            originId = "Sumatera Barat",
            shortDescription = "A rich and tender coconut beef stew, voted world's best food.",
            shortDescriptionId = "Semur daging sapi kelapa yang kaya dan lembut, terpilih sebagai makanan terenak dunia.",
            fullDescription = "Rendang is a spicy meat dish originating from the Minangkabau ethnic group " +
                    "of West Sumatra. It is made from beef slowly cooked in coconut milk and a mixture " +
                    "of lemongrass, galangal, garlic, turmeric, ginger, and chilies. The dish is " +
                    "cooked for hours until the liquid evaporates and the meat turns dark brown. " +
                    "CNN Travel voted it the world's most delicious food.",
            fullDescriptionId = "Rendang adalah hidangan daging pedas yang berasal dari suku Minangkabau " +
                    "di Sumatera Barat. Dibuat dari daging sapi yang dimasak perlahan dalam santan " +
                    "bersama serai, lengkuas, bawang putih, kunyit, jahe, dan cabai. Masakan ini " +
                    "dimasak berjam-jam hingga cairan menguap dan daging berwarna coklat tua. " +
                    "CNN Travel memilihnya sebagai makanan paling lezat di dunia.",
            category = CultureCategory.MAKANAN,
            imageRes = R.drawable.rendang,
            readMoreUrl = "https://en.wikipedia.org/wiki/Rendang"
        ),
        CultureItem(
            id = 5,
            name = "Gudeg",
            nameId = "Gudeg",
            origin = "Yogyakarta",
            originId = "Yogyakarta",
            shortDescription = "A sweet jackfruit stew that is the iconic dish of Yogyakarta.",
            shortDescriptionId = "Semur nangka manis yang menjadi hidangan ikonik Yogyakarta.",
            fullDescription = "Gudeg is a traditional Javanese dish from Yogyakarta made from young " +
                    "unripe jackfruit (nangka muda) boiled for hours with palm sugar, coconut milk, " +
                    "and various spices including bay leaves, galangal, and coriander. It has a " +
                    "distinctive sweet taste and is typically served with rice, chicken, egg, " +
                    "tofu, and krecek (spicy buffalo skin).",
            fullDescriptionId = "Gudeg adalah hidangan Jawa tradisional dari Yogyakarta yang terbuat " +
                    "dari nangka muda yang direbus berjam-jam dengan gula merah, santan, dan " +
                    "berbagai rempah termasuk daun salam, lengkuas, dan ketumbar. Memiliki rasa " +
                    "manis yang khas dan biasanya disajikan dengan nasi, ayam, telur, tahu, dan krecek.",
            category = CultureCategory.MAKANAN,
            imageRes = R.drawable.gudeg,
            readMoreUrl = "https://en.wikipedia.org/wiki/Gudeg"
        ),
        CultureItem(
            id = 6,
            name = "Pempek",
            nameId = "Pempek",
            origin = "South Sumatra",
            originId = "Sumatera Selatan",
            shortDescription = "Savory fish cakes from Palembang served with tangy vinegar sauce.",
            shortDescriptionId = "Kue ikan gurih dari Palembang yang disajikan dengan saus cuka asam.",
            fullDescription = "Pempek is a savory fishcake delicacy made from fish and tapioca, " +
                    "originating from Palembang, South Sumatra. It is served with a dark, " +
                    "rich sweet and sour sauce called cuko (or cuka). There are many varieties " +
                    "including kapal selam (with egg inside), lenjer (cylindrical), and adaan " +
                    "(round). It is one of Indonesia's most beloved street foods.",
            fullDescriptionId = "Pempek adalah makanan khas berupa kue ikan yang terbuat dari ikan " +
                    "dan tepung tapioka, berasal dari Palembang, Sumatera Selatan. Disajikan " +
                    "dengan saus manis asam berwarna gelap yang disebut cuko. Ada banyak variasi " +
                    "termasuk kapal selam (berisi telur), lenjer (silinder), dan adaan (bulat).",
            category = CultureCategory.MAKANAN,
            imageRes = R.drawable.pempek,
            readMoreUrl = "https://en.wikipedia.org/wiki/Pempek"
        ),

        // ── RUMAH ADAT ──────────────────────────────────────────────────────────
        CultureItem(
            id = 7,
            name = "Gadang House",
            nameId = "Rumah Gadang",
            origin = "West Sumatra",
            originId = "Sumatera Barat",
            shortDescription = "The iconic Minangkabau house with distinctive curved roof peaks.",
            shortDescriptionId = "Rumah Minangkabau ikonik dengan puncak atap melengkung yang khas.",
            fullDescription = "Rumah Gadang is the traditional communal house of the Minangkabau people " +
                    "of West Sumatra. Its most distinctive feature is the dramatic curved roof that " +
                    "sweeps upward at each end, resembling buffalo horns. The house is built on " +
                    "stilts and is traditionally owned by the women of the clan. It serves as a " +
                    "family home, ceremonial hall, and symbol of Minangkabau identity.",
            fullDescriptionId = "Rumah Gadang adalah rumah komunal tradisional masyarakat Minangkabau " +
                    "di Sumatera Barat. Ciri khasnya adalah atap melengkung dramatis yang mengarah " +
                    "ke atas di setiap ujungnya, menyerupai tanduk kerbau. Rumah dibangun di atas " +
                    "tiang dan secara tradisional dimiliki oleh kaum perempuan. Berfungsi sebagai " +
                    "rumah keluarga, aula upacara, dan simbol identitas Minangkabau.",
            category = CultureCategory.RUMAH_ADAT,
            imageRes = R.drawable.rumah_gadang,
            readMoreUrl = "https://en.wikipedia.org/wiki/Rumah_Gadang"
        ),
        CultureItem(
            id = 8,
            name = "Tongkonan House",
            nameId = "Rumah Tongkonan",
            origin = "South Sulawesi",
            originId = "Sulawesi Selatan",
            shortDescription = "The ancestral house of the Torajan people with a boat-shaped roof.",
            shortDescriptionId = "Rumah leluhur masyarakat Toraja dengan atap berbentuk perahu.",
            fullDescription = "Tongkonan is the traditional ancestral house of the Torajan people " +
                    "from the highlands of South Sulawesi. It is characterized by a massive " +
                    "curved roof structure that resembles a boat or buffalo horns. The house " +
                    "is built on wooden piles and is richly decorated with geometric carvings " +
                    "and painted in red, black, and yellow. It serves as the center of " +
                    "Torajan social life and ritual ceremonies.",
            fullDescriptionId = "Tongkonan adalah rumah leluhur tradisional masyarakat Toraja " +
                    "dari dataran tinggi Sulawesi Selatan. Ditandai dengan struktur atap melengkung " +
                    "besar yang menyerupai perahu atau tanduk kerbau. Rumah dibangun di atas tiang " +
                    "kayu dan dihiasi ukiran geometris serta dicat merah, hitam, dan kuning. " +
                    "Berfungsi sebagai pusat kehidupan sosial dan upacara ritual Toraja.",
            category = CultureCategory.RUMAH_ADAT,
            imageRes = R.drawable.rumah_tongkonan,
            readMoreUrl = "https://en.wikipedia.org/wiki/Tongkonan"
        ),
        CultureItem(
            id = 9,
            name = "Joglo House",
            nameId = "Rumah Joglo",
            origin = "Central Java",
            originId = "Jawa Tengah",
            shortDescription = "The elegant Javanese traditional house with a tiered pyramid roof.",
            shortDescriptionId = "Rumah tradisional Jawa yang elegan dengan atap piramida bertingkat.",
            fullDescription = "Joglo is a traditional Javanese house characterized by its distinctive " +
                    "tiered roof (tajug) supported by four main pillars called soko guru. " +
                    "The house is divided into several sections: the pendopo (open pavilion), " +
                    "pringgitan (middle room), and dalem (inner room). Joglo houses were " +
                    "traditionally built by Javanese nobility and are considered a symbol " +
                    "of high social status and refined Javanese culture.",
            fullDescriptionId = "Joglo adalah rumah tradisional Jawa yang ditandai dengan atap " +
                    "bertingkat (tajug) yang ditopang oleh empat pilar utama yang disebut soko guru. " +
                    "Rumah dibagi menjadi beberapa bagian: pendopo (paviliun terbuka), " +
                    "pringgitan (ruang tengah), dan dalem (ruang dalam). Rumah Joglo secara " +
                    "tradisional dibangun oleh bangsawan Jawa dan dianggap sebagai simbol " +
                    "status sosial tinggi dan budaya Jawa yang halus.",
            category = CultureCategory.RUMAH_ADAT,
            imageRes = R.drawable.rumah_joglo,
            readMoreUrl = "https://en.wikipedia.org/wiki/Joglo"
        ),

        // ── PAKAIAN ADAT ────────────────────────────────────────────────────────
        CultureItem(
            id = 10,
            name = "Dayak Traditional Clothing",
            nameId = "Pakaian Adat Dayak",
            origin = "Kalimantan",
            originId = "Kalimantan",
            shortDescription = "Ornate traditional attire of the Dayak people, adorned with feathers and beads.",
            shortDescriptionId = "Pakaian tradisional suku Dayak yang dihiasi bulu dan manik-manik khas.",
            fullDescription = "The traditional clothing of the Dayak people of Kalimantan is one of the " +
                    "most distinctive in Indonesia. It typically consists of a vest and short skirt " +
                    "made from tree bark or woven fabric, richly decorated with intricate beadwork, " +
                    "hornbill feathers, and animal motifs. The headdress (siger) adorned with " +
                    "hornbill feathers is a symbol of bravery and high status. This attire is worn " +
                    "during traditional ceremonies such as the Gawai harvest festival and war dances.",
            fullDescriptionId = "Pakaian tradisional suku Dayak di Kalimantan adalah salah satu yang " +
                    "paling khas di Indonesia. Biasanya terdiri dari rompi dan rok pendek yang " +
                    "terbuat dari kulit kayu atau kain tenun, dihiasi manik-manik rumit, bulu " +
                    "burung enggang, dan motif binatang. Hiasan kepala (siger) yang dihiasi bulu " +
                    "burung enggang merupakan simbol keberanian dan status tinggi. Pakaian ini " +
                    "dikenakan dalam upacara adat seperti festival panen Gawai dan tarian perang.",
            category = CultureCategory.PAKAIAN_ADAT,
            imageRes = R.drawable.pakaian_dayak,
            readMoreUrl = "https://en.wikipedia.org/wiki/Dayak_people"
        ),
        CultureItem(
            id = 11,
            name = "Ulos",
            nameId = "Ulos",
            origin = "North Sumatra",
            originId = "Sumatera Utara",
            shortDescription = "A sacred woven cloth of the Batak people with deep spiritual meaning.",
            shortDescriptionId = "Kain tenun sakral masyarakat Batak dengan makna spiritual yang dalam.",
            fullDescription = "Ulos is a traditional woven cloth of the Batak people of North Sumatra. " +
                    "It is considered sacred and is used in important ceremonies such as births, " +
                    "weddings, and funerals. Each type of ulos has a specific meaning and purpose. " +
                    "The cloth is woven on a traditional backstrap loom and features intricate " +
                    "geometric patterns. Giving ulos is a way of expressing love, blessing, " +
                    "and protection in Batak culture.",
            fullDescriptionId = "Ulos adalah kain tenun tradisional masyarakat Batak di Sumatera Utara. " +
                    "Dianggap sakral dan digunakan dalam upacara penting seperti kelahiran, " +
                    "pernikahan, dan pemakaman. Setiap jenis ulos memiliki makna dan tujuan " +
                    "tertentu. Kain ditenun dengan alat tenun tradisional dan menampilkan " +
                    "pola geometris yang rumit. Memberikan ulos adalah cara mengungkapkan " +
                    "cinta, berkah, dan perlindungan dalam budaya Batak.",
            category = CultureCategory.PAKAIAN_ADAT,
            imageRes = R.drawable.ulos,
            readMoreUrl = "https://en.wikipedia.org/wiki/Ulos"
        ),
        CultureItem(
            id = 12,
            name = "Batik",
            nameId = "Batik",
            origin = "Java",
            originId = "Jawa",
            shortDescription = "Indonesia's iconic wax-resist dyed fabric, a UNESCO heritage.",
            shortDescriptionId = "Kain celup lilin ikonik Indonesia, warisan UNESCO.",
            fullDescription = "Batik is a technique of wax-resist dyeing applied to whole cloth, " +
                    "and is the art of decorating cloth using this technique, originating from Java. " +
                    "The word batik originates from the Javanese word 'amba' (to write) and 'titik' " +
                    "(dot). Batik is made either by drawing dots and lines with a canting tool, " +
                    "or by printing the resist with a copper stamp called a cap. UNESCO inscribed " +
                    "Indonesian batik on the Intangible Cultural Heritage list in 2009.",
            fullDescriptionId = "Batik adalah teknik pewarnaan kain dengan menggunakan lilin sebagai " +
                    "perintang warna, dan merupakan seni menghias kain menggunakan teknik ini, " +
                    "berasal dari Jawa. Kata batik berasal dari kata Jawa 'amba' (menulis) dan " +
                    "'titik'. Batik dibuat dengan menggambar titik dan garis menggunakan canting, " +
                    "atau dengan mencetak lilin menggunakan cap tembaga. UNESCO mendaftarkan " +
                    "batik Indonesia dalam daftar Warisan Budaya Tak Benda pada 2009.",
            category = CultureCategory.PAKAIAN_ADAT,
            imageRes = R.drawable.batik,
            readMoreUrl = "https://en.wikipedia.org/wiki/Batik"
        )
    )

    fun getByCategory(category: CultureCategory): List<CultureItem> =
        allItems.filter { it.category == category }

    fun getById(id: Int): CultureItem? = allItems.find { it.id == id }
}
