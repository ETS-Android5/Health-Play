package ch.epfl.sdp.healthplay;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.SwitchCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

import ch.epfl.sdp.healthplay.auth.SignedInFragment;
import ch.epfl.sdp.healthplay.database.Database;
import ch.epfl.sdp.healthplay.model.Product;
//import static ch.epfl.sdp.healthplay.database.Database.INSTANCE;

public class BarcodeInformationActivity extends AppCompatActivity {

    private final AtomicReference<String> productName = new AtomicReference<>(Product.UNKNOWN_NAME);
    private final AtomicReference<String> energy = new AtomicReference<>(Product.UNKNOWN_NAME);
    private Product p;
    private String productString;
    private FirebaseUser user;

    public static final String EXTRA_MESSAGE = "ch.epfl.sdp.healthplay.MESSAGE";
    private static final String BASE = "{\"code\":\"0737628064502\",\"product\":{\"_id\":\"0737628064502\",\"_keywords\":[\"include\",\"asia\",\"gluten-free\",\"peanut\",\"potatoe\",\"stir-fry\",\"beverage\",\"product\",\"thailand\",\"cereal\",\"noodle\",\"seasoning\",\"food\",\"kitchen\",\"rice\",\"and\",\"kit\",\"thai\",\"plant-based\",\"their\",\"simply\"],\"added_countries_tags\":[],\"additives_debug_tags\":[],\"additives_n\":1,\"additives_old_n\":2,\"additives_old_tags\":[\"en:e330\",\"en:e551\"],\"additives_original_tags\":[\"en:e330\"],\"additives_prev_original_tags\":[\"en:e330\"],\"additives_tags\":[\"en:e330\"],\"additives_tags_n\":null,\"allergens\":\"\",\"allergens_from_ingredients\":\"en:peanuts\",\"allergens_from_user\":\"(en) \",\"allergens_hierarchy\":[\"en:peanuts\"],\"allergens_tags\":[\"en:peanuts\"],\"amino_acids_prev_tags\":[],\"amino_acids_tags\":[],\"brand_owner\":\"Simply Asia Foods, Inc.\",\"brand_owner_imported\":\"Simply Asia Foods, Inc.\",\"brands\":\"Thai Kitchen,Simply Asia\",\"brands_debug_tags\":[],\"brands_tags\":[\"thai-kitchen\",\"simply-asia\"],\"categories\":\"Plant-based foods and beverages, Plant-based foods, Cereals and potatoes, Cereals and their products, Noodles, Rice Noodles\",\"categories_hierarchy\":[\"en:plant-based-foods-and-beverages\",\"en:plant-based-foods\",\"en:cereals-and-potatoes\",\"en:cereals-and-their-products\",\"en:noodles\",\"en:Rice Noodles\"],\"categories_imported\":\"Plant-based foods and beverages, Plant-based foods, Cereals and potatoes, Cereals and their products, Noodles\",\"categories_lc\":\"en\",\"categories_old\":\"Plant-based foods and beverages, Plant-based foods, Cereals and potatoes, Cereals and their products, Noodles, Rice Noodles\",\"categories_properties\":{},\"categories_properties_tags\":[\"all-products\",\"categories-known\",\"agribalyse-food-code-unknown\",\"agribalyse-proxy-food-code-unknown\",\"ciqual-food-code-unknown\",\"agribalyse-unknown\"],\"categories_tags\":[\"en:plant-based-foods-and-beverages\",\"en:plant-based-foods\",\"en:cereals-and-potatoes\",\"en:cereals-and-their-products\",\"en:noodles\",\"en:rice-noodles\"],\"category_properties\":{},\"checkers\":[],\"checkers_tags\":[],\"ciqual_food_name_tags\":[\"unknown\"],\"cities_tags\":[],\"code\":\"0737628064502\",\"codes_tags\":[\"code-13\",\"0737628064xxx\",\"073762806xxxx\",\"07376280xxxxx\",\"0737628xxxxxx\",\"073762xxxxxxx\",\"07376xxxxxxxx\",\"0737xxxxxxxxx\",\"073xxxxxxxxxx\",\"07xxxxxxxxxxx\",\"0xxxxxxxxxxxx\"],\"compared_to_category\":\"en:noodles\",\"complete\":0,\"completeness\":0.8875,\"correctors\":[\"andre\",\"thierrym\"],\"correctors_tags\":[\"andre\",\"thierrym\",\"usda-ndb-import\",\"smartchef\",\"org-database-usda\",\"packbot\"],\"countries\":\"France, United States\",\"countries_debug_tags\":[],\"countries_hierarchy\":[\"en:france\",\"en:united-states\"],\"countries_imported\":\"United States\",\"countries_lc\":\"en\",\"countries_tags\":[\"en:france\",\"en:united-states\"],\"created_t\":1345799269,\"creator\":\"openfoodfacts-contributors\",\"data_quality_bugs_tags\":[],\"data_quality_errors_tags\":[],\"data_quality_info_tags\":[\"en:no-packaging-data\",\"en:ingredients-percent-analysis-ok\"],\"data_quality_tags\":[\"en:no-packaging-data\",\"en:ingredients-percent-analysis-ok\",\"en:nutrition-value-very-high-for-category-sugars\",\"en:ecoscore-packaging-packaging-data-missing\",\"en:ecoscore-production-system-no-label\"],\"data_quality_warnings_tags\":[\"en:nutrition-value-very-high-for-category-sugars\",\"en:ecoscore-packaging-packaging-data-missing\",\"en:ecoscore-production-system-no-label\"],\"data_sources\":\"Databases, database-usda, Database - USDA NDB\",\"data_sources_imported\":\"Databases, database-usda\",\"data_sources_tags\":[\"databases\",\"database-usda\",\"database-usda-ndb\"],\"debug_param_sorted_langs\":[\"en\"],\"ecoscore_data\":{\"adjustments\":{\"origins_of_ingredients\":{\"aggregated_origins\":[{\"origin\":\"en:thailand\",\"percent\":100}],\"epi_score\":0,\"epi_value\":-5,\"origins_from_origins_field\":[\"en:thailand\"],\"transportation_scores\":{\"ad\":19,\"al\":33,\"at\":8,\"ax\":4,\"ba\":11,\"be\":12,\"bg\":13,\"ch\":9,\"cy\":37,\"cz\":0,\"de\":12,\"dk\":0,\"dz\":9,\"ee\":7,\"eg\":33,\"es\":25,\"fi\":7,\"fo\":3,\"fr\":0,\"gg\":0,\"gi\":27,\"gr\":37,\"hr\":24,\"hu\":4,\"ie\":17,\"il\":33,\"im\":0,\"is\":0,\"it\":16,\"je\":0,\"lb\":37,\"li\":11,\"lt\":0,\"lu\":3,\"lv\":8,\"ly\":34,\"ma\":3,\"mc\":31,\"md\":22,\"me\":29,\"mk\":20,\"mt\":34,\"nl\":12,\"no\":12,\"pl\":0,\"ps\":41,\"pt\":16,\"ro\":23,\"rs\":10,\"se\":0,\"si\":27,\"sj\":0,\"sk\":0,\"sm\":10,\"sy\":24,\"tn\":33,\"tr\":0,\"ua\":33,\"uk\":7,\"us\":0,\"va\":1,\"world\":0,\"xk\":19},\"transportation_values\":{\"ad\":3,\"al\":5,\"at\":1,\"ax\":1,\"ba\":2,\"be\":2,\"bg\":2,\"ch\":1,\"cy\":6,\"cz\":0,\"de\":2,\"dk\":0,\"dz\":1,\"ee\":1,\"eg\":5,\"es\":4,\"fi\":1,\"fo\":0,\"fr\":0,\"gg\":0,\"gi\":4,\"gr\":6,\"hr\":4,\"hu\":1,\"ie\":3,\"il\":5,\"im\":0,\"is\":0,\"it\":2,\"je\":0,\"lb\":6,\"li\":2,\"lt\":0,\"lu\":0,\"lv\":1,\"ly\":5,\"ma\":0,\"mc\":5,\"md\":3,\"me\":4,\"mk\":3,\"mt\":5,\"nl\":2,\"no\":2,\"pl\":0,\"ps\":6,\"pt\":2,\"ro\":3,\"rs\":2,\"se\":0,\"si\":4,\"sj\":0,\"sk\":0,\"sm\":2,\"sy\":4,\"tn\":5,\"tr\":0,\"ua\":5,\"uk\":1,\"us\":0,\"va\":0,\"world\":0,\"xk\":3},\"values\":{\"ad\":-2,\"al\":0,\"at\":-4,\"ax\":-4,\"ba\":-3,\"be\":-3,\"bg\":-3,\"ch\":-4,\"cy\":1,\"cz\":-5,\"de\":-3,\"dk\":-5,\"dz\":-4,\"ee\":-4,\"eg\":0,\"es\":-1,\"fi\":-4,\"fo\":-5,\"fr\":-5,\"gg\":-5,\"gi\":-1,\"gr\":1,\"hr\":-1,\"hu\":-4,\"ie\":-2,\"il\":0,\"im\":-5,\"is\":-5,\"it\":-3,\"je\":-5,\"lb\":1,\"li\":-3,\"lt\":-5,\"lu\":-5,\"lv\":-4,\"ly\":0,\"ma\":-5,\"mc\":0,\"md\":-2,\"me\":-1,\"mk\":-2,\"mt\":0,\"nl\":-3,\"no\":-3,\"pl\":-5,\"ps\":1,\"pt\":-3,\"ro\":-2,\"rs\":-3,\"se\":-5,\"si\":-1,\"sj\":-5,\"sk\":-5,\"sm\":-3,\"sy\":-1,\"tn\":0,\"tr\":-5,\"ua\":0,\"uk\":-4,\"us\":-5,\"va\":-5,\"world\":-5,\"xk\":-2}},\"packaging\":{\"non_recyclable_and_non_biodegradable_materials\":0,\"packagings\":[{\"ecoscore_material_score\":0,\"ecoscore_shape_ratio\":1,\"material\":\"en:unknown\",\"shape\":\"xx:cellophane\"},{\"ecoscore_material_score\":0,\"ecoscore_shape_ratio\":1,\"material\":\"en:unknown\",\"shape\":\"en:container\"}],\"score\":-100,\"value\":-15,\"warning\":\"unscored_shape\"},\"production_system\":{\"labels\":[],\"value\":0,\"warning\":\"no_label\"},\"threatened_species\":{}},\"agribalyse\":{\"warning\":\"missing_agribalyse_match\"},\"missing\":{\"agb_category\":1,\"labels\":1,\"packagings\":1},\"missing_agribalyse_match_warning\":1,\"status\":\"unknown\"},\"ecoscore_grade\":\"unknown\",\"ecoscore_tags\":[\"unknown\"],\"editors\":[\"\",\"thierrym\",\"manu1400\",\"andre\",\"upcbot\"],\"editors_tags\":[\"manu1400\",\"org-database-usda\",\"usda-ndb-import\",\"smartchef\",\"packbot\",\"openfoodfacts-contributors\",\"upcbot\",\"thierrym\",\"andre\"],\"emb_codes\":\"\",\"emb_codes_20141016\":\"\",\"emb_codes_debug_tags\":[],\"emb_codes_orig\":\"\",\"emb_codes_tags\":[],\"entry_dates_tags\":[\"2012-08-24\",\"2012-08\",\"2012\"],\"expiration_date\":\"\",\"expiration_date_debug_tags\":[],\"food_groups\":\"en:cereals\",\"food_groups_tags\":[\"en:cereals-and-potatoes\",\"en:cereals\"],\"fruits-vegetables-nuts_100g_estimate\":0,\"generic_name\":\"Rice Noodles\",\"generic_name_en\":\"Rice Noodles\",\"generic_name_en_debug_tags\":[],\"id\":\"737628064502\",\"image_front_small_url\":\"https://images.openfoodfacts.org/images/products/073/762/806/4502/front_en.6.200.jpg\",\"image_front_thumb_url\":\"https://images.openfoodfacts.org/images/products/073/762/806/4502/front_en.6.100.jpg\",\"image_front_url\":\"https://images.openfoodfacts.org/images/products/073/762/806/4502/front_en.6.400.jpg\",\"image_ingredients_small_url\":\"https://images.openfoodfacts.org/images/products/073/762/806/4502/ingredients_en.10.200.jpg\",\"image_ingredients_thumb_url\":\"https://images.openfoodfacts.org/images/products/073/762/806/4502/ingredients_en.10.100.jpg\",\"image_ingredients_url\":\"https://images.openfoodfacts.org/images/products/073/762/806/4502/ingredients_en.10.400.jpg\",\"image_nutrition_small_url\":\"https://images.openfoodfacts.org/images/products/073/762/806/4502/nutrition_en.12.200.jpg\",\"image_nutrition_thumb_url\":\"https://images.openfoodfacts.org/images/products/073/762/806/4502/nutrition_en.12.100.jpg\",\"image_nutrition_url\":\"https://images.openfoodfacts.org/images/products/073/762/806/4502/nutrition_en.12.400.jpg\",\"image_small_url\":\"https://images.openfoodfacts.org/images/products/073/762/806/4502/front_en.6.200.jpg\",\"image_thumb_url\":\"https://images.openfoodfacts.org/images/products/073/762/806/4502/front_en.6.100.jpg\",\"image_url\":\"https://images.openfoodfacts.org/images/products/073/762/806/4502/front_en.6.400.jpg\",\"images\":{\"1\":{\"sizes\":{\"100\":{\"h\":71,\"w\":100},\"400\":{\"h\":284,\"w\":400},\"full\":{\"h\":1089,\"w\":1536}},\"uploaded_t\":1345799270,\"uploader\":\"openfoodfacts-contributors\"},\"2\":{\"sizes\":{\"100\":{\"h\":100,\"w\":72},\"400\":{\"h\":400,\"w\":289},\"full\":{\"h\":1812,\"w\":1311}},\"uploaded_t\":1345799309,\"uploader\":\"andre\"},\"3\":{\"sizes\":{\"100\":{\"h\":72,\"w\":100},\"400\":{\"h\":289,\"w\":400},\"full\":{\"h\":927,\"w\":1281}},\"uploaded_t\":1345799558,\"uploader\":\"andre\"},\"4\":{\"sizes\":{\"100\":{\"h\":72,\"w\":100},\"400\":{\"h\":289,\"w\":400},\"full\":{\"h\":927,\"w\":1281}},\"uploaded_t\":1345799666,\"uploader\":\"andre\"},\"5\":{\"sizes\":{\"100\":{\"h\":100,\"w\":54},\"400\":{\"h\":400,\"w\":216},\"full\":{\"h\":1611,\"w\":870}},\"uploaded_t\":1345799925,\"uploader\":\"andre\"},\"front\":{\"geometry\":\"0x0--4--4\",\"imgid\":\"2\",\"normalize\":null,\"rev\":\"6\",\"sizes\":{\"100\":{\"h\":100,\"w\":72},\"200\":{\"h\":200,\"w\":145},\"400\":{\"h\":400,\"w\":289},\"full\":{\"h\":1812,\"w\":1311}},\"white_magic\":null},\"front_en\":{\"geometry\":\"0x0--4--4\",\"imgid\":\"2\",\"normalize\":null,\"rev\":\"6\",\"sizes\":{\"100\":{\"h\":100,\"w\":72},\"200\":{\"h\":200,\"w\":145},\"400\":{\"h\":400,\"w\":289},\"full\":{\"h\":1812,\"w\":1311}},\"white_magic\":null},\"ingredients\":{\"geometry\":\"0x0--3--3\",\"imgid\":\"4\",\"normalize\":\"checked\",\"rev\":\"10\",\"sizes\":{\"100\":{\"h\":72,\"w\":100},\"200\":{\"h\":145,\"w\":200},\"400\":{\"h\":289,\"w\":400},\"full\":{\"h\":927,\"w\":1281}},\"white_magic\":null},\"ingredients_en\":{\"geometry\":\"0x0--3--3\",\"imgid\":\"4\",\"normalize\":\"checked\",\"rev\":\"10\",\"sizes\":{\"100\":{\"h\":72,\"w\":100},\"200\":{\"h\":145,\"w\":200},\"400\":{\"h\":289,\"w\":400},\"full\":{\"h\":927,\"w\":1281}},\"white_magic\":null},\"nutrition\":{\"geometry\":\"0x0--4--4\",\"imgid\":\"5\",\"normalize\":\"checked\",\"rev\":\"12\",\"sizes\":{\"100\":{\"h\":100,\"w\":54},\"200\":{\"h\":200,\"w\":108},\"400\":{\"h\":400,\"w\":216},\"full\":{\"h\":1611,\"w\":870}},\"white_magic\":null},\"nutrition_en\":{\"geometry\":\"0x0--4--4\",\"imgid\":\"5\",\"normalize\":\"checked\",\"rev\":\"12\",\"sizes\":{\"100\":{\"h\":100,\"w\":54},\"200\":{\"h\":200,\"w\":108},\"400\":{\"h\":400,\"w\":216},\"full\":{\"h\":1611,\"w\":870}},\"white_magic\":null}},\"informers\":[\"andre\",\"manu1400\",\"thierrym\"],\"informers_tags\":[\"andre\",\"manu1400\",\"thierrym\",\"upcbot\",\"smartchef\",\"org-database-usda\"],\"ingredients\":[{\"has_sub_ingredients\":\"yes\",\"id\":\"en:noodle\",\"percent_estimate\":53.8461538461538,\"percent_max\":100,\"percent_min\":7.69230769230769,\"rank\":1,\"text\":\"Noodle\"},{\"id\":\"en:water\",\"percent_estimate\":23.0769230769231,\"percent_max\":50,\"percent_min\":0,\"rank\":2,\"text\":\"water\",\"vegan\":\"yes\",\"vegetarian\":\"yes\"},{\"has_sub_ingredients\":\"yes\",\"id\":\"en:seasoning packet\",\"percent_estimate\":11.5384615384615,\"percent_max\":33.3333333333333,\"percent_min\":0,\"rank\":3,\"text\":\"seasoning packet\"},{\"id\":\"en:sugar\",\"percent_estimate\":5.76923076923077,\"percent_max\":25,\"percent_min\":0,\"rank\":4,\"text\":\"sugar\",\"vegan\":\"yes\",\"vegetarian\":\"yes\"},{\"id\":\"en:hydrolysed-soy-protein\",\"percent_estimate\":2.88461538461539,\"percent_max\":20,\"percent_min\":0,\"rank\":5,\"text\":\"hydrolyzed soy protein\",\"vegan\":\"yes\",\"vegetarian\":\"yes\"},{\"id\":\"en:spring-onion\",\"percent_estimate\":1.44230769230769,\"percent_max\":16.6666666666667,\"percent_min\":0,\"rank\":6,\"text\":\"green onion\",\"vegan\":\"yes\",\"vegetarian\":\"yes\"},{\"id\":\"en:corn-maltodextrin\",\"percent_estimate\":0.721153846153847,\"percent_max\":14.2857142857143,\"percent_min\":0,\"rank\":7,\"text\":\"corn maltodextrin\",\"vegan\":\"yes\",\"vegetarian\":\"yes\"},{\"has_sub_ingredients\":\"yes\",\"id\":\"en:spice\",\"percent_estimate\":0.36057692307692,\"percent_max\":12.5,\"percent_min\":0,\"rank\":8,\"text\":\"spice\",\"vegan\":\"yes\",\"vegetarian\":\"yes\"},{\"id\":\"en:e330\",\"percent_estimate\":0.18028846153846,\"percent_max\":11.1111111111111,\"percent_min\":0,\"rank\":9,\"text\":\"citric acid\",\"vegan\":\"yes\",\"vegetarian\":\"yes\"},{\"id\":\"en:sea-salt\",\"percent_estimate\":0.0901442307692264,\"percent_max\":10,\"percent_min\":0,\"rank\":10,\"text\":\"sea salt\",\"vegan\":\"yes\",\"vegetarian\":\"yes\"},{\"has_sub_ingredients\":\"yes\",\"id\":\"en:paprika\",\"percent_estimate\":0.0450721153846132,\"percent_max\":9.09090909090909,\"percent_min\":0,\"processing\":\"en:extract\",\"rank\":11,\"text\":\"paprika\",\"vegan\":\"yes\",\"vegetarian\":\"yes\"},{\"has_sub_ingredients\":\"yes\",\"id\":\"en:e551\",\"percent_estimate\":0.0225360576923066,\"percent_max\":8.33333333333333,\"percent_min\":0,\"rank\":12,\"text\":\"silicon dioxide\",\"vegan\":\"yes\",\"vegetarian\":\"yes\"},{\"id\":\"en:yeast-extract\",\"percent_estimate\":0.0225360576923066,\"percent_max\":7.69230769230769,\"percent_min\":0,\"rank\":13,\"text\":\"yeast extract\",\"vegan\":\"yes\",\"vegetarian\":\"yes\"},{\"id\":\"en:rice\",\"percent_estimate\":53.8461538461538,\"percent_max\":100,\"percent_min\":7.69230769230769,\"text\":\"rice\",\"vegan\":\"yes\",\"vegetarian\":\"yes\"},{\"id\":\"en:peanut\",\"percent_estimate\":11.5384615384615,\"percent_max\":33.3333333333333,\"percent_min\":0,\"text\":\"peanut\",\"vegan\":\"yes\",\"vegetarian\":\"yes\"},{\"id\":\"en:paprika\",\"percent_estimate\":0.36057692307692,\"percent_max\":12.5,\"percent_min\":0,\"text\":\"including paprika\",\"vegan\":\"yes\",\"vegetarian\":\"yes\"},{\"id\":\"en:colour\",\"percent_estimate\":0.0450721153846132,\"percent_max\":9.09090909090909,\"percent_min\":0,\"text\":\"color\"},{\"id\":\"en:added to make free flowing\",\"percent_estimate\":0.0225360576923066,\"percent_max\":8.33333333333333,\"percent_min\":0,\"text\":\"added to make free flowing\"}],\"ingredients_analysis\":{\"en:palm-oil-content-unknown\":[\"en:seasoning packet\",\"en:added to make free flowing\"],\"en:vegan-status-unknown\":[\"en:noodle\",\"en:seasoning packet\",\"en:added to make free flowing\"],\"en:vegetarian-status-unknown\":[\"en:noodle\",\"en:seasoning packet\",\"en:added to make free flowing\"]},\"ingredients_analysis_tags\":[\"en:palm-oil-content-unknown\",\"en:vegan-status-unknown\",\"en:vegetarian-status-unknown\"],\"ingredients_debug\":[\"RICE NOODLES \",\"(\",\"(\",null,null,\"RICE\",\",\",null,null,null,\" WATER)\",\",\",null,null,null,\" SEASONING PACKET \",\"(\",\"(\",null,null,\"PEANUT\",\",\",null,null,null,\" SUGAR\",\",\",null,null,null,\" SALT\",\",\",null,null,null,\" CORN STARCH\",\",\",null,null,null,\" SPICES \",\"[\",\"[\",null,null,\"CHILI\",\",\",null,null,null,\" CINNAMON\",\",\",null,null,null,\" PEPPER\",\",\",null,null,null,\" CUMIN\",\",\",null,null,null,\" CLOVE]\",\",\",null,null,null,\" HYDRDLYZED SOY PROTEIN\",\",\",null,null,null,\" GREEN ONIONS\",\",\",null,null,null,\" CITRIC ACID\",\",\",null,null,null,\" PEANUT OIL\",\",\",null,null,null,\" SESAME OIL\",\",\",null,null,null,\" \",\":\",\":\",null,null,\" NATURAL FLAVOR \",\":\",\":\",null,null,\" ).\"],\"ingredients_from_or_that_may_be_from_palm_oil_n\":0,\"ingredients_from_palm_oil_n\":0,\"ingredients_from_palm_oil_tags\":[],\"ingredients_hierarchy\":[\"en:noodle\",\"en:dough\",\"en:water\",\"en:seasoning packet\",\"en:sugar\",\"en:added-sugar\",\"en:disaccharide\",\"en:hydrolysed-soy-protein\",\"en:protein\",\"en:vegetable-protein\",\"en:hydrolysed-vegetable-protein\",\"en:soy-protein\",\"en:spring-onion\",\"en:vegetable\",\"en:root-vegetable\",\"en:onion\",\"en:corn-maltodextrin\",\"en:maltodextrins\",\"en:spice\",\"en:condiment\",\"en:e330\",\"en:sea-salt\",\"en:salt\",\"en:paprika\",\"en:e551\",\"en:yeast-extract\",\"en:yeast\",\"en:rice\",\"en:peanut\",\"en:nut\",\"en:colour\",\"en:added to make free flowing\"],\"ingredients_ids_debug\":[\"rice-noodles\",\"rice\",\"water\",\"seasoning-packet\",\"peanut\",\"sugar\",\"salt\",\"corn-starch\",\"spices\",\"chili\",\"cinnamon\",\"pepper\",\"cumin\",\"clove\",\"hydrdlyzed-soy-protein\",\"green-onions\",\"citric-acid\",\"peanut-oil\",\"sesame-oil\",\"natural-flavor\"],\"ingredients_n\":18,\"ingredients_n_tags\":[\"18\",\"11-20\"],\"ingredients_original_tags\":[\"en:noodle\",\"en:water\",\"en:seasoning packet\",\"en:sugar\",\"en:hydrolysed-soy-protein\",\"en:spring-onion\",\"en:corn-maltodextrin\",\"en:spice\",\"en:e330\",\"en:sea-salt\",\"en:paprika\",\"en:e551\",\"en:yeast-extract\",\"en:rice\",\"en:peanut\",\"en:paprika\",\"en:colour\",\"en:added to make free flowing\"],\"ingredients_percent_analysis\":1,\"ingredients_tags\":[\"en:noodle\",\"en:dough\",\"en:water\",\"en:seasoning-packet\",\"en:sugar\",\"en:added-sugar\",\"en:disaccharide\",\"en:hydrolysed-soy-protein\",\"en:protein\",\"en:vegetable-protein\",\"en:hydrolysed-vegetable-protein\",\"en:soy-protein\",\"en:spring-onion\",\"en:vegetable\",\"en:root-vegetable\",\"en:onion\",\"en:corn-maltodextrin\",\"en:maltodextrins\",\"en:spice\",\"en:condiment\",\"en:e330\",\"en:sea-salt\",\"en:salt\",\"en:paprika\",\"en:e551\",\"en:yeast-extract\",\"en:yeast\",\"en:rice\",\"en:peanut\",\"en:nut\",\"en:colour\",\"en:added-to-make-free-flowing\"],\"ingredients_text\":\"Noodle: rice, water. seasoning packet: peanut, sugar, hydrolyzed soy protein, green onion, corn maltodextrin, spice (including paprika), citric acid, sea salt, extractives of paprika (color), silicon dioxide (added to make free flowing), yeast extract.\",\"ingredients_text_debug\":\"RICE NOODLES (RICE, WATER), SEASONING PACKET (PEANUT, SUGAR, SALT, CORN STARCH, SPICES [CHILI, CINNAMON, PEPPER, CUMIN, CLOVE], HYDRDLYZED SOY PROTEIN, GREEN ONIONS, CITRIC ACID, PEANUT OIL, SESAME OIL, : NATURAL FLAVOR : ).\",\"ingredients_text_en\":\"Noodle: rice, water. seasoning packet: peanut, sugar, hydrolyzed soy protein, green onion, corn maltodextrin, spice (including paprika), citric acid, sea salt, extractives of paprika (color), silicon dioxide (added to make free flowing), yeast extract.\",\"ingredients_text_en_debug_tags\":[],\"ingredients_text_en_imported\":\"Noodle: rice, water. seasoning packet: peanut, sugar, hydrolyzed soy protein, green onion, corn maltodextrin, spice (including paprika), citric acid, sea salt, extractives of paprika (color), silicon dioxide (added to make free flowing), yeast extract.\",\"ingredients_text_with_allergens\":\"Noodle: rice, water. seasoning packet: peanut, sugar, hydrolyzed soy protein, green onion, corn maltodextrin, spice (including paprika), citric acid, sea salt, extractives of paprika (color), silicon dioxide (added to make free flowing), yeast extract.\",\"ingredients_text_with_allergens_en\":\"Noodle: rice, water. seasoning packet: peanut, sugar, hydrolyzed soy protein, green onion, corn maltodextrin, spice (including paprika), citric acid, sea salt, extractives of paprika (color), silicon dioxide (added to make free flowing), yeast extract.\",\"ingredients_that_may_be_from_palm_oil_n\":0,\"ingredients_that_may_be_from_palm_oil_tags\":[],\"ingredients_with_specified_percent_n\":0,\"ingredients_with_specified_percent_sum\":0,\"ingredients_with_unspecified_percent_n\":13,\"ingredients_with_unspecified_percent_sum\":100,\"interface_version_created\":\"20120622\",\"interface_version_modified\":\"20120622\",\"known_ingredients_n\":30,\"labels\":\"Gluten-free\",\"labels_hierarchy\":[\"en:no-gluten\"],\"labels_lc\":\"en\",\"labels_old\":\"Gluten-free\",\"labels_tags\":[\"en:no-gluten\"],\"lang\":\"en\",\"lang_debug_tags\":[],\"languages\":{\"en:english\":6},\"languages_codes\":{\"en\":6},\"languages_hierarchy\":[\"en:english\"],\"languages_tags\":[\"en:english\",\"en:1\"],\"last_edit_dates_tags\":[\"2022-02-10\",\"2022-02\",\"2022\"],\"last_editor\":\"packbot\",\"last_image_dates_tags\":[\"2012-08-24\",\"2012-08\",\"2012\"],\"last_image_t\":1345799925,\"last_modified_by\":\"packbot\",\"last_modified_t\":1644526860,\"lc\":\"en\",\"lc_imported\":\"en\",\"link\":\"\",\"link_debug_tags\":[],\"main_countries_tags\":[],\"manufacturing_places\":\"\",\"manufacturing_places_debug_tags\":[],\"manufacturing_places_tags\":[],\"max_imgid\":\"5\",\"minerals_prev_tags\":[],\"minerals_tags\":[],\"misc_tags\":[\"en:nutrition-fruits-vegetables-nuts-estimate-from-ingredients\",\"en:nutrition-all-nutriscore-values-known\",\"en:nutriscore-computed\",\"en:ecoscore-not-computed\",\"en:main-countries-no-scans\",\"en:ecoscore-extended-data-not-computed\"],\"new_additives_n\":1,\"no_nutrition_data\":\"\",\"nova_group\":4,\"nova_group_debug\":\" -- ingredients/en:salt : 3 -- ingredients/en:colour : 4\",\"nova_groups\":\"4\",\"nova_groups_tags\":[\"en:4-ultra-processed-food-and-drink-products\"],\"nucleotides_prev_tags\":[],\"nucleotides_tags\":[],\"nutrient_levels\":{\"fat\":\"moderate\",\"salt\":\"moderate\",\"saturated-fat\":\"moderate\",\"sugars\":\"high\"},\"nutrient_levels_tags\":[\"en:fat-in-moderate-quantity\",\"en:saturated-fat-in-moderate-quantity\",\"en:sugars-in-high-quantity\",\"en:salt-in-moderate-quantity\"],\"nutriments\":{\"calcium\":0.038,\"calcium_100g\":0.038,\"calcium_serving\":0.0198,\"calcium_unit\":\"mg\",\"calcium_value\":38,\"carbohydrates\":71.15,\"carbohydrates_100g\":71.15,\"carbohydrates_serving\":37,\"carbohydrates_unit\":\"g\",\"carbohydrates_value\":71.15,\"cholesterol\":0,\"cholesterol_100g\":0,\"cholesterol_serving\":0,\"cholesterol_unit\":\"mg\",\"cholesterol_value\":0,\"energy\":1611,\"energy-kcal\":385,\"energy-kcal_100g\":385,\"energy-kcal_serving\":200,\"energy-kcal_unit\":\"kcal\",\"energy-kcal_value\":385,\"energy_100g\":1611,\"energy_serving\":838,\"energy_unit\":\"kcal\",\"energy_value\":385,\"fat\":7.69,\"fat_100g\":7.69,\"fat_serving\":4,\"fat_unit\":\"g\",\"fat_value\":7.69,\"fiber\":1.9,\"fiber_100g\":1.9,\"fiber_serving\":0.988,\"fiber_unit\":\"g\",\"fiber_value\":1.9,\"fruits-vegetables-nuts-estimate-from-ingredients_100g\":0,\"fruits-vegetables-nuts-estimate-from-ingredients_serving\":0,\"iron\":0.00069,\"iron_100g\":0.00069,\"iron_serving\":0.000359,\"iron_unit\":\"mg\",\"iron_value\":0.69,\"nova-group\":4,\"nova-group_100g\":4,\"nova-group_serving\":4,\"nutrition-score-fr\":4,\"nutrition-score-fr_100g\":4,\"proteins\":9.62,\"proteins_100g\":9.62,\"proteins_serving\":5,\"proteins_unit\":\"g\",\"proteins_value\":9.62,\"salt\":0.72,\"salt_100g\":0.72,\"salt_serving\":0.374,\"salt_unit\":\"mg\",\"salt_value\":720,\"saturated-fat\":1.92,\"saturated-fat_100g\":1.92,\"saturated-fat_serving\":0.998,\"saturated-fat_unit\":\"g\",\"saturated-fat_value\":1.92,\"sodium\":0.288,\"sodium_100g\":0.288,\"sodium_serving\":0.15,\"sodium_unit\":\"mg\",\"sodium_value\":288,\"sugars\":13.46,\"sugars_100g\":13.46,\"sugars_serving\":7,\"sugars_unit\":\"g\",\"sugars_value\":13.46,\"trans-fat\":0,\"trans-fat_100g\":0,\"trans-fat_serving\":0,\"trans-fat_unit\":\"g\",\"trans-fat_value\":0,\"vitamin-a\":0.0001155,\"vitamin-a_100g\":0.0001155,\"vitamin-a_serving\":6.01e-05,\"vitamin-a_unit\":\"IU\",\"vitamin-a_value\":385,\"vitamin-c\":0,\"vitamin-c_100g\":0,\"vitamin-c_serving\":0,\"vitamin-c_unit\":\"mg\",\"vitamin-c_value\":0},\"nutriscore_data\":{\"energy\":1611,\"energy_points\":4,\"energy_value\":1611,\"fiber\":1.9,\"fiber_points\":1,\"fiber_value\":1.9,\"fruits_vegetables_nuts_colza_walnut_olive_oils\":0,\"fruits_vegetables_nuts_colza_walnut_olive_oils_points\":0,\"fruits_vegetables_nuts_colza_walnut_olive_oils_value\":0,\"grade\":\"c\",\"is_beverage\":0,\"is_cheese\":0,\"is_fat\":0,\"is_water\":0,\"negative_points\":10,\"positive_points\":6,\"proteins\":9.62,\"proteins_points\":5,\"proteins_value\":9.62,\"saturated_fat\":1.92,\"saturated_fat_points\":1,\"saturated_fat_ratio\":24.9674902470741,\"saturated_fat_ratio_points\":3,\"saturated_fat_ratio_value\":25,\"saturated_fat_value\":1.9,\"score\":4,\"sodium\":288,\"sodium_points\":3,\"sodium_value\":288,\"sugars\":13.46,\"sugars_points\":2,\"sugars_value\":13.46},\"nutriscore_grade\":\"c\",\"nutriscore_score\":4,\"nutriscore_score_opposite\":-4,\"nutrition_data\":\"on\",\"nutrition_data_per\":\"100g\",\"nutrition_data_per_debug_tags\":[],\"nutrition_data_per_imported\":\"100g\",\"nutrition_data_prepared\":\"\",\"nutrition_data_prepared_per\":\"100g\",\"nutrition_data_prepared_per_debug_tags\":[],\"nutrition_data_prepared_per_imported\":\"100g\",\"nutrition_grade_fr\":\"c\",\"nutrition_grades\":\"c\",\"nutrition_grades_tags\":[\"c\"],\"nutrition_score_beverage\":0,\"nutrition_score_warning_fruits_vegetables_nuts_estimate_from_ingredients\":1,\"nutrition_score_warning_fruits_vegetables_nuts_estimate_from_ingredients_value\":0,\"origins\":\"Thailand\",\"origins_hierarchy\":[\"en:thailand\"],\"origins_lc\":\"en\",\"origins_old\":\"Thailand\",\"origins_tags\":[\"en:thailand\"],\"other_nutritional_substances_tags\":[],\"packaging\":\"Container, Cellophane\",\"packaging_hierarchy\":[\"en:container\",\"xx:cellophane\"],\"packaging_lc\":\"en\",\"packaging_old\":\"Container, Cellophane\",\"packaging_old_before_taxonomization\":\"Cellophane,Carton\",\"packaging_tags\":[\"en:container\",\"xx:cellophane\"],\"packagings\":[{\"shape\":\"xx:cellophane\"},{\"shape\":\"en:container\"}],\"photographers\":[\"andre\"],\"photographers_tags\":[\"openfoodfacts-contributors\",\"andre\"],\"pnns_groups_1\":\"Cereals and potatoes\",\"pnns_groups_1_tags\":[\"cereals-and-potatoes\",\"known\"],\"pnns_groups_2\":\"Cereals\",\"pnns_groups_2_tags\":[\"cereals\",\"known\"],\"popularity_key\":20900000005,\"popularity_tags\":[\"bottom-25-percent-scans-2019\",\"bottom-20-percent-scans-2019\",\"top-85-percent-scans-2019\",\"top-90-percent-scans-2019\",\"top-50000-it-scans-2019\",\"top-100000-it-scans-2019\",\"top-country-it-scans-2019\",\"top-100000-scans-2020\",\"at-least-5-scans-2020\",\"top-75-percent-scans-2020\",\"top-80-percent-scans-2020\",\"top-85-percent-scans-2020\",\"top-90-percent-scans-2020\",\"top-10-by-scans-2020\",\"top-50-by-scans-2020\",\"top-100-by-scans-2020\",\"top-500-by-scans-2020\",\"top-1000-by-scans-2020\",\"top-5000-by-scans-2020\",\"top-10000-by-scans-2020\",\"top-50000-by-scans-2020\",\"top-100000-by-scans-2020\",\"top-country-by-scans-2020\",\"top-5000-ca-scans-2020\",\"top-10000-ca-scans-2020\",\"top-50000-ca-scans-2020\",\"top-100000-ca-scans-2020\",\"top-50000-be-scans-2020\",\"top-100000-be-scans-2020\",\"top-100000-scans-2021\",\"at-least-5-scans-2021\",\"top-75-percent-scans-2021\",\"top-80-percent-scans-2021\",\"top-85-percent-scans-2021\",\"top-90-percent-scans-2021\",\"top-5000-it-scans-2021\",\"top-10000-it-scans-2021\",\"top-50000-it-scans-2021\",\"top-100000-it-scans-2021\",\"top-country-it-scans-2021\",\"top-500-ae-scans-2021\",\"top-1000-ae-scans-2021\",\"top-5000-ae-scans-2021\",\"top-10000-ae-scans-2021\",\"top-50000-ae-scans-2021\",\"top-100000-ae-scans-2021\",\"top-5000-us-scans-2021\",\"top-10000-us-scans-2021\",\"top-50000-us-scans-2021\",\"top-100000-us-scans-2021\",\"top-500-in-scans-2021\",\"top-1000-in-scans-2021\",\"top-5000-in-scans-2021\",\"top-10000-in-scans-2021\",\"top-50000-in-scans-2021\",\"top-100000-in-scans-2021\"],\"product_name\":\"Thai peanut noodle kit includes stir-fry rice noodles & thai peanut seasoning\",\"product_name_en\":\"Thai peanut noodle kit includes stir-fry rice noodles & thai peanut seasoning\",\"product_name_en_debug_tags\":[],\"product_name_en_imported\":\"Thai peanut noodle kit includes stir-fry rice noodles & thai peanut seasoning\",\"product_quantity\":\"155\",\"purchase_places\":\"\",\"purchase_places_debug_tags\":[],\"purchase_places_tags\":[],\"quantity\":\"155 g\",\"quantity_debug_tags\":[],\"removed_countries_tags\":[],\"rev\":20,\"scans_n\":6,\"selected_images\":{\"front\":{\"display\":{\"en\":\"https://images.openfoodfacts.org/images/products/073/762/806/4502/front_en.6.400.jpg\"},\"small\":{\"en\":\"https://images.openfoodfacts.org/images/products/073/762/806/4502/front_en.6.200.jpg\"},\"thumb\":{\"en\":\"https://images.openfoodfacts.org/images/products/073/762/806/4502/front_en.6.100.jpg\"}},\"ingredients\":{\"display\":{\"en\":\"https://images.openfoodfacts.org/images/products/073/762/806/4502/ingredients_en.10.400.jpg\"},\"small\":{\"en\":\"https://images.openfoodfacts.org/images/products/073/762/806/4502/ingredients_en.10.200.jpg\"},\"thumb\":{\"en\":\"https://images.openfoodfacts.org/images/products/073/762/806/4502/ingredients_en.10.100.jpg\"}},\"nutrition\":{\"display\":{\"en\":\"https://images.openfoodfacts.org/images/products/073/762/806/4502/nutrition_en.12.400.jpg\"},\"small\":{\"en\":\"https://images.openfoodfacts.org/images/products/073/762/806/4502/nutrition_en.12.200.jpg\"},\"thumb\":{\"en\":\"https://images.openfoodfacts.org/images/products/073/762/806/4502/nutrition_en.12.100.jpg\"}}},\"serving_quantity\":\"52\",\"serving_size\":\"0.333 PACKAGE (52 g)\",\"serving_size_debug_tags\":[],\"serving_size_imported\":\"0.333 PACKAGE (52 g)\",\"sortkey\":1587581231,\"sources\":[{\"fields\":[\"brands\",\"countries\"],\"id\":\"usda-ndb\",\"images\":[],\"import_t\":1489067409,\"url\":\"https://api.nal.usda.gov/ndb/reports/?ndbno=45108002&type=f&format=json&api_key=DEMO_KEY\"},{\"fields\":[\"product_name_en\",\"categories\",\"brand_owner\",\"data_sources\",\"nutrition_data_per\",\"serving_size\",\"ingredients_text_en\",\"nutrients.calcium_unit\",\"nutrients.calcium_value\",\"nutrients.carbohydrates_value\",\"nutrients.cholesterol_unit\",\"nutrients.cholesterol_value\",\"nutrients.energy_value\",\"nutrients.energy-kcal_value\",\"nutrients.fat_value\",\"nutrients.fiber_value\",\"nutrients.iron_unit\",\"nutrients.iron_value\",\"nutrients.proteins_value\",\"nutrients.salt_unit\",\"nutrients.salt_value\",\"nutrients.saturated-fat_value\",\"nutrients.sugars_value\",\"nutrients.trans-fat_unit\",\"nutrients.trans-fat_value\",\"nutrients.vitamin-a_unit\",\"nutrients.vitamin-a_value\",\"nutrients.vitamin-c_unit\",\"nutrients.vitamin-c_value\"],\"id\":\"database-usda\",\"images\":[],\"import_t\":1587581231,\"manufacturer\":null,\"name\":\"database-usda\",\"url\":null}],\"sources_fields\":{\"org-database-usda\":{\"available_date\":\"2019-09-25T00:00:00Z\",\"fdc_category\":\"All Noodles\",\"fdc_data_source\":\"LI\",\"fdc_id\":\"628190\",\"modified_date\":\"2019-09-25T00:00:00Z\",\"publication_date\":\"2019-12-06T00:00:00Z\"}},\"states\":\"en:to-be-completed, en:nutrition-facts-completed, en:ingredients-completed, en:expiration-date-to-be-completed, en:packaging-code-to-be-completed, en:characteristics-completed, en:origins-completed, en:categories-completed, en:brands-completed, en:packaging-completed, en:quantity-completed, en:product-name-completed, en:photos-to-be-validated, en:packaging-photo-to-be-selected, en:nutrition-photo-selected, en:ingredients-photo-selected, en:front-photo-selected, en:photos-uploaded\",\"states_hierarchy\":[\"en:to-be-completed\",\"en:nutrition-facts-completed\",\"en:ingredients-completed\",\"en:expiration-date-to-be-completed\",\"en:packaging-code-to-be-completed\",\"en:characteristics-completed\",\"en:origins-completed\",\"en:categories-completed\",\"en:brands-completed\",\"en:packaging-completed\",\"en:quantity-completed\",\"en:product-name-completed\",\"en:photos-to-be-validated\",\"en:packaging-photo-to-be-selected\",\"en:nutrition-photo-selected\",\"en:ingredients-photo-selected\",\"en:front-photo-selected\",\"en:photos-uploaded\"],\"states_tags\":[\"en:to-be-completed\",\"en:nutrition-facts-completed\",\"en:ingredients-completed\",\"en:expiration-date-to-be-completed\",\"en:packaging-code-to-be-completed\",\"en:characteristics-completed\",\"en:origins-completed\",\"en:categories-completed\",\"en:brands-completed\",\"en:packaging-completed\",\"en:quantity-completed\",\"en:product-name-completed\",\"en:photos-to-be-validated\",\"en:packaging-photo-to-be-selected\",\"en:nutrition-photo-selected\",\"en:ingredients-photo-selected\",\"en:front-photo-selected\",\"en:photos-uploaded\"],\"stores\":\"\",\"stores_debug_tags\":[],\"stores_tags\":[],\"traces\":\"en:peanuts\",\"traces_debug_tags\":[],\"traces_from_ingredients\":\"\",\"traces_from_user\":\"(en) en:peanuts\",\"traces_hierarchy\":[\"en:peanuts\"],\"traces_tags\":[\"en:peanuts\"],\"unique_scans_n\":5,\"unknown_ingredients_n\":2,\"unknown_nutrients_tags\":[],\"update_key\":\"ing20220322\",\"vitamins_prev_tags\":[],\"vitamins_tags\":[]},\"status\":1,\"status_verbose\":\"product found\"}";


