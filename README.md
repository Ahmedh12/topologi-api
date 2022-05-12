
# Topology API
#### An API library which does the following:
* Read and write topologies to and from disk.
* Stores multiple topologies in memory.
* Execute operations on topologies


## API Reference

#### Write a given topology from the memory to a JSON file

```java
  saveTopology
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `topology` | `topolgy` | **Required**. the topology to save |

#### Read a topology from a given JSON file and store it in the memory

```java
  loadTopology
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `fileName`      | `string` | **Required**. the filename of the file to load the topology from |

#### Query about which topologies are currently in the memory

```java
  getTopologies
```

#### Delete a given topology from memory

```java
  deleteTopology
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `topologyID`| `string` | **Required**.  the id of the topology to delete|


#### Query about which devices are in a given topology

```java
  getComponents
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `fileName`      | `string` | **Required**. the id of the topolgy under query|


#### Query about which devices are connected to a given netlist node in a given topology.

```java
  getComponentsWithNetlistNode
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `fileName`      | `string` | **Required**. the id of the topology under query|
|`node`|`string`|**Required**. the node under query|


## Acknowledgements
[Sonar linter](https://www.sonarlint.org/?gclid=CjwKCAjwjtOTBhAvEiwASG4bCIOVg63E8QBH2fYeLoTkZRcnPRXwp9d-oMggES236nUGXZtaQDRCWBoCjqIQAvD_BwE)
 is used as the static code analysis tool for this project

## Author
[Ahmed Hussien](https://www.github.com/Ahmedh12)

