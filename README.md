# stats
A simple file analysis tool for your project

### Usage

- Step 1

Download [stats.jar](stats.jar) and add below shortcut to your `.bashrc`

```sh
function stats(){
    java -jar /path/to/stats.jar $1
}
```

- Step 2

Execute `stats` from your project along with the extension you want to manage

```console
~/projects/my-project$ stats java
```

You'll get something like this

```
Project : my-project
Extension to be managed : [.java]
Total files : 27
CSV Created
```

Done! You've successfully created your first stats file. Check your project directory, and you'll be able to find 
a file named `stats_*.csv`
