package com.mastercard.fld.utility;

import java.io.File;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.developer.encryption.FieldLevelEncryptionConfig;
import com.mastercard.developer.encryption.FieldLevelEncryptionConfig.FieldValueEncoding;
import com.mastercard.developer.encryption.FieldLevelEncryptionConfigBuilder;
import com.mastercard.developer.utils.EncryptionUtils;

/*
 *  Copyright (c) 2021 Mastercard
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class EncryptionHelper {

	private EncryptionHelper() {
	}

	private static Certificate getEncryptionCertificate(String filePath) throws IOException, CertificateException {
		Certificate encryptionCertificate = null;
		File file = new File(filePath);
		encryptionCertificate = EncryptionUtils.loadEncryptionCertificate(file.getPath());
		return encryptionCertificate;
	}

	public static FieldLevelEncryptionConfig encryptionConfig(String encryptioncert, String fingerprint)
			throws EncryptionException {

		FieldLevelEncryptionConfig config = null;

		try {

			config = FieldLevelEncryptionConfigBuilder.aFieldLevelEncryptionConfig()
					.withEncryptionCertificate(getEncryptionCertificate(encryptioncert)).withEncryptionPath("$", "$")
					.withOaepPaddingDigestAlgorithm("SHA-256").withEncryptedValueFieldName("encryptedValue")
					.withEncryptedKeyFieldName("encryptedKey").withIvFieldName("iv")
					.withEncryptionKeyFingerprint(fingerprint)
					.withEncryptionKeyFingerprintFieldName("publicKeyFingerprint")
					.withOaepPaddingDigestAlgorithmFieldName("oaepPaddingDigestAlgorithm")
					.withFieldValueEncoding(FieldValueEncoding.HEX).build();
			
		} catch (EncryptionException | IOException | CertificateException ex) {
			throw new EncryptionException("Error occured while creating FieldLevelEncryptionConfig for encryption", ex);
		}

		return config;
	}
}
