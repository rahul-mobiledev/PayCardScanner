# PAY.CARDS RECOGNIZER

Automatic recognition of bank card data using built-in camera on Android devices.

### Installation

* Add Maven URL for the pay.cards repository to your project `build.gradle` file.

    ```gradle
    	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
    ```


* Add the dependency

    ```gradle
    dependencies {
	        implementation 'com.github.rahul-mobiledev:PayCardScanner:1.0.0'
	}
    ```

### Usage

Build an Intent using the `ScanCardIntent.Builder` and start a new activity to perform the scan:

```java
static final int REQUEST_CODE_SCAN_CARD = 1;
 
...
     
private void scanCard() {
  Intent intent = new ScanCardIntent.Builder(this).build();
  startActivityForResult(intent, REQUEST_CODE_SCAN_CARD);
}
```

Handle the result:

```java
@Override
private void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == REQUEST_CODE_SCAN_CARD) {
        if (resultCode == Activity.RESULT_OK) {
            Card card = data.getParcelableExtra(ScanCardIntent.RESULT_PAYCARDS_CARD);
            String cardData = "Card number: " + card.getCardNumberRedacted() + "\n"
                            + "Card holder: " + card.getCardHolderName() + "\n"
                            + "Card expiration date: " + card.getExpirationDate();
            Log.i(TAG, "Card info: " + cardData);
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Log.i(TAG, "Scan canceled");
        } else {
            Log.i(TAG, "Scan failed");
        }
    }
}
```