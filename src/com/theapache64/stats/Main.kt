package com.theapache64.stats

import java.io.*
import java.util.*

fun main(args: Array<String>) {

    if (args.isEmpty()) {
        println("No extensions passed")
        return
    }

    // Getting extensions to be managed
    val exts = args[0].split(",").map { ".$it" }

    // Project name
    val currentDir = File(System.getProperty("user.dir"))
    val projectName = currentDir.name

    println("Project : $projectName")
    println("Extension to be managed : $exts")

    // Starting loop
    val files = mutableListOf<File>()
    getFiles(currentDir, exts, files)

    println("Total files : ${files.size}")

    createCSV(files)
}

fun createCSV(files: MutableList<File>) {
    val csvFile = File("stats_${Date()}.csv")
    if (csvFile.createNewFile()) {
        val bufferedWriter = BufferedWriter(FileWriter(csvFile))

        for (file in files) {
            bufferedWriter.write("${file.name},${getLineCount(file)}\n")

        }

        bufferedWriter.flush()
        bufferedWriter.close()

        println("CSV Created")
    } else {
        println("Failed to create CSV file")
    }
}

fun getLineCount(file: File): Int {
    var lines = 0
    val bufferedReader = BufferedReader(FileReader(file))
    while (bufferedReader.readLine() != null) {
        lines++
    }
    bufferedReader.close()
    return lines
}

fun getFiles(file: File, exts: List<String>, list: MutableList<File>) {

    if (file.isDirectory) {
        // it's a dir
        for (x in file.listFiles()) {
            getFiles(x, exts, list)
        }
    } else {
        // it's a file
        for (ext in exts) {
            if (file.name.endsWith(ext) || ext == ".*") {
                list.add(file)
                break
            }
        }
    }
}
