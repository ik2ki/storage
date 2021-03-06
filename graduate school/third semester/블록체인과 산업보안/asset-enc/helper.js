/*
 * SPDX-License-Identifier: Apache-2.0
 */

'use strict';

const crypto = require('crypto');

const bytes = (s) => {
    return ~-encodeURI(s).split(/%..|./).length;
};

/**
 * Retrieve an array containing randomized UUIDs
 * @param {number} assetNumber number of random asset uuids required to be in a return array
 */
module.exports.retrieveRandomAssetIds = function(assetNumber) {
    const uuids = [...Array(assetNumber).keys()];
    // shuffle array using Fisher-Yates shuffle
    for (let i = uuids.length - 1; i > 0; i--) {
        let j = Math.floor(Math.random() * (i + 1));
        // swap elements uuids[i] and uuids[j]
        [uuids[i], uuids[j]] = [uuids[j], uuids[i]];
    }
    return uuids;
}

/**
 * Insert asset batches
 * @param {Object} bcObj the BC object
 * @param {Object} context the BC context
 * @param {Integer} clientIdx the client index
 * @param {Object} args the client arguments
 */
module.exports.addBatchAssets = async function(bcObj, context, clientIdx, args, isPrivateData = false) {
    console.log('   -> Creating assets of sizes: ', args.create_sizes);

    const testAssetNum = args.assets ? parseInt(args.assets) : 0;
    for (let index in args.create_sizes) {
        const size = args.create_sizes[index];
        console.log('   -> Creating asset set of byte size: ', size);
        const uuidBase = 'client' + clientIdx + '_' + size + '_';

        // define the asset to be created in this loop
        const baseAsset = {};
        baseAsset.docType = 'asset-enc';
        baseAsset.byteSize = size;
        baseAsset.creator = 'client' + clientIdx;
        baseAsset.uuid = uuidBase;

        // Create assets in batches, because it is faster!!
        // -Complete the asset definition
        const paddingSize = size - bytes(JSON.stringify(baseAsset));
        baseAsset.content = 'B'.repeat(paddingSize);

        // -Generate all assets
        const assets = [];
        for (let i=0; i<testAssetNum; i++) {
            const asset = {};
            asset.docType = baseAsset.docType;
            asset.byteSize = baseAsset.byteSize;
            asset.creator = baseAsset.creator;
            asset.content = baseAsset.content;
            asset.uuid = uuidBase + i;
            assets.push(asset);
        }

        // -Break into batches of (max) 50
        const batches = [];
        var idx = 0;
        while(assets.length) {
            batches[idx]=assets.splice(0,50);
            idx++;
        }

        // -Insert each batch
        for (const index in batches){
            const batch = batches[index];
            try {
                let myArgs;
                if(!isPrivateData) {
                    myArgs = {
                        contractFunction: 'createAssetsFromBatch',
                        contractArguments: [JSON.stringify(batch)]
                    };
                } else {
                    myArgs = {
                        contractFunction: 'createPrivateAssetsFromBatch',
                        contractArguments: ['50'],
                        transientMap: {content: JSON.stringify(batch)}
                    }
                }

                myArgs.contractId = 'asset-enc';
                myArgs.readOnly = false;

                await bcObj.sendRequests(myArgs);
            } catch (err) {
                console.error('Error: ', err);
                throw err;
            }
        }
    }
};

/**
 * Insert asset batches of mixed size
 * @param {Object} bcObj the BC object
 * @param {Object} context the BC context
 * @param {Integer} clientIdx the client index
 * @param {Object} args the client arguments
 */
