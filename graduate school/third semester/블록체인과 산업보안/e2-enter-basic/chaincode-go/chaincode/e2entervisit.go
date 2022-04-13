package chaincode

import (
	"encoding/json"
	"fmt"

	"github.com/hyperledger/fabric-contract-api-go/contractapi"
)

// StoreVisitList are recorded when citizen visit the store visits the store.

func (s *SmartContract) Createvisit(ctx contractapi.TransactionContextInterface, id string, shopid string, civilid string, visittime string) error {
	exists, err := s.StateExists(ctx, id)
	if err != nil {
		return err
	}
	if exists {
		return fmt.Errorf("the visit %s alreay exists", id)
	}
	visit := StoreVisit{
		ID:             id,
		ShopID:         shopid,
		CivilID:        civilid,
		VisitTime:      visittime,
	}
	visitJSON, err := json.Marshal(visit)
	if err != nil {
		return err
	}

	return ctx.GetStub().PutState(id, visitJSON)
}

func (s *SmartContract) ReadVisit(ctx contractapi.TransactionContextInterface, id string) (*StoreVisit, error) {
	visitJSON, err := ctx.GetStub().GetState(id)
	if err != nil {
		return nil, fmt.Errorf("failed to read from world state: %v", err)
	}
	if visitJSON == nil {
		return nil, fmt.Errorf("the visit %s does not exist", id)
	}

	var visit StoreVisit
	err = json.Unmarshal(visitJSON, &visit)
	if err != nil {
		return nil, err
	}

	return &visit, nil
}

func (s *SmartContract) UpdateVisit(ctx contractapi.TransactionContextInterface, id string, shopid string, civilid string, visittime string) error {
	exists, err := s.StateExists(ctx, id)
	if err != nil {
		return err
	}
	if !exists {
		return fmt.Errorf("the storevisit %s does not exist", id)
	}

	visit := StoreVisit{
		ID:             id,
		ShopID:         shopid,
		CivilID:        civilid,
		VisitTime:      visittime,
	}
	visitJSON, err := json.Marshal(visit)
	if err != nil {
		return err
	}

	return ctx.GetStub().PutState(id, visitJSON)
}

func (s *SmartContract) GetAllVisits(ctx contractapi.TransactionContextInterface) ([]*StoreVisit, error) {
	// range query with empty string for startKey and endKey does an
	// open-ended query of all civils in the chaincode namespace.
	resultsIterator, err := ctx.GetStub().GetStateByRange("visit", "")
	if err != nil {
		return nil, err
	}
	defer resultsIterator.Close()

	var visits []*StoreVisit
	for resultsIterator.HasNext() {
		queryResponse, err := resultsIterator.Next()
		if err != nil {
			return nil, err
		}

		var visit StoreVisit
		err = json.Unmarshal(queryResponse.Value, &visit)
		if err != nil {
			return nil, err
		}
		visits = append(visits, &visit)
	}

	return visits, nil
}
