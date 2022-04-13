package chaincode

import (
	"encoding/json"
	"fmt"

	"github.com/hyperledger/fabric-contract-api-go/contractapi"
)

// Shop is describe basic information of shop

func (s *SmartContract) CreateShop(ctx contractapi.TransactionContextInterface, id string, name string, telephone string, address string) error {
	exists, err := s.StateExists(ctx, id)
	if err != nil {
		return err
	}
	if exists {
		return fmt.Errorf("the shop %s alreay exists", id)
	}

	shop := Shop{
		ID:             id,
		Name:           name,
		Telephone:      telephone,
		Address:        address,
	}
	shopJSON, err := json.Marshal(shop)
	if err != nil {
		return err
	}

	return ctx.GetStub().PutState(id, shopJSON)
}

func (s *SmartContract) ReadShop(ctx contractapi.TransactionContextInterface, id string) (*Shop, error) {
	shopJSON, err := ctx.GetStub().GetState(id)
	if err != nil {
		return nil, fmt.Errorf("failed to read from world state: %v", err)
	}
	if shopJSON == nil {
		return nil, fmt.Errorf("the shop %s does not exist", id)
	}

	var shop Shop
	err = json.Unmarshal(shopJSON, &shop)
	if err != nil {
		return nil, err
	}

	return &shop, nil
}

func (s *SmartContract) UpdateShop(ctx contractapi.TransactionContextInterface, id string, name string, telephone string, address string) error {
	exists, err := s.StateExists(ctx, id)
	if err != nil {
		return err
	}
	if !exists {
		return fmt.Errorf("the shop %s does not exist", id)
	}

	// overwriting original shop with new shop
	shop := Shop{
		ID:             id,
		Name:           name,
		Telephone:      telephone,
		Address:        address,
	}
	shopJSON, err := json.Marshal(shop)
	if err != nil {
		return err
	}

	return ctx.GetStub().PutState(id, shopJSON)
}

func (s *SmartContract) GetAllShops(ctx contractapi.TransactionContextInterface) ([]*Shop, error) {
	// range query with empty string for startKey and endKey does an
	// open-ended query of all civils in the chaincode namespace.
	resultsIterator, err := ctx.GetStub().GetStateByRange("shop", "")
	if err != nil {
		return nil, err
	}
	defer resultsIterator.Close()

	var shops []*Shop
	for resultsIterator.HasNext() {
		queryResponse, err := resultsIterator.Next()
		if err != nil {
			return nil, err
		}

		var shop Shop
		err = json.Unmarshal(queryResponse.Value, &shop)
		if err != nil {
			return nil, err
		}
		shops = append(shops, &shop)
	}

	return shops, nil
}
