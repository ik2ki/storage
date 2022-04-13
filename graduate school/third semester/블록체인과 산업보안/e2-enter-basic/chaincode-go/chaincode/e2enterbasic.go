package chaincode

import (
	"encoding/json"
	"fmt"

	"github.com/hyperledger/fabric-contract-api-go/contractapi"
)

// SmartContract provides functions for managing an Asset
type SmartContract struct {
	contractapi.Contract
}

// Civil describe basic details of what makes up a simple human
type Civil struct {
	ID            string `json:"civilID"`
	Name          string `json:"civilName"`
	PhoneNumber   string `json:"civilphoneNumber"`
	Address       string `json:"civilAddress"`
	Status        string `json:"civilStatus"`
}

// Shop is describe basic information of shop
type Shop struct {
    ID            string `json:"shopID"`
	Name          string `json:"shopName"`
	Telephone     string `json:"shopTelephone"`
	Address       string `json:"shopAddress"`
}

// StoreVisitList are recorded when citizen visit the store visits the store.
type StoreVisit struct {
	ID            string `json:"visitID"`
	ShopID        string `json:"shopID"`
    CivilID       string `json:"civilID"`
	VisitTime     string `json:"visitTime"`
}

// InitLedger adds a base set of electric enter lists to the ledger
func (s *SmartContract) InitLedger(ctx contractapi.TransactionContextInterface) error {
    civils := []Civil{
		{ID: "civil1", Name: "alice", PhoneNumber: "010-1234-5678", Address: "Wonchen-dong, Suwon", Status: "Normal"},
		{ID: "civil2", Name: "bob", PhoneNumber: "010-2345-6789", Address: "Seocho-dong, Seoul", Status: "Normal"},
		{ID: "civil3", Name: "charlie", PhoneNumber: "010-3456-7891", Address: "Dongbaek-dong, Yongin", Status: "Normal"},
		{ID: "civil4", Name: "dave", PhoneNumber: "010-4567-8912", Address: "Daehak-dong, Seoul", Status: "Normal"},
		{ID: "civil5", Name: "eve", PhoneNumber: "010-5678-9123", Address: "Yongjeon-dong, Daejeon", Status: "Normal"},
		{ID: "civil6", Name: "frank", PhoneNumber: "010-6789-1234", Address: "Doryang-dong, Gumi", Status: "Normal"},
		{ID: "civil7", Name: "grace", PhoneNumber: "010-7891-2345", Address: "Maetan-dong, Suwon", Status: "Normal"},
		{ID: "civil8", Name: "heidi", PhoneNumber: "010-8912-3456", Address: "Nam-dong, Yongin", Status: "Normal"},
		{ID: "civil9", Name: "ivan", PhoneNumber: "010-9123-4567", Address: "Dunsan-dong, Daejeon", Status: "Normal"},
		{ID: "civil10", Name: "justin", PhoneNumber: "010-9876-5432", Address: "Sillim-dong, Seoul", Status: "Normal"},
	}
	for _, civil := range civils {
		civilJSON, err := json.Marshal(civil)
		if err != nil {
			return err
		}

		err = ctx.GetStub().PutState(civil.ID, civilJSON)
		if err != nil {
			return fmt.Errorf("failed to put to world state. %v", err)
		}
	}
	shops := []Shop{
		{ID: "shop1", Name: "Burger King Gwanggyo Avenue France Branch", Telephone: "031-212-0360", Address: "85, Central town-ro, Yeongtong-gu, Suwon-si, Gyeonggi-do, Republic of Korea"},
		{ID: "shop2", Name: "Lotteria Gwanggyo Branch", Telephone: "031-212-2205", Address: "Edu Plaza, 43, Cheongdo-ro 89beon-gil, Yeongtong-gu, Suwon-si, Gyeonggi-do, Republic of Korea"},
		{ID: "shop3", Name: "McDonald's Ajou University Branch", Telephone: "070-7209-0922", Address: "46, Aju-ro, Yeongtong-gu, Suwon-si, Gyeonggi-do, Republic of Korea"},
		{ID: "shop4", Name: "KFC Ajou University Branch", Telephone: "031-217-0337", Address: "37, Aju-ro, Paldal-gu, Suwon-si, Gyeonggi-do, Republic of Korea"},
		{ID: "shop5", Name: "VIPS Ajou University Branch", Telephone: "0507-1365-1997", Address: "233, Jungbu-daero, Paldal-gu, Suwon-si, Gyeonggi-do, Republic of Korea"},
		{ID: "shop6", Name: "Downtowner Galleria Gwanggyo Branch", Telephone: "031-5174-7979", Address: "9th floor 124, Gwanggyo Jungang-ro, Yeongtong-gu, Suwon-si, Gyeonggi-do, Republic of Korea"},
		{ID: "shop7", Name: "The Brown Gwanggyo Elport Branch", Telephone: "031-217-1806", Address: "B1 floor 145, Gwanggyo Jungang-ro, Yeongtong-gu, Suwon-si, Gyeonggi-do, Republic of Korea"},
		{ID: "shop8", Name: "Starbucks Ajou University Branch", Telephone: "031-215-4516", Address: "205, World cup-ro, Paldal-gu, Suwon-si, Gyeonggi-do, Republic of Korea"},
		{ID: "shop9", Name: "A Twosome place Ajou University Branch", Telephone: "031-215-2785", Address: "199, World cup-ro, Yeongtong-gu, Suwon-si, Gyeonggi-do, Republic of Korea"},
		{ID: "shop10", Name: "Subway Ajou University Branch", Telephone: "031-211-0369", Address: "24, Aju-ro, Yeongtong-gu, Suwon-si, Gyeonggi-do, Republic of Korea"},
	}
	for _, shop := range shops {
		shopJSON, err := json.Marshal(shop)
		if err != nil {
			return err
		}

		err = ctx.GetStub().PutState(shop.ID, shopJSON)
		if err != nil {
			return fmt.Errorf("failed to put to world state. %v", err)
		}
	}
	visitlists := []StoreVisit{
		{ID: "visit1", ShopID: "shop1", CivilID: "civil1", VisitTime: "2021-05-21 17:45"},
		{ID: "visit2", ShopID: "shop2", CivilID: "civil2", VisitTime: "2021-05-21 18:04"},
		{ID: "visit3", ShopID: "shop3", CivilID: "civil3", VisitTime: "2021-05-21 20:14"},
		{ID: "visit4", ShopID: "shop4", CivilID: "civil4", VisitTime: "2021-05-21 21:05"},
		{ID: "visit5", ShopID: "shop5", CivilID: "civil5", VisitTime: "2021-05-21 21:54"},
		{ID: "visit6", ShopID: "shop6", CivilID: "civil1", VisitTime: "2021-05-22 10:40"},
		{ID: "visit7", ShopID: "shop6", CivilID: "civil2", VisitTime: "2021-05-22 12:03"},
		{ID: "visit8", ShopID: "shop7", CivilID: "civil2", VisitTime: "2021-05-22 14:13"},
		{ID: "visit9", ShopID: "shop8", CivilID: "civil6", VisitTime: "2021-05-22 16:02"},
		{ID: "visit10", ShopID: "shop10", CivilID: "civil10", VisitTime: "2021-05-22 17:39"},
	}
	for _, visitlist := range visitlists {
		visitlistJSON, err := json.Marshal(visitlist)
		if err != nil {
			return err
		}

		err = ctx.GetStub().PutState(visitlist.ID, visitlistJSON)
		if err != nil {
			return fmt.Errorf("failed to put to world state. %v", err)
		}
	}
	return nil
}

// DeleteState deletes an given id from the world state.
func (s *SmartContract) DeleteAsset(ctx contractapi.TransactionContextInterface, id string) error {
	exists, err := s.StateExists(ctx, id)
	if err != nil {
		return err
	}
	if !exists {
		return fmt.Errorf("the state %s does not exist", id)
	}

	return ctx.GetStub().DelState(id)
}

// AssetExists returns true when asset with given ID exists in world state
func (s *SmartContract) StateExists(ctx contractapi.TransactionContextInterface, id string) (bool, error) {
	stateJSON, err := ctx.GetStub().GetState(id)
	if err != nil {
		return false, fmt.Errorf("failed to read from world state: %v", err)
	}

	return stateJSON != nil, nil
}
