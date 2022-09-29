# Scalable Tensor Advanced Reinforcement(STAR) BlockChain

## Hey there👋, I have designed this new concept of blockchain, that can scale in both vertical and horizontal directions, It believes in grouping related nodes or transactions together

![starblockchain](https://user-images.githubusercontent.com/30797411/187959296-a2d6b56a-da5d-4fd3-9208-064196443ddf.png)

### Features

```
Open Source
Proof of Stake(POS)
Grouping: It groups similar nodes or transactions together
Permanent: All transactions are premanent
Immutable: Data cannot be modified once created
Verifiable: All transactions can be verified at any point of time
Scalabale: Scales in both vertical and horizontal directions
Sharding: Sharding makes small chunks of blockchain, which is later sent for parallel processing
Parallel processing: It processess the small chunks of blockchain parallelly
Encrypted: Secured with AES and RSA Encryption, default public
Peer-to-Peer: It is decentralized and can exchange nodes or 
                transactions without involvement of central authority(Future Support)
```

### Project Dependency

```
<dependency>
    <groupId>io.github.smdi</groupId>
    <artifactId>StarBlockChain</artifactId>
    <version>v0.0.1</version>
</dependency>
```

### Implementation

- Public
```
StarBlockChain starBlockChain = new StarBlockChain();
```

- Encrypted: AES, pass encryption method, secretKey, salt(optional)
```
String secretKey = "Secret";
String saltValue = "SimpleSalt";
StarBlockChain starBlockChain = new StarBlockChain(MetaData.AES, secretKey, saltValue);
```

- Encrypted: RSA, pass encryption method
- Note: Public key is generated in public folder, It is required while encryption
- Note: Private key is generated in secrets folder, keep it in a safeplace, It is required while decryption
```
StarBlockChain starBlockChain = new StarBlockChain(MetaData.RSA);
```

- List of userNames
```
ArrayList<String> userNames = new ArrayList<>(Arrays.asList("Adam", "Havva"));
```

- Add StarBlock #00, pass string data, userNames
```
starBlockChain.newStarBlock("Confidential data v1", userNames);
```

- Add StarBlock #01 to previous starBlock #00, pass string data, related, userNames
- Note: This starblock will be added at the end of the current row
```
starBlockChain.newStarBlock("Confidential data v2", true, userNames);
```

- Add StarBlock #10 at new row, pass string data, userNames
```
starBlockChain.newStarBlock("Transaction", userNames);
```
- Add StarBlock #11 to previous starBlock #10, pass string data, related, userNames
- Note: This starblock will be added at the end of the current row
```
starBlockChain.newStarBlock("Surcharge", true, userNames);
```

- Add StarBlock #02 to starblock #01, pass string data, related, currentIndex, index, userNames
- Note: This starblock will be added at the end of the 0th row
```
starBlockChain.newStarBlock("Confidential data v3", true, false, 0, userNames);
```

- Add StarBlock #12 to starblock #11
- Note: This starblock will be added at the end of the 1st row
```
starBlockChain.newStarBlock("Reward", true, false, 1, userNames);
```

- Check validity of starblockchain
```
boolean isValid = starBlockChain.isStarBlockChainValid();
```

- Get total nodes at index
```
System.out.println("Total newtwork strength at Index "+starBlockChain.getTotalTensorStrengthAtIndex(0));                
```
- Get total nodes of starblockchain
```
System.out.println("Total network strength "+starBlockChain.getTotalTensorNetworkStrength());
```

- Print the public starblockchain
```
System.out.println(starBlockChain.printStarBlockChain());
```

- Print the RSA encrypted starblockchain
- Note: Make sure to add private to secrets folder for decryption
```
System.out.println(starBlockChain.printStarBlockChain());
```

- Print the AES encrypted starblockchain, pass encryption method, secretKey, salt(optional)
```
System.out.println(starBlockChain.printStarBlockChain(secretKey, saltValue));
```

### [License](http://www.apache.org/licenses/LICENSE-2.0)

```
Copyright 2022 Mohammad Imran Shaik

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
