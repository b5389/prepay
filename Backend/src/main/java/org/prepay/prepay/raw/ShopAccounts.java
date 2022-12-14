package org.prepay.prepay.raw;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Event;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.Utf8String;
import org.fisco.bcos.sdk.abi.datatypes.generated.Int256;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.eventsub.EventCallback;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class ShopAccounts extends Contract {
    public static final String[] BINARY_ARRAY = {"6080604052611001600460006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055503480156200005457600080fd5b5060006040805190810160405280600d81526020017f73686f704163636f756e7473330000000000000000000000000000000000000081525060039080519060200190620000a4929190620001b7565b50600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166356004b6a60036040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016200011f9190620003d2565b602060405180830381600087803b1580156200013a57600080fd5b505af11580156200014f573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506200017591908101906200027c565b90507f6c1bcd858fe6f6d2c026038ec8d705ad1b72753706298f4bc77d30e961685b9581604051620001a89190620003b5565b60405180910390a15062000446565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620001fa57805160ff19168380011785556200022b565b828001600101855582156200022b579182015b828111156200022a5782518255916020019190600101906200020d565b5b5090506200023a91906200023e565b5090565b6200026391905b808211156200025f57600081600090555060010162000245565b5090565b90565b60006200027482516200043c565b905092915050565b6000602082840312156200028f57600080fd5b60006200029f8482850162000266565b91505092915050565b620002b38162000432565b82525050565b600081546001811660008114620002d95760018114620002fa576200033f565b607f600283041680865260ff1983166020870152604086019350506200033f565b60028204808652602086019550620003128562000420565b60005b82811015620003365781548189015260018201915060208101905062000315565b80880195505050505b505092915050565b6000601082527f62616c616e63652c6c65766572616765000000000000000000000000000000006020830152604082019050919050565b6000600682527f73686f70494400000000000000000000000000000000000000000000000000006020830152604082019050919050565b6000602082019050620003cc6000830184620002a8565b92915050565b60006060820190508181036000830152620003ee8184620002b9565b9050818103602083015262000403816200037e565b90508181036040830152620004188162000347565b905092915050565b60008160005260206000209050919050565b6000819050919050565b6000819050919050565b611ca380620004566000396000f30060806040526004361061006d576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630118565f14610072578063298daf5b1461009d5780632fa91cd0146100da578063bd50649414610117578063c10cab9c14610154575b600080fd5b34801561007e57600080fd5b50610087610192565b6040516100949190611930565b60405180910390f35b3480156100a957600080fd5b506100c460048036036100bf9190810190611669565b610230565b6040516100d191906118d1565b60405180910390f35b3480156100e657600080fd5b5061010160048036036100fc91908101906116aa565b6105f2565b60405161010e91906118d1565b60405180910390f35b34801561012357600080fd5b5061013e600480360361013991908101906116aa565b610c07565b60405161014b91906118d1565b60405180910390f35b34801561016057600080fd5b5061017b60048036036101769190810190611669565b611016565b6040516101899291906118ec565b60405180910390f35b60038054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156102285780601f106101fd57610100808354040283529160200191610228565b820191906000526020600020905b81548152906001019060200180831161020b57829003601f168201915b505050505081565b6000806000806000600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f23f63c960036040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016102b091906119f0565b602060405180830381600087803b1580156102ca57600080fd5b505af11580156102de573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506103029190810190611617565b9350600092508373ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561036c57600080fd5b505af1158015610380573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506103a491908101906115ee565b91508173ffffffffffffffffffffffffffffffffffffffff1663e942b516876040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016103fb9190611a12565b600060405180830381600087803b15801561041557600080fd5b505af1158015610429573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff16632ef8ba74846040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016104829190611a67565b600060405180830381600087803b15801561049c57600080fd5b505af11580156104b0573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff16632ef8ba74846040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016105099190611ab5565b600060405180830381600087803b15801561052357600080fd5b505af1158015610537573d6000803e3d6000fd5b505050508373ffffffffffffffffffffffffffffffffffffffff166331afac3687846040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610592929190611982565b602060405180830381600087803b1580156105ac57600080fd5b505af11580156105c0573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506105e49190810190611640565b905080945050505050919050565b600080600080600080600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f23f63c960036040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040161067391906119f0565b602060405180830381600087803b15801561068d57600080fd5b505af11580156106a1573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506106c59190810190611617565b94508473ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561072b57600080fd5b505af115801561073f573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250610763919081019061159c565b93508373ffffffffffffffffffffffffffffffffffffffff1663cd30a1d1896040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016107ba9190611a12565b600060405180830381600087803b1580156107d457600080fd5b505af11580156107e8573d6000803e3d6000fd5b505050508473ffffffffffffffffffffffffffffffffffffffff1663e8434e3989866040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610843929190611952565b602060405180830381600087803b15801561085d57600080fd5b505af1158015610871573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525061089591908101906115c5565b92508273ffffffffffffffffffffffffffffffffffffffff1663846719e060006040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016108ed9190611915565b602060405180830381600087803b15801561090757600080fd5b505af115801561091b573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525061093f91908101906115ee565b91508173ffffffffffffffffffffffffffffffffffffffff1663fda69fae6040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040161099490611a47565b602060405180830381600087803b1580156109ae57600080fd5b505af11580156109c2573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506109e69190810190611640565b6001819055508473ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610a5057600080fd5b505af1158015610a64573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250610a8891908101906115ee565b91508173ffffffffffffffffffffffffffffffffffffffff16632ef8ba7488600154016040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610ae39190611a67565b600060405180830381600087803b158015610afd57600080fd5b505af1158015610b11573d6000803e3d6000fd5b505050508473ffffffffffffffffffffffffffffffffffffffff1663bf2b70a18984876040518463ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610b6e939291906119b2565b602060405180830381600087803b158015610b8857600080fd5b505af1158015610b9c573d6000803e3d6000fd5b505050506040513d601f19601f","82011682018060405250610bc09190810190611640565b90507f2a1acf7456567e217ff3aaa8c6617a9159a0dd67982181ce7325d6e77b3a45d981604051610bf191906118d1565b60405180910390a1809550505050505092915050565b6000806000806000600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f23f63c960036040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610c8791906119f0565b602060405180830381600087803b158015610ca157600080fd5b505af1158015610cb5573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250610cd99190810190611617565b93508373ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610d3f57600080fd5b505af1158015610d53573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250610d7791908101906115ee565b92508273ffffffffffffffffffffffffffffffffffffffff16632ef8ba74876040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610dce9190611ab5565b600060405180830381600087803b158015610de857600080fd5b505af1158015610dfc573d6000803e3d6000fd5b505050508373ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610e6457600080fd5b505af1158015610e78573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250610e9c919081019061159c565b91508173ffffffffffffffffffffffffffffffffffffffff1663cd30a1d1886040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610ef39190611a12565b600060405180830381600087803b158015610f0d57600080fd5b505af1158015610f21573d6000803e3d6000fd5b505050508373ffffffffffffffffffffffffffffffffffffffff1663bf2b70a18885856040518463ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610f7e939291906119b2565b602060405180830381600087803b158015610f9857600080fd5b505af1158015610fac573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250610fd09190810190611640565b90507f2a1acf7456567e217ff3aaa8c6617a9159a0dd67982181ce7325d6e77b3a45d98160405161100191906118d1565b60405180910390a18094505050505092915050565b600080600080600080600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f23f63c960036040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040161109791906119f0565b602060405180830381600087803b1580156110b157600080fd5b505af11580156110c5573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506110e99190810190611617565b93508373ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561114f57600080fd5b505af1158015611163573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250611187919081019061159c565b92508273ffffffffffffffffffffffffffffffffffffffff1663cd30a1d1886040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016111de9190611a12565b600060405180830381600087803b1580156111f857600080fd5b505af115801561120c573d6000803e3d6000fd5b505050508373ffffffffffffffffffffffffffffffffffffffff1663e8434e3988856040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401611267929190611952565b602060405180830381600087803b15801561128157600080fd5b505af1158015611295573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506112b991908101906115c5565b91508173ffffffffffffffffffffffffffffffffffffffff1663846719e060006040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016113119190611915565b602060405180830381600087803b15801561132b57600080fd5b505af115801561133f573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525061136391908101906115ee565b90508073ffffffffffffffffffffffffffffffffffffffff1663fda69fae6040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016113b890611a47565b602060405180830381600087803b1580156113d257600080fd5b505af11580156113e6573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525061140a9190810190611640565b6001819055508073ffffffffffffffffffffffffffffffffffffffff1663fda69fae6040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040161146390611a95565b602060405180830381600087803b15801561147d57600080fd5b505af1158015611491573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506114b59190810190611640565b6002819055506001546002549550955050505050915091565b60006114da8251611b8e565b905092915050565b60006114ee8251611ba0565b905092915050565b60006115028251611bb2565b905092915050565b60006115168251611bc4565b905092915050565b600061152a8235611bd6565b905092915050565b600061153e8251611bd6565b905092915050565b600082601f830112151561155957600080fd5b813561156c61156782611b10565b611ae3565b9150808252602083016020830185838301111561158857600080fd5b611593838284611c16565b50505092915050565b6000602082840312156115ae57600080fd5b60006115bc848285016114ce565b91505092915050565b6000602082840312156115d757600080fd5b60006115e5848285016114e2565b91505092915050565b60006020828403121561160057600080fd5b600061160e848285016114f6565b91505092915050565b60006020828403121561162957600080fd5b60006116378482850161150a565b91505092915050565b60006020828403121561165257600080fd5b600061166084828501611532565b91505092915050565b60006020828403121561167b57600080fd5b600082013567ffffffffffffffff81111561169557600080fd5b6116a184828501611546565b91505092915050565b600080604083850312156116bd57600080fd5b600083013567ffffffffffffffff8111156116d757600080fd5b6116e385828601611546565b92505060206116f48582860161151e565b9150509250929050565b61170781611be0565b82525050565b61171681611bf2565b82525050565b61172581611b84565b82525050565b61173481611c04565b82525050565b600061174582611b59565b808452611759816020860160208601611c25565b61176281611c58565b602085010191505092915050565b600061177b82611b4e565b80845261178f816020860160208601611c25565b61179881611c58565b602085010191505092915050565b6000815460018116600081146117c357600181146117e357611824565b607f600283041680865260ff198316602087015260408601935050611824565b600282048086526020860195506117f985611b3c565b60005b8281101561181b578154818901526001820191506020810190506117fc565b80880195505050505b505092915050565b6000600682527f73686f70494400000000000000000000000000000000000000000000000000006020830152604082019050919050565b6000600782527f62616c616e6365000000000000000000000000000000000000000000000000006020830152604082019050919050565b6000600882527f6c657665726167650000000000000000000000000000000000000000000000006020830152604082019050919050565b60006020820190506118e6600083018461171c565b92915050565b6000604082019050611901600083018561171c565b61190e602083018461171c565b9392505050565b600060208201905061192a600083018461172b565b92915050565b6000602082019050818103600083015261194a8184611770565b905092915050565b6000604082019050818103600083015261196c818561173a565b905061197b60208301846116fe565b9392505050565b6000604082019050818103600083015261199c818561173a565b90506119ab602083018461170d565b9392505050565b600060608201905081810360008301526119cc818661173a565b90506119db602083018561170d565b6119e860408301846116fe565b949350505050565b60006020820190508181036000830152611a0a81846117a6565b905092915050565b60006040820190508181036000830152611a2b8161182c565b90508181036020830152611a3f818461173a565b905092915050565b60006020820190508181036000830152611a6081611863565b9050919050565b60006040820190508181036000830152611a8081611863565b9050611a8f602083018461171c565b92915050565b60006020820190508181036000830152611aae8161189a565b9050919050565b60006040820190508181036000830152611ace8161189a565b9050611add602083018461171c565b92915050565b6000604051905081810181811067ffffffffffffffff82111715611b0657600080fd5b8060405250919050565b600067ffffffffffffffff821115611b2757600080fd5b601f19601f8301169050602081019050919050565b60008160005260206000209050919050565b600081519050919050565b600081519050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b6000611b9982611b64565b9050919050565b6000611bab82611b64","565b9050919050565b6000611bbd82611b64565b9050919050565b6000611bcf82611b64565b9050919050565b6000819050919050565b6000611beb82611b64565b9050919050565b6000611bfd82611b64565b9050919050565b6000611c0f82611b84565b9050919050565b82818337600083830152505050565b60005b83811015611c43578082015181840152602081019050611c28565b83811115611c52576000848401525b50505050565b6000601f19601f83011690509190505600a265627a7a723058209d21012f2f6f50f27c125891546fc6ffe454c47b9f4f52cedc3ea386cc335e8e6c6578706572696d656e74616cf50037"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[],\"name\":\"tableName\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_shopID\",\"type\":\"string\"}],\"name\":\"createAccount\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_shopID\",\"type\":\"string\"},{\"name\":\"_value\",\"type\":\"int256\"}],\"name\":\"changeBalance\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_shopID\",\"type\":\"string\"},{\"name\":\"_value\",\"type\":\"int256\"}],\"name\":\"changeLeverage\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_shopID\",\"type\":\"string\"}],\"name\":\"selectByShopID\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"},{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"count\",\"type\":\"int256\"}],\"name\":\"CreateTable\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"count\",\"type\":\"int256\"}],\"name\":\"ChangeBalance\",\"type\":\"event\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_TABLENAME = "tableName";

    public static final String FUNC_CREATEACCOUNT = "createAccount";

    public static final String FUNC_CHANGEBALANCE = "changeBalance";

    public static final String FUNC_CHANGELEVERAGE = "changeLeverage";

    public static final String FUNC_SELECTBYSHOPID = "selectByShopID";

    public static final Event CREATETABLE_EVENT = new Event("CreateTable", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
    ;

    public static final Event CHANGEBALANCE_EVENT = new Event("ChangeBalance", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
    ;

    protected ShopAccounts(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public String tableName() throws ContractException {
        final Function function = new Function(FUNC_TABLENAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
    }

    public TransactionReceipt createAccount(String _shopID) {
        final Function function = new Function(
                FUNC_CREATEACCOUNT, 
                Arrays.<Type>asList(new Utf8String(_shopID)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void createAccount(String _shopID, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_CREATEACCOUNT, 
                Arrays.<Type>asList(new Utf8String(_shopID)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForCreateAccount(String _shopID) {
        final Function function = new Function(
                FUNC_CREATEACCOUNT, 
                Arrays.<Type>asList(new Utf8String(_shopID)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<String> getCreateAccountInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CREATEACCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public Tuple1<BigInteger> getCreateAccountOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_CREATEACCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public TransactionReceipt changeBalance(String _shopID, BigInteger _value) {
        final Function function = new Function(
                FUNC_CHANGEBALANCE, 
                Arrays.<Type>asList(new Utf8String(_shopID),
                new Int256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void changeBalance(String _shopID, BigInteger _value, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_CHANGEBALANCE, 
                Arrays.<Type>asList(new Utf8String(_shopID),
                new Int256(_value)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForChangeBalance(String _shopID, BigInteger _value) {
        final Function function = new Function(
                FUNC_CHANGEBALANCE, 
                Arrays.<Type>asList(new Utf8String(_shopID),
                new Int256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<String, BigInteger> getChangeBalanceInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CHANGEBALANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, BigInteger>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public Tuple1<BigInteger> getChangeBalanceOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_CHANGEBALANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public TransactionReceipt changeLeverage(String _shopID, BigInteger _value) {
        final Function function = new Function(
                FUNC_CHANGELEVERAGE, 
                Arrays.<Type>asList(new Utf8String(_shopID),
                new Int256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void changeLeverage(String _shopID, BigInteger _value, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_CHANGELEVERAGE, 
                Arrays.<Type>asList(new Utf8String(_shopID),
                new Int256(_value)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForChangeLeverage(String _shopID, BigInteger _value) {
        final Function function = new Function(
                FUNC_CHANGELEVERAGE, 
                Arrays.<Type>asList(new Utf8String(_shopID),
                new Int256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<String, BigInteger> getChangeLeverageInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CHANGELEVERAGE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, BigInteger>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public Tuple1<BigInteger> getChangeLeverageOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_CHANGELEVERAGE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public TransactionReceipt selectByShopID(String _shopID) {
        final Function function = new Function(
                FUNC_SELECTBYSHOPID, 
                Arrays.<Type>asList(new Utf8String(_shopID)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void selectByShopID(String _shopID, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_SELECTBYSHOPID, 
                Arrays.<Type>asList(new Utf8String(_shopID)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForSelectByShopID(String _shopID) {
        final Function function = new Function(
                FUNC_SELECTBYSHOPID, 
                Arrays.<Type>asList(new Utf8String(_shopID)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<String> getSelectByShopIDInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SELECTBYSHOPID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public Tuple2<BigInteger, BigInteger> getSelectByShopIDOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_SELECTBYSHOPID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}, new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<BigInteger, BigInteger>(

                (BigInteger) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public List<CreateTableEventResponse> getCreateTableEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(CREATETABLE_EVENT, transactionReceipt);
        ArrayList<CreateTableEventResponse> responses = new ArrayList<CreateTableEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            CreateTableEventResponse typedResponse = new CreateTableEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeCreateTableEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(CREATETABLE_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeCreateTableEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(CREATETABLE_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public List<ChangeBalanceEventResponse> getChangeBalanceEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(CHANGEBALANCE_EVENT, transactionReceipt);
        ArrayList<ChangeBalanceEventResponse> responses = new ArrayList<ChangeBalanceEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ChangeBalanceEventResponse typedResponse = new ChangeBalanceEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeChangeBalanceEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(CHANGEBALANCE_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeChangeBalanceEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(CHANGEBALANCE_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public static ShopAccounts load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new ShopAccounts(contractAddress, client, credential);
    }

    public static ShopAccounts deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(ShopAccounts.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }

    public static class CreateTableEventResponse {
        public TransactionReceipt.Logs log;

        public BigInteger count;
    }

    public static class ChangeBalanceEventResponse {
        public TransactionReceipt.Logs log;

        public BigInteger count;
    }
}
