import os
import json

DATA_FOLDER = 'data'
DOMAIN_FOLDER = f'{DATA_FOLDER}/zoomer_bows'
RECIPES_FOLDER = f'{DOMAIN_FOLDER}/recipes'

def generate_folder(folder_path):
    try:
        if not os.path.exists(folder_path):
            os.makedirs(folder_path)
            return True
        else:
            return False
    except Exception as e:
        return False

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
    new_bow(f'{material_source_mod_id}:{material_source_item_id}', f'zoomer_bows:{bow_prefix}_bow', f'{RECIPES_FOLDER}/{bow_prefix}_bow.json')

generate_folder(DATA_FOLDER)
generate_folder(DOMAIN_FOLDER)
generate_folder(RECIPES_FOLDER)

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

# Deeper and Darker
new_zoomers_bow('deeperdarker', 'reinforced_echo_shard', 'warden')

# Theabyss
new_zoomers_bow('theabyss', 'fixed_bone', 'bone')
new_zoomers_bow('theabyss', 'garnite_ingot', 'garnite')
new_zoomers_bow('theabyss', 'ignisithe_gem', 'ignisithe')
new_zoomers_bow('theabyss', 'fusion_ingot', 'fusion')
new_zoomers_bow('theabyss', 'aberythe_gem', 'aberythe')
new_zoomers_bow('theabyss', 'unorithe_ingot', 'unorithe')
new_zoomers_bow('theabyss', 'phantom_ingot', 'phantom')
new_zoomers_bow('theabyss', 'incorythe_gem', 'incorythe')