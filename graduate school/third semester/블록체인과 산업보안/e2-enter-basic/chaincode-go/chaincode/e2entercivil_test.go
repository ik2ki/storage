package chaincode_test

import (
	"encoding/json"
	"fmt"
	"testing"

	//"github.com/hyperledger/fabric-chaincode-go/shim"
	//"github.com/hyperledger/fabric-contract-api-go/contractapi"
	"github.com/hyperledger/fabric-protos-go/ledger/queryresult"
	"github.com/hyperledger/fabric-samples/asset-transfer-basic/chaincode-go/chaincode"
	"github.com/hyperledger/fabric-samples/asset-transfer-basic/chaincode-go/chaincode/mocks"
	"github.com/stretchr/testify/require"
)

func TestGetAllCivils(t *testing.T) {
	civil := &chaincode.Civil{ID: "civil1"}
	bytes, err := json.Marshal(civil)
	require.NoError(t, err)

	iterator := &mocks.StateQueryIterator{}
	iterator.HasNextReturnsOnCall(0, true)
	iterator.HasNextReturnsOnCall(1, false)
	iterator.NextReturns(&queryresult.KV{Value: bytes}, nil)

	chaincodeStub := &mocks.ChaincodeStub{}
	transactionContext := &mocks.TransactionContext{}
	transactionContext.GetStubReturns(chaincodeStub)

	chaincodeStub.GetStateByRangeReturns(iterator, nil)
	e2civilEnter := &chaincode.SmartContract{}
	civils, err := e2civilEnter.GetAllCivils(transactionContext)
	require.NoError(t, err)
	require.Equal(t, []*chaincode.Civil{civil}, civils)

	iterator.HasNextReturns(true)
	iterator.NextReturns(nil, fmt.Errorf("failed retrieving next item"))
	civils, err = e2civilEnter.GetAllCivils(transactionContext)
	require.EqualError(t, err, "failed retrieving next item")
	require.Nil(t, civils)

	chaincodeStub.GetStateByRangeReturns(nil, fmt.Errorf("failed retrieving all civils"))
	civils, err = e2civilEnter.GetAllCivils(transactionContext)
	require.EqualError(t, err, "failed retrieving all civils")
	require.Nil(t, civils)
}
