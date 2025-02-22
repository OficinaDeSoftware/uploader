## Uploader Firebase

Microservice for uploader imagens or another things on firebase storage.

For use this service need create a project on firebase: 
- [Firebase Create Project]( https://console.firebase.google.com/?hl=pt-br&fb_gclid=CjwKCAiA5eC9BhAuEiwA3CKwQgFm4YCErNz2gp48NqA2cyWOpCzlUtaLSXGoMHZeio8_FzmJ4Mpr_hoCMV8QAvD_BwE&_gl=1*1v03clk*_up*MQ..*_ga*MTM2MjEzNjI3MS4xNzM0ODk2NjMw*_ga_CW55HF8NVT*MTc0MDE5NjE4OS4yNC4xLjE3NDAxOTYyMTcuMzIuMC4w&gclid=CjwKCAiA5eC9BhAuEiwA3CKwQgFm4YCErNz2gp48NqA2cyWOpCzlUtaLSXGoMHZeio8_FzmJ4Mpr_hoCMV8QAvD_BwE&gclsrc=aw.ds )
- [Start on firebase]( https://firebase.google.com/docs/storage/web/start?hl=pt-br )

If you want to access the public URL for uploads, configure your bucket to do this

![Bucket policies](documentation/images/bucket_polices.png)

### Environment Variables

- `FIREBASE_DOWNLOAD_URL`
  - <b>Description</b>: Base url for download or access the item that is uploaded
  - <b>Example</b>: test-bucket.appspot.com
- `FIREBASE_BUCKET`
  - <b>Description</b>: Is a storage unit used by firebase storage to store and manage files
  - <b>Example</b>: test-bucket.appspot.com
- `GOOGLE_CLOUD_STORAGE_CREDENTIALS`
  - <b>Description</b>: Credentials in base64
  - <b>Content example get from firebase</b>:
    ``` 
    {
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
  - <b>Input valid for this variable</b>:
    ```
    ICAgewogICAgICAgICJ0eXBlIjogImFjY291bnQiLAogICAgICAgICJwc
    m9qZWN0X2lkIjogInRlc3QtaWQiLAogICAgICAgICJwcml2YXRlX2tleV9pZCI6ICJhc2Rhb
    HNrZGpoYW9zZGhhanNoZGtqYXNoZGtqYXNoZCIsCiAgICAgICAgInByaXZhdGVfa2V5IjogIi
    0tLS0tQkVHSU4gUFJJVkFURSBLRVktLS0tLVwgdGhpcyBpcyBhIHByaXZhdGUga2V5IFxuLS0
    tLS1FTkQgUFJJVkFURSBLRVktLS0tLVxuIiwKICAgICAgICAiY2xpZW50X2VtYWlsIjogImZpc
    mViYXNlLWFkbWluc2RrLUB0ZXN0LXRlc3QuaWFtLmdzZXJ2aWNlYWNjb3VudC5jb20iLAogICA
    gICAgICJjbGllbnRfaWQiOiAiYXNkYXNkYXNkYXNkYXNkYXNkYXNkIiwKICAgICAgICAiYXV0aF
    91cmkiOiAiaHR0cHM6Ly9hY2NvdW50cy5nb29nbGUuY29tL28vb2F1dGgyL2F1dGgiLAogICAgI
    CAgICJ0b2tlbl91cmkiOiAiaHR0cHM6Ly9vYXV0aDIuZ29vZ2xlYXBpcy5jb20vdG9rZW4iLAo
    gICAgICAgICJhdXRoX3Byb3ZpZGVyX3g1MDlfY2VydF91cmwiOiAiaHR0cHM6Ly93d3cuZ29vZ2
    xlYXBpcy5jb20vb2F1dGgyL3YxL2NlcnRzIiwKICAgICAgICAiY2xpZW50X3g1MDlfY2VydF91
    cmwiOiAiaHR0cHM6Ly93d3cuZ29vZ2xlYXBpcy5jb20vcm9ib3QvdjEvbWV0YWRhdGEvMi9maX
    JlYmFzZS1hZG1pbnNkay1AdGVzdC10ZXN0LmlhbS5nc2VydmljZWFjY291bnQuY29tIiwKICAg
    ICAgICAidW5pdmVyc2VfZG9tYWluIjogImdvb2dsZWFwaXMuY29tIgogICAgfQ== 
    ```