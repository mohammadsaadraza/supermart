# SuperMart System

## How to Run
This system uses `Java 11`, built with `Gradle`. 

This program requires 1 or 2 arguments.

- 1st argument is path to an inventory file like `static/inventory.csv`
- 2nd argument is a file with commands like `static/commands.txt`


```
java build/libs/<.jar file> <inventory_file.csv> <command_file.txt>
```

## Command Reference
- `add <item_name> <quantity>` - adds quantity of item into cart, that is present in inventory.
- `offer <"buy_2_get_1_free" | "buy_1_get_half_off"> <item_name>` - applies discount on item present in cart.
- `bill` - prints invoice for the current state of cart.
- `checkout` - closes/clears/resets the current cart. If empty, returns "empty cart".
- `remove <item_name> <quantity>` - removes quantity of item in cart and back into inventory.
- `list <"inventory" | "cart">` - lists the states of inventory or cart.
- `exit` - ends the interactive mode
