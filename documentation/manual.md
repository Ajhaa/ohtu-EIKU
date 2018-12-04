# Installation and operation manual

## Installing and running the program

Note that running the program requires you have Java installed on your computer. Java can be downloaded from [here](https://www.java.com/en/download/). The program has been tested to work with Java 8 on Linux systems.

You can download the latest version of the software from [here](https://github.com/Ajhaa/ohtu-EIKU/releases).

After downloading, execute the .jar file by double clicking it or executing

```
java -jar [jar file path and name]
```

on the command line.

## Operating the program

### The Main Menu

After executing, you are greeted with the main menu of the program where you currently have four commands available.

- ```view``` lists all memo items of a desired type 
- ```add``` allows you to insert the parameters and add an item of a desired type
- ```delete``` lists and allows you to delete an item of a desired type.
- ```quit``` closes the program.

### Using Commands

To select a command, type the command's name, or alternatively simply type the first character of the name. For an example, to close the program you can simply enter ```q```.

After entering a command, enter subsequent commands according to the program's instructions.

### Adding a New Memo Item

In the main menu, enter ```add```. You will be prompted to enter the type of the memo item you wish to add.

- ```books``` are memo items with a title, an author and a description
- ```internetcontent``` refers to content on the internet with a title, a url and a description
- ```podcasts``` are memo items with a name (the name of the podcast), a title (a short descriptor of a specific episode of the podcast) and a description.

After entering the details of an item, you will be returned back to the main menu.

### Deleting Memo Items

In the main menu, enter ```delete```. You will be prompted to enter the type of the memo item you wish to delete.

After selecting the type, all memo items of that type will be listed along with their respective unique *id*s. Find the item you wish to delete, then enter its id.

After the deletion is complete, you will be returned back to the main menu.

### Viewing Memo Items

In the main menu, enter ```view```. Choose the memo item type you wish to list, or alternatively enter ```all``` to view all memo items.

Next, you will be asked in which order you wish to list the memo items.

- ```alphabetical``` lists the memo items in an alphabetical order from A to Z.
- ```date``` lists the memo items in the order of their creation date, most recently added first.
- ```random``` lists the memo items in a randomized order.

The memo items are sorted using Java's ```Collections.sort``` method, which uses Merge Sort.

