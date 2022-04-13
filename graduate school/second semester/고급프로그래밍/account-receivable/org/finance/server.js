// ExpressJS Setup
const path = require('path');
const express = require('express');
const app = express();
var bodyParser = require('body-parser');

// Hyperledger Bridge Setup
const { Wallets, Gateway } = require('fabric-network');
const fs = require('fs');

// load the network configuration
const ccpPath = path.resolve('/home/apstudent', 'fabric-samples', 'test-network', 'organizations', 'peerOrganizations', 'finance.example.com', 'connection-finance.json');
const ccp = JSON.parse(fs.readFileSync(ccpPath, 'utf8'));


// Constants
const PORT = 9090;
const HOST = '0.0.0.0';

// server start
app.listen(PORT, HOST);
console.log(`Running on http://${HOST}:${PORT}`);

// use static file
app.use(express.static(path.join(__dirname)));

// configure app to use body-parser
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));

// main page routing
app.get('/', function(req, res){
    res.sendFile(__dirname + '/viewpage/index.html');
})
app.get('/page/createReqPurchase', function(req, res){
    res.sendFile(__dirname + '/viewpage/createReqPurchase.html');
})
app.get('/page/checkBuyRecv', function(req, res){    
    res.sendFile(__dirname + '/viewpage/checkBuyRecv.html');
})
app.get('/page/reqRecvEval', function(req, res){    
    res.sendFile(__dirname + '/viewpage/reqRecvEval.html');
})
app.get('/page/updateSale', function(req, res){    
    res.sendFile(__dirname + '/viewpage/updateSale.html');
})
app.get('/page/queryAllCars', function(req, res){    
    res.sendFile(__dirname + '/viewpage/queryAllCars.html');
})
app.get('/page/queryCar', function(req, res){    
    res.sendFile(__dirname + '/viewpage/queryCar.html');
})
app.get('/page/createCar', function(req, res){    
    res.sendFile(__dirname + '/viewpage/createCar.html');
})
app.get('/page/changeCarOwner', function(req, res){    
    res.sendFile(__dirname + '/viewpage/changeCarOwner.html');
})

// api routing
app.get('/api/querySaleRecvs', async function(req, res){
    const result = await callChainCode('querySaleRecvs')
    res.json(JSON.parse(result))
})
app.post('/api/createReqPurchase', async function(req, res){
    const reqcno = req.body.reqcno
    const recvno = req.body.recvno
    const reqorg = req.body.reqorg
    const reqprice = req.body.reqprice
    const reqdate = req.body.reqdate
    const reqstate = req.body.reqstate

    console.log(reqcno+' '+recvno+' '+reqorg+' '+reqprice+' '+reqdate+' '+reqstate);
    var args = [reqcno, recvno, reqorg, reqprice, reqdate, reqstate]
    await callChainCode('createReqPurchase', args)
    res.status(200).json({result: "success"})
})
app.post('/api/reqRecvEval', async function(req, res){
    const evalnum = req.body.evalnum
    const gradename = req.body.gradename
    const recvnum = req.body.recvnum

    var args = [evalnum,gradename,recvnum]    
    await callChainCode('ReqRecvEval',args)    
    res.status(200).json({result: "success"})
})
app.get('/api/checkBuyRecv', async function(req, res){
    const result = await callChainCode('CheckBuyRecv')        
    res.json(JSON.parse(result))    
})
app.post('/api/updateSale', async function(req, res){
    const recvnum = req.body.recvnum
    const recvisslae = req.body.recvisslae

    var args = [recvnum,recvisslae]    
    await callChainCode('UpdateSale',args)    
    res.status(200).json({result: "success"})
})
app.get('/api/queryAllCars', async function(req, res){
    const result = await callChainCode('queryAllCars')        
    res.json(JSON.parse(result))    
})

app.post('/api/queryCar', async function(req, res){
    const carno=req.body.carno
    const result = await callChainCode('queryCar',carno)    
    res.json(JSON.parse(result))
})

app.post('/api/createCar', async function(req, res){
    const carno = req.body.carno
    const carmake = req.body.carmake
    const carmodel = req.body.carmodel
    const carcol = req.body.carcol
    const carowner = req.body.carowner

    var args = [carno,carmake,carmodel,carcol,carowner]    
    await callChainCode('createCar',args)    
    res.status(200).json({result: "success"})
})

app.post('/api/changeCarOwner', async function(req, res){
    const carno = req.body.carno
    const carowner = req.body.carowner

    var args = [carno,carowner]    
    await callChainCode('changeCarOwner',args)    
    res.status(200).json({result: "success"})
})

async function callChainCode(fnName, args){
    
    // Create a new file system based wallet for managing identities.
    const walletPath = path.join(process.cwd(), 'wallet');
    const wallet = await Wallets.newFileSystemWallet(walletPath);
    var result;
    console.log(`Wallet path: ${walletPath}`);
    

    // Check to see if we've already enrolled the user.
    const identity = await wallet.get('appUser');
    if (!identity) {
        console.log('An identity for the user "appUser" does not exist in the wallet');
        console.log('Run the registerUser.js application before retrying');
        return;
    }

    // Create a new gateway for connecting to our peer node.
    const gateway = new Gateway();
    await gateway.connect(ccp, { wallet, identity: 'appUser', discovery: { enabled: true, asLocalhost: true } });
    
    // Get the network (channel) our contract is deployed to.
    const network = await gateway.getNetwork('mychannel');

    // Get the contract from the network.
    const contract = network.getContract('fabar');

    // Evaluate the specified transaction.    
    if(fnName == 'querySaleRecvs')
        result = await contract.evaluateTransaction(fnName);
    else if(fnName == 'createReqPurchase')
        result = await contract.submitTransaction(fnName,args[0],args[1],args[2],args[3],args[4],args[5]);    
    else if(fnName == 'CheckBuyRecv')
        result = await contract.evaluateTransaction(fnName)
    else if(fnName == 'ReqRecvEval')
        result = await contract.submitTransaction(fnName,args[0],args[1])
    else if(fnName == 'updateSale')
        result = await contract.submitTransaction(fnName,args[0],args[1])
    else if(fnName == 'queryCar')
        result = await contract.evaluateTransaction(fnName,args);
    else if(fnName == 'createCar')
        result = await contract.submitTransaction(fnName,args[0],args[1],args[2],args[3],args[4])
    else if(fnName == 'changeCarOwner')
        result = await contract.submitTransaction(fnName,args[0],args[1])
    else
        result = 'This function(' + fnName +') does not exist !'        
        
    console.log(`Transaction has been evaluated, result is: ${result.toString()}`);

    // Disconnect from the gateway.
    await gateway.disconnect();
    
    return result;
}