module.exports.addMixedBatchAssets = async function(bcObj, context, clientIdx, args) {
    console.log('   -> Creating assets of byte-size: ', args.create_sizes);

    const testAssetNum = args.assets ? parseInt(args.assets) : 0;
    const uuidBase = 'client' + clientIdx + '_';

    const baseAssets = [];
    // Define base assets sizes
    for (let index in args.create_sizes) {
        const size = args.create_sizes[index];

        // define the asset to be created in this loop
        const baseAsset = {};
        baseAsset.docType = 'asset-enc';
        baseAsset.byteSize = size;
        baseAsset.creator = 'client' + clientIdx;
        baseAsset.uuid = uuidBase;

        // Create assets in batches, because it is faster!!
        // -Complete the asset definition
        const paddingSize = size - bytes(JSON.stringify(baseAsset));
        baseAsset.content = 'B'.repeat(paddingSize);

        baseAssets.push(baseAsset);
    }

    // -Generate all assets
    const assets = [];
    let idx =0;
    for (let i=0; i<testAssetNum; i++) {
        // loop over baseAssets defined above
        const asset = {};
        asset.docType = baseAssets[idx].docType;
        asset.byteSize = baseAssets[idx].byteSize;
        asset.creator = baseAssets[idx].creator;
        asset.content = baseAssets[idx].content;
        asset.uuid = uuidBase + i;
        assets.push(asset);
        idx = (idx + 1) % args.create_sizes.length;
    }

    // -Break into batches of (max) 50
    const batches = [];
    idx = 0;
    while(assets.length) {
        batches[idx]=assets.splice(0,50);
        idx++;
    }

    // -Insert each batch
    console.log('   -> Adding ' + batches.length + ' batch(es) to DB');
    for (const index in batches){
        const batch = batches[index];
        try {
            const myArgs = {
                contractId: 'asset-enc',
                contractFunction: 'createAssetsFromBatch',
                contractArguments: [JSON.stringify(batch)]
            };
            await bcObj.sendRequests(myArgs);
        } catch (err) {
            console.error('Error: ', err);
            throw err;
        }
    }
};

module.exports.addEncryptAssets = async function(bcObj, context, clientIdx, args, isPrivateData = false) {
    console.log('   -> Creating assets of sizes: ', args.create_sizes);

    const testAssetNum = args.assets ? parseInt(args.assets) : 0;
    for (let index in args.create_sizes) {
        const size = args.create_sizes[index];
        console.log('   -> Creating asset set of byte size: ', size);
        const uuidBase = 'client' + clientIdx + '_' + size + '_';

        // define the asset to be created in this loop
        const baseAsset = {};
        baseAsset.docType = 'asset-enc';
        baseAsset.byteSize = size;
        baseAsset.creator = 'client' + clientIdx;
        baseAsset.uuid = uuidBase;

        // Create assets in batches, because it is faster!!
        // -Complete the asset definition
        const paddingSize = size - bytes(JSON.stringify(baseAsset));
        baseAsset.content = 'B'.repeat(paddingSize);

        const { privateKey, publicKey } = crypto.generateKeyPairSync('rsa', {
            modulusLength: 4096,
            publicKeyEncoding: {
                type: 'pkcs1',
                format: 'pem',
            },
            privateKeyEncoding: {
                type: 'pkcs1',
                format: 'pem',
                cipher: 'aes-256-cbc',
                passphrase: '',
            },
        });
        //console.log(JSON.stringify(baseAsset)+'\n');
        //console.log(privateKey+'\n');
        //console.log(publicKey+'\n');
        let pkiArgs = {
            contractId: 'asset-enc',
            contractFunction: 'createPki',
            contractArguments: [baseAsset.uuid, privateKey, publicKey],
        };
        //console.log(JSON.stringify(pkiArgs))
        await bcObj.sendRequests(pkiArgs);
        // -Generate all assets
        const assets = [];
        for (let i=0; i<testAssetNum; i++) {
            const asset = {};
            asset.docType = baseAsset.docType;
            asset.byteSize = baseAsset.byteSize;
            asset.creator = baseAsset.creator;
            asset.content = baseAsset.content;
            asset.uuid = uuidBase + i;
            const encAsset = {};
            var encData = crypto.publicEncrypt(publicKey, Buffer.from(JSON.stringify(asset), 'utf8')).toString('base64');
            encAsset.uuid = asset.uuid;
            encAsset.endData = encData;
            assets.push(encAsset);
        }

        // -Break into batches of (max) 50
        const batches = [];
        var idx = 0;
        while(assets.length) {
            batches[idx]=assets.splice(0,50);
            idx++;
        }

        // -Insert each batch
        for (const index in batches){
            const batch = batches[index];
            try {
                let myArgs;
                if(!isPrivateData) {
                    myArgs = {
                        contractFunction: 'createAssetsFromBatch',
                        contractArguments: [JSON.stringify(batch)]
                    };
                } else {
                    myArgs = {
                        contractFunction: 'createPrivateAssetsFromBatch',
                        contractArguments: ['50'],
                        transientMap: {content: JSON.stringify(batch)}
                    }
                }

                myArgs.contractId = 'asset-enc';
                myArgs.readOnly = false;

                await bcObj.sendRequests(myArgs);
            } catch (err) {
                console.error('Error: ', err);
                throw err;
            }
        }
    }
};