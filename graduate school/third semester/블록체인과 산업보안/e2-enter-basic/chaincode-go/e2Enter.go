/*
SPDX-License-Identifier: Apache-2.0
*/

package main

import (
	"log"

	"github.com/hyperledger/fabric-contract-api-go/contractapi"
	"github.com/hyperledger/fabric-samples/asset-transfer-basic/chaincode-go/chaincode"
)

func main() {
	e2EnterChaincode, err := contractapi.NewChaincode(&chaincode.SmartContract{})
	if err != nil {
		log.Panicf("Error creating e2-enter-basic chaincode: %v", err)
	}

	if err := e2EnterChaincode.Start(); err != nil {
		log.Panicf("Error starting e2-enter-basic chaincode: %v", err)
	}
}