    private void pullInformation(String barcode) {
        // The product is always present if this activity is launched
        if (barcode == null)
            barcode = BASE;
        Product.of(barcode).ifPresent(product -> {
            this.p = product;
            productString = p.getJsonString();
            productName.set(product.getName());
            int value = product.getKCalEnergy();
            energy.set(value < 0 ? "Unknown" : Integer.toString(value));

            ImageView imageNutriScore = findViewById(R.id.imageNutriscore);
            int imageRes = product.getNutriscore().getRes();
            imageNutriScore.setImageDrawable(AppCompatResources.getDrawable(getApplicationContext(),
                    imageRes));
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SignedInFragment.SetMode(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_information);

        Intent intent = getIntent();
        String barcode = intent.getStringExtra(EXTRA_MESSAGE);

        pullInformation(barcode);

        // Load the image of the product into the view
        String imageUrl = p.getImageURL();
        Glide.with(getApplicationContext())
                .asBitmap()
                .load(imageUrl)
                .into((ImageView) findViewById(R.id.pImage));

        // Get the authenticated user if any
        user = FirebaseAuth.getInstance().getCurrentUser();

        TextView pName = findViewById(R.id.pName);
        pName.setText(productName.get());

        TextView pEnergy = findViewById(R.id.pEnergy);
        pEnergy.setText(energy.get());

        if (p != null) {
            TextView pGenericName = findViewById(R.id.pGenericName);
            pGenericName.setText(p.getGenericName());
            LinearLayout parentLayout = findViewById(R.id.informationLayout);

            for (Product.Nutriments nutriments : Product.Nutriments.values()) {

                double serving = p.getNutrimentServing(nutriments);
                if (serving > 0) {
                    // Create new layout for the new fields
                    LinearLayout linearLayout = new LinearLayout(this);
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    parentLayout.addView(linearLayout);

                    // Get the text of the nutriment
                    TextView textView1 = new TextView(this);
                    String name = nutriments.getName();
                    String nameToDisplay = name.substring(0, 1).toUpperCase() + name.substring(1);
                    textView1.setText(String.format("%s:", nameToDisplay));
                    textView1.setTextSize(20);

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    // Trick to change dp to px as this function takes in pixels as arguments
                    params.setMargins((int) (10 * Resources.getSystem().getDisplayMetrics().density), 0, 0, 0);

                    textView1.setLayoutParams(params);

                    linearLayout.addView(textView1);

                    TextView textView2 = new TextView(this);
                    textView2.setText(String.format(
                            Double.toString(p.getNutrimentServing(nutriments)), Locale.ENGLISH));
                    textView2.setTextSize(20);
                    textView2.setLayoutParams(params);
                    // Set the tag of the textview to find it later
                    textView2.setTag(nutriments.getName());

                    linearLayout.addView(textView2);

                    // Create the switch
                    SwitchCompat s = new SwitchCompat(this);
                    s.setText("");
                    s.setTag(nutriments.getName() + "_switch");
                    LinearLayout.LayoutParams switchParams = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            1
                    );
                    s.setPaddingRelative(0, 0, (int) (10 * Resources.getSystem().getDisplayMetrics().density), 0);
                    s.setLayoutParams(switchParams);
                    s.setChecked(true);

                    // If the user is logged in, allow the choice
                    if (user != null)
                        linearLayout.addView(s);
                }
            }
        }

        // Check if a user is logged in and allow them to save the calorie counter
        if (user != null) {
            findViewById(R.id.incr_button).setVisibility(View.VISIBLE);
            findViewById(R.id.incr_button2).setVisibility(View.VISIBLE);
            findViewById(R.id.quantity_name).setVisibility(View.VISIBLE);
            findViewById(R.id.quantity_name2).setVisibility(View.VISIBLE);
            findViewById(R.id.add_to_counter_button).setVisibility(View.VISIBLE);
        }

        // If the text is clicked, start more information activity
        TextView tv = findViewById(R.id.showAdditionalInfo);
        tv.setOnClickListener(v -> {
            Intent newIntent = new Intent(this, AdditionalProductInformationActivity.class);
            newIntent.putExtra(EXTRA_MESSAGE, productString);
            startActivity(newIntent);
        });
    }

    private void changeValue(boolean divide) {
        TextView textView = findViewById(R.id.pEnergy);
        TextView counter = findViewById(R.id.quantity_name2);
        String calorieText = textView.getText().toString();
        double newCalorie = 0.0;
        int quantity = Integer.parseInt(counter.getText().toString());
        if (Integer.parseInt(counter.getText().toString()) > 1 && divide) {
            newCalorie = Double.parseDouble(calorieText) / 2.0;
            counter.setText(String.format(Integer.toString(quantity - 1), Locale.ENGLISH));
            String newText = String.format(Double.toString(newCalorie), Locale.ENGLISH);
            textView.setText(newText);
            for (Product.Nutriments nutriments : Product.Nutriments.values()) {
                double serving = p.getNutrimentServing(nutriments);
                if (serving > 0) {
                    textView = findViewById(R.id.informationLayout).findViewWithTag(nutriments.getName());
                    calorieText = textView.getText().toString();
                    newCalorie = Double.parseDouble(calorieText) / 2.0;
                    textView.setText(String.format(Double.toString(newCalorie), Locale.ENGLISH));
                }
            }
        } else if (!divide) {
            newCalorie = 2.0 * Double.parseDouble(calorieText);
            counter.setText(String.format(Integer.toString(quantity + 1), Locale.ENGLISH));
            String newText = String.format(Double.toString(newCalorie), Locale.ENGLISH);
            textView.setText(newText);
            for (Product.Nutriments nutriments : Product.Nutriments.values()) {
                double serving = p.getNutrimentServing(nutriments);
                if (serving > 0) {
                    textView = findViewById(R.id.informationLayout).findViewWithTag(nutriments.getName());
                    calorieText = textView.getText().toString();
                    newCalorie = Double.parseDouble(calorieText) * 2.0;
                    textView.setText(String.format(Double.toString(newCalorie), Locale.ENGLISH));
                }
            }
        }

    }

    public void increment(View view) {
        changeValue(false);
    }

    public void decrement(View view) {
        changeValue(true);
    }

    public void addToUser(View view) {
        Database db = new Database();
        TextView textView = findViewById(R.id.pEnergy);
        double calorie = Double.parseDouble(textView.getText().toString());
        if (((Switch) findViewById(R.id.pEnergyUnitSwitch)).isChecked())
            db.addCalorie(user.getUid(), (int) calorie);

        for (Product.Nutriments nutriments : Product.Nutriments.values()) {
            double serving = p.getNutrimentServing(nutriments);
            if (serving > 0) {
                if (((SwitchCompat) findViewById(R.id.informationLayout).findViewWithTag(nutriments.getName() + "_switch")).isChecked())
                    db.addNutrimentField(user.getUid(), nutriments, serving);
            }
        }

        db.addProduct(user.getUid(), p.getCode());
        addPoints();

        Toast t = Toast.makeText(getApplicationContext(), "Information saved on the profile !", Toast.LENGTH_SHORT);
        t.show();
    }

    /**
     * Add healthPoints to the user
     */
    private void addPoints() {
        Database db = new Database();
        int score = 10;
        switch (p.getNutriscore().getScore()) {
            case 0:
                score *= 10;
                break;
            case 1:
                score *= 5;
                break;
            case 2:
                score *= 3;
            case 3:
                score *= 2;
                break;
        }
        db.addHealthPoint(user.getUid(), 4 * p.getNutriscore().getScore());
    }
}
