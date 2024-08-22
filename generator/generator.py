from PIL import Image
import shutil
import json
import os

CONFIG_PATH = 'generator_config.json'
GENERATOR_ASSETS = 'generator_assets'
MOD_ID = 'zoomer_bows'
TMP_FOLDER = 'generated'
GENERATED_FOLDER = f'{TMP_FOLDER}/{MOD_ID}'
BASE_MODEL_ITEM_FILE = 'base_model_item.json'
BASE_MODEL_ITEM_PATH = f'{GENERATOR_ASSETS}/{BASE_MODEL_ITEM_FILE}'

TEXTURE_ITEMS_FOLDER = f'{GENERATED_FOLDER}/assets/{MOD_ID}/textures/item'
LANG_FOLDER = f'{GENERATED_FOLDER}/assets/{MOD_ID}/lang'
RECIPES_PATH = f'{GENERATED_FOLDER}/data/{MOD_ID}/recipes'

MODELS_FOLDER = TEXTURE_ITEMS_FOLDER.replace('textures', 'models')

def get_model_bow_sec(mod_id, item_id, index):
    return {
        "parent": f"{mod_id}:item/{item_id}",
        "textures": {
            "layer0": f"{mod_id}:item/{item_id}{index}"
        }
    }

def new_bow(material_name, bow_name, filename):
    recipe = {
        "type": "minecraft:crafting_shaped",
        "pattern": [
            " rs",
            "r s",
            " rs"
        ],
        "key": {
            "s": {
                "item": "minecraft:string"
            },
            "r": {
                "item": material_name
            }
        },
        "result": {
            "item": bow_name,
            "count": 1
        }
    }

    with open(filename, 'w+') as f:
        f.write(json.dumps(recipe, indent=4))

def new_zoomers_bow(material_source_mod_id, material_source_item_id, bow_prefix):
    new_bow(f'{material_source_mod_id}:{material_source_item_id}', f'zoomer_bows:{bow_prefix}_bow', f'{RECIPES_PATH}/{bow_prefix}_bow.json')

def copy_tree(src, dst):
    try:
        if not os.path.exists(dst):
            os.makedirs(dst)
        
        for item in os.listdir(src):
            s = os.path.join(src, item)
            d = os.path.join(dst, item)
            
            if os.path.isdir(s):
                shutil.copytree(s, d, dirs_exist_ok=True, copy_function=shutil.copy2)
            else:
                shutil.copy2(s, d)
    except Exception as e:
        print(f"An error occurred: {e}")

def remove_file(file_path):
    try:
        os.remove(file_path)
    except Exception:
        pass

def create_folder(folder_path):
    try:
        os.makedirs(folder_path, exist_ok=True)
    except Exception as e:
        pass

def combine_images(image_paths, output_path):
    if not image_paths:
        raise ValueError("No image paths provided.")
    images = [Image.open(path).convert("RGBA") for path in image_paths]
    base_size = images[0].size
    for img in images[1:]:
        if img.size != base_size:
            raise ValueError("All images must be the same size.")
    combined_image = Image.new("RGBA", base_size, (0, 0, 0, 0))
    for img in images:
        combined_image = Image.alpha_composite(combined_image, img)
    combined_image.save(output_path, "PNG")

def apply_rgb_tint(image_path, tint_color):
    img = Image.open(image_path).convert("RGBA")
    r, g, b, a = img.split()
    
    r = r.point(lambda p: p * tint_color[0] / 255)
    g = g.point(lambda p: p * tint_color[1] / 255)
    b = b.point(lambda p: p * tint_color[2] / 255)
    
    tinted_img = Image.merge("RGBA", (r, g, b, a))
    
    return tinted_img

def tint_image(image_path, tint_color, result_path):
    tinted_image = apply_rgb_tint(image_path, tint_color)
    tinted_image.save(result_path)

create_folder(TEXTURE_ITEMS_FOLDER)
create_folder(MODELS_FOLDER)
create_folder(LANG_FOLDER)
create_folder(RECIPES_PATH)

LANG_FILE_PATH = f'{LANG_FOLDER}/en_us.json'
lang_data = {
    "itemGroup.zoomer_bows": "Zoomer Bows",
    "category.zoomer_bows": "Zoomer Bows"
}

