# [ProjectKorra](https://github.com/ProjectKorra/ProjectKorra) + [MagicSpells](https://github.com/TheComputerGeek2/MagicSpells)

## *Create ProjectKorra abilities using MagicSpells!*

## How to use:

`PKMagicSpells/MagicSpells.yml`:

```yaml
Spells:
  INTERNAL_NAME:
    Element: Air
    Description: 'Description'
    Instruction: 'Instruction'
    Name: 'Displayed Name'
    # One or multiple of these can be used, by default 'LEFT_CLICK'
    Activation:
    - WATER_NEAR
    - WATER_TARGET
    - EARTH_TARGET
    - LAVA_TARGET
    - ICE_NEAR
    - PLANT_TARGET
    - ICE_TARGET
    - EARTH_NEAR
    - METAL_TARGET
    - SHIFT
    - RIGHT_CLICK
    - LAVA_NEAR
    - SAND_TARGET
    - LEFT_CLICK
```
### Example:
`PKMagicSpells/MagicSpells.yml`:
```yaml
Spells:
  airshot:
    Element: Air
    Description: 'Shoots air'
    Instruction: 'Left click and sneak'
    Name: 'AirShot'
    Activation:
    - SHIFT
    - LEFT_CLICK
  fireshot:
    Element: Fire
    Description: 'Shoots fire'
    Instruction: 'Sneak'
    Name: 'FireShot'
    Activation:
      - SHIFT
```

## Commands:
| Command             | Description                                        | Permission      |   |   |
|---------------------|----------------------------------------------------|-----------------|---|---|
| /pkspell            | Displays plugin information *(+ source code link)* | `none`          |   |   |
| /pkspell sourcecode | Displays plugin information *(+ source code link)* | `none`          |   |   |
| /pkspell reload     | Reloads the plugin                                 | `pkspell.admin` |   |   |

## Dependencies:
- ProjectKorra
- MagicSpells

