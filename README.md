# SuperMart System

## How to Run
This system uses `Java 11`, built with `Gradle`. Also it uses an external `Ruby`
Test Suite provided by `SadaPay`.

You will need a Linux-based OS or if you work with Windows, WSL 2.
The distro should be preferably `Ubuntu LTS 20.0`.

### Environment Configuration
```
sudo apt install openjdk-11-jre-headless gradle ruby

# Set Up Ruby Test Suite
cd $PROJECT_DIRECTORY
cd functional_spec
sudo gem install bundler
bundle update --bundler

cd ..
```

### Cleanup
```
./bin/cleanup.sh
```
### Build
```
./bin/setup.sh
```
### Run
```
./bin/run.sh <path to inventory file> <path to command file>?
```
### Run Ruby Test Suite
```
./bin/run_functional_tests.sh
```

## Command Reference
- `add <item_name> <quantity>` - adds quantity of item into cart, that is present in inventory.
- `offer <"buy_2_get_1_free" | "buy_1_get_half_off"> <item_name>` - applies discount on item present in cart.
- `bill` - prints invoice for the current state of cart.
- `checkout` - closes/clears/resets the current cart. If empty, returns "empty cart".
- `remove <item_name> <quantity>` - removes quantity of item in cart and back into inventory.
- `list <"inventory" | "cart">` - lists the states of inventory or cart.
- `exit` - ends the interactive mode

## Project Structure

```
+-- root
|   +-- bin (shell scripts to clean, run, build, externally test)
|   +-- functional_spec (external ruby test suite)
|   +-- static
|       +-- inventory.csv
|       +-- commands.txt
|   +-- supermartket (working directory)
|       +-- gradle
|       +-- src (source code)
|           +-- ...
|       +-- build.gradle
|       +-- ...
|   +-- .gitignore
|   +-- README.md
|   +-- submit.sh
```
## Code Structure
![diagram](/static/code_digram.png)

## Test Coverage
- [x] Ruby Test Suite - External
- [x] JUnit Test Suite