with open(CONFIG_PATH, 'r') as f:
    config_data = json.loads(f.read())
    for item_id in config_data:
        item_data = config_data.get(item_id)
        rgb_tint = (item_data.get('r'), item_data.get('g'), item_data.get('b'))
        lang_name = item_data.get('lang_name')

        lang_data[f'item.{MOD_ID}.{item_id}'] = lang_name

        for img_index in range(1,5):
            TMP_IMG = f'b_{item_id}.png'
            index_img_str = str(img_index)
            new_image_path = f'{item_id}{img_index}.png'
            if img_index==1:
                new_image_path = f'{item_id}.png'

            final_new_image_path = f'{TEXTURE_ITEMS_FOLDER}/{new_image_path}'

            tint_image(f'{GENERATOR_ASSETS}/bow_base_1.png', rgb_tint,TMP_IMG)
            combine_images([f'{GENERATOR_ASSETS}/string_{index_img_str}.png', TMP_IMG, f'{GENERATOR_ASSETS}/arrow_{index_img_str}.png',], final_new_image_path)
            remove_file(TMP_IMG)

        with open(BASE_MODEL_ITEM_PATH,'r') as bmi_f:
            bmi_data = bmi_f.read()
            with open(f'{MODELS_FOLDER}/{item_id}.json', 'w+') as mf_f:
                mf_f.write(bmi_data.replace('ITEM_ID_REPLACE', item_id).replace('MOD_ID_REPLACE', MOD_ID))
            
        for model_index in range(2,5):
            with open(f'{MODELS_FOLDER}/{item_id}{model_index}.json', 'w+') as mi_f:
                mi_f.write(json.dumps(get_model_bow_sec(MOD_ID, item_id, model_index), indent=4))

with open(LANG_FILE_PATH, 'w+') as f:
    f.write(json.dumps(lang_data,indent=4))


# Generate recipes

#overworld
new_zoomers_bow('minecraft', 'iron_ingot', 'iron')
new_zoomers_bow('minecraft', 'diamond', 'diamond')

#blue skies
new_zoomers_bow('blue_skies', 'pyrope_gem', 'pyrope')
new_zoomers_bow('blue_skies', 'aquite', 'aquite')
new_zoomers_bow('blue_skies', 'diopside_gem', 'diopside')
new_zoomers_bow('blue_skies', 'charoite', 'charoite')
new_zoomers_bow('blue_skies', 'horizonite_ingot', 'horizonite')

# Aether
new_zoomers_bow('aether', 'zanite_gemstone', 'zanite')
new_zoomers_bow('aether', 'enchanted_gravitite', 'gravitite')
new_zoomers_bow('aether', 'valkyrie_boots', 'valkyrie')

# Nether
new_zoomers_bow('betternether', 'cincinnasite_ingot', 'cincinnasite')
new_zoomers_bow('kubejs', 'cincinnasite_diamond_boots', 'cincinnasite_diamond')
new_zoomers_bow('betternether', 'nether_ruby', 'nether_ruby')
new_zoomers_bow('betternether', 'flaming_ruby_boots', 'fire_ruby')
new_zoomers_bow('minecraft', 'netherite_ingot', 'netherite')

# Undergarden
new_zoomers_bow('undergarden', 'cloggrum_ingot', 'cloggrum')
new_zoomers_bow('undergarden', 'froststeel_ingot', 'froststeel')
new_zoomers_bow('undergarden', 'utherium_crystal', 'utherium')
new_zoomers_bow('undergarden', 'forgotten_ingot', 'forgotten')

# End
new_zoomers_bow('betterend', 'aeternium_ingot', 'aeternium')
new_zoomers_bow('betterend', 'thallasium_ingot', 'thallasium')
new_zoomers_bow('betterend', 'terminite_ingot', 'terminite')

# Theabyss
new_zoomers_bow('theabyss', 'fixed_bone', 'bone')
new_zoomers_bow('theabyss', 'garnite_ingot', 'garnite')
new_zoomers_bow('theabyss', 'ignisithe_gem', 'ignisithe')
new_zoomers_bow('theabyss', 'fusion_ingot', 'fusion')
new_zoomers_bow('theabyss', 'aberythe_gem', 'aberythe')
new_zoomers_bow('theabyss', 'unorithe_ingot', 'unorithe')
new_zoomers_bow('theabyss', 'phantom_ingot', 'phantom')
new_zoomers_bow('theabyss', 'incorythe_gem', 'incorythe')



# Sync files
destination_folder = os.path.abspath(os.path.join(os.path.dirname(__file__), '../src/main/resources'))
copy_tree(GENERATED_FOLDER, destination_folder)
shutil.rmtree(TMP_FOLDER)
