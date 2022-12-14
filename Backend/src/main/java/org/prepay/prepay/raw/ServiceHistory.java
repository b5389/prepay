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
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple3;
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
public class ServiceHistory extends Contract {
    public static final String[] BINARY_ARRAY = {"6080604052611001600560006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055503480156200005457600080fd5b5060006040805190810160405280600f81526020017f73657276696365486973746f727933000000000000000000000000000000000081525060049080519060200190620000a4929190620001b7565b50600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166356004b6a60046040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016200011f9190620003d2565b602060405180830381600087803b1580156200013a57600080fd5b505af11580156200014f573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506200017591908101906200027c565b90507f6c1bcd858fe6f6d2c026038ec8d705ad1b72753706298f4bc77d30e961685b9581604051620001a89190620003b5565b60405180910390a15062000446565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620001fa57805160ff19168380011785556200022b565b828001600101855582156200022b579182015b828111156200022a5782518255916020019190600101906200020d565b5b5090506200023a91906200023e565b5090565b6200026391905b808211156200025f57600081600090555060010162000245565b5090565b90565b60006200027482516200043c565b905092915050565b6000602082840312156200028f57600080fd5b60006200029f8482850162000266565b91505092915050565b620002b38162000432565b82525050565b600081546001811660008114620002d95760018114620002fa576200033f565b607f600283041680865260ff1983166020870152604086019350506200033f565b60028204808652602086019550620003128562000420565b60005b82811015620003365781548189015260018201915060208101905062000315565b80880195505050505b505092915050565b6000601782527f6361726449442c7265636f72642c636f6d706c61696e740000000000000000006020830152604082019050919050565b6000600982527f73657276696365494400000000000000000000000000000000000000000000006020830152604082019050919050565b6000602082019050620003cc6000830184620002a8565b92915050565b60006060820190508181036000830152620003ee8184620002b9565b9050818103602083015262000403816200037e565b90508181036040830152620004188162000347565b905092915050565b60008160005260206000209050919050565b6000819050919050565b6000819050919050565b611aef80620004566000396000f300608060405260043610610062576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630118565f146100675780631449b27a1461009257806339b314c4146100d05780634bec79ce1461010d575b600080fd5b34801561007357600080fd5b5061007c61014a565b60405161008991906116cf565b60405180910390f35b34801561009e57600080fd5b506100b960048036036100b491908101906112d3565b6101e8565b6040516100c792919061178f565b60405180910390f35b3480156100dc57600080fd5b506100f760048036036100f291908101906113c1565b610800565b6040516101049190611699565b60405180910390f35b34801561011957600080fd5b50610134600480360361012f9190810190611355565b610c42565b6040516101419190611699565b60405180910390f35b60048054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156101e05780601f106101b5576101008083540402835291602001916101e0565b820191906000526020600020905b8154815290600101906020018083116101c357829003601f168201915b505050505081565b606080600080600080600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f23f63c960046040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040161026991906117c6565b602060405180830381600087803b15801561028357600080fd5b505af1158015610297573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506102bb9190810190611281565b93508373ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561032157600080fd5b505af1158015610335573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506103599190810190611206565b92508273ffffffffffffffffffffffffffffffffffffffff1663cd30a1d1886040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016103b091906118a5565b600060405180830381600087803b1580156103ca57600080fd5b505af11580156103de573d6000803e3d6000fd5b505050508373ffffffffffffffffffffffffffffffffffffffff1663e8434e3988856040518363ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016104399291906116f1565b602060405180830381600087803b15801561045357600080fd5b505af1158015610467573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525061048b919081019061122f565b91508173ffffffffffffffffffffffffffffffffffffffff1663846719e060006040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016104e391906116b4565b602060405180830381600087803b1580156104fd57600080fd5b505af1158015610511573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506105359190810190611258565b90508073ffffffffffffffffffffffffffffffffffffffff16639c981fcb6040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040161058a906118da565b600060405180830381600087803b1580156105a457600080fd5b505af11580156105b8573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f820116820180604052506105e19190810190611314565b600290805190602001906105f6929190611051565b508073ffffffffffffffffffffffffffffffffffffffff16639c981fcb6040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040161064a906117e8565b600060405180830381600087803b15801561066457600080fd5b505af1158015610678573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f820116820180604052506106a19190810190611314565b600390805190602001906106b6929190611051565b5060026003818054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107505780601f1061072557610100808354040283529160200191610750565b820191906000526020600020905b81548152906001019060200180831161073357829003601f168201915b50505050509150808054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107ec5780601f106107c1576101008083540402835291602001916107ec565b820191906000526020600020905b8154815290600101906020018083116107cf57829003601f168201915b505050505090509550955050505050915091565b600080600080600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f23f63c960046040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040161087e91906117c6565b602060405180830381600087803b15801561089857600080fd5b505af11580156108ac573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506108d09190810190611281565b92508273ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561093657600080fd5b505af115801561094a573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525061096e9190810190611258565b91508173ffffffffffffffffffffffffffffffffffffffff1663e942b516886040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016109c591906118a5565b600060405180830381600087803b1580156109df57600080fd5b505af11580156109f3573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff1663e942b516876040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610a4c9190611870565b600060405180830381600087803b158015610a6657600080fd5b505af1158015610a7a573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff1663e942b516866040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610ad391906118fa565b600060405180830381600087803b158015610aed57600080fd5b505af1158015610b01573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff1663e942b5166040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610b589061183d565b600060405180830381600087803b158015610b7257600080fd5b505af1158015610b86573d6000803e3d6000fd5b505050508273ffffffffffffffffffffffffffffffffffffffff166331afac36888460","40518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610be1929190611721565b602060405180830381600087803b158015610bfb57600080fd5b505af1158015610c0f573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250610c3391908101906112aa565b90508093505050509392505050565b6000806000806000600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f23f63c960046040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610cc291906117c6565b602060405180830381600087803b158015610cdc57600080fd5b505af1158015610cf0573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250610d149190810190611281565b93508373ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610d7a57600080fd5b505af1158015610d8e573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250610db29190810190611258565b92508273ffffffffffffffffffffffffffffffffffffffff1663e942b516876040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610e099190611808565b600060405180830381600087803b158015610e2357600080fd5b505af1158015610e37573d6000803e3d6000fd5b505050508373ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610e9f57600080fd5b505af1158015610eb3573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250610ed79190810190611206565b91508173ffffffffffffffffffffffffffffffffffffffff1663cd30a1d1886040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610f2e91906118a5565b600060405180830381600087803b158015610f4857600080fd5b505af1158015610f5c573d6000803e3d6000fd5b505050508373ffffffffffffffffffffffffffffffffffffffff1663bf2b70a18885856040518463ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610fb993929190611751565b602060405180830381600087803b158015610fd357600080fd5b505af1158015610fe7573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525061100b91908101906112aa565b90507fc0106f9429858fd091728e75f3c1e40455a595b0ded861a8284bc368a97e35348160405161103c9190611699565b60405180910390a18094505050505092915050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061109257805160ff19168380011785556110c0565b828001600101855582156110c0579182015b828111156110bf5782518255916020019190600101906110a4565b5b5090506110cd91906110d1565b5090565b6110f391905b808211156110ef5760008160009055506001016110d7565b5090565b90565b600061110282516119da565b905092915050565b600061111682516119ec565b905092915050565b600061112a82516119fe565b905092915050565b600061113e8251611a10565b905092915050565b60006111528251611a22565b905092915050565b600082601f830112151561116d57600080fd5b813561118061117b8261195c565b61192f565b9150808252602083016020830185838301111561119c57600080fd5b6111a7838284611a62565b50505092915050565b600082601f83011215156111c357600080fd5b81516111d66111d18261195c565b61192f565b915080825260208301602083018583830111156111f257600080fd5b6111fd838284611a71565b50505092915050565b60006020828403121561121857600080fd5b6000611226848285016110f6565b91505092915050565b60006020828403121561124157600080fd5b600061124f8482850161110a565b91505092915050565b60006020828403121561126a57600080fd5b60006112788482850161111e565b91505092915050565b60006020828403121561129357600080fd5b60006112a184828501611132565b91505092915050565b6000602082840312156112bc57600080fd5b60006112ca84828501611146565b91505092915050565b6000602082840312156112e557600080fd5b600082013567ffffffffffffffff8111156112ff57600080fd5b61130b8482850161115a565b91505092915050565b60006020828403121561132657600080fd5b600082015167ffffffffffffffff81111561134057600080fd5b61134c848285016111b0565b91505092915050565b6000806040838503121561136857600080fd5b600083013567ffffffffffffffff81111561138257600080fd5b61138e8582860161115a565b925050602083013567ffffffffffffffff8111156113ab57600080fd5b6113b78582860161115a565b9150509250929050565b6000806000606084860312156113d657600080fd5b600084013567ffffffffffffffff8111156113f057600080fd5b6113fc8682870161115a565b935050602084013567ffffffffffffffff81111561141957600080fd5b6114258682870161115a565b925050604084013567ffffffffffffffff81111561144257600080fd5b61144e8682870161115a565b9150509250925092565b61146181611a2c565b82525050565b61147081611a3e565b82525050565b61147f816119d0565b82525050565b61148e81611a50565b82525050565b600061149f826119a5565b8084526114b3816020860160208601611a71565b6114bc81611aa4565b602085010191505092915050565b60006114d58261199a565b8084526114e9816020860160208601611a71565b6114f281611aa4565b602085010191505092915050565b60008154600181166000811461151d576001811461153d5761157e565b607f600283041680865260ff19831660208701526040860193505061157e565b6002820480865260208601955061155385611988565b60005b8281101561157557815481890152600182019150602081019050611556565b80880195505050505b505092915050565b6000600982527f636f6d706c61696e7400000000000000000000000000000000000000000000006020830152604082019050919050565b6000600682527f63617264494400000000000000000000000000000000000000000000000000006020830152604082019050919050565b6000600982527f73657276696365494400000000000000000000000000000000000000000000006020830152604082019050919050565b6000600682527f7265636f726400000000000000000000000000000000000000000000000000006020830152604082019050919050565b6000600482527f4e756c6c000000000000000000000000000000000000000000000000000000006020830152604082019050919050565b60006020820190506116ae6000830184611476565b92915050565b60006020820190506116c96000830184611485565b92915050565b600060208201905081810360008301526116e981846114ca565b905092915050565b6000604082019050818103600083015261170b8185611494565b905061171a6020830184611458565b9392505050565b6000604082019050818103600083015261173b8185611494565b905061174a6020830184611467565b9392505050565b6000606082019050818103600083015261176b8186611494565b905061177a6020830185611467565b6117876040830184611458565b949350505050565b600060408201905081810360008301526117a98185611494565b905081810360208301526117bd8184611494565b90509392505050565b600060208201905081810360008301526117e08184611500565b905092915050565b6000602082019050818103600083015261180181611586565b9050919050565b6000604082019050818103600083015261182181611586565b905081810360208301526118358184611494565b905092915050565b6000604082019050818103600083015261185681611586565b9050818103602083015261186981611662565b9050919050565b60006040820190508181036000830152611889816115bd565b9050818103602083015261189d8184611494565b905092915050565b600060408201905081810360008301526118be816115f4565b905081810360208301526118d28184611494565b905092915050565b600060208201905081810360008301526118f38161162b565b9050919050565b600060408201905081810360008301526119138161162b565b905081810360208301526119278184611494565b905092915050565b6000604051905081810181811067ffffffffffffffff8211171561195257600080fd5b8060405250919050565b600067ffffffffffffffff82111561197357600080fd5b601f19601f8301169050602081019050919050565b60008160005260206000209050919050565b600081519050919050565b600081519050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b60006119e5826119b0565b9050919050565b60006119f7826119b0565b9050919050565b6000611a09826119b0565b9050919050565b6000611a1b826119b0565b9050919050565b6000819050919050565b6000611a37826119b0565b9050919050565b6000611a49826119b0565b9050919050565b6000611a5b826119d0565b9050919050565b82818337600083830152505050565b60005b83811015611a8f578082015181840152602081019050611a74565b83811115611a9e576000848401525b50505050565b6000601f19601f83011690509190505600a265627a7a72305820700f2614496241916a1f3879029249741df6d63c4e313fb982bdce20b9d5c3276c6578706572696d656e74616cf50037"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[],\"name\":\"tableName\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_serviceID\",\"type\":\"string\"}],\"name\":\"selectByServiceID\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_serviceID\",\"type\":\"string\"},{\"name\":\"_cardID\",\"type\":\"string\"},{\"name\":\"_record\",\"type\":\"string\"}],\"name\":\"createHistory\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_serviceID\",\"type\":\"string\"},{\"name\":\"_complaint\",\"type\":\"string\"}],\"name\":\"changeComplaint\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"count\",\"type\":\"int256\"}],\"name\":\"CreateTable\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"count\",\"type\":\"int256\"}],\"name\":\"ChangeComplaint\",\"type\":\"event\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_TABLENAME = "tableName";

    public static final String FUNC_SELECTBYSERVICEID = "selectByServiceID";

    public static final String FUNC_CREATEHISTORY = "createHistory";

    public static final String FUNC_CHANGECOMPLAINT = "changeComplaint";

    public static final Event CREATETABLE_EVENT = new Event("CreateTable", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
    ;

    public static final Event CHANGECOMPLAINT_EVENT = new Event("ChangeComplaint", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
    ;

    protected ServiceHistory(String contractAddress, Client client, CryptoKeyPair credential) {
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

    public TransactionReceipt selectByServiceID(String _serviceID) {
        final Function function = new Function(
                FUNC_SELECTBYSERVICEID, 
                Arrays.<Type>asList(new Utf8String(_serviceID)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void selectByServiceID(String _serviceID, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_SELECTBYSERVICEID, 
                Arrays.<Type>asList(new Utf8String(_serviceID)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForSelectByServiceID(String _serviceID) {
        final Function function = new Function(
                FUNC_SELECTBYSERVICEID, 
                Arrays.<Type>asList(new Utf8String(_serviceID)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<String> getSelectByServiceIDInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SELECTBYSERVICEID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public Tuple2<String, String> getSelectByServiceIDOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_SELECTBYSERVICEID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, String>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    public TransactionReceipt createHistory(String _serviceID, String _cardID, String _record) {
        final Function function = new Function(
                FUNC_CREATEHISTORY, 
                Arrays.<Type>asList(new Utf8String(_serviceID),
                new Utf8String(_cardID),
                new Utf8String(_record)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void createHistory(String _serviceID, String _cardID, String _record, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_CREATEHISTORY, 
                Arrays.<Type>asList(new Utf8String(_serviceID),
                new Utf8String(_cardID),
                new Utf8String(_record)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForCreateHistory(String _serviceID, String _cardID, String _record) {
        final Function function = new Function(
                FUNC_CREATEHISTORY, 
                Arrays.<Type>asList(new Utf8String(_serviceID),
                new Utf8String(_cardID),
                new Utf8String(_record)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple3<String, String, String> getCreateHistoryInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CREATEHISTORY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple3<String, String, String>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue()
                );
    }

    public Tuple1<BigInteger> getCreateHistoryOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_CREATEHISTORY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public TransactionReceipt changeComplaint(String _serviceID, String _complaint) {
        final Function function = new Function(
                FUNC_CHANGECOMPLAINT, 
                Arrays.<Type>asList(new Utf8String(_serviceID),
                new Utf8String(_complaint)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void changeComplaint(String _serviceID, String _complaint, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_CHANGECOMPLAINT, 
                Arrays.<Type>asList(new Utf8String(_serviceID),
                new Utf8String(_complaint)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForChangeComplaint(String _serviceID, String _complaint) {
        final Function function = new Function(
                FUNC_CHANGECOMPLAINT, 
                Arrays.<Type>asList(new Utf8String(_serviceID),
                new Utf8String(_complaint)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<String, String> getChangeComplaintInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CHANGECOMPLAINT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, String>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    public Tuple1<BigInteger> getChangeComplaintOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_CHANGECOMPLAINT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
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

    public List<ChangeComplaintEventResponse> getChangeComplaintEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(CHANGECOMPLAINT_EVENT, transactionReceipt);
        ArrayList<ChangeComplaintEventResponse> responses = new ArrayList<ChangeComplaintEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ChangeComplaintEventResponse typedResponse = new ChangeComplaintEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeChangeComplaintEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(CHANGECOMPLAINT_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeChangeComplaintEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(CHANGECOMPLAINT_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public static ServiceHistory load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new ServiceHistory(contractAddress, client, credential);
    }

    public static ServiceHistory deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(ServiceHistory.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }

    public static class CreateTableEventResponse {
        public TransactionReceipt.Logs log;

        public BigInteger count;
    }

    public static class ChangeComplaintEventResponse {
        public TransactionReceipt.Logs log;

        public BigInteger count;
    }
}
