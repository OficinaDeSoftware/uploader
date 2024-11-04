## Uploader

Microservice for uploader imagens or another things on firebase storage.

### Environment Variables

- `FIREBASE_DOWNLOAD_URL`
  - <b>Description</b>: Base url for download or access the item that is uploaded
  - <b>Example</b>: test-bucket.appspot.com
- `FIREBASE_BUCKET`
  - <b>Description</b>: Is a storage unit used by firebase storage to store and manage files
  - <b>Example</b>: test-bucket.appspot.com
- `FIREBASE_PRIVATE_KEY_PATH`
  - <b>Description</b>: Directory where firebase's private java is located
  - <b>Example</b>: /resources/firebase-private-key.json
  - <b>Content example</b>:
    ```{
        "type": "account",
        "project_id": "test-id",
        "private_key_id": "asdalskdjhaosdhajshdkjashdkjashd",
        "private_key": "-----BEGIN PRIVATE KEY-----\ this is a private key \n-----END PRIVATE KEY-----\n",
        "client_email": "firebase-adminsdk-@test-test.iam.gserviceaccount.com",
        "client_id": "asdasdasdasdasdasdasd",
        "auth_uri": "https://accounts.google.com/o/oauth2/auth",
        "token_uri": "https://oauth2.googleapis.com/token",
        "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
        "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/2/firebase-adminsdk-@test-test.iam.gserviceaccount.com",
        "universe_domain": "googleapis.com"
        }
    ```

