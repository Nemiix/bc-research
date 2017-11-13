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
