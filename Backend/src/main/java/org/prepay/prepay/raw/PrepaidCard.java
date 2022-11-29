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
import org.fisco.bcos.sdk.abi.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple6;
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
public class PrepaidCard extends Contract {
    public static final String[] BINARY_ARRAY = {"6080604052611001600760006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055503480156200005457600080fd5b5060006040805190810160405280600b81526020017f707265706169644361726400000000000000000000000000000000000000000081525060069080519060200190620000a4929190620001b7565b50600760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166356004b6a60066040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016200011f9190620003f8565b602060405180830381600087803b1580156200013a57600080fd5b505af11580156200014f573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506200017591908101906200027c565b90507f6c1bcd858fe6f6d2c026038ec8d705ad1b72753706298f4bc77d30e961685b9581604051620001a89190620003db565b60405180910390a1506200046c565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620001fa57805160ff19168380011785556200022b565b828001600101855582156200022b579182015b828111156200022a5782518255916020019190600101906200020d565b5b5090506200023a91906200023e565b5090565b6200026391905b808211156200025f57600081600090555060010162000245565b5090565b90565b600062000274825162000462565b905092915050565b6000602082840312156200028f57600080fd5b60006200029f8482850162000266565b91505092915050565b620002b38162000458565b82525050565b600081546001811660008114620002d95760018114620002fa576200033f565b607f600283041680865260ff1983166020870152604086019350506200033f565b60028204808652602086019550620003128562000446565b60005b82811015620003365781548189015260018201915060208101905062000315565b80880195505050505b505092915050565b6000600682527f63617264494400000000000000000000000000000000000000000000000000006020830152604082019050919050565b6000603182527f636f6e73756d657249442c73686f7049442c62616c616e63652c636f6e74726160208301527f6374486173682c63726561746554696d650000000000000000000000000000006040830152606082019050919050565b6000602082019050620003f26000830184620002a8565b92915050565b60006060820190508181036000830152620004148184620002b9565b90508181036020830152620004298162000347565b905081810360408301526200043e816200037e565b905092915050565b60008160005260206000209050919050565b6000819050919050565b6000819050919050565b612349806200047c6000396000f300608060405260043610610062576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630118565f146100675780630a33be62146100925780632fa91cd0146100cf578063b3dade061461010c575b600080fd5b34801561007357600080fd5b5061007c61014e565b6040516100899190611e34565b60405180910390f35b34801561009e57600080fd5b506100b960048036036100b49190810190611a7d565b6101ec565b6040516100c69190611dfe565b60405180910390f35b3480156100db57600080fd5b506100f660048036036100f19190810190611a29565b610741565b6040516101039190611dfe565b60405180910390f35b34801561011857600080fd5b50610133600480360361012e91908101906119a7565b610d56565b60405161014596959493929190611ef4565b60405180910390f35b60068054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156101e45780601f106101b9576101008083540402835291602001916101e4565b820191906000526020600020905b8154815290600101906020018083116101c757829003601f168201915b505050505081565b600080600080600760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f23f63c960066040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040161026a9190611f6a565b602060405180830381600087803b15801561028457600080fd5b505af1158015610298573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506102bc9190810190611955565b92508273ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561032257600080fd5b505af1158015610336573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525061035a919081019061192c565b91508173ffffffffffffffffffffffffffffffffffffffff1663e942b5168b6040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016103b191906120a4565b600060405180830381600087803b1580156103cb57600080fd5b505af11580156103df573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff1663e942b5168a6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610438919061204f565b600060405180830381600087803b15801561045257600080fd5b505af1158015610466573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff1663e942b516896040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016104bf9190611ffa565b600060405180830381600087803b1580156104d957600080fd5b505af11580156104ed573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff16632ef8ba74886040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016105469190612147565b600060405180830381600087803b15801561056057600080fd5b505af1158015610574573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff16632ef8ba74876040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016105cd91906120f9565b600060405180830381600087803b1580156105e757600080fd5b505af11580156105fb573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff16638a42ebe9866040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016106549190611fac565b600060405180830381600087803b15801561066e57600080fd5b505af1158015610682573d6000803e3d6000fd5b505050508273ffffffffffffffffffffffffffffffffffffffff166331afac368b846040518363ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016106dd929190611e86565b602060405180830381600087803b1580156106f757600080fd5b505af115801561070b573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525061072f919081019061197e565b90508093505050509695505050505050565b600080600080600080600760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f23f63c960066040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016107c29190611f6a565b602060405180830381600087803b1580156107dc57600080fd5b505af11580156107f0573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506108149190810190611955565b94508473ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561087a57600080fd5b505af115801561088e573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506108b291908101906118da565b93508373ffffffffffffffffffffffffffffffffffffffff1663cd30a1d1896040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040161090991906120a4565b600060405180830381600087803b15801561092357600080fd5b505af1158015610937573d6000803e3d6000fd5b505050508473ffffffffffffffffffffffffffffffffffffffff1663e8434e3989866040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610992929190611e56565b602060405180830381600087803b1580156109ac57600080fd5b505af11580156109c0573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506109e49190810190611903565b92508273ffffffffffffffffffffffffffffffffffffffff1663846719e060006040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610a3c9190611e19565b602060405180830381600087803b158015610a5657600080fd5b505af1158015610a6a573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250610a8e919081019061192c565b91508173ffffffffffffffffffffffffffffffffffffffff1663fda69fae6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610ae390612127565b602060405180830381600087803b158015610afd57600080fd5b505af1158015610b11573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250610b35919081019061197e565b6003819055508473ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260","0401602060405180830381600087803b158015610b9f57600080fd5b505af1158015610bb3573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250610bd7919081019061192c565b91508173ffffffffffffffffffffffffffffffffffffffff16632ef8ba7488600354036040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610c329190612147565b600060405180830381600087803b158015610c4c57600080fd5b505af1158015610c60573d6000803e3d6000fd5b505050508473ffffffffffffffffffffffffffffffffffffffff1663bf2b70a18984876040518463ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610cbd93929190611eb6565b602060405180830381600087803b158015610cd757600080fd5b505af1158015610ceb573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250610d0f919081019061197e565b90507f2a1acf7456567e217ff3aaa8c6617a9159a0dd67982181ce7325d6e77b3a45d981604051610d409190611dfe565b60405180910390a1809550505050505092915050565b60608060606000806000806000806000600760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f23f63c960066040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610dde9190611f6a565b602060405180830381600087803b158015610df857600080fd5b505af1158015610e0c573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250610e309190810190611955565b93508373ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610e9657600080fd5b505af1158015610eaa573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250610ece91908101906118da565b92508273ffffffffffffffffffffffffffffffffffffffff1663cd30a1d18c6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610f2591906120a4565b600060405180830381600087803b158015610f3f57600080fd5b505af1158015610f53573d6000803e3d6000fd5b505050508373ffffffffffffffffffffffffffffffffffffffff1663e8434e398c856040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610fae929190611e56565b602060405180830381600087803b158015610fc857600080fd5b505af1158015610fdc573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506110009190810190611903565b91508173ffffffffffffffffffffffffffffffffffffffff1663846719e060006040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016110589190611e19565b602060405180830381600087803b15801561107257600080fd5b505af1158015611086573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506110aa919081019061192c565b90508073ffffffffffffffffffffffffffffffffffffffff16639c981fcb6040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016110ff90612084565b600060405180830381600087803b15801561111957600080fd5b505af115801561112d573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f8201168201806040525061115691908101906119e8565b6000908051906020019061116b9291906116e9565b508073ffffffffffffffffffffffffffffffffffffffff16639c981fcb6040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016111bf9061202f565b600060405180830381600087803b1580156111d957600080fd5b505af11580156111ed573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f8201168201806040525061121691908101906119e8565b6001908051906020019061122b9291906116e9565b508073ffffffffffffffffffffffffffffffffffffffff16639c981fcb6040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040161127f90611fda565b600060405180830381600087803b15801561129957600080fd5b505af11580156112ad573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f820116820180604052506112d691908101906119e8565b600290805190602001906112eb9291906116e9565b508073ffffffffffffffffffffffffffffffffffffffff1663fda69fae6040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040161133f90612127565b602060405180830381600087803b15801561135957600080fd5b505af115801561136d573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250611391919081019061197e565b6003819055508073ffffffffffffffffffffffffffffffffffffffff1663fda69fae6040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016113ea906120d9565b602060405180830381600087803b15801561140457600080fd5b505af1158015611418573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525061143c919081019061197e565b6004819055508073ffffffffffffffffffffffffffffffffffffffff16633536046a6040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040161149590611f8c565b602060405180830381600087803b1580156114af57600080fd5b505af11580156114c3573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506114e79190810190611b4e565b600581905550600060016002600354600454600554858054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156115915780601f1061156657610100808354040283529160200191611591565b820191906000526020600020905b81548152906001019060200180831161157457829003601f168201915b50505050509550848054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561162d5780601f106116025761010080835404028352916020019161162d565b820191906000526020600020905b81548152906001019060200180831161161057829003601f168201915b50505050509450838054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156116c95780601f1061169e576101008083540402835291602001916116c9565b820191906000526020600020905b8154815290600101906020018083116116ac57829003601f168201915b505050505093509950995099509950995099505050505091939550919395565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061172a57805160ff1916838001178555611758565b82800160010185558215611758579182015b8281111561175757825182559160200191906001019061173c565b5b5090506117659190611769565b5090565b61178b91905b8082111561178757600081600090555060010161176f565b5090565b90565b600061179a825161222a565b905092915050565b60006117ae825161223c565b905092915050565b60006117c2825161224e565b905092915050565b60006117d68251612260565b905092915050565b60006117ea8235612272565b905092915050565b60006117fe8251612272565b905092915050565b600082601f830112151561181957600080fd5b813561182c611827826121a2565b612175565b9150808252602083016020830185838301111561184857600080fd5b6118538382846122bc565b50505092915050565b600082601f830112151561186f57600080fd5b815161188261187d826121a2565b612175565b9150808252602083016020830185838301111561189e57600080fd5b6118a98382846122cb565b50505092915050565b60006118be823561227c565b905092915050565b60006118d2825161227c565b905092915050565b6000602082840312156118ec57600080fd5b60006118fa8482850161178e565b91505092915050565b60006020828403121561191557600080fd5b6000611923848285016117a2565b91505092915050565b60006020828403121561193e57600080fd5b600061194c848285016117b6565b91505092915050565b60006020828403121561196757600080fd5b6000611975848285016117ca565b91505092915050565b60006020828403121561199057600080fd5b600061199e848285016117f2565b91505092915050565b6000602082840312156119b957600080fd5b600082013567ffffffffffffffff8111156119d357600080fd5b6119df84828501611806565b91505092915050565b6000602082840312156119fa57600080fd5b600082015167ffffffffffffffff811115611a1457600080fd5b611a208482850161185c565b91505092915050565b60008060408385031215611a3c57600080fd5b600083013567ffffffffffffffff811115611a5657600080fd5b611a6285828601611806565b9250506020611a73858286016117de565b9150509250929050565b60008060008060008060c08789031215611a9657600080fd5b600087013567ffffffffffffffff811115611ab057600080fd5b611abc89828a01611806565b965050602087013567ffffffffffffffff811115611ad957600080fd5b611ae589828a01611806565b955050604087013567ffffffffffffffff811115611b0257600080fd5b611b0e89828a01611806565b9450506060611b1f89828a016117de565b9350506080611b3089828a016117de565b92505060a0611b4189828a016118b2565b9150509295509295509295565b600060208284031215611b6057600080fd5b6000611b6e848285016118c6565b91505092915050565b611b8081612286565b825250","50565b611b8f81612298565b82525050565b611b9e81612216565b82525050565b611bad816122aa565b82525050565b6000611bbe826121eb565b808452611bd28160208601602086016122cb565b611bdb816122fe565b602085010191505092915050565b6000611bf4826121e0565b808452611c088160208601602086016122cb565b611c11816122fe565b602085010191505092915050565b600081546001811660008114611c3c5760018114611c5c57611c9d565b607f600283041680865260ff198316602087015260408601935050611c9d565b60028204808652602086019550611c72856121ce565b60005b82811015611c9457815481890152600182019150602081019050611c75565b80880195505050505b505092915050565b6000600a82527f63726561746554696d65000000000000000000000000000000000000000000006020830152604082019050919050565b6000600682527f73686f70494400000000000000000000000000000000000000000000000000006020830152604082019050919050565b6000600a82527f636f6e73756d65724944000000000000000000000000000000000000000000006020830152604082019050919050565b6000600682527f63617264494400000000000000000000000000000000000000000000000000006020830152604082019050919050565b6000600c82527f636f6e74726163744861736800000000000000000000000000000000000000006020830152604082019050919050565b6000600782527f62616c616e6365000000000000000000000000000000000000000000000000006020830152604082019050919050565b611df881612220565b82525050565b6000602082019050611e136000830184611b95565b92915050565b6000602082019050611e2e6000830184611ba4565b92915050565b60006020820190508181036000830152611e4e8184611be9565b905092915050565b60006040820190508181036000830152611e708185611bb3565b9050611e7f6020830184611b77565b9392505050565b60006040820190508181036000830152611ea08185611bb3565b9050611eaf6020830184611b86565b9392505050565b60006060820190508181036000830152611ed08186611bb3565b9050611edf6020830185611b86565b611eec6040830184611b77565b949350505050565b600060c0820190508181036000830152611f0e8189611bb3565b90508181036020830152611f228188611bb3565b90508181036040830152611f368187611bb3565b9050611f456060830186611b95565b611f526080830185611b95565b611f5f60a0830184611def565b979650505050505050565b60006020820190508181036000830152611f848184611c1f565b905092915050565b60006020820190508181036000830152611fa581611ca5565b9050919050565b60006040820190508181036000830152611fc581611ca5565b9050611fd46020830184611def565b92915050565b60006020820190508181036000830152611ff381611cdc565b9050919050565b6000604082019050818103600083015261201381611cdc565b905081810360208301526120278184611bb3565b905092915050565b6000602082019050818103600083015261204881611d13565b9050919050565b6000604082019050818103600083015261206881611d13565b9050818103602083015261207c8184611bb3565b905092915050565b6000602082019050818103600083015261209d81611d4a565b9050919050565b600060408201905081810360008301526120bd81611d4a565b905081810360208301526120d18184611bb3565b905092915050565b600060208201905081810360008301526120f281611d81565b9050919050565b6000604082019050818103600083015261211281611d81565b90506121216020830184611b95565b92915050565b6000602082019050818103600083015261214081611db8565b9050919050565b6000604082019050818103600083015261216081611db8565b905061216f6020830184611b95565b92915050565b6000604051905081810181811067ffffffffffffffff8211171561219857600080fd5b8060405250919050565b600067ffffffffffffffff8211156121b957600080fd5b601f19601f8301169050602081019050919050565b60008160005260206000209050919050565b600081519050919050565b600081519050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b6000819050919050565b6000612235826121f6565b9050919050565b6000612247826121f6565b9050919050565b6000612259826121f6565b9050919050565b600061226b826121f6565b9050919050565b6000819050919050565b6000819050919050565b6000612291826121f6565b9050919050565b60006122a3826121f6565b9050919050565b60006122b582612216565b9050919050565b82818337600083830152505050565b60005b838110156122e95780820151818401526020810190506122ce565b838111156122f8576000848401525b50505050565b6000601f19601f83011690509190505600a265627a7a723058206df104ae4a58bd1d04911f1185393c82df7b2dde888b942a7f76fd231bd345bd6c6578706572696d656e74616cf50037"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[],\"name\":\"tableName\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_cardID\",\"type\":\"string\"},{\"name\":\"_consumerID\",\"type\":\"string\"},{\"name\":\"_shopID\",\"type\":\"string\"},{\"name\":\"_balance\",\"type\":\"int256\"},{\"name\":\"_contractHash\",\"type\":\"int256\"},{\"name\":\"_createTime\",\"type\":\"uint256\"}],\"name\":\"createCard\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_cardID\",\"type\":\"string\"},{\"name\":\"_value\",\"type\":\"int256\"}],\"name\":\"changeBalance\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_cardID\",\"type\":\"string\"}],\"name\":\"selectByCardID\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"int256\"},{\"name\":\"\",\"type\":\"int256\"},{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"count\",\"type\":\"int256\"}],\"name\":\"CreateTable\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"count\",\"type\":\"int256\"}],\"name\":\"ChangeBalance\",\"type\":\"event\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_TABLENAME = "tableName";

    public static final String FUNC_CREATECARD = "createCard";

    public static final String FUNC_CHANGEBALANCE = "changeBalance";

    public static final String FUNC_SELECTBYCARDID = "selectByCardID";

    public static final Event CREATETABLE_EVENT = new Event("CreateTable", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
    ;

    public static final Event CHANGEBALANCE_EVENT = new Event("ChangeBalance", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
    ;

    protected PrepaidCard(String contractAddress, Client client, CryptoKeyPair credential) {
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

    public TransactionReceipt createCard(String _cardID, String _consumerID, String _shopID, BigInteger _balance, BigInteger _contractHash, BigInteger _createTime) {
        final Function function = new Function(
                FUNC_CREATECARD, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_cardID), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_consumerID), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_shopID), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(_balance), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(_contractHash), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(_createTime)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void createCard(String _cardID, String _consumerID, String _shopID, BigInteger _balance, BigInteger _contractHash, BigInteger _createTime, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_CREATECARD, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_cardID), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_consumerID), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_shopID), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(_balance), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(_contractHash), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(_createTime)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForCreateCard(String _cardID, String _consumerID, String _shopID, BigInteger _balance, BigInteger _contractHash, BigInteger _createTime) {
        final Function function = new Function(
                FUNC_CREATECARD, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_cardID), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_consumerID), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_shopID), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(_balance), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(_contractHash), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(_createTime)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple6<String, String, String, BigInteger, BigInteger, BigInteger> getCreateCardInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CREATECARD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Int256>() {}, new TypeReference<Int256>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple6<String, String, String, BigInteger, BigInteger, BigInteger>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue(), 
                (BigInteger) results.get(3).getValue(), 
                (BigInteger) results.get(4).getValue(), 
                (BigInteger) results.get(5).getValue()
                );
    }

    public Tuple1<BigInteger> getCreateCardOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_CREATECARD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public TransactionReceipt changeBalance(String _cardID, BigInteger _value) {
        final Function function = new Function(
                FUNC_CHANGEBALANCE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_cardID), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void changeBalance(String _cardID, BigInteger _value, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_CHANGEBALANCE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_cardID), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForChangeBalance(String _cardID, BigInteger _value) {
        final Function function = new Function(
                FUNC_CHANGEBALANCE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_cardID), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(_value)), 
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

    public TransactionReceipt selectByCardID(String _cardID) {
        final Function function = new Function(
                FUNC_SELECTBYCARDID, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_cardID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void selectByCardID(String _cardID, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_SELECTBYCARDID, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_cardID)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForSelectByCardID(String _cardID) {
        final Function function = new Function(
                FUNC_SELECTBYCARDID, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_cardID)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<String> getSelectByCardIDInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SELECTBYCARDID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public Tuple6<String, String, String, BigInteger, BigInteger, BigInteger> getSelectByCardIDOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_SELECTBYCARDID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Int256>() {}, new TypeReference<Int256>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple6<String, String, String, BigInteger, BigInteger, BigInteger>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue(), 
                (BigInteger) results.get(3).getValue(), 
                (BigInteger) results.get(4).getValue(), 
                (BigInteger) results.get(5).getValue()
                );
    }

    public List<CreateTableEventResponse> getCreateTableEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CREATETABLE_EVENT, transactionReceipt);
        ArrayList<CreateTableEventResponse> responses = new ArrayList<CreateTableEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
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
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CHANGEBALANCE_EVENT, transactionReceipt);
        ArrayList<ChangeBalanceEventResponse> responses = new ArrayList<ChangeBalanceEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
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

    public static PrepaidCard load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new PrepaidCard(contractAddress, client, credential);
    }

    public static PrepaidCard deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(PrepaidCard.class, client, credential, getBinary(client.getCryptoSuite()), "");
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
