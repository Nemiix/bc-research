# bc-research

## Setup
Before launching the code, start the Parity Ethereum Client (https://github.com/paritytech/parity/releases/tag/v1.8.2/) with the following prompt:

```
parity --chain testnet
```

To Enable TestNet Mining, follow the tutorial (https://github.com/paritytech/parity/wiki/Mining). To enable mining, start Parity with stratum enabled, using the prompt:

```
parity --author <YourAccountAdress> --stratum --stratum-interface=0.0.0.0 --stratum-port=9009
```
To start mining with EthMiner, use:

```
ethminer -G -S 127.0.0.1:9009
```

Next, you need a wallet for your Ethereum. You will need to download the latest version of Web3j command line tools. Then use:

```
web3j wallet create
```

# Smart Contracts

## Setup
Before launching the code, start testrpc to generate 10 test accounts with virtual currency (no ether requiered). To install, follow Medium.com's guide (https://medium.com/@PrateeshNanada/steps-to-install-testrpc-in-windows-10-96989a6cd594)

The Smart Contract is programmed using the language "Solidity". It has to be stored in a ".sol"-file. To compile and deploy, use the command line, navigate to your contract and run the following code:

```
Web3 = require('web3')
web3 = new Web3(new Web3.providers.HttpProvider("http://localhost:8545"));

code = fs.readFileSync('Your_Contract_File_Name.sol').toString()
solc = require('solc')
compiledCode = solc.compile(code)

abiDefinition = JSON.parse(compiledCode.contracts[':Your_Contract_File_Name'].interface)
Contract = web3.eth.contract(abiDefinition)
byteCode = compiledCode.contracts[':Your_Contract_File_Name'].bytecode
deployedContract = Contract.new(['Rama','Nick','Jose'],{data: byteCode, from: web3.eth.accounts[0], gas: 4700000})
contractInstance = Contract.at(deployedContract.address)
```

### Code Explanation

First, you have to aquire the web3.js utils to access the blockchain. We read the contract's code and compile it via solc. This creates the bytecode which will be running in the Ethereum VM and the interface for users to access the Contract. With the interface code, we can provide an abi definition, the so called contract template. We deploy the contract by using web3 to create the contract definition and then using Contract.new(). We provide a list of users (the example is a voting contract, so these are the contestants), the compiled bytecode, information about who deployed the contract and a gas amount. This determines how much you are willing to pay the miners to validate your interaction with the blockchain. Finally, we get an instance of ourdeployed contract to further interact with it.