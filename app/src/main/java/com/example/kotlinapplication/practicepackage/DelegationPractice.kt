package com.example.kotlinapplication.practicepackage

interface Uploader {
    fun upload()
}


interface Downloader {
    fun download()
}

class FileUploader(var stringToBePassed: String) : Uploader {
    override fun upload() {
        println("this is the name: " + stringToBePassed)
    }
}


class FileDownloader(var stringToBePassed: String) : Downloader {
    override fun download() {
        println("this is the name: " + stringToBePassed)
    }
}

//class FileHandler(uploaderObj: Uploader, downloaderObj: Downloader) : Downloader by downloaderObj, Uploader by uploaderObj {
//    // No need to override upload() and download() here
//}
//Explanation of Changes
//by keyword: The by keyword after Downloader and Uploader signifies that we're using object delegation.
//Delegated Objects: downloaderObj and uploaderObj become the delegate objects for the Downloader and Uploader functionalities, respectively.
//Implicit Override: By using delegation, we no longer need to explicitly override the upload() and download() methods. Kotlin automatically provides the implementations from the delegate objects. How Delegation Works:

/////////////////////////////////////////////////////
class FileHandler(var uploaderObj: Uploader, var downloaderObj: Downloader) : Downloader, Uploader {
    override fun upload() {
        uploaderObj.upload()
    }

    override fun download() {
        downloaderObj.download()
    }
}

fun main(){
    val downloaderObject:Downloader = FileDownloader("100GB File Download");
    val uploaderObject:Uploader = FileUploader("100GB File Upload")

    val FileHandlerObject = FileHandler(uploaderObject,downloaderObject)

    FileHandlerObject.download();
    FileHandlerObject.download()

}

//so let me get his straight correct my understanding we have
//done multiple inheritence and we hav eot implement both
//interfaces so instead we provide implementations of these
//interfaces and their parameters and their methods as
//replacement for the filehandler class to use ?the objects that
//owuld be passed as parameters would be used as delegations
//for the filehandler class