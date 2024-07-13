const fs = require('fs');
const config = require('./generator_config.json');
const sharp = requireGlobal('sharp');

const names = config.names;
const ids = config.ids;
const tints = config.tints;

const langFilePath = './lang/en_us.json';

function writeLangFile() {
    let langFileContent = {}
    for (let bowIndex = 0; bowIndex < ids.length; bowIndex++) {
        const id = ids[bowIndex];
        const name = names[bowIndex];
        langFileContent[`item.zoomer_bows.${id}`] = name;
    }
    langFileContent["itemGroup.zoomer_bows"] = "Zoomer Bows";
    langFileContent["category.zoomer_bows"] = "Zoomer Bows";
    let jsonContent = JSON.stringify(langFileContent, null, 4);
    fs.writeFileSync(langFilePath, jsonContent);
}

function writeItemTextures() {
    const textureFolder = './textures/item/';
    const templatePaths = ["iron_bow.png", "iron_bow2.png", "iron_bow3.png", "iron_bow4.png"];
    for (let bowIndex = 1; bowIndex < ids.length; bowIndex++) {
        const id = ids[bowIndex];
        for (const templatePath of templatePaths) {
            const templatePathFull = textureFolder + templatePath;
            const resultPath = textureFolder + templatePath.replace('iron_bow', id);
            const tint = tints[bowIndex];
            const finalTint = {r: tint.r * 255, g: tint.g * 255, b: tint.b * 255}
            sharp(templatePathFull)
                .tint(finalTint)
                .toFile(resultPath, (err, info) => {
                    if (err) {
                        console.error(err);
                    }
                });
        }
    }
}

function writeItemModels() {
    const modelFolder = './models/item/';
    const rootPath = "iron_bow.json";
    const modelPaths = ["iron_bow2.json", "iron_bow3.json", "iron_bow4.json"];
    for (let bowIndex = 0; bowIndex < ids.length; bowIndex++) {
        const id = ids[bowIndex];

        const rootPathFull = modelFolder + rootPath;
        const rootFileContent = fs.readFileSync(rootPathFull, 'utf8');
        const rootFileJson = JSON.parse(rootFileContent);
        rootFileJson.textures.layer0 = `zoomer_bows:item/${id}`;
        rootFileJson.overrides[0].model = `zoomer_bows:item/${id}2`;
        rootFileJson.overrides[1].model = `zoomer_bows:item/${id}3`;
        rootFileJson.overrides[2].model = `zoomer_bows:item/${id}4`;
        const resultPathRoot = modelFolder + rootPath.replace('iron_bow', id);
        let jsonContentRoot = JSON.stringify(rootFileJson, null, 4);
        fs.writeFileSync(resultPathRoot, jsonContentRoot);

        for (const modelPath of modelPaths) {
            const modelPathFull = modelFolder + modelPath;
            const modelFileContent = fs.readFileSync(modelPathFull, 'utf8');
            const modelFileJson = JSON.parse(modelFileContent);
            const indexAffix = modelPaths.indexOf(modelPath) + 2;
            modelFileJson.parent = `zoomer_bows:item/${id}`;
            modelFileJson.textures.layer0 = `zoomer_bows:item/${id}` + indexAffix;
            const resultPath = modelFolder + modelPath.replace('iron_bow', id);
            let jsonContent = JSON.stringify(modelFileJson, null, 4);
            fs.writeFileSync(resultPath, jsonContent);
        }
    }
}

function tintImages() {

}

function requireGlobal(packageName) {
    var childProcess = require('child_process');
    var path = require('path');
    var fs = require('fs');

    var globalNodeModules = childProcess.execSync('npm root -g').toString().trim();
    var packageDir = path.join(globalNodeModules, packageName);
    if (!fs.existsSync(packageDir))
        packageDir = path.join(globalNodeModules, 'npm/node_modules', packageName); //find package required by old npm

    if (!fs.existsSync(packageDir))
        throw new Error('Cannot find global module \'' + packageName + '\'');

    var packageMeta = JSON.parse(fs.readFileSync(path.join(packageDir, 'package.json')).toString());
    var main = path.join(packageDir, packageMeta.main);

    return require(main);
}

writeLangFile();
writeItemTextures();
writeItemModels();