## This is all subject to change as needed.
# Root project
- follows gradle project structure if omitted 
```
Root
L changelogs/
    L Version Number/
        L Changelog Markdown
        L Additional Media
```
# Common Mod Structure
- Root is src/main/java/com/github/amulittle/resizer_gun/
```
Root
L Main file
L mixin/
|   L Mixin Definitions
L packet/
|   L Packet Handelers
|   L Packet Definitions
L block/
|   L Block Name/
|       L Block Definitions
|       L Block Entity Definitions
L item/
|   L Item Definitions
L entity/
|   L Entity Definitions
L register/
|   L Register Definitions
L util/
    L Utility Definitions
```
# Client Mod Structure
- Root is src/client/java/com/github/amulittle/resizer_gun/client/
```
Root
L Main File
L mixin/
|   L Mixin Definitions
L block/
|   L Block Name/
|       L Block Renderer Definitions
|       L Block Client Logic Definitions
L item/
|   L Item Name/
|       L Item Renderer Definitinos
|       L Item Client Logic Definitions
L entity/
    L Entity Name/
        L Entity Renderer Definitions
        L Entity Client Logic Definitions
```