package chaincode

import (
	"encoding/json"
	"fmt"

	"github.com/hyperledger/fabric-contract-api-go/contractapi"
)

// Civil describe basic details of what makes up a simple human

func (s *SmartContract) CreateCivil(ctx contractapi.TransactionContextInterface, id string, name string, phonenumber string, address string, status string) error {
	exists, err := s.StateExists(ctx, id)
	if err != nil {
		return err
	}
	if exists {
		return fmt.Errorf("the civil %s alreay exists", id)
	}

	civil := Civil{
		ID:             id,
		Name:           name,
		PhoneNumber:    phonenumber,
		Address:        address,
		Status:         status,
	}
	civilJSON, err := json.Marshal(civil)
	if err != nil {
		return err
	}

	return ctx.GetStub().PutState(id, civilJSON)
}

func (s *SmartContract) ReadCivil(ctx contractapi.TransactionContextInterface, id string) (*Civil, error) {
	civilJSON, err := ctx.GetStub().GetState(id)
	if err != nil {
		return nil, fmt.Errorf("failed to read from world state: %v", err)
	}
	if civilJSON == nil {
		return nil, fmt.Errorf("the civil %s does not exist", id)
	}

	var civil Civil
	err = json.Unmarshal(civilJSON, &civil)
	if err != nil {
		return nil, err
	}

	return &civil, nil
}

func (s *SmartContract) UpdateCivil(ctx contractapi.TransactionContextInterface, id string, name string, phonenumber string, address string, status string) error {
	exists, err := s.StateExists(ctx, id)
	if err != nil {
		return err
	}
	if !exists {
		return fmt.Errorf("the civil %s does not exist", id)
	}

	// overwriting original civil with new civil
	civil := Civil{
		ID:             id,
		Name:           name,
		PhoneNumber:    phonenumber,
		Address:        address,
		Status:         status,
	}
	civilJSON, err := json.Marshal(civil)
	if err != nil {
		return err
	}

	return ctx.GetStub().PutState(id, civilJSON)
}

func (s *SmartContract) GetAllCivils(ctx contractapi.TransactionContextInterface) ([]*Civil, error) {
	// range query with empty string for startKey and endKey does an
	// open-ended query of all civils in the chaincode namespace.
	resultsIterator, err := ctx.GetStub().GetStateByRange("civil", "")
	if err != nil {
		return nil, err
	}
	defer resultsIterator.Close()

	var civils []*Civil
	for resultsIterator.HasNext() {
		queryResponse, err := resultsIterator.Next()
		if err != nil {
			return nil, err
		}

		var civil Civil 
		err = json.Unmarshal(queryResponse.Value, &civil)
		if err != nil {
			return nil, err
		}
		civils = append(civils, &civil)
	}

	return civils, nil
}
