## Fraud and Loss Database Reference Implementation

This reference implementation showcases the Fraud and Loss Database (FLD) APIs from: [Mastercard Developers](https://developer.mastercard.com/product/fraud-and-loss-database).

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Mastercard_fld-api-client-ref-app&metric=alert_status)](https://sonarcloud.io/dashboard?id=Mastercard_fld-api-client-ref-app)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=Mastercard_fld-api-client-ref-app&metric=coverage)](https://sonarcloud.io/dashboard?id=Mastercard_fld-api-client-ref-app)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=Mastercard_fld-api-client-ref-app&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=Mastercard_fld-api-client-ref-app)
[![](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/Mastercard/fld-api-client-ref-app/blob/master/LICENSE)

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## Optional - Installing Markdown Viewer in Google Chrome

- Follow the steps [here](https://imagecomputing.net/damien.rohmer/teaching/general/markdown_viewer/index.html) to view .md files in the browser.
- Paste the README.md file path in the chrome browser.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## Frameworks / Libraries used

- [Apache Maven](https://maven.apache.org/index.html)
- [OpenAPI Generator](https://github.com/OAI/OpenAPI-Specification)
- [Mastercard OAuth1 Signer library](https://github.com/Mastercard?utf8=%E2%9C%93&q=oauth1-signer&type=&language=)

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## Prerequisite

- JDK 8+
- Set up the [JAVA_HOME](https://explainjava.com/java-path/) environment variable to match the location of your Java installation.
- Set up the [MAVEN_HOME](https://dzone.com/articles/installing-maven) environment variable to match the location of your Maven bin folder.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## Setup

1. Create an account on [Mastercard Developers](https://developer.mastercard.com/account/sign-up).
2. Create a new project [here](https://developer.mastercard.com/dashboard).
3. Add the Fraud And Loss Database API to your project and click continue.
4. Give your project a name and optionally invite other team members, then click continue.
5. Download the Sandbox Signing Key zip and unzip it. This should result in a `.p12` file. **Note**: On Safari, the file name will be `Unknown`. Rename it to a `.p12` extension.
6. Finish creating the project.
7. Copy the downloaded `.p12` file to `src/main/resources` folder in the code.
8. Navigate to your projects page, found [here](https://developer.mastercard.com/dashboard) and select your project.
9. Here, under your sandbox keys, you should be able to see a consumer key, default keystore alias and default keystore password. Copy these values into their respective placeholders in `BaseClassUtil`.
10. Update the value of `signingKeyFilePath` in `BaseClassUtil` to reflect the name of your `.p12` files path. For example, if your `.p12` files name is `TestProject-sandbox.p12`, this value should be set to `src/main/resources/TestProject-sandbox.p12`. 
11. Download the most recent yaml file, available [here](https://developer.mastercard.com/fld-fraud-submission/documentation/api-reference/) by clicking on the Open Specification link at the top of the page.
12. Copy this file into your `src/main/resources` folder and rename it `swagger.yaml`.
13. Run `mvn clean install` from the root of the project directory.
14. You should now be able to run all the provided API calls that are organized under `src/main/java/com/mastercard/fld/api`.  

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## Configuring Payload Encryption
The [Mastercard Encryption Library](https://github.com/Mastercard/client-encryption-java) provides interceptor class you can use when configuring your API client. This [interceptor](https://github.com/Mastercard/client-encryption-java#usage-of-the-okhttpfieldlevelencryptioninterceptor-openapi-generator-4xy) will encrypt the payload before sending the request.

**Encryption Config**
```
FieldLevelEncryptionConfig config = FieldLevelEncryptionConfigBuilder
                    .aFieldLevelEncryptionConfig()
                    .withEncryptionCertificate(cert)
                    .withEncryptionPath("$", "$")
                    .withEncryptedValueFieldName("encryptedData")
                    .withEncryptedKeyFieldName("encryptedKey")
                    .withOaepPaddingDigestAlgorithmFieldName("oaepHashingAlgorithm")
                    .withOaepPaddingDigestAlgorithm("SHA-256")
                    .withEncryptionKeyFingerprintFieldName("publicKeyFingerprint")
                    .withIvFieldName("iv")
                    .withFieldValueEncoding(FieldLevelEncryptionConfig.FieldValueEncoding.HEX)
                    .build();
```

See also: 
- [Securing Sensitive Data Using Payload Encryption](https://developer.mastercard.com/platform/documentation/security-and-authentication/securing-sensitive-data-using-payload-encryption/).

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## Tutorial

Several tutorials have been provided under `src/test/java/com/mastercard/fld/api` that will show the different FLD flows. These examples will cover FDA, FDC, FDD & FDE. 

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## Service Documentation

Fraud and Loss Database service documentation can be found [here](https://developer.mastercard.com/product/fraud-and-loss-database).

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## Use Cases

Fraud and Loss Database service use cases can be found [here](https://developer.mastercard.com/fld-fraud-submission/documentation/use-cases/).

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## Documentation for API Endpoints

*NOTE: Make sure you use the latest Fraud and Loss Database API specification. See documentation [here](https://developer.mastercard.com/fld-fraud-submission/documentation/api-reference/).*

| Class | Method | HTTP request |
|:------------ |:------------- |:-------------|
| *ConfirmFraudManageApi* | fraudChangeMastercardWithHttpInfo(body) | **PUT** /confirmed-frauds/mastercard-frauds |
| *ConfirmFraudManageApi* | fraudStateWithHttpInfo(body) | **PUT** /confirmed-frauds/fraud-states |
| *ConfirmFraudSubmissionApi* | createCaseFilingWithHttpInfo(body) | **POST** /confirmed-frauds/mastercard-frauds |

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## How to generate the client library

Go to the link [here](https://developer.mastercard.com/platform/documentation/security-and-authentication/generating-and-configuring-a-mastercard-api-client/) to read up on how to generate the client library with the openapi-generator.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## Contact us

Please email [API Support](mailto:apisupport@mastercard.com) with any questions or feedback you may have.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## License and Copyright

<p>Copyright 2021 Mastercard</p>
<p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
the License. You may obtain a copy of the License at:</p>
<pre><code>   http://www.apache.org/licenses/LICENSE-2.0
</code></pre>
<p>Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
specific language governing permissions and limitations under the License.</p>
